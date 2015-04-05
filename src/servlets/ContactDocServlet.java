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

import model.Appointment;
import model.Doctor;
import model.DoctorSchedule;
import model.Patient;
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

        Controller con = new Controller();
        DateFormat formatter = new SimpleDateFormat("hh:mm");
        String start = request.getParameter("startTime");
        String appDate = request.getParameter("date");
        String concern = request.getParameter("dropdown");
        String remarks = request.getParameter("remarks");

        try {
            DateFormat sdf = new SimpleDateFormat("hh:mm");
            Date dateStart = sdf.parse(start);
            Time startTime = new Time(dateStart.getTime());

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date appointmentDate = df.parse(appDate);
            Date dateToday = new Date();
            Time requestTime = new Time(dateToday.getTime());

            User user = (User) con.getUserInstance(((User) request.getSession().getAttribute("currentUser")).getUsername());
            DoctorSchedule ds = (DoctorSchedule) request.getSession().getAttribute("doctorSched");
            Patient p = con.getPatientInstance(user.getUsername());

            Appointment a = new Appointment(0, "request", concern, remarks, startTime, requestTime,
                    dateToday, appointmentDate, 0, 0, p.getPatientID(), ds.getScheduleID());
            con.addAppointment(a);
            response.sendRedirect("hospitals.jsp");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
