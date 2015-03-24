package model;

import java.util.Date;

public class DoctorSchedule {
	private int scheduleID;
	private String scheduleDate;
	private Date startTime;
	private Date endTime;
	private boolean isAvailable;
	private int doctorScheduleID;
	private int hospitalScheduleID;

	public DoctorSchedule(int scheduleID, String scheduleDate, Date startTime, Date endTime, 
			boolean isAvailable, int doctorScheduleID, int hospitalScheduleID)
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
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
