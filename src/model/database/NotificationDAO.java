package model.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import model.Notification;



public class NotificationDAO implements DAOInterface {

	private DBConnection connect = DBConnection.getInstance();
	private PreparedStatement statement;
	private ResultSet rs;
	private ArrayList<Notification> nList = null;

	private static NotificationDAO nD = null;

	public static synchronized NotificationDAO getInstance() {
		if (nD == null) {
			nD = new NotificationDAO();
		}
		return nD;
	}

	@Override
	public Iterator<Notification> getAllData() {

		try {
			String query = "SELECT  "
							+ "FROM notification";
			statement = connect.getConnection().prepareStatement(query);
			rs = statement.executeQuery();
			Notification n = null;

			nList = new ArrayList<Notification>();
			while (rs.next()) 
			{
				n = new Notification(rs.getInt("notificationID"), rs.getInt("appID"), rs.getString("notificationContent"),
								     rs.getDate("notifDate"), rs.getTime("notifTime"),rs.getInt("isViewed"), rs.getInt("isRejected"));
				nList.add(n);

			}

		} catch (SQLException e) {
			System.out.println("ERROR in getting all Doctors");
			e.printStackTrace();
		}
		connect.close();
		return nList.iterator();
	}

	@Override
	public boolean insertData(Object obj) {

		Notification n = (Notification) obj;
		try {

			String query = "INSERT INTO notification VALUES(NULL, ?, ?, ?, ?, ?, ?);";
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, n.getNotifID());
			statement.setInt(2,  n.getAppID());
			statement.setString(3, n.getNotifContent());
			statement.setDate(4, new Date(n.getNotifDate().getTime()));
			statement.setTime(5, n.getNotifTime());
			statement.setInt(6, n.getIsViewed());
			statement.setInt(7, n.getIsRejected());
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
		Notification n = (Notification)obj;
		String query = "UPDATE Notification "
					 + "SET  isViewed = ? "
				     + "WHERE notificationID = ? ";
		try 
		{
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, n.getIsViewed());
			statement.setInt(2, n.getNotifID());
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

	public Iterator<Notification> getAllNotificationNotViewed(int patientID)
	{
		Notification n = null;
		nList = new ArrayList<Notification>();
		try {
			String query = "SELECT notificationID, appID, notificationContent, notifDate, notifTime, isViewed, isRejected "
					+ "FROM notification "
					+ "INNER JOIN appointments "
					+ "ON notification.appID = appointments.appointmentsID AND appointments.patient_ID = ? and isViewed = 0 "
					+ "ORDER BY notification.notifDate DESC, notification.notifTime DESC;";
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, patientID);
			rs = statement.executeQuery();
			
			while (rs.next()) {

				n = new Notification(rs.getInt("notificationID"), rs.getInt("appID"), rs.getString("notificationContent"),
					     			 rs.getDate("notifDate"), rs.getTime("notifTime"),rs.getInt("isViewed"),rs.getInt("isRejected"));
				nList.add(n);
			}

		}
		catch (SQLException e)
		{
			System.out.println("Unable to INSERT new doctor");
			e.printStackTrace();
		}
		connect.close();
		System.out.println("NLIST SIZE: "+ nList);
		return nList.iterator();
	}

	@Override
	public Notification getData(Object key) 
	{
		int notifID = (Integer) key;
		Notification n = null;
		try 
		{
			String query = "SELECT * "
						 + "FROM notification "
						 + "WHERE notificationID = ?";
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, notifID);
			rs = statement.executeQuery();
			if (rs.next()) {
				 n = new Notification(rs.getInt("notificationID"), rs.getInt("appID"), rs.getString("notificationContent"),
					     rs.getDate("notifDate"), rs.getTime("notifTime"),rs.getInt("isViewed"),rs.getInt("isRejected"));
				
			}
		}
		catch (SQLException e)
		{
			System.out.println("Unable to INSERT new doctor");
			e.printStackTrace();
		}
		connect.close();
		return n;
	}
	
	public int getNotificationCount(int patientID)
	{
		int count = 0;
		try {
			String query = "SELECT COUNT(notificationID) "
					+ "FROM notification "
					+ "INNER JOIN appointments "
					+ "ON appID = appointmentsID AND patient_ID = ? AND isViewed = 0;";
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, patientID);
			rs = statement.executeQuery();
			
			if(rs.next()) {

				count = rs.getInt(1);
			}

		}
		catch (SQLException e)
		{
			System.out.println("Unable to INSERT new doctor");
			e.printStackTrace();
		}
		connect.close();

		return count;
	}
}
