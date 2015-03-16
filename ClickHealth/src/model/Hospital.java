package model;

public class Hospital
{
	private int ID;
	private String name;
	private String street;
	private String city;
	
	public Hospital(int ID, String name, String street, String city)
	{
		setID(ID);
		setName(name);
		setStreet(street);
		setCity(city);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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
