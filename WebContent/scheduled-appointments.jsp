<%@page import="model.UserContact"%>
<%@page import="model.Patient"%>
<%@page import="model.Hospital"%>
<%@page import="model.DoctorSchedule"%>
<%@page import="model.Appointment"%>
<%@page import="model.User"%>
<%@page import="controller.Controller"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Time"%>
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
	    
		String userID = "";
	    Cookie[] cookies = request.getCookies();
	    for(Cookie cookie:cookies){
	        if(cookie.getName().equals("user")){
	            userID = cookie.getValue();
	        }
	    }
	    
	   User user = con.getUserInstance(userID);
	   String uName = user.getFirstname() + " "+ user.getLastname();
	   
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
                    <li ><a href="doctor-appointment-requests.jsp">APPOINTMENT REQUESTS</a></li>
                    <li class ="active-button" style= "margin-right: 10px"><a  href="scheduled-appointments.jsp">SCHEDULED APPOINTMENTS</a></li>
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
                            <label id = "left-bar-name">Dr. <%=uName %></label>
                            <a href = "doctor-account-settings.html"><label id = "left-bar-account">Account Settings</label></a>
                            <a href= "index.jsp" id = "left-bar-logout">Logout </a> <br>
                            
                        </div>
                    </div>
                </div>
                <div id = "mid-content" class = "row appointment-content">
                	<%
	                	String success = request.getParameter("Success");
		        		String msg = "";
		        		if(success != null)
		        		{
                			if(success.equals("1"))
                			{
                	%>
	                			<div data-alert class="alert-box success radius"> 
						    		Appointment Schedule has been resolved! 
					        		<a href="#" class="close">&times;</a>
								</div>
						  <%
                			} 
                			else
                			{
						  %>
						  		<div data-alert class="alert-box alert radius"> 
						    		Error: Appointment Schedule failed to be resolved!
					        		<a href="#" class="close">&times;</a>
								</div>
                	<%
                			}
		        		}
                	%>
                    <div class ="large-12 columns" id = "appointment">
                       <br>
                       <form action = "ScheduledAppointmentServlet" method = "post">
                         <%
                         	String notif;
                         	
                         	int licenseId = con.getDoctorByUserId(user.getUserID()).getLicenseID();
                         	System.out.println("LICENSE ID: "+ licenseId);
                         	Iterator<Appointment> i = con.getDoctorAppointments(licenseId);
                         	if(i != null)
                         		while(i.hasNext())
                         		{
                         			
                         			Appointment a = (Appointment)i.next();
                         			DoctorSchedule ds = con.getDoctorSchedule(a.getDoctorSchedID());
                         			Hospital h = con.getHospitalByID(ds.getHospitalScheduleID());
                         			Patient p = con.getPatientByID(a.getPatientID());
                          			String hospital = h.getName();
                         			String address = h.getStreet() + "," + h.getCity();
                         			String patient = p.getFirstname()+ " "+ p.getLastname();
                         			String concern = a.getConcern();
                         			String remarks = a.getRemarks();
                         			
                         			Date date = a.getAppointmentDate();
                         			Time time = a.getStartTime();
   
                         			Iterator cList = con.getUserContacts(p.getUserID());
                         			System.out.println("CLIST:" + cList.hasNext());
                         			String appointmentID = "card"+ a.getAppID();
                         			String buttonAction = "removeElement('appointment','"+appointmentID+"')";
                         			
                          %>
		                        <ul  id = "card1" class="appointment-card">
		                              <li>Patient Name: <%=patient%></li>
		                              <li> Contact/s:</li>
		                            <%
			                            while(cList.hasNext())
		                              	{
		                              		UserContact c = (UserContact) cList.next();
		                              		String type = c.getType();
		                              		String contactInfo = c.getContactInfo();
		                            %>
				                       <li><%=type %> :  <%=contactInfo %></li>
			                         <%
	                        			 }
			                         %>
		                              <li>Area of Concern: <%=concern %></li>
		                              <li>Remarks: <%=remarks %></li>
		                            
		                              <li class = "label-spacing"> <span class = "label-appointment"> Appointment Schedule Info</span></li>
		                              <li class = "label-appointment-sched">Hospital: <%=hospital%></li>
		                              <li class = "label-appointment-sched">Hospital Address: <%=address%></li>
		                              <li class = "label-appointment-sched">Date: <%=date%></li>
		                              <li class = "label-appointment-sched">Time: <%=time%></li>
		                              <li class = "line"></li>
   								  <input type = "submit" id=<%=a.getAppID() %> name = <%=a.getAppID()%> class = "appointment-card-button" value = "Resolve" onClick = "getResolveID(this);">
	                              <input type = "hidden" name = "resolveID" id = "resolveID">
	                            </ul>
                        <% } %>
                       </form>
                    </div>
                </div>
            </section>
        </div>

          <script src= "Foundation/js/vendor/jquery.js"></script>
          <script src= "Foundation/js/foundation.min.js"></script>
          <script src= "Foundation/js/foundation/foundation.js"></script>
          <script src= "Foundation/js/foundation/foundation.topbar.js"></script>
          <script src= "Foundation/js/foundation/foundation.reveal.js"></script> 
          <script src="Foundation/js/vendor/modernizr.js"></script>
          <script src="Foundation/js/foundation/foundation.alert.js"></script>
		  <script src= "javascript.js"></script>
        
    </body>
</html>
