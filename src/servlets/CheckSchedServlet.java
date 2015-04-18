package servlets;

import java.io.IOException;
<<<<<<< HEAD
=======
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
>>>>>>> 2247597c1c6cc7e71ed7cede9f922fbcbce3d8f9

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
=======
import model.Appointment;
import model.DoctorSchedule;
import model.Patient;
import model.User;
>>>>>>> 2247597c1c6cc7e71ed7cede9f922fbcbce3d8f9
import controller.Controller;

@WebServlet("/CheckSchedServlet")
public class CheckSchedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckSchedServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
        Controller c = new Controller();

        String date = request.getParameter("schedDate");
        String time = request.getParameter("schedTime");
        System.out.println("date is " + date);
        System.out.println("time is " + time);
        String respMess = "";
        respMess += "date is " + date + "\ntime is " + time;

        
        // check if the schedule typed is part of the days of doc
=======
        Controller con = new Controller();

        String date = request.getParameter("schedDate");
        String time = request.getParameter("schedTime");
        int doctor = Integer.parseInt(request.getParameter("schedDoctor"));
        System.out.println("date is " + date);
        System.out.println("time is " + time);
        
        Date mydate = null;
		try {
			mydate = new SimpleDateFormat("yyyy-mm-dd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String respMess = "";
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(mydate);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        
        //Check day of week
        String day = null;
        switch(dayOfWeek)
        {
        	case 1: day = "Sa"; break;
        	case 2: day = "Su"; break;
        	case 3: day = "M"; break;
        	case 4: day = "T"; break;
        	case 5: day = "W"; break;
        	case 6: day = "H"; break;
        	case 7: day = "F"; break;
        
        }
        
        // check if the schedule typed is part of the days of doc
        DoctorSchedule docsched = null;
        if(con.checkDay(day, doctor))
        {
        	docsched = con.getDoctorScheduleByDay(day, doctor);
        	if(con.checkDaySched(docsched, time, date))
        	{        
        		respMess = "successful!";
        	}
        	else
        	{
        		respMess = "sched already taken";
        	}
        }
        else
        {
        	respMess = "date is invalid";
        }
        
>>>>>>> 2247597c1c6cc7e71ed7cede9f922fbcbce3d8f9
        // check if available yung time (hindi pending)
        // check if the schedule typed is in the time of doc

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(respMess);

	}

}
