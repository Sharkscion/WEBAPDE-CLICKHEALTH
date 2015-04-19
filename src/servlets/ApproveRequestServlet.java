package servlets;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Appointment;
import model.Notification;
import model.User;
import controller.Controller;

/**
 * Servlet implementation class ApproveRequestServlet
 */
@WebServlet("/ApproveRequestServlet")
public class ApproveRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ApproveRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Controller c = new Controller();
		
		String rejectId = request.getParameter("rejectID");
		String approveId = request.getParameter("approveID");
		String requestId = request.getParameter("requestID");
		Notification n = null;
		Appointment a = null;
		User u = null;
		int success =0;
		int id = -1;
		String notifContent = "";
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateToday = new Date();
		Time requestTime = new Time(dateToday.getTime());
		
		if(approveId != null && approveId.equals("")== false)
		{
			
			id = Integer.parseInt(approveId);
			c.changeAppointmentStat(id, "pending");
			u = c.getAppointmentDoctorInfo(id);
			a = c.getAppointment(id);
		
			notifContent = "Dr. "+u.getFirstname()+" "+u.getLastname()
					+" has approved your appointment on "+ a.getAppointmentDate()
					+" at "+ a.getStartTime();
			n = new Notification(0, Integer.parseInt(approveId), notifContent, dateToday, requestTime,0,0);
			success = 1;
			c.addNotification(n);
			
		} 
		else if(rejectId != null && rejectId.equals("")== false)
		{
			id = Integer.parseInt(rejectId);
		
			c.changeAppointmentStat(id, "rejected");
			u = c.getAppointmentDoctorInfo(id);
			a = c.getAppointment(id);
			notifContent = "Dr. "+u.getFirstname()+" "+u.getLastname()
					+" has rejected your appointment on "+ a.getAppointmentDate()
					+" at "+ a.getStartTime();
			n = new Notification(0, id, notifContent, dateToday, requestTime,0,1);
			c.addNotification(n);
			success = 2;
		}
		else if(rejectId == null && approveId == null)
		{
			success = -1;
		}

		response.sendRedirect("doctor-appointment-requests.jsp?Success="+ success);
	}

}
