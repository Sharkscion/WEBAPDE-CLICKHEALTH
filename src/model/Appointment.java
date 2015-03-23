package model;

import java.sql.Date;
import java.sql.Time;

public class Appointment
{
	private int ID;
	private String status;
	private String concern;
	private Time startTime;
	private Date appointmentDate;
	private int patientID;
	private int doctorID;
	private int hospitalID;

	public Appointment(int ID, String status, String concern, Time startTime, Date appointmentDate, int patientID, int doctorID, int hospitalID)
	{
		setID(ID);
		setStatus(status);
		setConcern(concern);
		setStartTime(startTime);
		setPatientID(patientID);
		setDoctorID(doctorID);
		setHospitalID(hospitalID);
		setAppointmentDate(appointmentDate);
		
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	public int getHospitalID() {
		return hospitalID;
	}

	public void setHospitalID(int hospitalID) {
		this.hospitalID = hospitalID;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConcern() {
		return concern;
	}

	public void setConcern(String concern) {
		this.concern = concern;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}


	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}
	
	public Date getAppointmentDate()
	{
		return appointmentDate;
	}
	
	public void setAppointmentDate(Date appointmentDate)
	{
		this.appointmentDate = appointmentDate;
	}
	
	
}
