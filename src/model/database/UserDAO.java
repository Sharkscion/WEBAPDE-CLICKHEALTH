package model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import model.*;

public class UserDAO implements DAOInterface {

    private DBConnection connect = DBConnection.getInstance();
    private ResultSet rs;
    private PreparedStatement statement;
    private ArrayList<User> users;

    private static UserDAO uD = null;

    public static synchronized UserDAO getInstance() {
        if (uD == null) {
            uD = new UserDAO();
        }

        return uD;
    }

    public UserDAO() {
        connect = DBConnection.getInstance();
        users = new ArrayList<User>();
    }

    public User validateUser(String username, String password) {
        try {
            String query = "SELECT * FROM User WHERE username = ? AND password = ?";
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            rs = statement.executeQuery();

            User u;

            if (rs.wasNull() == false && rs.next()) {
                u = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("email"), rs.getString("password"),
                        rs.getString("lastname"), rs.getString("firstname"), rs.getString("type"));
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Iterator getAllData() {
        try {
            String query = "SELECT * FROM User;";
            statement = connect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

            users = new ArrayList<User>();
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                users.add(u);
            }

        } catch (SQLException e) {
            System.out.println("ERROR in getting all users from DB");
            e.printStackTrace();
        }
        connect.close();
        return users.iterator();
    }

    @Override
    public void insertData(Object obj) {
        // TODO Auto-generated method stub
    }

    @Override
    public void updateData(Object obj) {
        // TODO Auto-generated method stub
    }

    @Override
    public Object getData(String keyID) {
    	try {
            String query = "SELECT * FROM User WHERE userID =\""+keyID+"\"";
            statement = connect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                return u;
            }

        } catch (SQLException e) {
            System.out.println("ERROR in getting all users from DB");
            e.printStackTrace();
        }
        connect.close();
        return null;
    }

}
