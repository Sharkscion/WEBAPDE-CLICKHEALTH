package model.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Post;
import model.User;

public class PostDAO implements DAOInterface {

	private DBConnection connect;
	
	public PostDAO()
	{
		connect = DBConnection.getInstance();
	}
	@Override
	public Object getData( Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Post> getUserPost(int userID)
	{
		ArrayList<Post> posts = new ArrayList<Post>();
		try 
		{
			String query = "SELECT * FROM Post WHERE userID = ? ;";
			PreparedStatement statement = connect.getConnection().prepareStatement(query);
			statement.setInt(1,userID);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				Post p = new Post(rs.getInt(2), rs.getInt(1), rs.getString(3));
				posts.add(p);			
			}
						
		} 
		catch (SQLException e) {
			System.out.println("ERROR in getting all posts from DB");
			e.printStackTrace();
		}
		connect.close();
		return posts;
	}
	public ArrayList<Post> getAll()
	{		
		ArrayList<Post> posts = new ArrayList<Post>();
		try 
		{
			String query = "SELECT * FROM Post;";
			PreparedStatement statement = connect.getConnection().prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				Post p = new Post(rs.getInt(2), rs.getInt(1), rs.getString(3));
				posts.add(p);			
			}
						
		} 
		catch (SQLException e) {
			System.out.println("ERROR in getting all posts from DB");
			e.printStackTrace();
		}
		connect.close();
		return posts;
	}

	@Override
	public void insertData(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateData(Object obj) {
		// TODO Auto-generated method stub
		Post p = (Post) obj;
		
//		System.out.println("IN DB POST == " + p.getPost());
//		System.out.println("IN DB USER == " + p.getUserID());
//		System.out.println("IN DB P == " + p.getPostID());
		
		String query = "UPDATE Post SET post = ? WHERE userID = ? AND postID = ?;";
		try 
		{
			PreparedStatement statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, p.getPost());
			statement.setInt(2, p.getUserID());
			statement.setInt(3, p.getPostID());
			statement.execute();
			connect.close();
			
		} catch (SQLException a) {
	
			System.out.println("Update Error");
			a.printStackTrace();
		}
		
//		System.out.println("IN DB POST == " + p.getPost());
//		System.out.println("IN DB USER == " + p.getUserID());
//		System.out.println("IN DB P == " + p.getPostID());
		
		
		connect.close();
	}

}
