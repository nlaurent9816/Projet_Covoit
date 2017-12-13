package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		if(request.getParameter("Nav")!=null) {//on a cliqué sur la barre de navigation
			navigation(request, response);
		}
		else { //autre action que la barre de navigation
			if("register".equals(request.getParameter("todo"))) {
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
					}
					else {
						System.out.println("Il manque des informations");


					}
					//System.out.println(nom + " "+ prenom +" "+ sexe +" "+ tel +" "+ email +" "+ login +" "+ password +" "+ password_verif);
					
				}
				else {
					System.out.println("Les mots de passe ne sont pas identiques");

				}	
				request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);

			}
			else {
				request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

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
