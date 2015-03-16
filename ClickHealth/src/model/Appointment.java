package model;

public class Appointment
{
	private int ID;
	private String status;
	private String concern;
	private String startTime;
	private String endTime;
	private int patientID;
	private int doctorID;
	
	public Appointment(int ID, String status, String concern, String startTime, String endTime, int patientID, int doctorID)
	{
		setID(ID);
		setStatus(status);
		setConcern(concern);
		setStartTime(startTime);
		setEndTime(endTime);
		setPatientID(patientID);
		setDoctorID(doctorID);
		
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	
	
	
	
}
