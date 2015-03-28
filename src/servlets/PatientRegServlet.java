package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Patient;
import model.UserContact;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import controller.Controller;

/**
 * Servlet implementation class PatientRegServlet
 */
@WebServlet("/PatientRegServlet")
public class PatientRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientRegServlet() {
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
		
		String username = request.getParameter("pUName");
		String email = request.getParameter("pEmail");
		String password = request.getParameter("pPassword");
		
		String lastname = request.getParameter("pLName");
		String firstname = request.getParameter("pFName");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		
		Patient p = new Patient(0, username, email, password, lastname, firstname, "patient", 0, street, city);
		con.addUser(p);	
		con.addPatient(p);
		
		UserContact c = new UserContact(con.getUser(username).getUserID(), email, "E-mail");
		con.addContact(c);
		response.sendRedirect("index.jsp");
	}

}
