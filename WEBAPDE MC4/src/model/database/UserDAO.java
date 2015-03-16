package model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO implements DAOInterface{

	private DBConnection connect;
	
	public UserDAO()
	{
		connect = DBConnection.getInstance();
	}
	@Override
	public Object getData( Object obj) {
		// TODO Auto-generated method stub
		
		User u = null;
		String username = (String) obj;
		 try {
	            String query = "SELECT * FROM user WHERE username = ?";
	            PreparedStatement ps = connect.getConnection().prepareStatement(query);
	            ps.setString(1, username);
	            ResultSet rs = ps.executeQuery();

	            if(rs.next()) {
	               u = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("password"));
	            }
	        } catch (SQLException se) {
	            se.printStackTrace();
	        }
	        return u;
	}
	
	public User validateUser(String username, String password)
	{

		User u = null;

		 try {
	            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
	            PreparedStatement ps = connect.getConnection().prepareStatement(query);
	            ps.setString(1, username);
	            ps.setString(2, password);
	            ResultSet rs = ps.executeQuery();

	            if(rs.next()) {
	               u = new User(rs.getInt("userID"), rs.getString("username"), rs.getString("password"));
	            }
	        } catch (SQLException se) {
	            se.printStackTrace();
	        }
	        return u;
	}

	@Override
	public void insertData(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateData(Object obj) {
		// TODO Auto-generated method stub
		
	}

}
