<%@page import="model.Doctor"%>
<%@page import="model.DoctorSchedule"%>
<%@page import="model.Appointment"%>
<%@page import="servlets.AppointmentServlet"%>
<%@page import="model.Notification"%>
<%@page import="model.Patient"%>
<%@page import="model.Hospital"%>
<%@page import="model.User"%>
<%@page import="controller.Controller"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <link rel = "stylesheet" type="text/css" href="CSS/style-menu.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-scrollbar.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-hospital.css">
        <link rel = "stylesheet" type="text/css" href="Foundation/css/foundation.min.css">
        <link rel = "stylesheet" type="text/css" href="Foundation/css/foundation.css">
    </head>
    
    <%
        Controller con = new Controller();
	    String userID = "";
	    Cookie[] cookies = request.getCookies();
	    for(Cookie cookie:cookies){
	        if(cookie.getName().equals("user")){
	            userID = cookie.getValue();
	        }
	    }
	    
	   User user = con.getUserInstance(userID);
	   String uName = user.getUsername();
    %>
    
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
                <!-- Right Nav Section --> 
                <ul class="right">
                	<li id = "notifIcon"><a  data-dropdown="notif-dropdown" href="hospitals.jsp" ><img class = "tasks" src="Assets/notifFalse.png">
                	<%
                		Patient p = con.getPatientInstance(user.getUsername());
                	    int notifCount = con.getNotifCount(p.getPatientID());
                	%>
                		<span id = "notifCount"><%=notifCount%></span></a></li>
                
                	<li><a href="user-appointments.jsp"></a></li>
                    <li class="divider"></li>
                    <li><a href="user-appointments.jsp">APPOINTMENTS</a></li>
                    <li class="divider"></li>
                    <li class = "active-button"><a  href = "#">HOSPITALS</a></li>
                    <li class="divider"></li>
                    <li style= "margin-right: 10px;"><a href="availabledocs.jsp">DOCTORS</a></li>
                </ul>
	            <form action = "SearchServlet" method = "post">
                <input id = "searchbox" name = "searchbox" input="text" placeholder=" Search Here ">
                <input type="image" id= "searchicon" src="Assets/icon-search.png" alt="Submit">
                <div id = "suggest" name = "suggest">
                </div>
             	</form>               
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
                            <label id = "left-bar-name"><%=uName%></label>
                            <input type= hidden id = "username" name="username" value = "<%=uName%>">
                            <a href = "user-account-settings.jsp">
                            	<label id = "left-bar-account">Account Settings</label></a>
                            <a href= "index.jsp" id = "left-bar-logout">Logout </a> <br>
                            
                        </div>
                    </div>
                </div>
                <div id = "mid-content" class = "row hospital-content">
                    <div class = "large-12 columns">
                        <%
                    		
                   			Iterator<Hospital> hospitals = con.getAllHospitals();
                        	while(hospitals.hasNext())
                        	{
                        		Hospital hospital = hospitals.next();
                        %>
	                        <div class = "row">
	                            <div class = "large-2 columns">
	                                 <img class = "hospital-img" src="<%=hospital.getImageURL()%>">
	                            </div>
	                           <div class = "large-10 columns">
	                                <h4><%=hospital.getName() %></h4>
	                                <p><%=hospital.getStreet()%></p>
	                            </div>
	                        </div>
	                         <hr>
                         <%
                         }
                         %>
                    </div>
                </div>
            </section>
        </div>
<!--**************************************************Notif Drop Down*************************************************************-->
       <div class="reveal-modal small form" id ="viewNotif-modal" data-reveal>     		
       	
            <a class="close-reveal-modal">&#215;</a>
       </div>
<!--*************************************************User Sign In Drop Down*******************************************************-->
       
<!--**************************************************Notif Drop Down*************************************************************-->
        <div  id ="notif-dropdown" class="f-dropdown small content form form-dropdown" data-dropdown-content>
     		
        </div>
<!--*************************************************User Sign In Drop Down*******************************************************-->
   
          <script src = "Foundation/js/vendor/jquery.js"></script>
          <script src = "Foundation/js/foundation.min.js"></script>
          <script src = "Foundation/js/foundation/foundation.js"></script>
          <script src = "Foundation/js/foundation/foundation.topbar.js"></script>
          <script src = "Foundation/js/foundation/foundation.reveal.js"></script> 
          <script src="Foundation/js/foundation/foundation.dropdown.js"></script>
		  <script src = "javascript.js"></script> 
    </body>
</html>
