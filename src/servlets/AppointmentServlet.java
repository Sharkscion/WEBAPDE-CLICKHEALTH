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
        
        schedId =  request.getParameter("docID");
//        System.out.println("DOC ID: "+ docId);
        	System.out.println("CHEDID: "+ schedId);
        //if(schedId.equals("") == false)
        	pangilan = Integer.parseInt((String) request.getParameter("docID"));
        
        DoctorSchedule ds = c.getDoctorSchedule(pangilan);
        
        System.out.println("DOCTOR SCHED in APPOINTMENT: "+ ds);
        
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
