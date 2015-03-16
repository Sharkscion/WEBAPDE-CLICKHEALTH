package model;

public class Doctor extends User {

	private int licenseID;
	private String specialization;
	
	public Doctor(int userID, String username, String email, String password,
			String lastname, String firstname, String type, int licenseID, String specialization) {
		super(userID, username, email, password, lastname, firstname, type);
		// TODO Auto-generated constructor stub
		
		this.licenseID = licenseID;
		this.specialization = specialization;
	}

	public int getLicenseID() {
		return licenseID;
	}

	public void setLicenseID(int licenseID) {
		this.licenseID = licenseID;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

}
