package Controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.*;
import model.database.*;

public class Controller
{
	private AppointmentDAO ad;
	private DoctorDAO dd;
	private DoctorScheduleDAO sd;
	private HospitalDAO hd;
	private PatientDAO pd;
	private UserDAO ud;

	public Controller()
	{
		ad = new AppointmentDAO();
		dd = new DoctorDAO();
		sd = new DoctorScheduleDAO();
		hd = new HospitalDAO();
		pd = new PatientDAO();
		ud = new UserDAO();
	}
	
	public Iterator<User> getAllUsers()
	{
		return ud.getAllData();
	}
	
	public User getUser(String username, String password)
	{
		return ud.validateUser(username, password);
	}
	

}


