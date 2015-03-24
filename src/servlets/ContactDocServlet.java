package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Appointment;
import model.Doctor;
import model.User;

/**
 * Servlet implementation class ContactDocServlet
 */
@WebServlet("/ContactDocServlet")
public class ContactDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContactDocServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String date = request.getParameter("date");
		String concern = request.getParameter("dropdown");
		User user = (User) request.getSession().getAttribute("currentUser");
		Doctor doc = (Doctor) request.getSession().getAttribute("doctor"); 
		
		Controller con = new Controller();
		con.addAppointment(new Appointment(0, "pending", concern, null,null, user.getUserID(), doc.getLicenseID(), 1/*hospitalID*/));
		
		//String remarks = request.getParameter("textarea");
		
	}

}
