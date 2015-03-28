package model;

import java.sql.Time;

public class DoctorSchedule {
	private int scheduleID;
	private String scheduleDay;
	private Time startTime;
	private Time endTime;
	private int doctorScheduleID;
	private int hospitalScheduleID;

	public DoctorSchedule(int scheduleID, String scheduleDay, Time startTime, Time endTime,
						  int doctorScheduleID, int hospitalScheduleID)
	{
		setScheduleID(hospitalScheduleID);
		setScheduleDay(scheduleDay);
		setStartTime(startTime);
		setEndTime(endTime);
		setDoctorScheduleID(doctorScheduleID);
		setHospitalScheduleID(hospitalScheduleID);
	}
	
	public int getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}

	public String getScheduleDay() {
		return scheduleDay;
	}

	public void setScheduleDay(String scheduleDay) {
		this.scheduleDay = scheduleDay;
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
