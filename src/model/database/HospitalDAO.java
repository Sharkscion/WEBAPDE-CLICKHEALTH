package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import model.Hospital;
import model.Patient;

public class HospitalDAO implements DAOInterface {

    private DBConnection connect = DBConnection.getInstance();
    private ArrayList<Hospital> hospitals;

    private static HospitalDAO hD = null;

    public static synchronized HospitalDAO getInstance() {
        if (hD == null) {
            hD = new HospitalDAO();
            
        }
        
        return hD;
    }

    @Override
    public Iterator getAllData() 
    {
        Connection con = connect.getConnection();
        Hospital hosp = null;
        hospitals = new ArrayList<Hospital>();
        
        try 
        {
            String query = "SELECT * FROM hospital ORDER BY hospitalname;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                hosp = new Hospital(resultSet.getInt("hospitalID"), resultSet.getString("hospitalName"),
                        resultSet.getString("hospitalStreet"), resultSet.getString("hospitalCity"));
                System.out.println("ID == " +hosp.getID());
                System.out.println("name == " +hosp.getName());
                hospitals.add(hosp);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }

        return hospitals.iterator();
    }

    @Override
    public void insertData(Object obj) 
    {
        Connection con = connect.getConnection();
        Hospital hosp = (Hospital) obj;
        try {

            String query = "INSERT INTO hospital VALUES(?,?,?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, hosp.getID());
            preparedStatement.setString(2, hosp.getName());
            preparedStatement.setString(3, hosp.getStreet());
            preparedStatement.setString(4, hosp.getCity());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
    }

    public int getHospitalID(String username)
    {
    	  Connection con = connect.getConnection();
          int i = 0;
          try 
          {
              String query = "SELECT hospitalID FROM hospital WHERE hospitalName = ?";
              PreparedStatement preparedStatement = con.prepareStatement(query);
              preparedStatement.setString(1, username);
              ResultSet resultSet = preparedStatement.executeQuery();
              if (resultSet.next()) 
            	  i = resultSet.getInt("hospitalID");
                  
          } catch (SQLException sqlException) {
              sqlException.printStackTrace();
          } finally {
              try {
                  if (con != null) {
                      con.close();
                  }
              } catch (SQLException sqlee) {
                  sqlee.printStackTrace();
              }
          }
          
          return i;
    }
    @Override
    public void updateData(Object obj) {
        // TODO Auto-generated method stub
    }

    @Override
    public Object getData(String keyID) 
    {
        Connection con = connect.getConnection();
        Hospital hosp;
        try 
        {
            String query = "SELECT * FROM hospital WHERE hospitalID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(keyID));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                hosp = new Hospital(resultSet.getInt("hospitalID"), resultSet.getString("hospitalName"),
                        resultSet.getString("hospitalStreet"), resultSet.getString("hospitalCity"));
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException sqlee) {
                    sqlee.printStackTrace();
                }
                return hosp;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }

        return null;
    }

	 public Iterator<Hospital> getSpecializationHospitals(String specialization)
	 {
		 Connection con = connect.getConnection();
	        hospitals = new ArrayList<Hospital>();
	         try {
	             String query = "SELECT * FROM Hospital WHERE hospitalid IN (SELECT hospitalscheduleID from doctorschedule " +
	            		 "WHERE doctorScheduleID IN (SELECT licenseID from doctor Where specialization LIKE \"%" + specialization + "%\"));";
	             PreparedStatement preparedStatement = con.prepareStatement(query);
	             ResultSet resultSet = preparedStatement.executeQuery();
	             while (resultSet.next()) {
	            	 	
	            	 	Hospital hosp = new Hospital(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));	            	 
	                     hospitals.add(hosp);
	                 }
	             
	         } catch (SQLException sqlException) {
	             sqlException.printStackTrace();
	         } finally {
	             try {
	                 if (con != null) {
	                     con.close();
	                 }
	             } catch (SQLException sqlee) {
	                 sqlee.printStackTrace();
	             }
	         }

	         return hospitals.iterator();
		 
		 
	 }
    
}
