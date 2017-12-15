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
	
	private static final long serialVersionUID = 1L;
    
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
				//on enregistre le nouvel utilisateur
				try {
					facade.enregistrerUtilisateur(nom, prenom, sexe, tel, email, login, password);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
			}
			else {
				System.out.println("Il manque des informations");
				request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
			}
		}
		else {
			System.out.println("Les mots de passe ne sont pas identiques");
			request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
		}	
		

	}
    
    private void registerTrajet(HttpServletRequest request, HttpServletResponse response, String conducteur) throws ServletException, IOException {
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
		if(vehicule_desc==null) {
			System.out.println("Say Hello !");
		}
		System.out.println(vehicule_desc+ " "+vehicule_gabarit+" "+date_trajet+" "+heure_trajet+" "+ville_depart+" "+ville_arrivee+" "+tarif_trajet+" "+place_trajet);
		for(int i =0; i<etapes_trajet.length;i++) {
			System.out.println(etapes_trajet[i]+" "+tarifs_etapes[i]);
		}
		
		if(!(conducteur.equals("")) && !(vehicule_desc.equals("")) && !(vehicule_gabarit.equals("")) && !(date_trajet.equals("")) && !(heure_trajet.equals("")) && !(ville_depart.equals("")) && !(ville_arrivee.equals("")) && !(tarif_trajet.equals("")) && !(place_trajet.equals("")) && (tarifs_etapes.length==etapes_trajet.length)) 
		{
				//on enregistre le nouveau trajet
			System.out.println("Tentative d'enregistrement du trajet...");
				facade.enregistrerTrajet(conducteur, vehicule_desc, vehicule_gabarit, date_trajet, heure_trajet, ville_depart, ville_arrivee, tarif_trajet, etapes_trajet, tarifs_etapes, place_trajet);	
			System.out.println("Trajet enregistré");
				request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		}
		else {
			System.out.println("Il manque des informations");
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
				System.out.println("Connexion réussie");
			}
			else {
				System.out.println("Connexion échouée");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//que la connexion ait réussi ou non, on revient sur page index
		//ajouter un message d'erreur si connexion non réussie
		
		request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

	}
    
    private void navigation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	switch (request.getParameter("Nav")) {
		case "accueil":
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
			break;
		case "register":
			request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
			break;
		case "compte":
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
			break;
		case "recherche":
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
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
		default:
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
			break;
		}
    }
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupération de la session ou création de la session+ajout de la facade personnalisée
		facade=(Facade) request.getSession().getAttribute("facade");
		if(facade==null){
			try{
				Context ctx=new InitialContext();
				facade=(Facade) ctx.lookup("java:app/Covoiturage/Facade");
				request.getSession().setAttribute("facade", facade);
			}
			catch (NamingException e) {
				e.printStackTrace();
			}
		}	
		
		String todo = request.getParameter("todo");
		String currentLogin = (String) request.getSession().getAttribute("login");
			
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
			navigation(request, response);
		}
		else {//autre action que la barre de navigation
			//si pas sur un clic de bouton, on revient page d'accueil
			if(todo==null) {
				request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
			}
			else {
				switch(todo) {
				case "register" :
					this.registerUtilisateur(request, response);
					break;
				case "newTrajet" :
					this.registerTrajet(request, response, currentLogin);
					break;
				default:
					request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
					break;
				
				}
			}
		}
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
