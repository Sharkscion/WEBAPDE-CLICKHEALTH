package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.Appointment;
import model.Doctor;
import model.User;

/**
 * Servlet implementation class ContactDocServlet
 */
@WebServlet("/ContactDocServlet")
public class ContactDocServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ContactDocServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String dateString = request.getParameter("date");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date dateObj=null;
        try {
            dateObj = new java.sql.Date(df.parse(dateString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String concern = request.getParameter("dropdown");
        User user = (User) request.getSession().getAttribute("currentUser");
        Doctor doc = (Doctor) request.getSession().getAttribute("doctor");

        Controller con = new Controller();
        con.addAppointment(new Appointment(0, "pending", concern, null, dateObj, user.getUserID(), doc.getLicenseID(), 1/*hospitalID*/));
		//public Appointment(int ID, String status, String concern, Time startTime, Date appointmentDate, int patientID, int doctorID, int hospitalID)

        //String remarks = request.getParameter("textarea");
        response.sendRedirect("hospitals.jsp");

    }

}
