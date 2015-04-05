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

//    public int getUserID(String userName)
//    {
//          int i = 0;
//          try 
//          {
//              String query = "SELECT userID FROM user WHERE username = ?";
//              PreparedStatement preparedStatement = con.prepareStatement(query);
//              preparedStatement.setString(1, userName);
//              ResultSet resultSet = preparedStatement.executeQuery();
//              if (resultSet.next()) 
//            	  i = resultSet.getInt("userID");
//                  
//          } catch (SQLException sqlException) {
//              sqlException.printStackTrace();
//          } finally {
//              try {
//                  if (con != null) {
//                      con.close();
//                  }
//              } catch (SQLException sqlee) {
//                  sqlee.printStackTrace();
//              }
//          }
//          
//          return i;
//        
//    }
//    
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
				return true;
			}
		}
		catch (SQLException e)
		{
			System.out.println("Unable to INSERT new user");
			e.printStackTrace();
		}
		
		connect.close();
		return false;
	}

    @Override
    public boolean updateData(Object obj) {
        // TODO Auto-generated method stub
    	
    	return false;
    }

    public static String encryptPassword(String password){
	    String sha1 = "";
	    System.out.println("PASSWORD: "+ password);
	    if(password.equals("") == false)
	    {
	    	System.out.println("PASSORD");
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
    public User getData(Object uName) {
    	
    	User u = null;
    	String username = (String) uName;
    	try {
            String query = "SELECT * "
	            		+ "FROM User"
	            		+ " WHERE username = ?";
            statement = connect.getConnection().prepareStatement(query);
            statement.setString(1, username);
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
