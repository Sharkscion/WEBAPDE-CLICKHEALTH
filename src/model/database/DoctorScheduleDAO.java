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

public class DoctorScheduleDAO implements DAOInterface {

	private DBConnection connect = DBConnection.getInstance();
	private PreparedStatement statement;
	private ResultSet rs;
	private ArrayList<DoctorSchedule> sList = null;

	private static DoctorScheduleDAO dD = null;

	public static synchronized DoctorScheduleDAO getInstance() {
		if (dD == null) {
			dD = new DoctorScheduleDAO();
		}
		return dD;
	}

	@Override
	public Iterator<DoctorSchedule> getAllData() {

		try 
		{
			sList = new ArrayList<DoctorSchedule>();
			DoctorSchedule ds = null;

			String query = "SELECT ds.scheduleID, ds.scheduleDay, ds.startTime, ds.endTime, ds.doctorScheduleID, ds.hospitalScheduleID " 
					+ "FROM doctorschedule ds "
					+ "INNER JOIN doctor d "
					+ "ON d.licenseID = ds.doctorScheduleID "
					+ "INNER JOIN user u "
					+ "ON u.userID = d.user_ID "
					+ "ORDER BY u.lastname;";
			statement = connect.getConnection().prepareStatement(query);
			rs = statement.executeQuery();

			while (rs.next()) {
				ds = new DoctorSchedule(rs.getInt("scheduleID"), rs.getString("scheduleDay"), 
										rs.getTime("startTime"), rs.getTime("endTime"), rs.getInt("doctorScheduleID"), 
										rs.getInt("hospitalScheduleID"));

				sList.add(ds);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Unable to get all scheudles");
			e.printStackTrace();
		}
		connect.close();

		return  sList.iterator();
	}

	@Override
	public boolean insertData(Object obj) {
		
		DoctorSchedule ds = (DoctorSchedule) obj;
		try {

			String query = "INSERT INTO doctorschedule VALUES(NULL,?,?,?,?,?)";
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, ds.getScheduleDay());
			statement.setTime(2, ds.getStartTime());
			statement.setTime(3, ds.getEndTime());
			statement.setInt(4, ds.getDoctorScheduleID());
			statement.setInt(5, ds.getHospitalScheduleID());
			statement.execute();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Unable to get all scheudles");
			e.printStackTrace();
		}
		connect.close();
		return false;
	}

	@Override
	public boolean updateData(Object obj) {
		DoctorSchedule d = (DoctorSchedule)obj;
		String query = "UPDATE doctorschedule "
					 + "SET  scheduleDay = ?, startTime = ?, endTime = ?"
				     + "WHERE scheduleID = ?";
		try 
		{
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, d.getScheduleDay());
			statement.setTime(2, d.getStartTime());
			statement.setTime(3, d.getEndTime());
		
			if(statement.execute())
			{
				System.out.println("UPDATED DoctorScehdule");
				connect.close();
				return true;
			}
		
		} catch (SQLException e) {
	
			System.out.println("Update Error");
			e.printStackTrace();
		}
		connect.close();
		return false;
	}

	public Iterator<DoctorSchedule> getDoctorsSchedules(int licenseID, int hospitalID)
	{
		sList= new ArrayList<DoctorSchedule>();
		try 
		{
			String query = "SELECT * "
					+ "FROM doctorschedule "
					+ "WHERE doctorScheduleID = ? "
					+ "AND hospitalScheduleID = ?;";
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, licenseID);
			statement.setInt(2, hospitalID);
			rs = statement.executeQuery();
			while (rs.next()) 
			{
				DoctorSchedule ds = new DoctorSchedule(rs.getInt("scheduleID"), rs.getString("scheduleDay"), rs.getTime("startTime"), 
									rs.getTime("endTime"),rs.getInt("doctorScheduleID"), 
									rs.getInt("hospitalScheduleID"));
				sList.add(ds);
			}

		} catch (SQLException e) {
			
			System.out.println("Update Error");
			e.printStackTrace();
		}
		connect.close();
		return sList.iterator();
	}
	
	@Override
	public DoctorSchedule getData(Object schedID) {
		
		int scheduleID = (Integer) schedID;
		DoctorSchedule ds = null;
		try {
			String query = "SELECT * "
					+ "FROM doctorschedule "
					+ "WHERE scheduleID = ?";
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, scheduleID);
			rs = statement.executeQuery();
			if (rs.next()) {
				ds  = new DoctorSchedule(rs.getInt("scheduleID"), rs.getString("scheduleDay"), rs.getTime("startTime"), 
							rs.getTime("endTime"),rs.getInt("doctorScheduleID"), 
							rs.getInt("hospitalScheduleID"));

				
			}

		} catch (SQLException e) {
			System.out.println("ERROR in getting all users from DB");
			e.printStackTrace();
		}
		connect.close();
		return ds;
	}

}
