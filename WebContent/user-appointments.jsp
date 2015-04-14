<%@page import="model.UserContact"%>
<%@page import="model.Doctor"%>
<%@page import="model.Hospital"%>
<%@page import="model.DoctorSchedule"%>
<%@page import="model.Appointment"%>
<%@page import="model.User"%>
<%@page import="controller.Controller"%>
<%@page import="java.sql.Time"%>
<%@page import="java.util.Date"%>
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
     <body id = "scroll-style" class = "page-content" >
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
                    <li class ="active-button"><a href="user-appointments.jsp">APPOINTMENTS</a></li>
                    <li class="divider"></li>
                    <li><a  href="hospitals.jsp">HOSPITALS</a></li>
                    <li class="divider"></li>
                    <li style= "margin-right: 10px;"><a href="availabledocs.jsp">DOCTORS</a></li>
                </ul>
                <!-- Right Nav Section -->
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
                            <a href = "user-account-settings.jsp"><label id = "left-bar-account">Account Settings</label></a>
                            <a href= "index.jsp" id = "left-bar-logout">Logout </a> <br>
                            
                        </div>
                    </div>
                </div>
                <div id = "mid-content" class = "row appointment-content" >
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
                      <form action = "UserAppointmentServlet" method = "post">
                         <%
                         	String notif;
                         	
                         	Iterator i = con.getPatientAppointments(user.getUserID());
                         	
                         	if(i != null)
                         		while(i.hasNext())
                         		{
                         			Appointment a = (Appointment)i.next();
                         			DoctorSchedule ds = con.getDoctorSchedule(a.getDoctorSchedID());
                         			Hospital h = con.getHospitalByID(ds.getHospitalScheduleID());
                         			Doctor d = con.getDoctor(ds.getDoctorScheduleID());
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
			                             
	                              <input type = "submit" id=<%=a.getAppID() %> name = <%=a.getAppID()%> class = "appointment-card-button" value = "Resolve" onClick = "getResolveID(this);">
	                              <input type = "hidden" name = "resolveID" id = "resolveID">
	                        </ul>
                        <% } 
                         	else
                         	{
                        %>	
                        	<h4 style= "font-family: cenutry gothic, sans-serif;">No Scheduled Appointments</h4>
                        <%
                        	}
                        %>
                        </form>
                    </div>
                </div>
            </section>
        </div>

        <script src = "Foundation/js/vendor/jquery.js"></script>
        <script src = "Foundation/js/foundation.min.js"></script>
        <script src = "Foundation/js/foundation/foundation.js"></script>
        <script src = "Foundation/js/foundation/foundation.topbar.js"></script>
        <script src = "Foundation/js/foundation/foundation.reveal.js"></script> 
        <script src="Foundation/js/vendor/modernizr.js"></script>
        <script src="Foundation/js/foundation/foundation.alert.js"></script>
        <script src = "javascript.js"></script> 
        
                  <script>
          

          $(document).ready(function()
          		{
          			$("#searchbox").keyup(function()
          			{
          				$("#suggest").html("");
          				var searchbox = $("#searchbox").val();
          				$.ajax({
          							type: "POST",
          							url: "SearchCompleteServlet",
          							data: {"searchbox": searchbox},
          								error: function(data)
          								{
          									alert("ERROR: " + data);
          								},
          							success: function(data){
          								$("#suggest").html(data);
          								$("#suggest ul li").mouseover(function(){
          									$("#suggest ul li").removeClass("hover");
          									$(this).addClass("hover");
          									
          								});
          								$("#suggest ul li").click(function(){
          									var value = $(this).html();
          									$("#searchbox").val(value);
          									$("#suggest ul").remove();
          								});
          							}

          				  });
          			});          			
          		});
          
          </script>
        
    </body>
</html>
