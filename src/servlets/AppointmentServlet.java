package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
       
    	String docId = "";
    	String patientId = "";
    	int pangilan = 0;
    	Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("doctor")){
                docId = cookie.getValue();
            }
            else if(cookie.getName().equals("user")){
                patientId = cookie.getValue();
            }
        }

        Controller c = new Controller();
        
        docId =  request.getParameter("docID");
        System.out.println("DOC ID: "+ docId);
        if(docId.equals("") == false)
        	pangilan = Integer.parseInt((String) request.getParameter("docID"));
        
        Iterator doctors = c.getDoctors();
        Doctor doctor = null;
        
        while(doctors.hasNext())
        {
        		doctor = (Doctor) doctors.next();
        		if(doctor.getUserID() == pangilan)
        		{      
        			System.out.println("Doctor in appt. == " + doctor.getFirstname() + " " + doctor.getLastname());
        			break;
        		}
        }
                
        request.getSession().setAttribute("doctor", doctor);
        
        response.sendRedirect("contactdoc.jsp");
    }

}
