<%@page import="model.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="control.Controller"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <%
    	Controller con = Controller.getInstance();
    	ArrayList<Post> posts = con.getUserPost(con.getUser().getUserID());
    	int unID = con.getUser((String)session.getAttribute("username")).getUserID();
    	session.setAttribute("userID1", posts.get(0).getUserID());
    	session.setAttribute("postID1", posts.get(0).getPostID());
    	session.setAttribute("userID2", posts.get(1).getUserID());
    	session.setAttribute("postID2", posts.get(1).getPostID());
    	
    	System.out.println("userID1" + posts.get(0).getUserID());
    	System.out.println("postID2" + posts.get(0).getPostID());
    	System.out.println("userID1" + posts.get(1).getUserID());
    	System.out.println("postID2" + posts.get(1).getPostID());

    	String bg = (String) session.getAttribute("bgColor");
        String f = (String) session.getAttribute("fontColor");
        String p = (String) session.getAttribute("postColor");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("bgColor"+ con.getUser().getUserID())) {
                    bg = cookie.getValue();
                }
                if (cookie.getName().equals("fontColor"+con.getUser().getUserID())) {
                    f = cookie.getValue();
                }
                if (cookie.getName().equals("postColor"+con.getUser().getUserID())) {
                    p = cookie.getValue();
                }
            }
        }

        if (bg == null || bg.equals("")) {
            bg = "blue";
        }
        if (f == null || f.equals("")) {
            f = "black";
        }
        if (p == null || p.equals("")) {
            p = "white";
        }
    %>
    <head>
        <link href = "preferences.jsp" rel = "stylesheet" type="text/css">
        <style>
            .post-box
            {
                background: <%=p%>;
                color: <%=f%>;
                height: 350px;
                width: 425px;
                position: relative;
                margin-left: auto;
                margin-right: auto;
                border-color: red;
                margin-bottom: 10px;
            } 

            .post-box textarea
            {
                color: <%=f%>;
            }   
        </style>
    </head>
    <body bgColor ="<%=bg%>" >
        <form method = "post" action = "LogoutServlet">
            <input style = "float: right" type = "submit" value = "Logout">
        </form>
	<form method = "post" action="PostServlet">
        <div class = "post-box">
            <h1>POST #1</h1>
            
                <textarea id = "post1" name= "post1" rows = 15 cols = 50>
	            <%=posts.get(0).getPost()%>
                </textarea>
                <input type="submit" value = "Post">
            
        </div>

        <div class = "post-box">
            <h1>POST #2</h1>
                <textarea id = "post2" name= "post2" rows = 15 cols = 50>
	            <%=posts.get(1).getPost()%>
                </textarea>
                <input type = "submit" value="Post">
        </div>
		</form>
        <div class = "post-box">
            <a href = "preferences.jsp"> Set Preferences</a>
        </div>
    </body>


</html>