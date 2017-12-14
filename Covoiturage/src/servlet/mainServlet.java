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
			if(nom!=null && prenom!=null && sexe!=null && tel!=null && email!=null && login!=null) {
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
			// TODO Auto-generated catch block
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
