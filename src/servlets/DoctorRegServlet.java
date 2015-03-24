package servlets;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Formatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Doctor;
import model.DoctorSchedule;
import controller.Controller;

/**
 * Servlet implementation class DoctorRegServlet
 */
@WebServlet("/DoctorRegServlet")
public class DoctorRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorRegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
		String schedDay = request.getParameter("schedDay");
		String start = request.getParameter("startTime");
		String end = request.getParameter("endTime");
		
		System.out.println("START: "+ start);
		//start+= ":00";
		
		DateFormat sdf = new SimpleDateFormat("hh:mm");
		
		
		try {
			Date dateStart = sdf.parse(start);
			Time startTime = new Time(dateStart.getTime());
			
			Date dateEnd = sdf.parse(end);
			Time endTime = new Time(dateEnd.getTime());
			
			int licenseID = 0;
			
			if(license.equals("") == false)
				licenseID = Integer.parseInt(license);
			
			
			
			Doctor d = new Doctor(0, username, email, password, lastname, firstname, "doctor", licenseID, specialization);
			DoctorSchedule  ds = new DoctorSchedule(0, schedDay, startTime, endTime, 0, con.getDoctorID(username), con.getHospitalID(hospital));
			con.addUser(d);
			con.addDoctor(d);
			con.addDoctorSchedule(ds);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	
		
		
	}

}
