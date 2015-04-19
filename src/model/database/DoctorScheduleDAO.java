package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import model.Doctor;
import model.DoctorSchedule;
import model.Hospital;
import model.Patient;

public class DoctorScheduleDAO implements DAOInterface {

    private DBConnection connect = DBConnection.getInstance();
    private PreparedStatement statement;
    private ResultSet rs;
    private ArrayList<DoctorSchedule> sList = null;

    private static DoctorScheduleDAO dD = null;

    public static synchronized DoctorScheduleDAO getInstance() {
        if (dD == null) {
            dD = new DoctorScheduleDAO();
        }
        return dD;
    }

    @Override
    public Iterator<DoctorSchedule> getAllData() {

        try {
            sList = new ArrayList<DoctorSchedule>();
            DoctorSchedule ds = null;

            String query = "SELECT ds.scheduleID, ds.scheduleDay, ds.startTime, ds.endTime, ds.doctorScheduleID, ds.hospitalScheduleID "
                    + "FROM doctorschedule ds "
                    + "INNER JOIN doctor d "
                    + "ON d.licenseID = ds.doctorScheduleID "
                    + "INNER JOIN user u "
                    + "ON u.userID = d.user_ID "
                    + "ORDER BY u.lastname;";
            statement = connect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            while (rs.next()) {
                ds = new DoctorSchedule(rs.getInt("scheduleID"), rs.getString("scheduleDay"),
                        rs.getTime("startTime"), rs.getTime("endTime"), rs.getInt("doctorScheduleID"),
                        rs.getInt("hospitalScheduleID"));

                sList.add(ds);
            }
        } catch (SQLException e) {
            System.out.println("Unable to get all scheudles");
            e.printStackTrace();
        }
        connect.close();

        return sList.iterator();
    }

    @Override
    public boolean insertData(Object obj) {

        DoctorSchedule ds = (DoctorSchedule) obj;
        try {

            String query = "INSERT INTO doctorschedule VALUES(NULL,?,?,?,?,?)";
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, ds.getScheduleDay());
            statement.setTime(2, ds.getStartTime());
            statement.setTime(3, ds.getEndTime());
            statement.setInt(4, ds.getDoctorScheduleID());
            statement.setInt(5, ds.getHospitalScheduleID());
            statement.execute();
            connect.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Unable to get all scheudles");
            e.printStackTrace();
        }
        connect.close();
        return false;
    }

    @Override
    public boolean updateData(Object obj) {
        DoctorSchedule d = (DoctorSchedule) obj;
        String query = "UPDATE doctorschedule "
                + "SET  scheduleDay = ?, startTime = ?, endTime = ?"
                + "WHERE scheduleID = ?";
        try {
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, d.getScheduleDay());
            statement.setTime(2, d.getStartTime());
            statement.setTime(3, d.getEndTime());
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

    public Iterator<DoctorSchedule> getDoctorsSchedules(int licenseID, int hospitalID) {
        sList = new ArrayList<DoctorSchedule>();
        try {
            String query = "SELECT * "
                    + "FROM doctorschedule "
                    + "WHERE doctorScheduleID = ? "
                    + "AND hospitalScheduleID = ?;";
            statement = connect.getConnection().prepareStatement(query);
            statement.setInt(1, licenseID);
            statement.setInt(2, hospitalID);
            rs = statement.executeQuery();
            while (rs.next()) {
                DoctorSchedule ds = new DoctorSchedule(rs.getInt("scheduleID"), rs.getString("scheduleDay"), rs.getTime("startTime"),
                        rs.getTime("endTime"), rs.getInt("doctorScheduleID"),
                        rs.getInt("hospitalScheduleID"));
                sList.add(ds);
            }

        } catch (SQLException e) {

            System.out.println("Update Error");
            e.printStackTrace();
        }
        connect.close();
        return sList.iterator();
    }

    @Override
    public DoctorSchedule getData(Object schedID) {

        int scheduleID = (Integer) schedID;
        DoctorSchedule ds = null;
        try {
            String query = "SELECT * "
                    + "FROM doctorschedule "
                    + "WHERE scheduleID = ?";
            statement = connect.getConnection().prepareStatement(query);
            statement.setInt(1, scheduleID);
            rs = statement.executeQuery();
            if (rs.next()) {
                ds = new DoctorSchedule(rs.getInt("scheduleID"), rs.getString("scheduleDay"), rs.getTime("startTime"),
                        rs.getTime("endTime"), rs.getInt("doctorScheduleID"),
                        rs.getInt("hospitalScheduleID"));

            }

        } catch (SQLException e) {
            System.out.println("ERROR in getting all users from DB");
            e.printStackTrace();
        }
        connect.close();
        return ds;
    }

    public Iterator<DoctorSchedule> getSpecializationHospitalDoctorScheds(String specialization, int hospitalID) {
        try {
            sList = new ArrayList<DoctorSchedule>();
            DoctorSchedule ds = null;

            String query = "SELECT ds.scheduleID, ds.scheduleDay, ds.startTime, ds.endTime, ds.doctorScheduleID, ds.hospitalScheduleID "
                    + "FROM doctorschedule ds "
                    + "INNER JOIN hospital h "
                    + "ON ds.hospitalScheduleID = h.hospitalID "
                    + "INNER JOIN doctor d "
                    + "ON d.licenseID = ds.doctorScheduleID "
                    + "INNER JOIN user u "
                    + "ON u.userID = d.user_ID "
                    + "WHERE d.specialization LIKE '%" + specialization + "%' "
                    + "AND h.hospitalID = " + hospitalID + " "
                    + "ORDER BY u.lastname;";
            statement = connect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            while (rs.next()) {
                System.out.println("CSHEDLE ID DAO: " + rs.getInt("scheduleID"));
                ds = new DoctorSchedule(rs.getInt("scheduleID"), rs.getString("scheduleDay"),
                        rs.getTime("startTime"), rs.getTime("endTime"), rs.getInt("doctorScheduleID"),
                        rs.getInt("hospitalScheduleID"));
                System.out.println("SCHEDULE DAU: " + ds.getScheduleID());
                sList.add(ds);
            }
        } catch (SQLException e) {
            System.out.println("Unable to get all schedules");
            e.printStackTrace();
        }
        connect.close();

        return sList.iterator();
    }

    public boolean checkDay(String day, int doctor) {
        String dayofweek = day;
        int licenseID = (Integer) doctor;
        DoctorSchedule ds = null;
        try {
            String query = "SELECT * "
                    + "FROM doctorschedule "
                    + "WHERE scheduleDay = ? AND doctorScheduleID = ?";
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, dayofweek);
            statement.setInt(2, licenseID);
            rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("ERROR in getting all users from DB");
            e.printStackTrace();
        }
        connect.close();
        return false;
    }

    public DoctorSchedule getDoctorScheduleByDay(String day, int doctor) {
        String dayofweek = day;
        int licenseID = (Integer) doctor;
        DoctorSchedule ds = null;
        try {
            String query = "SELECT * "
                    + "FROM doctorschedule "
                    + "WHERE scheduleDay = ? AND doctorScheduleID = ?";
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, dayofweek);
            statement.setInt(2, licenseID);
            rs = statement.executeQuery();
            if (rs.next()) {
                ds = new DoctorSchedule(rs.getInt("scheduleID"), rs.getString("scheduleDay"), rs.getTime("startTime"),
                        rs.getTime("endTime"), rs.getInt("doctorScheduleID"),
                        rs.getInt("hospitalScheduleID"));

            }

        } catch (SQLException e) {
            System.out.println("ERROR in getting all users from DB");
            e.printStackTrace();
        }
        connect.close();
        return ds;
    }

    public Iterator<DoctorSchedule> getSchedules(int licenseid) {
        try {
            sList = new ArrayList<DoctorSchedule>();
            DoctorSchedule ds = null;

            String query = "SELECT *  "
                    + "FROM doctorschedule ds WHERE ds.doctorScheduleID = ?";
            statement = connect.getConnection().prepareStatement(query);
            statement.setInt(1, licenseid);
            rs = statement.executeQuery();

            while (rs.next()) {
                ds = new DoctorSchedule(rs.getInt("scheduleID"), rs.getString("scheduleDay"),
                        rs.getTime("startTime"), rs.getTime("endTime"), rs.getInt("doctorScheduleID"),
                        rs.getInt("hospitalScheduleID"));
                sList.add(ds);
            }
        } catch (SQLException e) {
            System.out.println("Unable to get all schedules");
            e.printStackTrace();
        }
        connect.close();

        return sList.iterator();
    }

    public void deleteAll(int licenseid) {
        try {

            String query = "DELETE FROM doctorschedule\n"
                    + "WHERE doctorScheduleID = ?";
            statement = connect.getConnection().prepareStatement(query);
            statement.setInt(1, licenseid);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Unable to get all schedules");
            e.printStackTrace();
        }
        connect.close();
    }

}
