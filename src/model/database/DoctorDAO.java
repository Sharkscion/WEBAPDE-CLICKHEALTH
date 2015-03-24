package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import model.Doctor;
import model.Hospital;
import model.Patient;
import model.User;

public class DoctorDAO implements DAOInterface {

    private DBConnection connect = DBConnection.getInstance();
    private ArrayList<Doctor> doctors;

    private static DoctorDAO dD = null;

    public static synchronized DoctorDAO getInstance() {
        if (dD == null) {
            dD = new DoctorDAO();
        }
        return dD;
    }

    public int getLicenseID(String doctorName)
    {

    	  Connection con = connect.getConnection();
          int i = 0;
          try 
          {
              String query = "SELECT licenseID FROM user, doctor WHERE username = ?";
              PreparedStatement preparedStatement = con.prepareStatement(query);
              preparedStatement.setString(1, doctorName);
              ResultSet resultSet = preparedStatement.executeQuery();
              if (resultSet.next()) 
            	  i = resultSet.getInt("licenseID");
                  
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
    public Iterator getAllData() {
        Connection con = connect.getConnection();
        Doctor doc;
        doctors = new ArrayList<Doctor>();
        try {
            String query = "SELECT * FROM doctor";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String query2 = "SELECT * FROM user WHERE userID = \"" + resultSet.getInt("user_ID") + "\"";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                if (resultSet2.next()) {
                	doc = new Doctor(resultSet.getInt(3), resultSet2.getString(2),
                            resultSet2.getString(3), resultSet2.getString(4),
                            resultSet2.getString(5), resultSet2.getString(6),
                            resultSet2.getString(7), resultSet.getInt(1),
                            resultSet.getString(2));
                    doctors.add(doc);
                }
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

        return doctors.iterator();
    }

    @Override
    public void insertData(Object obj) {
        Connection con = connect.getConnection();
        Doctor doc = (Doctor) obj;
        try {

            String query = "INSERT INTO doctor VALUES(?,?,(SELECT userID from user WHERE username = ?));";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            //preparedStatement.setInt(1, pat.getPatientID());
            preparedStatement.setInt(1, doc.getLicenseID() );
            preparedStatement.setString(2, doc.getSpecialization());
            preparedStatement.setString(3, doc.getUsername());
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
    
    public Object getAppointmentDoctor(int keyID) 
    {
        Connection con = connect.getConnection();
        System.out.println("DOCTOR DAO");
        Doctor doc;
        try {
            String query = "SELECT * FROM user u, doctor d WHERE u.userID = d.user_ID AND d.licenseID = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, keyID);
            ResultSet resultSet = preparedStatement.executeQuery();
      
        if (resultSet.next()) {
            doc = new Doctor(resultSet.getInt("user_ID"), resultSet.getString("username"),
                    resultSet.getString("email"), resultSet.getString("password"),
                    resultSet.getString("lastname"), resultSet.getString("firstname"),
                    resultSet.getString("type"), resultSet.getInt("licenseID"),
                    resultSet.getString("specialization"));
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
            return doc;
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
    public Object getData(int keyID) {
        Connection con = connect.getConnection();
        Doctor doc;
        System.out.println("In DB key == " + keyID);
        try {
            String query = "SELECT * FROM doctor WHERE user_ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, keyID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String query2 = "SELECT * FROM user WHERE userID = ?";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                preparedStatement2.setInt(1,resultSet.getInt(3));
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                if (resultSet2.next()) {
                    /*(int userID, String username, String email, String password,
                			String lastname, String firstname, String type, int licenseID, String specialization)*/

                	
                    doc = new Doctor(resultSet.getInt(3), resultSet2.getString(2),
                            resultSet2.getString(3), resultSet2.getString(4),
                            resultSet2.getString(5), resultSet2.getString(6),
                            resultSet2.getString(7), resultSet.getInt(1),
                            resultSet.getString(2));
                    try {
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sqlee) {
                        sqlee.printStackTrace();
                    }
                    return doc;
                }
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

    public Iterator<String> getSpecializations()
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

         return specs.iterator();
    	
    	/*ArrayList<String> specializations = new ArrayList<String>();
    	Iterator docs = getAllData();
    	
    	while(docs.hasNext())
    	{
    		Doctor doctor = (Doctor) docs.next();
    		if(!specializations.contains(doctor.getSpecialization()))
    			specializations.add(doctor.getSpecialization());
    	}
    	
    	return specializations.iterator();*/
    }

	@Override
	public Object getData(String keyID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public Iterator<String> getSpecializations(String specialization)
    {
    	Connection con = connect.getConnection();
        String spec;
        ArrayList<String> specs = new ArrayList<String>();
         try {
             String query = "SELECT DISTINCT(specialization) FROM doctor WHERE specialization Like \"%" + specialization + "%\"";
             PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()) {
            	 	System.out.println(resultSet.getString(1));
                     specs.add(resultSet.getString(1));
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

         return specs.iterator();
    }

	 public Iterator<Doctor> getSpecializationHospitalDoctors(String specialization, int hospitalScheduleID)
	 {
		 Connection con = connect.getConnection();
	        doctors = new ArrayList<Doctor>();
	         try {
	             String query = "SELECT * FROM doctor d, user u WHERE d.user_ID = u.userID AND d.specialization " + 
	            		 "IN (SELECT specialization FROM doctor WHERE specialization  LIKE \"%" + specialization + "%\")" +
	            		 " AND d.licenseID IN (SELECT doctorScheduleID from doctorschedule WHERE hospitalScheduleID = ?);";
	             PreparedStatement preparedStatement = con.prepareStatement(query);
	             preparedStatement.setInt(1, hospitalScheduleID);
	             ResultSet resultSet = preparedStatement.executeQuery();
	             while (resultSet.next()) {
	            	 	
	            	 	Doctor doc = new Doctor(resultSet.getInt(3), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getInt(1), resultSet.getString(2));
	            	 	doctors.add(doc);
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

	         return doctors.iterator();
	 }
	 
}
