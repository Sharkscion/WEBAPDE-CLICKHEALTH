package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Appointment;
import model.Notification;

/**
 * Servlet implementation class ApproveReSchedServlet
 */
@WebServlet("/ApproveReSchedServlet")
public class ViewReSchedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewReSchedServlet() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String notifId = request.getParameter("notifID");
		String appId = request.getParameter("appID");
		System.out.println("notifId"+ notifId);
		System.out.println("appId"+ appId);
		int id = 0;
		request.setAttribute("notifId", notifId);
		request.setAttribute("appId", notifId);
//		Controller c = new Controller();
//		if(reschedID != null && reschedID.equals("")== false)
//		{
//			
//			id = Integer.parseInt(reschedID);
//			c.changeAppointmentStat(id, "pending");
//			
//		} 
		String respMess = "";
		 if(notifId.equals("") == false && appId.equals("") && notifId  != null && appId != null)
		 {
			 Controller c = new Controller();
			 Notification n = c.getNotification(Integer.parseInt(notifId));
			 Appointment a = c.getAppointment(Integer.parseInt(appId));
			 
			 DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
			 DateFormat sdf = new SimpleDateFormat("hh:mm");
			
			 respMess = "<form action = \"ApproveReSchedServlet\" method= \"post\">";
			 String notif[] = n.getNotifContent().split("%");
			 respMess += " <div class = \"notification-box\">";
			 respMess += " <span class =\"notification-header\">"+ notif[0] + "</span> <br>";
			 respMess += " <p> Reason : "+ notif[1] + "</p>";
			 respMess += " <p> Would you like to have the appointment on </p>";
			 respMess += " <label>Appointment Date: "+ df.format(a.getAppointmentDate()) +"</label>";
			 respMess += " <label>Appointment Time: "+ df.format(a.getStartTime()) +"</label>";
			 respMess += " <input type = \"hidden\" id = \"approveID\" name = \"approveID\"> ";
			 respMess += " <input type = \"hidden\" id = \"rejectID\" name = \"rejectID\"> ";
		     respMess += " <input class = \" notif-button\" value = \"Accept\" type = \"submit\" id = "+a.getAppID()+" name = "+a.getAppID()+" value = \"View Notification\" onClick = \"getApproveID(this)\";> ";
		     respMess += " <input class = \" notif-button\" value = \"Reject\" type = \"submit\" id = "+a.getAppID()+" name = "+a.getAppID()+" value = \"View Notification\" onClick = \"getRejectID(this)\";> "; 
		     respMess += "</form>";
		 }
		  
		  
		  response.setContentType("text/plain");
	      response.setCharacterEncoding("UTF-8");
	      response.getWriter().write(respMess);
	}
	

}
