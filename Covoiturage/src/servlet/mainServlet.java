package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejbs.Facade;

/**
 * Servlet implementation class mainServlet
 */
@WebServlet("/mainServlet")
public class mainServlet extends HttpServlet {
	
	@EJB 
	private Facade facade; 
	private String currentLogin;
	
	private static final long serialVersionUID = 1L;
	
	/*********************************************************Partie Navigation du site *****************************************************************/

	  private void navigation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	switch (request.getParameter("Nav")) {
			case "accueil":
				this.goAccueil(request, response);
				break;
			case "register":
				request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
				break;
			case "compte":
				this.goCompte(request, response);
				break;
			case "recherche":
				this.goRecherche(request, response);
				break;
			case "ajout":
				this.goAjoutBD(request, response);
				break;
			case "ajoutTrajet":
				//faire passer la liste des villes et des véhicules
				this.goAjoutTrajet(request, response);
				break;
			case "deconnexion":
				this.Deconnexion(request, response);
				break;
			default:
				this.goAccueil(request,  response);			
				break;
			}
	    }
	    
		private void goAccueil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setAttribute("listeVilles", facade.getNameVille());
			request.setAttribute("listeVehicules", facade.getNameVehicule());
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		}
		
	    private void goCompte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	request.setAttribute("listeTrajetConducteur", facade.getTrajetConducteur(currentLogin));
			request.setAttribute("listeReservations", facade.getReservations(currentLogin));
			request.getRequestDispatcher("WEB-INF/compte.jsp").forward(request, response);
	    }

	    
	    private void goRecherche(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	request.setAttribute("listeVilles", facade.getNameVille());
			request.getRequestDispatcher("WEB-INF/recherche.jsp").forward(request, response);
	    }
	    
	    private void goAjoutBD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	request.setAttribute("listeVilles", facade.getNameVille());
			request.setAttribute("listeVehicules", facade.getNameVehicule());
			request.getRequestDispatcher("WEB-INF/ajoutBD.jsp").forward(request, response);
	    }
	    
	    private void Deconnexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	request.setAttribute("connecte", "false");
			request.getSession().setAttribute("login", null);
			request.getSession().setAttribute("facade", null);
			this.goAccueil(request,  response);
	    }
	    
	    private void goAjoutTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	request.setAttribute("listeVilles", facade.getNameVille());
			request.setAttribute("listeVehicules", facade.getNameVehicule());
			request.getRequestDispatcher("WEB-INF/nouveauTrajet.jsp").forward(request, response);
	    }

	    /**
	     * Enregistre un utilisateur selon les informations entrées dans le formulaire de la page register.jsp
	     * @param request
	     * @param response
	     * @throws ServletException
	     * @throws IOException
	     */
    private void registerUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = (String) request.getParameter("mdp");
		String password_verif = (String) request.getParameter("mdpbis");
		if (password.equals(password_verif)) {
			String nom = (String) request.getParameter("nom");
			String prenom = (String) request.getParameter("prenom");
			String sexe = (String) request.getParameter("sexe");
			String tel = (String) request.getParameter("tel");
			String email = (String) request.getParameter("email");
			String login = (String) request.getParameter("login");
				if(!nom.equals("") && !prenom.equals("") && sexe!=null && !tel.equals("") && !email.equals("") && !login.equals("")) {
			
					try {
						facade.enregistrerUtilisateur(nom, prenom, sexe, tel, email, login, password);
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
					
					goAccueil(request, response);
				}
				else {
					request.setAttribute("failRegister", "true");
					request.setAttribute("reason", "Il manque des informations");
					request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
				}
		}
		else {
			request.setAttribute("failRegister", "true");
			request.setAttribute("reason","Les mots de passe ne sont pas identiques");
			request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
		}	
		

	}
    
    /**
     * Enregistre un nouveau trajet selon les informations entré par l'utilisateur sur la page nouveauTrajet.jsp
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void registerTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String vehicule_desc = (String) request.getParameter("vehiculeDesc");
		String vehicule_gabarit = (String) request.getParameter("vehiculeGabarit");
		String date_trajet = (String) request.getParameter("dateTrajet");
		String heure_trajet = (String) request.getParameter("heureTrajet");
		String minute_trajet=(String) request.getParameter("minuteTrajet");
		heure_trajet=heure_trajet+" h "+minute_trajet;
		String ville_depart = (String) request.getParameter("villeDepart");
		String ville_arrivee = (String) request.getParameter("villeArrivee");
		String tarif_trajet = (String) request.getParameter("tarifTrajet");
		String[] etapes_trajet = (String[]) request.getParameterValues("etapeTrajet");
		String[] tarifs_etapes = (String[]) request.getParameterValues("tarifEtape");
		String place_trajet = (String) request.getParameter("placeTrajet");
		
		if(!(this.currentLogin.equals("")) && !(vehicule_desc.equals("")) && !(vehicule_gabarit.equals("")) && !(date_trajet.equals("")) && !(heure_trajet.equals("")) && !(ville_depart.equals("")) && !(ville_arrivee.equals("")) && !(tarif_trajet.equals("")) && !(place_trajet.equals(""))) 
		{
				this.facade.enregistrerTrajet(this.currentLogin, vehicule_desc, vehicule_gabarit, date_trajet, heure_trajet, ville_depart, ville_arrivee, tarif_trajet, etapes_trajet, tarifs_etapes, place_trajet);	
				request.setAttribute("successTrajet", "true");
				this.goAjoutTrajet(request, response);
		}
		else {
			request.setAttribute("failTrajet", "true");
			request.setAttribute("reason", "Il manque des informations");
			this.goAjoutTrajet(request, response);
		}
			
		

	}
    
    /**
     * Connecte l'utilisateur si données entré dans le formulaire de la page register sont dans la BDD
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void connectUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = (String) request.getParameter("mdp");
		String login = (String) request.getParameter("login");
		try {
			if(facade.checkUtilisateur(login, password)) {
				request.getSession().setAttribute("login", login);
				request.setAttribute("connecte", "true");
				if (this.facade.isAdmin(login)) {
					request.setAttribute("role", "admin");
					this.goAccueil(request, response);
				}
				else {
					request.setAttribute("role", "utilisateur");
					this.goAccueil(request, response);
				}
			}
			else {
				request.setAttribute("failConnect", "true");
				request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
    
  

	/**
	 * Partie principale du servlet, gère la plupart des actions autre que la navigation
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Récupération de la session ou création de la session+ajout de la facade personnalisée*/
		this.facade=(Facade) request.getSession().getAttribute("facade");
		if(facade==null){
			try{
				Context ctx=new InitialContext();
				this.facade=(Facade) ctx.lookup("java:app/Covoiturage/Facade");
				request.getSession().setAttribute("facade", facade);
			}
			catch (NamingException e) {
				e.printStackTrace();
			}
		}	
		
		/*Recupération du login si déjà connecté*/
		String todo = request.getParameter("todo");
		this.currentLogin = (String) request.getSession().getAttribute("login");
			
		/*Si non connecté et qu'on veut se connecter*/
		if(currentLogin==null) {
			if(todo!=null && todo.equals("connect")) {
				this.connectUtilisateur(request, response);
				return;
			}
		}
		else {
			request.setAttribute("connecte", "true");
			if (this.facade.isAdmin(currentLogin)) {
				request.setAttribute("role", "admin");
			}
			else {
				request.setAttribute("role", "utilisateur");
			}
		}
		
		/*si action liée à la barre de navigation*/
		if(request.getParameter("Nav")!=null) {//on a cliqué sur la barre de navigation
			this.navigation(request, response);
		}
		/*autre action*/
		else {
			if(todo==null) {
				this.goAccueil(request, response);
			}
			else {
				switch(todo) {
				case "register" :
					this.registerUtilisateur(request, response);
					break;
				case "newTrajet" :
					this.registerTrajet(request, response);
					break;
				case "recherche" :
					this.rechercheTrajet(request, response);
					break;
				case "reservation":
					this.reserverTrajet(request, response);
					break;
				case "VerifReservation":
					this.verifReservation(request,  response);
					break;
				case "ConfirmerReservation":
					this.confirmerReservation(request, response);
					break;
				case "RefuserReservation":
					this.refuserReservation(request,  response);
					break;
				case "AnnulerReservation":
					this.annulerReservation(request,response);
					break;
				case "SupprimerTrajet" :
					this.supprimerTrajet(request, response);
					break;
				case "ajouterVille" :
					this.ajouterVille(request,response);
					break;
				case "ajouterVehicule" :
					this.ajouterVehicule(request,response);
					break;
				default:
					this.goAccueil(request, response);
					break;
				
				}
			}
		}
	}
	
	private void refuserReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idReservation = Integer.parseInt(request.getParameter("idReservation"));
		this.facade.refuserReservation(idReservation);
		this.verifReservation(request, response);
	}
	
	private void verifReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idTrajet"));
		request.setAttribute("idTrajet", id);
		request.setAttribute("lesResAValide", this.facade.getResAValide(id));
		request.setAttribute("lesResValide", this.facade.getResValide(id));
		request.setAttribute("lesResRefuse", this.facade.getResRefuse(id));
		request.getRequestDispatcher("WEB-INF/detailsTrajet.jsp").forward(request, response);
	}
	
	private void confirmerReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idReservation = Integer.parseInt(request.getParameter("idReservation"));
		this.facade.confirmerReservation(idReservation);
		this.verifReservation(request, response);
	}
	
	private void annulerReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.facade.annulerReservation(Integer.parseInt(request.getParameter("idReservation")));
		this.goCompte(request, response);
	}
	
	private void supprimerTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.facade.supprimerTrajet(Integer.parseInt(request.getParameter("idTrajet")));
		this.goCompte(request, response);
	}
	
	private void ajouterVille(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.facade.ajoutVille(request.getParameter("ajoutVille"));
		this.goAjoutBD(request, response);
	}
	
	private void ajouterVehicule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.facade.ajoutVehicule(request.getParameter("ajoutVehicule"));
		this.goAjoutBD(request, response);
	}

	private void reserverTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
		int nbPlaces = Integer.parseInt(request.getParameter("nbPlaces"));
		if(request.getParameter("arrivee")!=null) {
			int idEtapeArrivee = Integer.parseInt(request.getParameter("arrivee"));
			facade.reserverTrajet(idTrajet, nbPlaces,currentLogin, idEtapeArrivee);
			request.setAttribute("successReservation", "true");
		}
		else {
			request.setAttribute("failReservation", "true");
			request.setAttribute("reason", "Vous n'avez pas choisi d'étapes d'arrivée !");
		}
		
		
		//faire en sorte que les villes choisies restent dans les listes déroulantes
		this.rechercheTrajet(request, response);
		
	}
	
	
	private void rechercheTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String villeDep = (String) request.getParameter("villeDepart");
		String villeArr = (String) request.getParameter("villeArrivee");
		request.setAttribute("villeDepartSelect", villeDep);
		request.setAttribute("villeArriveeSelect", villeArr);
		request.setAttribute("listeTrajets", facade.getTrajets(villeDep, villeArr));
		request.setAttribute("listeVehicules", facade.getNameVehicule());
		this.goRecherche(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
