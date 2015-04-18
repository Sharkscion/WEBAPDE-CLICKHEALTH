package servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Hospital;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Controller con = new Controller();
		String searchedSpecialization = (String)request.getParameter("searchbox");
		request.getSession().setAttribute("specialization", searchedSpecialization);
		request.getSession().setAttribute("hospitals", con.getSpecializationHospitals(searchedSpecialization));
		response.sendRedirect("searchedHospitals.jsp");
		
					
	}
	

}
