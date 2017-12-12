package servlet;

import java.io.IOException;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("Nav")!=null) {//on a cliqué sur la barre de navigation
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
		else { //autre action que la barre de navigation
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
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
