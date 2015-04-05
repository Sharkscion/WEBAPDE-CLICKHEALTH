package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import model.Doctor;
import model.Patient;

public class PatientDAO implements DAOInterface {

	private DBConnection connect = DBConnection.getInstance();
	private PreparedStatement statement;
	private ResultSet rs;
	private ArrayList<Patient> pList;

	private static PatientDAO pD = null;

	public static synchronized PatientDAO getInstance() {
		if (pD == null) {
			pD = new PatientDAO();
		}
		return pD;
	}

	@Override
	public Iterator<Patient> getAllData() {

		Patient pat;
		pList = new ArrayList<Patient>();
		try {
			String query = "SELECT * "
						+ "FROM user, patient "
						+ "WHERE userID =  user_ID;";
			statement = connect.getConnection().prepareStatement(query);
			rs = statement.executeQuery();
			while (rs.next()) {

				pat = new Patient(rs.getInt("user_ID"), rs.getString("username"),
						rs.getString("email"), rs.getString("password"),
						rs.getString("lastname"), rs.getString("firstname"),
						rs.getString("type"), rs.getInt("patientID"),
						rs.getString("street"), rs.getString("city"));
				pList.add(pat);
			}

		} catch (SQLException e) {
			System.out.println("ERROR in getting all Patient");
			e.printStackTrace();
		}
		connect.close();

		return pList.iterator();
	}

	@Override
	public void insertData(Object obj) {

		Patient pat = (Patient) obj;
		try {

			String query = "INSERT INTO patient VALUES(NULL,?,?, (SELECT userID from user WHERE username = ?));";
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, pat.getStreet());
			statement.setString(2, pat.getCity());
			statement.setString(3, pat.getUsername());
			statement.execute();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} 
		connect.close();
	}

	@Override
	public void updateData(Object obj) {
		Patient p = (Patient)obj;
		String query = "UPDATE patient "
					 + "SET  street = ?, city = ?"
				     + "WHERE patientID = ?";
		try 
		{
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, p.getStreet());
			statement.setString(2, p.getCity());
                        statement.setInt(3, p.getPatientID());
			if(statement.execute())
			{
				System.out.println("UPDATED Patinet");
				connect.close();
			}
		
		} catch (SQLException e) {
	
			System.out.println("Update Error");
			e.printStackTrace();
		}
		connect.close();
	}

	public Patient getData(Object user) {
		String username = (String) user;
		Patient pat = null;
		try {
			String query = "SELECT * "
					+ "FROM user, patient "
					+ "WHERE user_ID = userID AND username = ?";
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, username);
			rs = statement.executeQuery();
			if (rs.next()) {
				pat = new Patient(rs.getInt("user_ID"), rs.getString("username"),
						rs.getString("email"), rs.getString("password"),
						rs.getString("lastname"), rs.getString("firstname"),
						rs.getString("type"), rs.getInt("patientID"),
						rs.getString("street"), rs.getString("city"));
				
			}

		} catch (SQLException e) {
			System.out.println("ERROR in getting all users from DB");
			e.printStackTrace();
		}
		connect.close();
		return pat;
	}

	public Patient getPatientByID(int patientID) {
	
		Patient pat = null;
		try {
			String query = "SELECT * "
					+ "FROM user, patient "
					+ "WHERE user_ID = userID AND patientID = ?";
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, patientID);
			rs = statement.executeQuery();
			if (rs.next()) {
				pat = new Patient(rs.getInt("user_ID"), rs.getString("username"),
						rs.getString("email"), rs.getString("password"),
						rs.getString("lastname"), rs.getString("firstname"),
						rs.getString("type"), rs.getInt("patientID"),
						rs.getString("street"), rs.getString("city"));
				
			}

		} catch (SQLException e) {
			System.out.println("ERROR in getting all users from DB");
			e.printStackTrace();
		}
		connect.close();
		return pat;
	}
}
