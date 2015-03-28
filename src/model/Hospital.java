package model;

public class Hospital
{
	private int hospID;
	private String name;
	private String street;
	private String city;
	
	public Hospital(int hospID, String name, String street, String city)
	{
		setHospID(hospID);
		setName(name);
		setStreet(street);
		setCity(city);
	}

	public int getHospID() {
		return hospID;
	}


	public void setHospID(int hospID) {
		this.hospID = hospID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
