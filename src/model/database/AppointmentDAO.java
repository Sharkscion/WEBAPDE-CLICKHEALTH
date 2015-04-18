package model.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import model.Appointment;
import model.Doctor;
import model.DoctorSchedule;
import model.Hospital;

public class AppointmentDAO implements DAOInterface{

	private DBConnection connect = DBConnection.getInstance();
	private PreparedStatement statement;
	private ResultSet rs;
	private ArrayList<Appointment> aList = null;
	private static AppointmentDAO dD = null;

	public static synchronized AppointmentDAO getInstance() {
		if (dD == null) {
			dD = new AppointmentDAO();
		}
		return dD;
	}
	
	@Override
	public Appointment getData(Object keyID) {
		
		Appointment a = null;
		int appID = (Integer) keyID;
		try
		{
			String query = "SELECT * FROM appointments WHERE appointmentsID = ?";
			
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, appID);
			rs = statement.executeQuery();
			
			if (rs.next())
			{
				a = new Appointment(rs.getInt("appointmentsID"), rs.getString("status"), rs.getString("concern"), rs.getString("remarks"), 
									rs.getTime("startTime"), rs.getTime("requestedTime"), rs.getDate("requestedDate"), rs.getDate("appointmentDate"), 
									rs.getInt("isResolvedPatient"), rs.getInt("isResolvedDoctor"), rs.getInt("patient_ID"), 
									rs.getInt("doctorSched_ID"));
			}
		}
		
		catch (SQLException e)
		{
			System.out.println("Unable to SELECT cinema");
			e.printStackTrace();
		}
		
		connect.close();
		return a;
	}
	
	@Override
	public Iterator<Appointment> getAllData() {

		try{
			String query = "SELECT * "
					+ "FROM appointments "
					+ "ORDER BY requestedDate DESC, requestedTime DESC";
			statement = connect.getConnection().prepareStatement(query);
			rs = statement.executeQuery();

			aList = new ArrayList<Appointment>();
			Appointment a = null;
			
			while (rs.next())
			{
				a = new Appointment(rs.getInt("appointmentsID"), rs.getString("status"), rs.getString("concern"), rs.getString("remarks"), 
									rs.getTime("startTime"), rs.getTime("requestedTime"), rs.getDate("requestedDate"), rs.getDate("appointmentDate"), 
									rs.getInt("isResolvedPatient"), rs.getInt("isResolvedDoctor"), rs.getInt("patient_ID"), 
									rs.getInt("doctorSched_ID"));
				aList.add(a);
			}

			connect.close();
			return aList.iterator();
		}
		catch (SQLException e)
		{
			System.out.println("Unable to SELECT cinema");
			e.printStackTrace();
		}

		connect.close();
		return null;
	}


	@Override
	public boolean insertData(Object obj) {
		// TODO Auto-generated method stub
		try
		{
			Appointment a = (Appointment) obj;
	
			String query = "INSERT INTO appointments values(NULL,?,?,?,?,?,?,?,?,?,?,?)";
			
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, a.getStatus());
			statement.setString(2, a.getConcern());
			statement.setString(3, a.getRemarks());
			statement.setTime(4, a.getStartTime());
			statement.setTime(5, a.getRequestedTime());
			statement.setDate(6,  new Date(a.getRequestedDate().getTime()));
			statement.setDate(7, new Date(a.getAppointmentDate().getTime()));
			statement.setInt(8, a.getIsResolvedPatient());
			statement.setInt(9, a.getIsResolvedDoctor());
			statement.setInt(10, a.getPatientID());
			statement.setInt(11, a.getDoctorSchedID());
			statement.execute();
			connect.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Unable to INSERT new appointments");
			e.printStackTrace();
		}
		connect.close();
		return false;
	}
	
	@Override
	public boolean updateData(Object obj) {

		Appointment a = (Appointment)obj;
		String query = "UPDATE appointments "
					 + "SET  status = ?, concern = ?, remarks = ?, startTime = ?, "
						  + "requestedDate = ?, appointmentDate = ?, isResolvedPatient = ?, isResolvedDoctor = ?, "
						  + "patient_ID = ?, doctorSched_ID = ? "
				     + "WHERE appointmentsID = ?";
		try 
		{
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, a.getStatus());
			statement.setString(2, a.getConcern());
			statement.setString(3, a.getRemarks());
			statement.setTime(4, a.getStartTime());
			statement.setDate(5,  new Date(a.getRequestedDate().getTime()));
			statement.setDate(6, new Date(a.getAppointmentDate().getTime()));
			statement.setInt(7, a.getIsResolvedPatient());
			statement.setInt(8, a.getIsResolvedDoctor());
			statement.setInt(9, a.getPatientID());
			statement.setInt(10, a.getDoctorSchedID());
			statement.setInt(11, a.getAppID());
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



	public Iterator<Appointment> getPatientAppointments(int patientID) 
	{
		System.out.println("APPOINTMENTS GET USER DAO");
		
		Appointment a =  null;
		
		try {
			String query = "SELECT * "
						+ "FROM appointments "
						+ "WHERE patient_ID = ? "
						+ "AND status = \"pending\" "
						+ "AND isResolvedPatient = 0 "
						+ "ORDER BY requestedDate DESC, requestedTime DESC";
			
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, patientID);
			rs = statement.executeQuery();

			aList = new ArrayList<Appointment>();
			while (rs.next()) {

				a = new Appointment(rs.getInt("appointmentsID"), rs.getString("status"), rs.getString("concern"), rs.getString("remarks"), 
									rs.getTime("startTime"), rs.getTime("requestedTime"), rs.getDate("requestedDate"), rs.getDate("appointmentDate"), 
									rs.getInt("isResolvedPatient"), rs.getInt("isResolvedDoctor"), rs.getInt("patient_ID"), 
									rs.getInt("doctorSched_ID"));
				aList.add(a);
			}

		} catch (SQLException e) {
			System.out.println("ERROR in getting all users from DB");
			e.printStackTrace();
		}
		connect.close();
		return aList.iterator();
	}

	public Iterator<Appointment> getDoctorAppointments(int licenseID)
	{
		System.out.println("APPOINTMENTS DCOTOR DAO");
		System.out.println("APPOINTMENTS DCOTOR DAO: "+ licenseID);
		Appointment a =  null;
		
		try {
			String query = "SELECT * "
					+ "FROM appointments, doctor "
					+ "WHERE licenseID = ? "
					+ "AND status = \"pending\" "
					+ "AND isResolvedDoctor = 0 "
					+ "ORDER BY requestedDate DESC, requestedTime DESC";
			
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, licenseID);
			rs = statement.executeQuery();

			aList = new ArrayList<Appointment>();
			while (rs.next()) {

				a = new Appointment(rs.getInt("appointmentsID"), rs.getString("status"), rs.getString("concern"), rs.getString("remarks"), 
									rs.getTime("startTime"), rs.getTime("requestedTime"), rs.getDate("requestedDate"), rs.getDate("appointmentDate"), 
									rs.getInt("isResolvedPatient"), rs.getInt("isResolvedDoctor"), rs.getInt("patient_ID"), 
									rs.getInt("doctorSched_ID"));
				aList.add(a);
			}

		} catch (SQLException e) {
			System.out.println("ERROR in getting doctor appointments from DB");
			e.printStackTrace();
		}
		connect.close();
		return aList.iterator();
	}
	
	public Iterator<Appointment> getRequestAppointments(int licenseID)
	{
		try 
		{
			String query = "SELECT * "
						+ "FROM appointments, doctor "
						+ "WHERE licenseID = (SELECT licenseID FROM doctorschedule WHERE licenseID = ?) "
						+ "AND appointments.status = 'request' "
						+ "AND isResolvedDoctor = 0 "
						+ "ORDER BY requestedDate DESC, requestedTime DESC";

			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, licenseID);
			rs = statement.executeQuery();
			
			Appointment a =  null;
			aList = new ArrayList<Appointment>();
			
			while (rs.next()) 
			{
				a = new Appointment(rs.getInt("appointmentsID"), rs.getString("status"), rs.getString("concern"), rs.getString("remarks"), 
								  rs.getTime("startTime"), rs.getTime("requestedTime"), rs.getDate("requestedDate"), rs.getDate("appointmentDate"), 
								  rs.getInt("isResolvedPatient"), rs.getInt("isResolvedDoctor"), rs.getInt("patient_ID"), 
								  rs.getInt("doctorSched_ID"));
				aList.add(a);
			}

		} catch (SQLException e) {
			System.out.println("ERROR in getting all request from DB");
			e.printStackTrace();
		}
		connect.close();
		return aList.iterator();
	}
	

	public boolean changeAppointmentStat(int appID, String stat)
	{
		String query = "UPDATE appointments "
					 + "SET  status = ?"
				     + "WHERE appointmentsID = ?";
		try 
		{
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, stat);
			statement.setInt(2, appID);
			statement.execute();
			connect.close();
			return true;
		
		} catch (SQLException e) {
	
			System.out.println("Update Error");
			connect.close();
			e.printStackTrace();
		}
		connect.close();
		return false;
	}


	public boolean resolvePatientAppointment(int appId)
	{
		
		String query = "UPDATE appointments "
					 + "SET isResolvedPatient = ? "
				     + "WHERE appointmentsID = ?";
		try 
		{
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, 1);
			statement.setInt(2,  appId);
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

	public boolean resolveDoctorAppointment(int appId)
	{
		
		String query = "UPDATE appointments "
					 + "SET isResolvedDoctor = ? "
				     + "WHERE appointmentsID = ?";
		try 
		{
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, 1);
			statement.setInt(2,  appId);
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


	public boolean checkDaySched(DoctorSchedule ds, String time, String date)
	{
		try
		{
			String query = "SELECT * FROM appointments WHERE startTime = ? and doctorSched_ID = ? and appointmentDate = ? and status = \"pending\"";	
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, time);
			statement.setInt(2, ds.getScheduleID());
			statement.setString(3, date);
			rs = statement.executeQuery();
			
			if (rs.next())
			{
				return false;
			}
		}
		
		catch (SQLException e)
		{
			System.out.println("Unable to SELECT cinema");
			e.printStackTrace();
		}
		
		connect.close();
		return true;
	}
}
