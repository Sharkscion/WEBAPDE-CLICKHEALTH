package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import java.sql.Date;
import java.sql.Time;
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
        Time startTime = null;
        Controller con = new Controller();
        DateFormat formatter = new SimpleDateFormat("hh:mm");
        System.out.println("PACKING TIME: "+request.getParameter("datetimepicker"));
        try{
        startTime =  new Time(formatter.parse(request.getParameter("startTime")).getTime());
        }catch(ParseException e){
            e.printStackTrace();
        }
        
        String dateString = request.getParameter("date");
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date dateObj=null;
        try {
            dateObj = new java.sql.Date(df.parse(dateString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String concern = request.getParameter("dropdown");
        User user = (User) con.getUserInstance(String.valueOf(((User)request.getSession().getAttribute("currentUser")).getUserID()));
        Doctor doc = (Doctor) request.getSession().getAttribute("doctor");

        con.addAppointment(new Appointment(0, "pending", concern, startTime, dateObj, user.getUserID(), doc.getLicenseID(), 1));

        //String remarks = request.getParameter("textarea");
        response.sendRedirect("hospitals.jsp");

    }

}
