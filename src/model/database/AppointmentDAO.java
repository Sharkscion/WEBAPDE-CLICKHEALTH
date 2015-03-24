package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import model.Appointment;
import model.Doctor;
import model.Hospital;

public class AppointmentDAO implements DAOInterface{

	  private DBConnection connect = DBConnection.getInstance();
	    private ArrayList<Appointment> appointments;

	    private static AppointmentDAO dD = null;

    public static synchronized AppointmentDAO getInstance() {
        if (dD == null) {
            dD = new AppointmentDAO();
        }
        return dD;
    }

	@Override
	public Iterator getAllData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertData(Object obj) {
        Connection con = connect.getConnection();
        Appointment app = (Appointment) obj;
        try {

            String query = "INSERT INTO appointments VALUES(NULL,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, app.getStatus());
            preparedStatement.setString(2, app.getConcern());
            preparedStatement.setTime(3, app.getStartTime());
            preparedStatement.setDate(4, app.getAppointmentDate());
            preparedStatement.setInt(5, app.getPatientID());
            preparedStatement.setInt(6, app.getDoctorID());
            preparedStatement.setInt(7, app.getHospitalID());
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

	@Override
	public void updateData(Object obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object getData(String keyID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Iterator getUserAppointments(int keyID) 
	{
		 System.out.println("APPOINTMENTS DAO");
		 ArrayList<Appointment> aList = null;
		 Appointment a =  null;
		  try {
	            String query = "SELECT * FROM appointments WHERE patient_ID = ? AND status = \"pending\";";
	            PreparedStatement statement = (PreparedStatement) connect.getConnection().prepareStatement(query);
	            statement.setInt(1, keyID);
	            ResultSet rs = statement.executeQuery();
	           
	            aList = new ArrayList<Appointment>();
	            while (rs.next()) {

	                 a = new Appointment(rs.getInt("appointmentsID"),  rs.getString("status"), rs.getString("concern"), 
	                					 rs.getTime("startTime"), rs.getDate("appointmentDate"),rs.getInt("patient_ID"), 
	                					 rs.getInt("doctor_ID"), rs.getInt("hospital_ID"));
	                 aList.add(a);
	            }

	        } catch (SQLException e) {
	            System.out.println("ERROR in getting all users from DB");
	            e.printStackTrace();
	        }
	        connect.close();
	        return aList.iterator();
	}
	
	public Iterator<Appointment> getDoctorAppointments(String username)
	{
		 Connection con = connect.getConnection();
	        appointments = new ArrayList<Appointment>();
	         try 
	         {
	             String query = "SELECT * FROM appointments WHERE doctor_ID = (SELECT licenseID FROM doctor WHERE user_ID = (SELECT userID FROM user where username = \""+ username + "\"));";
	             PreparedStatement preparedStatement = con.prepareStatement(query);
	             ResultSet rs = preparedStatement.executeQuery();
	             while (rs.next()) 
	             {
	            	 	
	            	 	Appointment app = new Appointment(rs.getInt("appointmentsID"),  rs.getString("status"), rs.getString("concern"), 
	            	 									  rs.getTime("startTime"), rs.getDate("appointmentDate"),rs.getInt("patient_ID"), 
	            	 									  rs.getInt("doctor_ID"), rs.getInt("hospital_ID"));
	                     appointments.add(app);
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

	         return appointments.iterator();
	}

}
