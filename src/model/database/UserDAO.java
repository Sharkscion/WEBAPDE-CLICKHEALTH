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

    public int getUserID(String userName)
    {

    	  Connection con = connect.getConnection();
          int i = 0;
          try 
          {
              String query = "SELECT userID FROM user WHERE username = ?";
              PreparedStatement preparedStatement = con.prepareStatement(query);
              preparedStatement.setString(1, userName);
              ResultSet resultSet = preparedStatement.executeQuery();
              if (resultSet.next()) 
            	  i = resultSet.getInt("userID");
                  
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
    
    public User validateUser(String username, String password) {
        try {
            String query = "SELECT * FROM User WHERE username = ? AND password = ?";
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, encryptPassword(password));
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

		try 
		{
			User u = (User)obj;
			String query = "INSERT INTO user values(NULL,?,?,?,?,?,?)";
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, u.getUsername());
			statement.setString(2, u.getEmail());
			statement.setString(3, encryptPassword(u.getPassword()));
			statement.setString(4, u.getLastname());
			statement.setString(5, u.getFirstname());
			statement.setString(6, u.getType());
			if(statement.execute())
			{
				connect.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println("Unable to INSERT new user");
			e.printStackTrace();
		}
		
		connect.close();
	}

    @Override
    public void updateData(Object obj) {
        // TODO Auto-generated method stub
    }

    public static String encryptPassword(String password){
	    String sha1 = "";
	    try{
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}
	
	private static String byteToHex(final byte[] hash){
	    Formatter formatter = new Formatter();
	    for (byte b : hash){
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
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
