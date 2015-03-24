package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import model.DoctorSchedule;
import model.Patient;

public class DoctorScheduleDAO implements DAOInterface {

	private DBConnection connect = DBConnection.getInstance();

	   private static DoctorScheduleDAO dD = null;

	    public static synchronized DoctorScheduleDAO getInstance() {
	        if (dD == null) {
	            dD = new DoctorScheduleDAO();
	        }
	        return dD;
	    }
	    
	@Override
	public Iterator getAllData() {
		// TODO Auto-generated method stub
		return null;
	}

	 @SuppressWarnings("deprecation")
	@Override
	    public void insertData(Object obj) {
	        Connection con = connect.getConnection();
	        DoctorSchedule ds = (DoctorSchedule) obj;
	        try {

	            String query = "INSERT INTO doctorschedule VALUES(NULL,?,?,?,?,?, ?";
	            PreparedStatement preparedStatement = con.prepareStatement(query);
	            //preparedStatement.setInt(1, pat.getPatientID());
	            preparedStatement.setString(1, ds.getScheduleDate());
	            preparedStatement.setTime(2, new java.sql.Time(ds.getStartTime().getHour(), ds.getStartTime().getMinute(), ds.getStartTime().getSecond()));

	            preparedStatement.setTime(3, new java.sql.Time(ds.getEndTime().getHour(), ds.getEndTime().getMinute(), ds.getEndTime().getSecond()));
	            //preparedStatement.setTime(3, ds.getEndTime());
	            preparedStatement.setInt(4, ds.isAvailable());
	            preparedStatement.setInt(5, ds.getDoctorScheduleID());
	            preparedStatement.setInt(6, ds.getHospitalScheduleID());
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

}
