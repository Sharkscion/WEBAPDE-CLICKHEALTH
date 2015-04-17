<%@page import="model.Appointment"%>
<%@page import="controller.Controller"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel = "stylesheet" type="text/css" href="CSS/style-menu.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-scrollbar.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-appointment.css">
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
                    <li class ="active-button"><a href="appointment-requests.html">APPOINTMENT REQUESTS</a></li>
                    <li class="divider"></li>
                    <li  style= "margin-right: 10px"><a  href="scheduled-appointments.html">SCHEDULED APPOINTMENTS</a></li>
                    <li class="divider"></li>
                </ul>
            </section>
        </nav>
        </div>
        <div class = "content-wrapper">
            <section id = "main-content">
                <div class = "left-bar">
                    <div class = "row">
                        <div class = "large-5 columns" style=" margin-top: 70px; padding-left: 0px; margin-right: 0px;">
                            <img id = "left-bar-dp" src = "Assets/doctor-icon.png"/> 
                        </div>
                        <div class = "large-7 columns" id = "left-bar-name-box">
                            <label id = "left-bar-name">Dr. Sharko Tan</label>
                            <a href = "doctor-account-settings.html"><label id = "left-bar-account">Account Settings</label></a>
                            <a href= "index.html" id = "left-bar-logout">Logout </a> <br>
                            
                        </div>
                    </div>
                </div>
                <div id = "mid-content" class = "row appointment-content appointment-request" style = "margin-top: 90px;">
                	<%
                		Controller con = new Controller();
             			Iterator<Appointment> appointments = con.getDoctorAppointments((String)session.getAttribute("doctor"));
                		Appointment app = null;
                		String patient = null;
                		
                		while(appointments.hasNext())
                		{
                			app = appointments.next();
                			patient = con.getUserUserName(app.getPatientID());
                	%>
		                	<div id = "request-1" class = "row request-box">
		                       <div class = "large-2 columns"> <%=app.getStartTime() %> </div>
		                        <div class = "large-2 columns"> March 3, 2015</div>
		                        <div class = "large-5 columns">
		                            <p>Appointment with <%=patient %> <br>
		                               Area of Concern: <%=app.getConcern() %><br>
		                               Time: <%=app.getStartTime() %></p>
		                        </div>
		                        <div class = "large-3 columns"> <input type="button" class = "appointment-request-button" value="Remove"                                     onClick="removeElement('mid-content','request-1');">
		                            <input type="button" class = "appointment-request-button" value="Approve" onClick="addElement();"></div>
		                    </div>
                	<% } %>
                
                    <!-- <div id = "request-1" class = "row request-box">
                       <div class = "large-2 columns"> 8:00 AM </div>
                        <div class = "large-2 columns"> March 3, 2015</div>
                        <div class = "large-5 columns">
                            <p>Appointment with Siao Long Bao <br>
                               Area of Concern: So delicious :> Big tummy :><br>
                               Time: 10:00 AM</p>
                        </div>
                        <div class = "large-3 columns"> <input type="button" class = "appointment-request-button" value="Remove"                                     onClick="removeElement('mid-content','request-1');">
                            <input type="button" class = "appointment-request-button" value="Approve" onClick="addElement();"></div>
                    </div> -->
                </div>
            </section>
        </div>

          <script src="Foundation/js/vendor/jquery.js"></script>
          <script src="Foundation/js/foundation.min.js"></script>
          <script src="Foundation/js/foundation/foundation.js"></script>
          <script src="Foundation/js/foundation/foundation.topbar.js"></script>
          <script src= "Foundation/js/foundation/foundation.reveal.js"></script> 
          <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
          <script src = "javascript.js"></script> 
        
    </body>
</html>
