
<%@page import="controller.Controller"%>
<%@page import="model.Hospital"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <link rel = "stylesheet" type="text/css" href="CSS/style-menu.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-scrollbar.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-hospital.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-doctor.css">
        <link rel = "stylesheet" type="text/css" href="Foundation/css/foundation.min.css">
        <link rel = "stylesheet" type="text/css" href="Foundation/css/foundation.css">
    </head>
    <body id = "scroll-style" class = "page-content">
        <div class="fixed">
          <nav class="top-bar" id = "clickHealth-navbar" data-topbar>
            <ul class="title-area">
                <!-- Title Area -->
                <li class="name">
                    <img id ="clickHealth-logo" src ="Assets/clickHealth.png"/>
                </li>
                <li class="toggle-topbar menu-icon">
                    <a href="#">
                        <span>ClickHealth Menu</span>
                    </a>
                </li>
            </ul>
            
            <section class="top-bar-section" id = "clickHealth-menu">
                <ul class="right">
                    <li class="divider"></li>
                    <li><a href="user-appointments.jsp">APPOINTMENTS</a></li>
                    <li class="divider"></li>
                    <li class = "active-button"><a  href = "#">HOSPITALS</a></li>
                    <li class="divider"></li>
                    <li><a href="availabledocs.jsp">DOCTORS</a></li>
                    <li><a style= "margin-right: 10px;" href="contactdoc.jsp">CONTACTS</a></li>
                </ul>
                <!-- Right Nav Section --> 
	            <form action = "SearchServlet" method = "post">
                <input id = "searchbox" name = "searchbox" input="text" placeholder=" Search Here ">
                	<input type="image" id= "searchicon" src="Assets/icon-search.png" alt="Submit">
                       <!-- <a href = "#"><img id= "search-icon" src = "Assets/icon-search.png"/></a> -->
                 </form>
                 <!-- Winona inserted line below -->
                 <!-- <div id = "suggest">
                 </div> -->
                
            </section>
                     
            
        </nav>
        </div>
        <div class = "content-wrapper">
            <section id = "main-content">
                <div class = "left-bar">
                    <div class = "row">
                        <div class = "large-5 columns" style=" margin-top: 70px; padding-left: 0px; margin-right: 0px;">
                            <img id = "left-bar-dp" src = "Assets/user-icon.png"/> 
                        </div>
                        <div class = "large-7 columns" id = "left-bar-name-box">
                            <label id = "left-bar-name">Shark Tan</label>
                            <a href = "account.html"><label id = "left-bar-account">Account Settings</label></a>
                            <a href= "index.jsp" id = "left-bar-logout">Logout </a> <br>
                            
                        </div>
                    </div>
                </div>
                <div id = "mid-content" class = "row hospital-content">
 	     		<form action="SearchDoctorsServlet" method="post">

                    <div class = "large-12 columns">
                        <%
	                    	Controller con = new Controller();
	                   		Iterator<Hospital> hospitals = (Iterator<Hospital>) session.getAttribute("hospitals");
	                   		//session.setAttribute("specialization", session.getAttribute("specialization"));
                        	while(hospitals.hasNext())
                        	{
                        		Hospital hospital = hospitals.next();
                        %>
		                        <div class = "row">
		                            <div class = "large-2 columns">
		                                 <img class = "hospital-img" src="Assets/clickHealth2.png">
		                            </div>
		                           <div class = "large-10 columns">
		                                <h4><%=hospital.getName() %></h4>
		                                <p><%=hospital.getStreet() %></p>
		                            </div>
		                            <div class = "large-2 columns">
		                                  <input type="submit" class = "contact-button" id = "<%=hospital.getHospID() %>" name  = "<%=hospital.getHospID() %>" value="Choose Hospital" onClick = "getHospID(this);">
		                            </div>
		                        </div>
		                         <hr>
                         <%
                        	 }
                         %>
                         <input type = "hidden" name = "hospID" id = "hospID">
                    </div>
                    </form>
    
                </div>
            </section>
        </div>
          <script src="Foundation/js/vendor/jquery.js"></script>
          <script src="Foundation/js/foundation.min.js"></script>
          <script src="Foundation/js/foundation/foundation.js"></script>
          <script src="Foundation/js/foundation/foundation.topbar.js"></script>
          <script src= "Foundation/js/foundation/foundation.reveal.js"></script> 
		  <script src = "javascript.js"></script> 
          
        
        <script>
        
        
        </script>
        
    </body>
</html>
