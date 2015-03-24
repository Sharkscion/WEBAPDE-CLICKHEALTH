package controller;

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
	private ContactDAO cd;
	private UserDAO ud;
//	private static Controller controller;
//
//	public static synchronized Controller getInstance() 
//	{
//        if (controller == null) {
//            controller = new Controller();
//        }
// 
//        return controller;
//    }
	public Controller()
	{
		ad = new AppointmentDAO();
		dd = new DoctorDAO();
		sd = new DoctorScheduleDAO();
		hd = new HospitalDAO();
		pd = new PatientDAO();
		ud = new UserDAO();
		cd = new ContactDAO();
	}
	
	public Iterator<User> getAllUsers()
	{
		return ud.getAllData();
	}
	
	public User getUser(String username, String password)
	{
		return ud.validateUser(username, password);
	}
	
	public Iterator<Hospital> getAllHospitals()
	{
		return hd.getAllData();
	}
	
	public Iterator<String> getSpecializations()
	{
		return dd.getSpecializations();
	}
	
	public Iterator<String> getSpecializations(String specialization)
	{
		return dd.getSpecializations(specialization);
	}
	
	public Iterator getDoctors(){
		return dd.getAllData();
	}
	
	public Object getUserInstance(String key){
		return (Object) ud.getData(key);
	}
	
	public Object getDoctor(int key)
	{
		return dd.getData(key);
	}

	public Doctor getAppointmentDoctor(int keyID)
	{
		return (Doctor) dd.getAppointmentDoctor(keyID);
	}
	public Hospital getHospital(int hospitalID)
	{
		return (Hospital) hd.getData(String.valueOf(hospitalID));
	}
	
	public Iterator<Hospital> getSpecializationHospitals(String specialization)
	{
		return hd.getSpecializationHospitals(specialization);
	}
	
	public Iterator<Doctor> getSpecializationHospitalDoctors(String specialization, int hospitalID)
	{
		return dd.getSpecializationHospitalDoctors(specialization, hospitalID);
	}
	
	public Iterator<Doctor> getSpecializationDoctors()
	{
				/*SELECT * FROM doctor d, user u
		WHERE d.user_ID = u.userID AND d.specialization 
		IN (SELECT specialization FROM doctor WHERE specialization Like "%o%");*/
		return null;
	}
	
	public Iterator getPatientAppointments(int patientID)
	{
		return ad.getUserAppointments(patientID);
	}
	
	public Iterator<Appointment> getDoctorAppointments(String username)
	{
		return ad.getDoctorAppointments(username);
	}
	
	public String getUserUserName(int ID)
	{
		User u = (User) ud.getData(String.valueOf(ID));
		return u.getFirstname() + " " + u.getLastname();
	}
	
	public Iterator getUserContacts(int id)
	{
		return cd.getUserContacts(id); 
	}
	
	//public 
	public void addAppointment(Appointment app)
	{
		ad.insertData((Object) app);
	}
}


