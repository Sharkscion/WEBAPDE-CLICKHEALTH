package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import model.Doctor;
import model.DoctorSchedule;
import model.Hospital;
import model.Patient;
import model.User;
import model.UserContact;

public class DoctorDAO implements DAOInterface {

	private DBConnection connect = DBConnection.getInstance();
	private PreparedStatement statement;
	private ResultSet rs;
	private ArrayList<Doctor> dList = null;

	private static DoctorDAO dD = null;

	public static synchronized DoctorDAO getInstance() {
		if (dD == null) {
			dD = new DoctorDAO();
		}
		return dD;
	}

	
	@Override
	public Iterator<Doctor> getAllData() {

		try {
			String query = "SELECT * "
							+ "FROM user, doctor "
							+ "WHERE user_ID = userID;";
			statement = connect.getConnection().prepareStatement(query);
			rs = statement.executeQuery();
			Doctor d = null;

			dList = new ArrayList<Doctor>();
			while (rs.next()) 
			{
				d = new Doctor(rs.getInt("userID"), rs.getString("username"), rs.getString("email"), rs.getString("password"),
						rs.getString("lastname"), rs.getString("firstname"), rs.getString("type"), rs.getInt("licenseID"),
						rs.getString("specialization"));
				dList.add(d);

			}

		} catch (SQLException e) {
			System.out.println("ERROR in getting all Doctors");
			e.printStackTrace();
		}
		connect.close();
		return dList.iterator();
	}

	@Override
	public boolean insertData(Object obj) {

		Doctor doc = (Doctor) obj;
		try {

			String query = "INSERT INTO doctor VALUES(?,?,(SELECT userID from user WHERE username = ?));";
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, doc.getLicenseID() );
			statement.setString(2, doc.getSpecialization());
			statement.setString(3, doc.getUsername());
			statement.execute();
			connect.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Unable to INSERT new doctor");
			e.printStackTrace();
		}
		connect.close();
		return false;
	}

	@Override
	public boolean updateData(Object obj) {
		Doctor d = (Doctor)obj;
		String query = "UPDATE doctor "
					 + "SET  specialization = ?"
				     + "WHERE licenseID = ?";
		try 
		{
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, d.getSpecialization());
                        statement.setInt(2, d.getLicenseID());
			statement.execute();
			connect.close();
			return true;
		} catch (SQLException e) {
	
			System.out.println("Update Error");
			e.printStackTrace();
		}
		connect.close();
		return false;
	}

	public Iterator<String> getAllSpecializations()
	{
		Connection con = connect.getConnection();
		String spec;
		ArrayList<String> specs = new ArrayList<String>();
		try {
			String query = "SELECT DISTINCT(specialization) FROM doctor";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				specs.add(resultSet.getString(1));
			}

		}
		catch (SQLException e)
		{
			System.out.println("Unable to INSERT new doctor");
			e.printStackTrace();
		}
		connect.close();

		return specs.iterator();
	}

	@Override
	public Doctor getData(Object key) 
	{
		String username = (String)key;

		Doctor d = null;
		try 
		{
			String query = "SELECT * FROM user u, doctor d "
					+ "WHERE u.userID = d.user_ID "
					+ "AND u.username = ?;";
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, username);
			rs = statement.executeQuery();
			if (rs.next()) {
				d = new Doctor(rs.getInt("userID"), rs.getString("username"), rs.getString("email"), 
						rs.getString("password"),rs.getString("lastname"), rs.getString("firstname"), 
						rs.getString("type"), rs.getInt("licenseID"),rs.getString("specialization"));
				
			}
		}
		catch (SQLException e)
		{
			System.out.println("Unable to INSERT new doctor");
			e.printStackTrace();
		}
		connect.close();
		return d;
	}

	public Doctor getDoctorByUserId(int userId) 
	{
		Doctor d = null;
		try 
		{
			String query = "SELECT * FROM user u, doctor d "
					+ "WHERE u.userID = ?";
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, userId);
			rs = statement.executeQuery();
			if (rs.next()) {
				d = new Doctor(rs.getInt("userID"), rs.getString("username"), rs.getString("email"), 
						rs.getString("password"),rs.getString("lastname"), rs.getString("firstname"), 
						rs.getString("type"), rs.getInt("licenseID"),rs.getString("specialization"));
				
			}
		}
		catch (SQLException e)
		{
			System.out.println("Unable to INSERT new doctor");
			e.printStackTrace();
		}
		connect.close();
		return d;
	}
	public Iterator<String> getSpecializations(String specialization)
	{
		ArrayList<String> sList = new ArrayList<String>();
		try {
			String query = "SELECT DISTINCT(specialization) "
						+ "FROM doctor "
						+ "WHERE specialization Like \"%" + specialization + "%\"";
			statement = connect.getConnection().prepareStatement(query);
			rs = statement.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
				sList.add(rs.getString(1));
			}
			
		}
		catch (SQLException e)
		{
			System.out.println("Unable to get speciclaitions");
			e.printStackTrace();
		}
		connect.close();
		return sList.iterator();
	}

	public Iterator<Doctor> getSpecializationHospitalDoctors(String specialization, int scheduleID)
	{
	
		dList = new ArrayList<Doctor>();
		try {
			String query = "SELECT * "
					+ "FROM user, doctor "
					+ "WHERE user_ID = userID "
					+ "AND specialization LIKE '%" + specialization + "%' "
					+ "AND licenseID = (SELECT doctorScheduleID FROM doctorschedule WHERE scheduleID = '" + scheduleID +"');" ;
			statement = connect.getConnection().prepareStatement(query);
			rs = statement.executeQuery();
			while (rs.next()) {

				Doctor doc = new Doctor(rs.getInt("userID"), rs.getString("username"), rs.getString("email"), 
						rs.getString("password"),rs.getString("lastname"), rs.getString("firstname"), 
						rs.getString("type"), rs.getInt("licenseID"),rs.getString("specialization"));
				dList.add(doc);
			}

		}
		catch (SQLException e)
		{
			System.out.println("Unable to get speciclaitions");
			e.printStackTrace();
		}
		connect.close();
		return dList.iterator();
	}

	
}
