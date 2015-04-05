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

import controller.Controller;
import javax.servlet.http.Cookie;

import model.Appointment;
import model.Doctor;
import model.DoctorSchedule;
import model.Patient;
import model.User;

/**
 * Servlet implementation class ContactDocServlet
 */
@WebServlet("/EditPatientServlet")
public class EditPatientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public EditPatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        Controller con = new Controller();
        Patient p;
        String userID = "";
        User u;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                userID = cookie.getValue();
            }
        }
        
        u = con.getUserInstance(userID);
        p = con.getPatientInstance(u.getUsername());

        String type = request.getParameter("settingCategory");
        System.out.println("WENT HERE Type: "+type);
        if (type.equals("name")) {
            
            u.setFirstname(request.getParameter("firstNameTxt"));
            u.setLastname(request.getParameter("lastNameTxt"));
            con.editUser(u);
            
        } else if (type.equals("address")) {
            p.setStreet(request.getParameter("streetTxt"));
            p.setCity(request.getParameter("cityTxt"));
            con.editPatient(p);
            
        } else if (type.equals("username")) {
            u.setUsername(request.getParameter("usernameTxt"));
            con.editUser(u);
            
        } else if (type.equals("password")) {
            u.setPassword(request.getParameter("passwordTxt"));
            con.editUser(u);
        }
        
        response.sendRedirect("user-account-settings.jsp");

    }

}
