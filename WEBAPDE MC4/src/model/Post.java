package model;

public class Post {
	private int userID;
	private int postID;
	private String post;
	
	public Post(int userID, int postID, String post)
	{
		this.userID = userID;
		this.postID = postID;
		this.post = post;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}

	public int getPostID()
	{
		return postID;
	}
	
}
