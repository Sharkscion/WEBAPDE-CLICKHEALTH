package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import model.DoctorSchedule;
import model.Hospital;
import model.Patient;

public class HospitalDAO implements DAOInterface {

	private DBConnection connect = DBConnection.getInstance();
	private PreparedStatement statement;
	private ResultSet rs;
	private ArrayList<Hospital> hList = null;

	private static HospitalDAO hD = null;

	public static synchronized HospitalDAO getInstance() {
		if (hD == null) {
			hD = new HospitalDAO();

		}

		return hD;
	}

	@Override
	public Iterator<Hospital> getAllData() 
	{
		Hospital hosp = null;
		hList = new ArrayList<Hospital>();

		try 
		{
			String query = "SELECT * "
					+ "FROM hospital "
					+ "ORDER BY hospitalname;";
					statement = connect.getConnection().prepareStatement(query);
					rs = statement.executeQuery();

					while (rs.next()) {
						hosp = new Hospital(rs.getInt("hospitalID"), rs.getString("hospitalName"),
								rs.getString("hospitalStreet"), rs.getString("hospitalCity"));

						hList.add(hosp);
					}
		} catch (SQLException e) {
			System.out.println("ERROR in getting all Doctors");
			e.printStackTrace();
		}
		connect.close();

		return hList.iterator();
	}

	@Override
	public boolean insertData(Object obj) 
	{
		Hospital hosp = (Hospital) obj;
		try {

			String query = "INSERT INTO hospital VALUES(NULL,?,?,?);";
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, hosp.getName());
			statement.setString(2, hosp.getStreet());
			statement.setString(3, hosp.getCity());
			statement.execute();
			connect.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Unable to INSERT new hosptials");
			e.printStackTrace();
		}
		connect.close();
		return false;
	}

    public Hospital getHospitalByID(int hospID)
    {
    	Hospital hosp = null;
          try 
          {
              String query = "SELECT * "
              			+ "FROM hospital "
              			+ "WHERE hospitalID = ?";
              statement = connect.getConnection().prepareStatement(query);
              statement.setInt(1, hospID);
              rs = statement.executeQuery();
              if (rs.next()) 
            		hosp = new Hospital(rs.getInt("hospitalID"), rs.getString("hospitalName"),
    						rs.getString("hospitalStreet"), rs.getString("hospitalCity"));
                  
          }
  		catch (SQLException e)
  		{
  			System.out.println("Unable to gethospital ID");
  			e.printStackTrace();
  		}
  		connect.close();
        return hosp;
    }

	@Override
	public boolean updateData(Object obj) {
		Hospital h = (Hospital)obj;
		String query = "UPDATE hospital"
					 + "SET  hospitalName = ?, hospitalCity = ?, hospitalStreet = ?"
				     + "WHERE hospitalID = ?";
		try 
		{
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, h.getName());
			statement.setString(2, h.getCity());
			statement.setString(3, h.getStreet());
			statement.setInt(4, h.getHospID());
			statement.execute();
			return true;
		
		} catch (SQLException e) {
	
			System.out.println("Update Error");
			e.printStackTrace();
		}
		connect.close();
		return false;
	}

	@Override
	public Hospital getData(Object hospitalName) 
	{
		Hospital hosp = null;
		String hName = (String) hospitalName;
		try 
		{
			String query = "SELECT * "
					+ "FROM hospital "
					+ "WHERE hospitalName = ?";
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, hName);
			rs = statement.executeQuery();
			if (rs.next()) {
				hosp = new Hospital(rs.getInt("hospitalID"), rs.getString("hospitalName"),
						rs.getString("hospitalStreet"), rs.getString("hospitalCity"));
				
			}
		}
		catch (SQLException e)
		{
			System.out.println("Unable to get hospital");
			e.printStackTrace();
		}
		connect.close();
		return hosp;
	}

	public Iterator<Hospital> getSpecializationHospitals(String specialization)
	{
		hList = new ArrayList<Hospital>();
		try {
			String query = "SELECT * "
					+ "FROM Hospital "
					+ "WHERE hospitalid IN (SELECT hospitalscheduleID FrOm doctorschedule WHERE doctorScheduleID "
					+ "IN (SELECT licenseID FROM doctor WHERE specialization LIKE \"%?%\"));";
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, specialization);
			rs = statement.executeQuery();
			while (rs.next()) {

				Hospital hosp = new Hospital(rs.getInt("hospitalID"), rs.getString("hospitalName"),
						rs.getString("hospitalStreet"), rs.getString("hospitalCity"));	            	 
				hList.add(hosp);
			}

		}
		catch (SQLException e)
		{
			System.out.println("Unable to get all hospital");
			e.printStackTrace();
		}
		connect.close();

		return hList.iterator();


	}

}
