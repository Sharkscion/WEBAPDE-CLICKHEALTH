package model;

public class Patient extends User {

	private int patientID;
	private String street;
	private String city;
	
	public Patient(int userID, String username, String email, String password,
			String lastname, String firstname, String type, int patientID, String street, String city) 
	{
		super(userID, username, email, password, lastname, firstname, type);
		setPatientID(patientID);
		setStreet(street);
		setCity(city);
	}
	
	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	

}
