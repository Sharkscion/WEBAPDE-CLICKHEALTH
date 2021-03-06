package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DoctorSchedule;
import model.User;

import java.util.Iterator;

import javax.servlet.http.Cookie;

import controller.Controller;
import model.Doctor;

@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AppointmentServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	String schedId = "";
    	String patientId = "";
    	int pangilan = 0;
    	Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies)
        {	
            if(cookie.getName().equals("doctorSched")){
                schedId = cookie.getValue();
            }
            else if(cookie.getName().equals("user")){
                patientId = cookie.getValue();
            }
        }

        Controller c = new Controller();
        
        schedId =  request.getParameter("docSchedID");
        System.out.println("SCHED ID: "+ schedId);
        pangilan = Integer.parseInt(schedId);  
        DoctorSchedule ds = c.getDoctorSchedule(pangilan);

     
        if(ds != null)
        {
        	request.getSession().setAttribute("doctorSched", ds);
        	response.sendRedirect("contactdoc.jsp");
        }
        else
        {
        	response.sendRedirect("availabledocs.jsp");
        }
        
    }

}
