package model.database;

import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import model.Hospital;
import model.Patient;

public class HospitalDAO implements DAOInterface {

    private DBConnection connect;
    private ArrayList<Hospital> hospitals;

    private static HospitalDAO hD = null;

    public static synchronized HospitalDAO getInstance() {
        if (hD == null) {
            hD = new HospitalDAO();
        }
        return hD;
    }

    @Override
    public Iterator getAllData() {
        Connection con = connect.getConnection();
        Hospital hosp;
        hospitals = new ArrayList<Hospital>();
        try {
            String query = "SELECT * FROM hospital";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                hosp = new Hospital(resultSet.getInt("hospitalID"), resultSet.getString("hospitalName"),
                        resultSet.getString("hospitalStreet"), resultSet.getString("hospitalCity"));
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
    public void insertData(Object obj) {
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

    @Override
    public void updateData(Object obj) {
        // TODO Auto-generated method stub
    }

    @Override
    public Object getData(String keyID) {
        Connection con = connect.getConnection();
        Hospital hosp;
        try {
            String query = "SELECT * FROM hospital";
            PreparedStatement preparedStatement = con.prepareStatement(query);
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

}
