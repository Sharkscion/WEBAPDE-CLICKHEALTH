package model;

import java.sql.Time;

public class DoctorSchedule {
	private int scheduleID;
	private String scheduleDate;
	private Time startTime;
	private Time endTime;
	private int isAvailable;
	private int doctorScheduleID;
	private int hospitalScheduleID;

	public DoctorSchedule(int scheduleID, String scheduleDate, Time startTime, Time endTime, 
			int isAvailable, int doctorScheduleID, int hospitalScheduleID)
	{
		this.scheduleID = scheduleID;
		this.scheduleDate = scheduleDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isAvailable = isAvailable;
		this.doctorScheduleID = doctorScheduleID;
		this.hospitalScheduleID = hospitalScheduleID;
		
	}
	
	public int getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public int isAvailable() {
		return isAvailable;
	}

	public void setAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getDoctorScheduleID() {
		return doctorScheduleID;
	}

	public void setDoctorScheduleID(int doctorScheduleID) {
		this.doctorScheduleID = doctorScheduleID;
	}

	public int getHospitalScheduleID() {
		return hospitalScheduleID;
	}

	public void setHospitalScheduleID(int hospitalScheduleID) {
		this.hospitalScheduleID = hospitalScheduleID;
	}

	

}
