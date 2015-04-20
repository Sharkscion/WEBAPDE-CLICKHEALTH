package servlets;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
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
 * Servlet implementation class RejectRequestServlet
 */
@WebServlet("/RejectRequestServlet")
public class RejectRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public RejectRequestServlet() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Controller c = new Controller();
		
		String rejectId = request.getParameter("reSchedID");
		String notifReason = request.getParameter("notifContent");
		Notification n = null;
		Appointment a = null;
		User u = null;
		int success =0;
		int id = -1;
		String notifContent = "";
		String startTime = request.getParameter("startTime");
		String appDate = request.getParameter("date");
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateToday = new Date();
		Time requestTime = new Time(dateToday.getTime());
		
		System.out.println("REJECT ID: "+ rejectId);
		
		if(rejectId != null && rejectId.equals("")== false)
		{
			id = Integer.parseInt(rejectId);
			
			DateFormat sdf = new SimpleDateFormat("hh:mm");
			Date dateStart;
			try {
				dateStart = sdf.parse(startTime);
				Time time = new Time(dateStart.getTime());
				Date appointmentDate = df.parse(appDate);
				c.changeAppointmentStat(id, "rescheduled");
				u = c.getAppointmentDoctorInfo(id);
				
				a = c.getAppointment(id);
				a.setAppointmentDate(appointmentDate);
				a.setStartTime(time);
				
				if(c.reschedAppointment(a))
				{
					notifContent = "Dr. "+u.getFirstname()+" "+u.getLastname()
							+" has reschuled your appointment on "+ df.format(a.getAppointmentDate())
							+" at "+ sdf.format(a.getStartTime()) + "%"+ notifReason + "%" + a.getAppID();
					n = new Notification(0, id, notifContent, dateToday, requestTime,0,1);
					c.addNotification(n);
					success = -2;
				}else
				{
					System.out.println("HELLO Reject");
					success = -1;
				}
				response.sendRedirect("doctor-appointment-requests.jsp?Success="+ success);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
}
