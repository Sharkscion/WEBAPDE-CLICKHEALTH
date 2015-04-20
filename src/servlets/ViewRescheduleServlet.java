package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Appointment;
import model.Notification;
import controller.Controller;

/**
 * Servlet implementation class ViewRescheduleServlet
 */
@WebServlet("/ViewRescheduleServlet")
public class ViewRescheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRescheduleServlet() {
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
		String notifId = request.getParameter("notifID");
		String appId = request.getParameter("appID");
		System.out.println("notifId"+ notifId);
		System.out.println("appId"+ appId);
		int id = 0;
		request.setAttribute("notifId", notifId);
		request.setAttribute("appId", appId);
//		Controller c = new Controller();
//		if(reschedID != null && reschedID.equals("")== false)
//		{
//			
//			id = Integer.parseInt(reschedID);
//			c.changeAppointmentStat(id, "pending");
//			
//		} 
		String respMess = "";
		 if(notifId.equals("") == false && appId.equals("") == false && notifId  != null && appId != null)
		 {
			 Controller c = new Controller();
			 Notification n = c.getNotification(Integer.parseInt(notifId));
			 Appointment a = c.getAppointment(Integer.parseInt(appId));
			 
			 n.setIsViewed(1);
			 c.changeNotificationStat(n);
			 
			 DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
			 DateFormat sdf = new SimpleDateFormat("hh:mm");
			
			 respMess = "<form action = \"ApproveReschedServlet\" method= \"post\">";
			 String notif[] = n.getNotifContent().split("%");
			 respMess += " <div class = \"notification-Resched\">";
			 respMess += " 		<span class =\"notification-header\">"+ notif[0] + "</span> <br>";
			 respMess += " 		<label> Reason : "+ notif[1] + "</label>";
			 respMess += " 		<label> Would you like to have the appointment on </label>";
			 respMess += " 		<label>Appointment Date: "+ df.format(a.getAppointmentDate()) +"</label>";
			 respMess += " 		<label>Appointment Time: "+ df.format(a.getStartTime()) +"</label>";
			 respMess += " 		<input type = \"hidden\" id = \"approveID\" name = \"approveID\"> ";
			 respMess += "		<input type = \"hidden\" id = \"rejectID\" name = \"rejectID\"> ";
			 respMess += " 		<input type = \"hidden\" id = \"url\" name = \"url\"> ";
		     respMess += " 		<input class = \" notif-button\" value = \"Accept\" type = \"submit\" id = "+a.getAppID()+" name = "+a.getAppID()+" value = \"View Notification\" onClick = \"getApproveID(this)\";> ";
		     respMess += " 		<input class = \" notif-button\" value = \"Reject\" type = \"submit\" id = "+a.getAppID()+" name = "+a.getAppID()+" value = \"View Notification\" onClick = \"getRejectID(this)\";> "; 
		     respMess += "</div></form>";
		 }
		  
		  
		  response.setContentType("text/plain");
	      response.setCharacterEncoding("UTF-8");
	      response.getWriter().write(respMess);
	}

}
