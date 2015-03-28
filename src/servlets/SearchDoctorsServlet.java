package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

/**
 * Servlet implementation class SearchDoctorsServlet
 */
@WebServlet("/SearchDoctorsServlet")
public class SearchDoctorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDoctorsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Controller con = new Controller();
		String searchedSpecialization = (String)request.getSession().getAttribute("specialization");
		request.getSession().setAttribute("hID", Integer.parseInt((String) request.getParameter("hospID")));
		request.getSession().setAttribute("doctors", con.getSpecializationHospitalDoctors(searchedSpecialization, Integer.parseInt((String) request.getParameter("hospID"))));
		response.sendRedirect("searchedDoctors.jsp");
	}

}
