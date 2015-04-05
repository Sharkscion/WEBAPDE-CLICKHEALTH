package model.database;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;

import model.*;

public class UserDAO implements DAOInterface {

    private DBConnection connect = DBConnection.getInstance();
    private ResultSet rs;
    private PreparedStatement statement;
    private ArrayList<User> uList = null;

    private static UserDAO uD = null;

    public static synchronized UserDAO getInstance() {
        if (uD == null) {
            uD = new UserDAO();
        }

        return uD;
    }

    public User validateUser(String username, String password) {

        User u = null;
        try {
            String query = "SELECT * "
                    + "FROM User "
                    + "WHERE username = ? "
                    + "AND password = ?";
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, encryptPassword(password));
            rs = statement.executeQuery();

            if (rs.next()) {
                u = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("email"), rs.getString("password"),
                        rs.getString("lastname"), rs.getString("firstname"), rs.getString("type"));
                System.out.println("HELLLOOO PASOK KA?");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        connect.close();
        return u;
    }

    @Override
    public Iterator<User> getAllData() {
        try {
            String query = "SELECT * "
                    + "FROM User;";
            statement = connect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            uList = new ArrayList<User>();
            while (rs.next()) {
                User u = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("email"), rs.getString("password"),
                        rs.getString("lastname"), rs.getString("firstname"), rs.getString("type"));
                uList.add(u);
            }

        } catch (SQLException e) {
            System.out.println("ERROR in getting all users from DB");
            e.printStackTrace();
        }
        connect.close();
        return uList.iterator();
    }

    @Override
    public boolean insertData(Object obj) {

        try {
            User u = (User) obj;
            String query = "INSERT INTO user values(NULL,?,?,?,?,?,?)";
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, u.getUsername());
            statement.setString(2, u.getEmail());
            statement.setString(3, encryptPassword(u.getPassword()));
            statement.setString(4, u.getLastname());
            statement.setString(5, u.getFirstname());
            statement.setString(6, u.getType());
            statement.execute();
            connect.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Unable to INSERT new user");
            e.printStackTrace();
        }

        connect.close();
        return false;
    }

    @Override
    public boolean updateData(Object obj) {
        User user = (User) obj;
        String query = "UPDATE user "
                + "SET  firstname = ?, lastname = ?, username=?, "
                + "email =?, password=? "
                + "WHERE userID = ?";
        try {
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getEmail());
            statement.setString(5, encryptPassword(user.getPassword()));
            statement.setInt(6, user.getUserID());
            statement.execute();
            return true;

        } catch (SQLException e) {

            System.out.println("Update Error");
            e.printStackTrace();
        }
        connect.close();
        return false;
    }

    public User getUserInstance(String key) {
        User user = null;
        try {
            String query = "SELECT * FROM User WHERE UserID =?";
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, key);
            rs = statement.executeQuery();

            while (rs.next()) {
                user = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("email"), rs.getString("password"),
                        rs.getString("lastname"), rs.getString("firstname"), rs.getString("type"));
            }

        } catch (SQLException e) {
            System.out.println("ERROR in getting all users from DB");
            e.printStackTrace();
        }
        connect.close();
        return user;
    }

    public static String encryptPassword(String password) {
        String sha1 = "";
        System.out.println("PASSWORD: " + password);
        if (password.equals("") == false) {
            System.out.println("PASSORD");
            try {
                MessageDigest crypt = MessageDigest.getInstance("SHA-1");
                crypt.reset();
                crypt.update(password.getBytes("UTF-8"));
                sha1 = byteToHex(crypt.digest());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    @Override
    public User getData(Object id) {

        User u = null;
        String userID = (String) id;
        try {
            String query = "SELECT * "
                    + "FROM User"
                    + " WHERE userID = ?";
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, userID);
            rs = statement.executeQuery();
            if (rs.next()) {
                u = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("email"), rs.getString("password"),
                        rs.getString("lastname"), rs.getString("firstname"), rs.getString("type"));
                connect.close();
                return u;
            }

        } catch (SQLException e) {
            System.out.println("ERROR in getting all users from DB");
            e.printStackTrace();
        }
        connect.close();
        return u;
    }
    

    public User getDataUsername(Object username) {

        User u = null;
        String un = (String) username;
        try {
            String query = "SELECT * "
                    + "FROM User"
                    + " WHERE username = ?";
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, un);
            rs = statement.executeQuery();
            if (rs.next()) {
                u = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("email"), rs.getString("password"),
                        rs.getString("lastname"), rs.getString("firstname"), rs.getString("type"));
                connect.close();
                return u;
            }

        } catch (SQLException e) {
            System.out.println("ERROR in getting all users from DB");
            e.printStackTrace();
        }
        connect.close();
        return u;
    }

}
