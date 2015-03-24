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
//    private ArrayList<User> users;

    private static ContactDAO cD = null;

    public static synchronized ContactDAO getInstance() {
        if (cD == null) {
            cD = new ContactDAO();
        }

        return cD;
    }

    public ContactDAO() {
        connect = DBConnection.getInstance();
        //users = new ArrayList<User>();
    }


    public Iterator getUserContacts(int keyID)
    {
    	ArrayList<UserContact> cList = new ArrayList<UserContact>();
    	System.out.println("CONTACTS DAO");
        try {
            String query = "SELECT * FROM usercontact WHERE userID = ?";
            statement = connect.getConnection().prepareStatement(query);
            statement.setInt(1, keyID);
            rs = statement.executeQuery();

           
            while (rs.next()) {
                UserContact c = new UserContact(rs.getInt("userID"), rs.getString("contactNo"), rs.getString("type"));
                cList.add(c);
            }

        } catch (SQLException e) {
            System.out.println("ERROR in getting all users from DB");
            e.printStackTrace();
        }
        finally {
            if (connect != null) {
			    connect.close();
			}
        }

        System.out.println("CLIST SIZE: "+ cList.size());
        return cList.iterator();
    }
    
    @Override
    public Iterator getAllData() {
    	 ArrayList<UserContact> cList = new ArrayList<UserContact>();
        try {
            String query = "SELECT * FROM usercontact;";
            statement = connect.getConnection().prepareStatement(query);
            rs = statement.executeQuery();

           
            while (rs.next()) {
                UserContact c = new UserContact(rs.getInt("userID"), rs.getString("contactNo"),rs.getString("type"));
                cList.add(c);
            }

        } catch (SQLException e) {
            System.out.println("ERROR in getting all users from DB");
            e.printStackTrace();
        }
        finally {
            if (connect != null) {
			    connect.close();
			}
        }
        return cList.iterator();
    }

    @Override
    public void insertData(Object obj) 
    {
    	Connection con = connect.getConnection();
        UserContact c = (UserContact) obj;
        try {

            String query = "INSERT INTO usercontact VALUES(?,?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            //preparedStatement.setInt(1, pat.getPatientID());
            preparedStatement.setInt(1, c.getUserID());
            preparedStatement.setString(2, c.getContactInfo());
            preparedStatement.setString(3, c.getType());
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
        // TODO Auto-generated method stub
        return null;
    }

}
