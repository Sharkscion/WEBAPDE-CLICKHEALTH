<%@page import="model.DoctorSchedule"%>
<%@page import="controller.Controller"%>
<%@page import="java.sql.Time"%>
<%@page import="model.UserContact"%>
<%@page import="java.util.Date"%>
<%@page import="model.User"%>
<%@page import="model.Hospital"%>
<%@page import="model.Appointment"%>
<%@page import="model.Doctor"%>
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
	<%
		Controller con = new Controller();
	    
	    int id=-1;
	    Cookie[] cookies = request.getCookies();
	    for(Cookie cookie:cookies){
	        if(cookie.getName().equals("user")){
	            id=Integer.parseInt(cookie.getValue());
	        }
	    }
	    
	   User user = (User) con.getUserInstance(String.valueOf(id));
	   String username = user.getUsername();
	   
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
                <input id = "search-box" input="text" placeholder=" Search Here ">
                        <a href = "#"><img id= "search-icon" src = "Assets/icon-search.png"/></a>
                <ul class="right">
                    <li class="divider"></li>
                    <li class ="active-button"><a href="user-appointments.jsp">APPOINTMENTS</a></li>
                    <li class="divider"></li>
                    <li><a  href="hospitals.jsp">HOSPITALS</a></li>
                    <li class="divider"></li>
                    <li><a style= "margin-right: 10px;" href="availabledocs.jsp">DOCTORS</a></li>
                </ul>
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
                            <label id = "left-bar-name"><%=username%></label>
                            <a href = "user-account-settings.html"><label id = "left-bar-account">Account Settings</label></a>
                            <a href= "index.html" id = "left-bar-logout">Logout </a> <br>
                            
                        </div>
                    </div>
                </div>
                <div id = "mid-content" class = "row appointment-content">
                    <div class ="large-12 columns" id = "appointment">
                         <br>
                         <%
                         	String notif;
                         	
                         	Iterator i = con.getPatientAppointments(user.getUserID());
                         	if(i == null)
                        		 notif = "No Scheduled Appointments";
                         	else
                         		while(i.hasNext())
                         		{
                         			
                         			Appointment a = (Appointment)i.next();
                         			DoctorSchedule ds = con.getDoctorSchedule(a.getDoctorSchedID());
                         			Hospital h = con.getHospitalByID(ds.getHospitalScheduleID());
                         			Doctor d = con.getDoctor(ds.getHospitalScheduleID());
                         			String hospital = h.getName();
                         			String address = h.getStreet() + "," + h.getCity();
                         			String doctor = d.getFirstname()+ " "+ d.getLastname();
                         			String specialization = d.getSpecialization();
                         			
                         			Date date = a.getAppointmentDate();
                         			Time time = a.getStartTime();
                         			
                         			System.out.println("DOCTOR: "+d.getLicenseID());
                         			System.out.println("DOCTOR: "+d.getUserID());
                         			Iterator cList = con.getUserContacts(d.getUserID());
                         			System.out.println("CLIST:" + cList.hasNext());
                         			String appointmentID = "card"+ a.getAppID();
                         			String buttonAction = "removeElement('appointment','"+appointmentID+"')";
                         			
                          %>
	                          <ul  id = "<%=appointmentID%>" class="appointment-card">
	                              <li>Hospital: <%=hospital%> </li>
	                              <li>Address: <%=address%></li>
	                              <li class = "label-spacing">Doctor: Dr. <%=doctor%></li>
	                              <li> Specialization: <%=specialization%></li>
	                              <li> Contact/s:</li>
	                            <%
		                            while(cList.hasNext())
	                              	{
	                              		UserContact c = (UserContact) cList.next();
	                              		String type = c.getType();
	                              		String contactInfo = c.getContactInfo();
	                            %>
			                           <li><%=type %> : <%=contactInfo %></li>
		                          <%
                        			 }
		                          %>
			                              <li class = "label-spacing"> <span class = "label-appointment"> Appointment Schedule</span></li>
			                              <li class = "label-appointment-sched">Date: <%=date%></li>
			                              <li class = "label-appointment-sched">Time: <%=time%></li>
			                              <li class = "line"></li>
			                             
	                              <input type="button" class = "appointment-card-button" value="Resolve" onClick= <%=buttonAction%>>
	                        </ul>
                        <% } %>
                    </div>
                </div>
            </section>
        </div>

        <script src = "Foundation/js/vendor/jquery.js"></script>
        <script src = "Foundation/js/foundation.min.js"></script>
        <script src = "Foundation/js/foundation/foundation.js"></script>
        <script src = "Foundation/js/foundation/foundation.topbar.js"></script>
        <script src = "Foundation/js/foundation/foundation.reveal.js"></script> 
        <script src = "javascript.js"></script> 
        
    </body>
</html>
