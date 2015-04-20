package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Notification;
import model.Patient;
import controller.Controller;

@WebServlet("/ShowNotificationServlet")
public class ShowNotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowNotificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Controller c = new Controller();
		String respMssg = "";
		String notifID = request.getParameter("notifID");
		String username = request.getParameter("user_name");
		
		System.out.println("SHOW NOTIFICAITON SRVLET");
		if(notifID.equals("") == false && notifID != null )
		{
			Notification n = c.getNotification(Integer.parseInt(notifID));
			n.setIsViewed(1);
			c.changeNotificationStat(n);
			if(n.getIsRejected() == 0)
			{
				   Patient p = c.getPatientInstance(username);
				 response.setContentType("text/plain");
			     response.setCharacterEncoding("UTF-8");
			     System.out.println("OTIF COUNT: "+ c.getNotifCount(p.getPatientID()));
			     response.getWriter().write("1|"+c.getNotifCount(p.getPatientID()));
			}else
			{
				respMssg = n.getNotifContent();
				 response.setContentType("text/plain");
			     response.setCharacterEncoding("UTF-8");
			     response.getWriter().write(respMssg);
			}
			
			
		}
	}

}
