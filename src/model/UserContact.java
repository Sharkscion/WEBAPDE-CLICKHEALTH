package model;

public class UserContact {
	private int userID;
	private String contactInfo;
	private String type;
	
	public UserContact(int userID, String contactInfo, String type)
	{
		setUserID(userID);
		setContactInfo(contactInfo);
		setType(type);
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
