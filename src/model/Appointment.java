package model;


import java.sql.Time;
import java.util.Date;

public class Appointment
{
	private int appID;

	private String status;
	private String concern;
	private String remarks;
	private Time startTime;
	private Time requestedTime;
	private Date requestedDate;
	private Date appointmentDate;
	private int isResolvedPatient;
	private int isResolvedDoctor;
	private int patientID;
	private int doctorSchedID;

	public Appointment(int appID, String status, String concern, String remarks, Time startTime, Time requestedTime,
					   Date dateToday, Date appointmentDate2, int isResolvedPatient, int isResolvedDoctor, 
					   int patientID, int doctorSchedID)
   {
		setAppID(appID);
		setStatus(status);
		setConcern(concern);
		setRemarks(remarks);
		setStartTime(startTime);
		setRequestedTime(requestedTime);
		setRequestedDate(dateToday);
		setAppointmentDate(appointmentDate2);
		setIsResolvedDoctor(isResolvedDoctor);
		setIsResolvedPatient(isResolvedPatient);
		setPatientID(patientID);
		setDoctorSchedID(doctorSchedID);
   }
	
	public int getAppID() {
		return appID;
	}

	public void setAppID(int appID) {
		this.appID = appID;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public int getIsResolvedPatient() {
		return isResolvedPatient;
	}

	public void setIsResolvedPatient(int isResolvedPatient) {
		this.isResolvedPatient = isResolvedPatient;
	}

	public int getIsResolvedDoctor() {
		return isResolvedDoctor;
	}

	public void setIsResolvedDoctor(int isResolvedDoctor) {
		this.isResolvedDoctor = isResolvedDoctor;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public int getDoctorSchedID() {
		return doctorSchedID;
	}

	public void setDoctorSchedID(int doctorSchedID) {
		this.doctorSchedID = doctorSchedID;
	}

	public Time getRequestedTime() {
		return requestedTime;
	}

	public void setRequestedTime(Time requestedTime) {
		this.requestedTime = requestedTime;
	}

}
