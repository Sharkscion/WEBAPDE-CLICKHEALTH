package model;

import java.sql.Time;
import java.util.Date;

public class Notification {
	private int notifID;
	private int appID;
	private String notifContent;
	private Date notifDate;
	private Time notifTime;
	private int isViewed;
	
	public Notification(int notifID, int appID, String notifContent, Date notifDate, Time notifTime, int isViewed)
	{
		setNotifID(notifID);
		setAppID(appID);
		setNotifContent(notifContent);
		setNotifDate(notifDate);
		setNotifTime(notifTime);
		setIsViewed(isViewed);
	}
	public int getNotifID() {
		return notifID;
	}
	public void setNotifID(int notifID) {
		this.notifID = notifID;
	}
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public String getNotifContent() {
		return notifContent;
	}
	public void setNotifContent(String notifContent) {
		this.notifContent = notifContent;
	}
	public Date getNotifDate() {
		return notifDate;
	}
	public void setNotifDate(Date notifDate) {
		this.notifDate = notifDate;
	}
	public Time getNotifTime() {
		return notifTime;
	}
	public void setNotifTime(Time notifTime) {
		this.notifTime = notifTime;
	}
	public int getIsViewed() {
		return isViewed;
	}
	public void setIsViewed(int isViewed) {
		this.isViewed = isViewed;
	}
}
