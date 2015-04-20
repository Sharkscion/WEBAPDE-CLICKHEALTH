package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Notification;

/**
 * Servlet implementation class ApproveReSchedServlet
 */
@WebServlet("/ApproveReSchedServlet")
public class ApproveReSchedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveReSchedServlet() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String reschedID = request.getParameter("approveSched");
		System.out.println("RESHED ID IN APPORVE RESCHED SERVLET: "+ reschedID);
		
		int id = 0;
		Controller c = new Controller();
		if(reschedID != null && reschedID.equals("")== false)
		{
			
			id = Integer.parseInt(reschedID);
			c.changeAppointmentStat(id, "pending");
			
		} 
		  response.setContentType("text/plain");
	      response.setCharacterEncoding("UTF-8");
	      response.getWriter().write("");
	}
	

}
