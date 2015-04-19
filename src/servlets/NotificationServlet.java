package servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Notification;
import model.Patient;
import controller.Controller;

@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NotificationServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Controller c = new Controller();
		String respMess = "";
		String username = request.getParameter("user_name");
		System.out.println("USRENAME: "+username);
		Patient p = c.getPatientInstance(username);
		int hasNext = 0;
		int notifCount = 0;
		if(p!=null)
		{
			Iterator<Notification> nList = c.getAllNotificationNotViewed(p.getPatientID());
			
			while(nList.hasNext())
			{
				Notification n = nList.next();
				respMess += " <div id = "+n.getNotifID()+" class = \"notification-box\">";
				respMess += "<span class =\"notification-header\">"+ n.getNotifContent() + "</span> <br>";
				respMess +=  n.getNotifDate()+" " + n.getNotifTime();   
				respMess += "</div><hr>";

				System.out.println("HELLO");
				hasNext = 1;
			}

			notifCount = c.getNotifCount(p.getPatientID());
		}
			
		
		if(hasNext == 0)
			respMess += "No Notifications";
		if(notifCount > 0)
		{
			respMess += "|"+notifCount;
		}


		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(respMess);
	}

}
