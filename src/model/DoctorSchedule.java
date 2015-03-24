package model;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class DoctorSchedule {
	private int scheduleID;
	private String scheduleDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private int isAvailable;
	private int doctorScheduleID;
	private int hospitalScheduleID;

	public DoctorSchedule(int scheduleID, String scheduleDate, LocalTime startTime, LocalTime endTime, 
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

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
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
