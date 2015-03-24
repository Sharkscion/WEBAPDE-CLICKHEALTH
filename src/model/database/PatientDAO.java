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
    private ArrayList<Patient> patients;

    private static PatientDAO pD = null;

    public static synchronized PatientDAO getInstance() {
        if (pD == null) {
            pD = new PatientDAO();
        }
        return pD;
    }

    @Override
    public Iterator getAllData() {
        Connection con = connect.getConnection();
        Patient pat;
        patients = new ArrayList<Patient>();
        try {
            String query = "SELECT * FROM patient";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String query2 = "SELECT * FROM user WHERE userID = \"" + resultSet.getInt("user_ID") + "\"";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                if (resultSet2.next()) {
                    pat = new Patient(resultSet.getInt("user_ID"), resultSet2.getString("username"),
                            resultSet2.getString("email"), resultSet2.getString("password"),
                            resultSet2.getString("lastname"), resultSet2.getString("firstname"),
                            resultSet2.getString("type"), resultSet.getInt("patientID"),
                            resultSet.getString("street"), resultSet.getString("city"));
                    patients.add(pat);
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

        return patients.iterator();
    }

   
    @Override
    public void insertData(Object obj) {
        Connection con = connect.getConnection();
        Patient pat = (Patient) obj;
        try {

            String query = "INSERT INTO patient VALUES(NULL,?,?, (SELECT userID from user WHERE username = ?));";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            //preparedStatement.setInt(1, pat.getPatientID());
            preparedStatement.setString(1, pat.getStreet());
            preparedStatement.setString(2, pat.getCity());
            preparedStatement.setString(3, pat.getUsername());
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
        Patient pat;
        patients = new ArrayList<Patient>();
        try {
            String query = "SELECT * FROM patient";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String query2 = "SELECT * FROM user WHERE userID = \"" + resultSet.getInt("user_ID") + "\"";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                if (resultSet2.next()) {
                    pat = new Patient(resultSet.getInt("user_ID"), resultSet2.getString("username"),
                            resultSet2.getString("email"), resultSet2.getString("password"),
                            resultSet2.getString("lastname"), resultSet2.getString("firstname"),
                            resultSet2.getString("type"), resultSet.getInt("patientID"),
                            resultSet.getString("street"), resultSet.getString("city"));
                    try {
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException sqlee) {
                        sqlee.printStackTrace();
                    }
                    return pat;
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
