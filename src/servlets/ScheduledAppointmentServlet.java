package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

@WebServlet("/ScheduledAppointmentServlet")
public class ScheduledAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ScheduledAppointmentServlet() {
        super();
     
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Controller c = new Controller();
		String appId = request.getParameter("resolveID");
		int success = 0;
		System.out.println("APP ID: "+appId);
		
		if(appId != null && appId.equals("") == false)
		{
			if(c.resolveDoctorAppointment(Integer.parseInt(appId)))
				success = 1;
		}
		
		response.sendRedirect("scheduled-appointments.jsp?Success="+success);
	}

}
