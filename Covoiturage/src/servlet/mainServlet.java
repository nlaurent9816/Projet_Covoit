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
	
	private void goAccueil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listeVilles", facade.getNameVille());
		request.setAttribute("listeVehicules", facade.getNameVehicule());
		request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
	}
    
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
			//if(facade.nouveauLogin(login)) {
				if(!nom.equals("") && !prenom.equals("") && sexe!=null && !tel.equals("") && !email.equals("") && !login.equals("")) {
					//on enregistre le nouvel utilisateur
					try {
						facade.enregistrerUtilisateur(nom, prenom, sexe, tel, email, login, password);
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
					
					goAccueil(request, response);
				}
				else {
					//System.out.println("Il manque des informations");
					//request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
					request.setAttribute("failRegister", "true");
					request.setAttribute("reason", "Il manque des informations");
					request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
				}
			/*}
			else {

				System.out.println("Login déjà utilisé !");
			}*/
			

		}
		else {
			request.setAttribute("failRegister", "true");
			request.setAttribute("reason","Les mots de passe ne sont pas identiques");
			request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
		}	
		

	}
    
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
		
		if(!(this.currentLogin.equals("")) && !(vehicule_desc.equals("")) && !(vehicule_gabarit.equals("")) && !(date_trajet.equals("")) && !(heure_trajet.equals("")) && !(ville_depart.equals("")) && !(ville_arrivee.equals("")) && !(tarif_trajet.equals("")) && !(place_trajet.equals("")) && (tarifs_etapes.length==etapes_trajet.length)) 
		{
				//on enregistre le nouveau trajet
				this.facade.enregistrerTrajet(this.currentLogin, vehicule_desc, vehicule_gabarit, date_trajet, heure_trajet, ville_depart, ville_arrivee, tarif_trajet, etapes_trajet, tarifs_etapes, place_trajet);	
				this.goAccueil(request, response);
		}
		else {
			request.setAttribute("failTrajet", "true");
			request.setAttribute("reason", "Il manque des informations");
			request.setAttribute("listeVilles", facade.getNameVille());
			request.setAttribute("listeVehicules", facade.getNameVehicule());
			request.getRequestDispatcher("WEB-INF/nouveauTrajet.jsp").forward(request, response);
		}
			
		

	}
    
    private void connectUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = (String) request.getParameter("mdp");
		String login = (String) request.getParameter("login");
		try {
			if(facade.checkUtilisateur(login, password)) {
				request.getSession().setAttribute("login", login);
				request.setAttribute("connecte", "true");
			}
			else {
				request.setAttribute("failConnect", "true");
				request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//que la connexion ait réussi ou non, on revient sur page index
		//ajouter un message d'erreur si connexion non réussie
		
		this.goAccueil(request, response);

	}
    
    private void navigation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	switch (request.getParameter("Nav")) {
		case "accueil":
			this.goAccueil(request, response);
			break;
		case "register":
			request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
			break;
		case "compte":
			request.setAttribute("listeTrajetConducteur", facade.getTrajetConducteur(currentLogin));
			request.setAttribute("listeReservations", facade.getReservations(currentLogin));
			request.getRequestDispatcher("WEB-INF/compte.jsp").forward(request, response);
			break;
		case "recherche":
			request.setAttribute("listeVilles", facade.getNameVille());
			request.setAttribute("listeVehicules", facade.getNameVehicule());
			request.getRequestDispatcher("WEB-INF/recherche.jsp").forward(request, response);
			break;
		case "ajout":
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
			break;
		case "ajoutTrajet":
			//faire passer la liste des villes et des véhicules
			request.setAttribute("listeVilles", facade.getNameVille());
			request.setAttribute("listeVehicules", facade.getNameVehicule());
			request.getRequestDispatcher("WEB-INF/nouveauTrajet.jsp").forward(request, response);
			break;
		case "deconnexion":
			request.setAttribute("connecte", "false");
			request.getSession().setAttribute("login", null);
			request.getSession().setAttribute("facade", null);
			System.out.println("je me déconnecte");
			this.goAccueil(request,  response);
			break;
		default:
			this.goAccueil(request,  response);			
			break;
		}
    }
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupération de la session ou création de la session+ajout de la facade personnalisée
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
		
		String todo = request.getParameter("todo");
		this.currentLogin = (String) request.getSession().getAttribute("login");
			
		//on vérifie d'abord si on n'est pas connecté
		if(currentLogin==null) {
			if(todo!=null && todo.equals("connect")) {
				this.connectUtilisateur(request, response);
				return;
			}
		}
		else {
			request.setAttribute("connecte", "true");
		}
		
		//si clic sur barre de navigation
		if(request.getParameter("Nav")!=null) {//on a cliqué sur la barre de navigation
			this.navigation(request, response);
		}
		else {//autre action que la barre de navigation
			//si pas sur un clic de bouton, on revient page d'accueil
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
					int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
					request.setAttribute("idTrajet", idTrajet);
					request.setAttribute("lesResAValide", this.facade.getResAValide(idTrajet));
					request.setAttribute("lesResValide", this.facade.getResValide(idTrajet));
					request.getRequestDispatcher("WEB-INF/detailsTrajet.jsp").forward(request, response);
					break;
				case "ConfirmerReservation":
					int idReservation = Integer.parseInt(request.getParameter("idReservation"));
					System.out.println("idRes retrouvé :" +request.getParameter("idTrajet"));
					int id = Integer.parseInt(request.getParameter("idTrajet"));
					this.facade.confirmerReservation(idReservation);
					request.setAttribute("lesResAValide", this.facade.getResAValide(id));
					request.setAttribute("lesResValide", this.facade.getResValide(id));
					request.setAttribute("idTrajet", id);
					request.getRequestDispatcher("WEB-INF/detailsTrajet.jsp").forward(request, response);
					break;
				case "AnnulerReservation":
					this.facade.annulerReservation(Integer.parseInt(request.getParameter("idReservation")));
					request.setAttribute("listeTrajetConducteur", facade.getTrajetConducteur(currentLogin));
					request.setAttribute("listeReservations", facade.getReservations(currentLogin));
					request.getRequestDispatcher("WEB-INF/compte.jsp").forward(request, response);
					break;
				case "SupprimerTrajet" :
					this.facade.supprimerTrajet(Integer.parseInt(request.getParameter("idTrajet")));
					request.setAttribute("listeTrajetConducteur", facade.getTrajetConducteur(currentLogin));
					request.setAttribute("listeReservations", facade.getReservations(currentLogin));
					request.getRequestDispatcher("WEB-INF/compte.jsp").forward(request, response);
					break;
				default:
					this.goAccueil(request, response);
					break;
				
				}
			}
		}
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
		request.setAttribute("listeVilles", facade.getNameVille());
		request.setAttribute("listeVehicules", facade.getNameVehicule());
		request.getRequestDispatcher("WEB-INF/recherche.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
