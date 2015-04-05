package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import model.*;

public class ContactDAO implements DAOInterface {

    private DBConnection connect;
    private ResultSet rs;
    private PreparedStatement statement;
    private ArrayList<UserContact> cList = null;

    private static ContactDAO cD = null;

    public static synchronized ContactDAO getInstance() {
        if (cD == null) {
            cD = new ContactDAO();
        }

        return cD;
    }

    public ContactDAO() {
        connect = DBConnection.getInstance();
    }

    public Iterator<UserContact> getUserContacts(int userID)
    {
    	System.out.println("USER CONTACTS DAO");
        try {
            String query = "SELECT * "
            		+ "FROM usercontact "
            		+ "WHERE userID = ?";
            statement = connect.getConnection().prepareStatement(query);
            statement.setInt(1, userID);
            rs = statement.executeQuery();

            cList = new ArrayList<UserContact>();
            UserContact u = null;
            
            while (rs.next()) {
                u = new UserContact(rs.getInt("userID"), rs.getString("contactNo"), rs.getString("type"));
                cList.add(u);
            }

    	} catch (SQLException e) {
			System.out.println("ERROR in getting user contacts");
			e.printStackTrace();
		}
		connect.close();
		return cList.iterator();
    }
    
    @Override
    public Iterator<UserContact> getAllData() {
    	
        try 
        {
            String query = "SELECT * FROM usercontact;";
            statement = connect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();
            
            cList = new ArrayList<UserContact>();
            UserContact u = null;
            
            while (rs.next()) {
                u = new UserContact(rs.getInt("userID"), rs.getString("contactNo"),rs.getString("type"));
                cList.add(u);
            }

        } catch (SQLException e) {
			System.out.println("ERROR in getting all contacts");
			e.printStackTrace();
		}
		connect.close();
		return cList.iterator();
    }

    @Override
    public boolean insertData(Object obj) 
    {
        try 
        {
        	UserContact c = (UserContact) obj;
            String query = "INSERT INTO usercontact VALUES(?,?,?);";
            statement = connect.getConnection().prepareStatement(query);
            statement.setInt(1, c.getUserID());
            statement.setString(2, c.getContactInfo());
            statement.setString(3, c.getType());
            if(statement.execute())
			{
				connect.close();
				return true;
			}
		}
		catch (SQLException e)
		{
			System.out.println("Unable to INSERT new contacts");
			e.printStackTrace();
		}
		connect.close();
		return false;
    }

    @Override
    public boolean updateData(Object obj) {
    	
    	UserContact u = (UserContact)obj;
		String query = "UPDATE usercontact "
					 + "SET  contactNo = ?, type = ?"
				     + "WHERE userID = ?";
		try 
		{
			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, u.getContactInfo());
			statement.setString(2, u.getType());
			statement.setInt(3, u.getUserID());
		
			if(statement.execute())
			{
				System.out.println("UPDATED CONTACT TAT");
				connect.close();
				return true;
			}
		
		} catch (SQLException e) {
	
			System.out.println("Update Error");
			e.printStackTrace();
		}
		connect.close();
		return false;
    }

    @Override
    public UserContact getData(Object userID) {
    	
    	UserContact u = null;
    	int user = (Integer) userID;
    	try
		{
		
			String query = "SELECT * "
					+ "FROM usercontact "
					+ "WHERE userID = ?";
	
			statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1, user);
			rs = statement.executeQuery();
			
			if (rs.next())
			{
				u = new UserContact(rs.getInt("userID"), rs.getString("contactNo"),rs.getString("type"));
			}
		}
		
		catch (SQLException e)
		{
			System.out.println("Unable to SELECT user contact");
			e.printStackTrace();
		}
		
		connect.close();
		return u;
    }

}
