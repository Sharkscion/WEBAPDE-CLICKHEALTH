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
	
	public boolean changeAppointmentStat(int id, String stat)
	{
		return ad.changeAppointmentStat(id, stat);
	}
	public boolean addAppointment(Appointment app)
	{
		System.out.println("HELLO: ");
		return ad.insertData(app);
	}
	
	public boolean addContact(UserContact uc)
	{
		return cd.insertData(uc);
	}
	
	public boolean addDoctor(Doctor d)
	{
		return dd.insertData(d);
	}
	
	public boolean addDoctorSchedule(DoctorSchedule ds)
	{
		return sd.insertData(ds);
	}
	
	public boolean addUser(User u)
	{
		return ud.insertData(u);
	}

	public boolean addPatient(Patient p)
	{
		return pd.insertData(p);
	}
	
	public Appointment getAppointment(int appId)
	{
		return ad.getData(appId);
	}
	public Hospital getHospital(String hospitalName)
	{
		return hd.getData(hospitalName);
	}
	
	public Hospital getHospitalByID(int hospID)
	{
		return hd.getHospitalByID(hospID);
	}
	public User getUser(String username)
	{
		User u = ud.getData(username);
		return ud.getData(username);
	}
	
	public Patient getPatientByID(int patientID)
	{
		return pd.getPatientByID(patientID);
	}

	public Doctor getDoctor(int licenseID)
	{
		return dd.getData(licenseID);
	}
	
	public Doctor getDoctorByUsername(String username)
	{
		return dd.getDoctorByUsername(username);
	}
	public User validateUser(String username, String password)
	{
		return ud.validateUser(username, password);
	}

	public Iterator<Hospital> getAllHospitals()
	{
		return hd.getAllData();
	}
	
	public Iterator getAllDoctors()
	{
		return dd.getAllData();
	}
	
	public Iterator<DoctorSchedule> getAllDoctorSchedule()
	{
		return sd.getAllData();
	}
	
	public Iterator<String> getAllSpecializations()
	{
		return dd.getAllSpecializations();
	}
	
	public Iterator<User> getAllUsers()
	{
		return ud.getAllData();
	}
	
	public Iterator<String> getSpecializations(String specialization)
	{
		return dd.getSpecializations(specialization);
	}
	
	public User getUserInstance(String username){
		return ud.getData(username);
	}
        
    public Patient getPatientInstance(String username){
		return pd.getData(username);
	}

	public DoctorSchedule getDoctorSchedule(int scheduleID)
	{
		return sd.getData(scheduleID);
	}
//	public Doctor getSpecificDoctor(int licenseKey)
//	{
//		return dd.getData(licenseKey);
//	}
//	public Doctor getAppointmentDoctor(int keyID)
//	{
//		return dd.getAppointmentDoctor(keyID);
//	}
//	public Hospital getHospital(int hospitalID)
//	{
//		return hd.getData(String.valueOf(hospitalID));
//	}
//	
	public Iterator<Appointment> getPatientAppointments(int patientID)
	{
		return ad.getPatientAppointments(patientID);
	}
	
	public Iterator<Appointment> getRequestAppointment(int licenseID)
	{
		return ad.getRequestAppointments(licenseID);
	}
	public Iterator<Hospital> getSpecializationHospitals(String specialization)
	{
		return hd.getSpecializationHospitals(specialization);
	}
	
	public Iterator<Doctor> getSpecializationHospitalDoctors(String specialization, int hospitalID)
	{
		return dd.getSpecializationHospitalDoctors(specialization, hospitalID);
	}
	
//	public Iterator<Doctor> getSpecializationDoctors()
//	{
//				/*SELECT * FROM doctor d, user u
//		WHERE d.user_ID = u.userID AND d.specialization 
//		IN (SELECT specialization FROM doctor WHERE specialization Like "%o%");*/
//		return null;
//	}
	
	
	public Iterator<UserContact> getUserContacts(int id)
	{
		return cd.getUserContacts(id); 
	}

	public Iterator<DoctorSchedule> getDoctorsSchedules(int licenseID, int hospitalID)
	{
		return sd.getDoctorsSchedules(licenseID, hospitalID);
	}
	
	
}


