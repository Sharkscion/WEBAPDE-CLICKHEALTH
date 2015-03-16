package control;

import java.util.ArrayList;

import model.Post;
import model.User;
import model.database.PostDAO;
import model.database.UserDAO;

public class Controller {
	
	private UserDAO userDB;
	private PostDAO postDB;
	private User u;

	
	private static Controller controller = null;
	
	public static synchronized Controller getInstance() 
	{
        if (controller == null) {
            controller = new Controller();
        }
 
        return controller;
    }
	public Controller()
	{
		userDB = new UserDAO();
		postDB = new PostDAO();
	}
	
	public User getUser()
	{
		return u;
	}
	
	public User getUser(String user)
	{
		return (User) userDB.getData((Object)user);
	}
	

	public boolean validateUser(String username, String password)
	{
		User temp = userDB.validateUser(username, password);
		if( temp == null)
			return false;
		else
		{
			u = temp;
		}
		return true;
	}
	
	public ArrayList<Post> getUserPost(int userID)
	{
		return postDB.getUserPost(userID);
	}
	
	public void updatePost(Post post)
	{
		postDB.updateData((Object)post);
	}

}
