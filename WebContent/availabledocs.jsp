<%@page import="model.Patient"%>
<%@page import="model.Hospital"%>
<%@page import="model.Doctor"%>
<%@page import="model.DoctorSchedule"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.User"%>
<%@page import="controller.Controller"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <link rel = "stylesheet" type="text/css" href="CSS/style-menu.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-scrollbar.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-doctor.css">
        <link rel = "stylesheet" type="text/css" href="Foundation/css/foundation.min.css">
        <link rel = "stylesheet" type="text/css" href="Foundation/css/foundation.css">

    </head>
	<% 
	  	Controller c = new Controller();
		String userID = "";
	    Cookie[] cookies = request.getCookies();
	    for(Cookie cookie:cookies){
	        if(cookie.getName().equals("user")){
	            userID = cookie.getValue();
	        }
	    }
	    
	   User user = c.getUserInstance(userID);
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
                <ul class="right">
                	<li id = "notifIcon"><a data-dropdown="notif-dropdown" href="hospitals.jsp" ><img class = "tasks" src="Assets/notifFalse.png">
                	<%
                		Patient p = c.getPatientInstance(user.getUsername());
                	    int notifCount = c.getNotifCount(p.getPatientID());
                	%>
                		<span id = "notifCount"><%=notifCount%></span></a></li>
                
                    <li class="divider"></li>
                    <li><a href="user-appointments.jsp">APPOINTMENTS</a></li>
                    <li class="divider"></li>
                    <li><a  href = "hospitals.jsp">HOSPITALS</a></li>
                    <li class="divider"></li>
                    <li class = "active-button" style= "margin-right: 10px;" ><a href="availabledocs.jsp">DOCTORS</a></li>
                  <!--   <li><a  " href="contactdoc.jsp">CONTACTS</a></li>-->
                </ul>
                <!-- Right Nav Section --> 
	            <form action = "SearchServlet" method = "post">
                <input id = "searchbox" name = "searchbox" input="text" placeholder=" Search Here ">
                	<input type="image" id= "searchicon" src="Assets/icon-search.png" alt="Submit">
                      
                 </form>
                 <div id = "suggest">
                 </div>
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
                            <a href = "user-account-settings.jsp"><label id = "left-bar-account">Account Settings</label></a>
                            <a href= "index.jsp" id = "left-bar-logout">Logout </a> <br>

                        </div>

                    </div>
                </div>
                <div id = "mid-content" class = "row doctor-content"> 
                    <div  id="doctorsList" class = "large-12 columns"> <br>
                        <form action="AppointmentServlet" method="post">
                        <% 
                            //Controller c = Controller.getInstance();
                        	Iterator iterator = c.getAllDoctorSchedule();
                  
                        	while (iterator.hasNext()) 
                        	{
                                DoctorSchedule ds = (DoctorSchedule) iterator.next();
                                Doctor d = c.getDoctor(ds.getDoctorScheduleID());
                                Hospital h =  c.getHospitalByID(ds.getHospitalScheduleID());
                                                               
                                String address = h.getStreet() + ", "+h.getCity();
                        %>    
	                            <div class = "row">
	                                <div class = "large-2 columns"> <img class = "hospital-img" src="Assets/clickHealth2.png"></div>
	                                <div class = "large-8 columns">
	
	                                    <p><b>Hospital:</b> <%=h.getName()%></p>
	                                    <p><b>Hospital Address:</b> <%=address%></p>
	                                    <p><b>Doctor:</b> Dr. <%=d.getFirstname() + " " + d.getLastname()%> </p>
	                                    <p><b>Specialization:</b> <%=d.getSpecialization()%></p>
	                                   	<p><b>Schedule Day:</b> <%=ds.getScheduleDay()%></p>
	                                </div>
	                                <div class = "large-2 columns">
	                                  <input type="submit" class = "contact-button" id = "<%=ds.getScheduleID()%>" name  = "<%=ds.getScheduleID() %>" value="Set Appointment" onClick = "getDocID(this);">
	                                </div>
	                            </div>
	                        	<hr>
                        <% } %>
                        <input type = "hidden" name = "docSchedID" id = "docSchedID">
                        </form>
                        
                    </div>
                </div>
            </section>
        </div>
<!--**************************************************Notif Drop Down*************************************************************-->
        <div  id ="notif-dropdown" class="f-dropdown small content form form-dropdown" data-dropdown-content>
     		
        </div>
<!--*************************************************User Sign In Drop Down*******************************************************-->
   
   	<script src="Foundation/js/foundation/foundation.dropdown.js"></script>
        <script src="Foundation/js/vendor/jquery.js"></script>
        <script src="Foundation/js/foundation.min.js"></script>
        <script src="Foundation/js/foundation/foundation.js"></script>
        <script src="Foundation/js/foundation/foundation.topbar.js"></script>
        <script src= "Foundation/js/foundation/foundation.reveal.js"></script> 
		<script src = "javascript.js"></script> 

    </body>
</html>
