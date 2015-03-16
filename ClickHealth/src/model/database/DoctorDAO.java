package model.database;

import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import model.Doctor;
import model.User;

public class DoctorDAO implements DAOInterface {

    private DBConnection connect;
    private ArrayList<Doctor> doctors;

    private static DoctorDAO dD = null;

    public static synchronized DoctorDAO getInstance() {
        if (dD == null) {
            dD = new DoctorDAO();
        }
        return dD;
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
                    doc = new Doctor(resultSet.getInt("user_ID"), resultSet2.getString("username"),
                            resultSet2.getString("email"), resultSet2.getString("password"),
                            resultSet2.getString("lastname"), resultSet2.getString("firstname"),
                            resultSet2.getString("type"), resultSet.getInt("licenseID"),
                            resultSet.getString("specialization"));
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

            String query = "INSERT INTO doctor VALUES(?,?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, doc.getLicenseID());
            preparedStatement.setString(2, doc.getSpecialization());
            preparedStatement.setInt(3, doc.getUserID());
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
        Connection con = connect.getConnection();
        Doctor doc;
        try {
            String query = "SELECT * FROM doctor WHERE user_ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, keyID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String query2 = "SELECT * FROM user WHERE userID = \"" + resultSet.getInt("user_ID") + "\"";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                if (resultSet2.next()) {
                    doc = new Doctor(resultSet.getInt("user_ID"), resultSet2.getString("username"),
                            resultSet2.getString("email"), resultSet2.getString("password"),
                            resultSet2.getString("lastname"), resultSet2.getString("firstname"),
                            resultSet2.getString("type"), resultSet.getInt("licenseID"),
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

}
