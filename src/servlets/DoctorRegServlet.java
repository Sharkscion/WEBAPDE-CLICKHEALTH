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

import model.Doctor;
import model.DoctorSchedule;
import model.Hospital;
import model.UserContact;
import controller.Controller;

/**
 * Servlet implementation class DoctorRegServlet
 */
@WebServlet("/DoctorRegServlet")
public class DoctorRegServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public DoctorRegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Controller con = new Controller();

        String username = request.getParameter("dUName");
        String password = request.getParameter("dPassword");
        String email = request.getParameter("dEmail");

        String lastname = request.getParameter("dLName");
        String firstname = request.getParameter("dFName");
        String license = request.getParameter("dLicense");
        String specialization = request.getParameter("dSpec");

        String hospital = request.getParameter("hospital");
        String schedDay = request.getParameter("schedDays");
        String start = request.getParameter("startTime");
        String end = request.getParameter("endTime");

        try {
            DateFormat sdf = new SimpleDateFormat("hh:mm");
            Date dateStart = sdf.parse(start);
            Time startTime = new Time(dateStart.getTime());

            Date dateEnd = sdf.parse(end);
            Time endTime = new Time(dateEnd.getTime());

            int licenseID = 0;

            if (license.equals("") == false) {
                licenseID = Integer.parseInt(license);
            }

            Hospital h = con.getHospital(hospital);
            System.out.println("HOSPITAL: "+ hospital + " ID: "+ h.getHospID());
            Doctor d = new Doctor(0, username, email, password, lastname, firstname, "doctor", licenseID, specialization);
            con.addUser(d);
            con.addDoctor(d);

            DoctorSchedule ds = new DoctorSchedule(0, schedDay, startTime, endTime, licenseID, h.getHospID());
            con.addDoctorSchedule(ds);

            UserContact c = new UserContact(con.getUser(username).getUserID(), email, "E-mail");
            con.addContact(c);
            response.sendRedirect("index.jsp");

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
