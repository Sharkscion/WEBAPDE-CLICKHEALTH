<%@page import="model.UserContact"%>
<%@page import="model.Hospital"%>
<%@page import="model.Doctor"%>
<%@page import="model.User"%>
<%@page import="model.DoctorSchedule"%>
<%@page import="controller.Controller"%>
<%@page import="java.sql.Time"%>
<%@page import="java.util.Iterator"%>

<html>
    <head>
        <link rel = "stylesheet" type="text/css" href="CSS/style-contact-docs.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-menu.css">
        <link rel = "stylesheet" type="text/css" href="scrollbar.css">
        <link rel = "stylesheet" type="text/css" href="font-imports.css">
        <link rel = "stylesheet" type="text/css" href="Foundation/css/foundation.min.css">
        <link rel = "stylesheet" type="text/css" href="Foundation/css/foundation.css">
        <link rel = "stylesheet" type="text/css" href="style-doctor.css">
        <link  type = "text/css" rel = "stylesheet" href = "CSS/jquery.datetimepicker.css"/>
        
    </head>
    <%
            Controller c = new Controller();
            Iterator iterator = c.getAllDoctorSchedule();
            
            DoctorSchedule ds = (DoctorSchedule) session.getAttribute("doctorSched");
    	    String userID = "";
    	    Cookie[] cookies = request.getCookies();
    	    for(Cookie cookie:cookies){
    	        if(cookie.getName().equals("user")){
    	            userID = cookie.getValue();
    	        }
    	    }
    	    
    	   User user = c.getUserInstance(userID);
    	   System.out.println("USEr NAME ID: "+ userID);
    	   System.out.println("USEr NAME: "+ user.getUsername());
    	   String uName = user.getUsername();
    	
            Cookie cookieDoc = new Cookie("doctorSched", String.valueOf(ds.getScheduleID()));
            response.addCookie(cookieDoc);
            
            session.setAttribute("currentUser", user);
            session.setAttribute("doctorSched", ds);
            
            String hospitalName = "";
            String hospitalAddress = "";
            
            String doctorName = "";
            String doctorSpec = "";
            String schedDay = "";
            String startTime = null;
            String endTime = null;
            Doctor d = null;
            if(ds != null)
            {
            	 Hospital h = c.getHospitalByID(ds.getHospitalScheduleID());
            	 d = c.getDoctor(ds.getDoctorScheduleID());
            	 //UserContact uc = (UserContact) c.getUserContacts(d.getUserID());
            	 
            	 hospitalName = h.getName();
            	 hospitalAddress =  h.getStreet() + ", "+ h.getCity();
            	 
            	 doctorName = "Dr. "+ d.getFirstname() + " "+ d.getLastname();
            	 doctorSpec = d.getSpecialization();
            	 schedDay = ds.getScheduleDay();
            	 startTime = ds.getStartTime().toString();
            	 endTime = ds.getEndTime().toString();
            }
            
          
        %>
    <body id = "scroll-style" class = "page-content" onload="setMinTime('<%=startTime.substring(0,5)%>', '<%=endTime.substring(0,5)%>')">
        
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
                    <li style= "margin-right: 10px;"><a  href="availabledocs.jsp">DOCTORS</a></li>
                
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
                            <label id = "left-bar-name"><%=uName %></label>
                            <a href = "user-account-settings.jsp">
                            	<label id = "left-bar-account">Account Settings</label></a>
                            <a href= "index.jsp" id = "left-bar-logout">Logout </a> <br>
                            
                        </div>
                    </div>
                </div>
                <div id = "mid-content" class = "row">
                	<%
	                	String success = request.getParameter("Success");
		        		String msg = "";
		        		if(success != null)
		        		{
                			if(success.equals("1"))
                			{
                	%>
	                			<div data-alert class="alert-box success radius"> 
						    		Appointment Schedule has been successfully requested! 
					        		<a href="#" class="close">&times;</a>
								</div>
						  <%
                			} 
                			else
                			{
						  %>
						  		<div data-alert class="alert-box alert radius"> 
						    		Appointment Schedule has already been occupied 
					        		<a href="#" class="close">&times;</a>
								</div>
                	<%
                			}
		        		}
                	%>
                	<form data-abide action = "ContactDocServlet" method = "post">
                        <div id="form-left-content" class="large-5 columns">
                              <div class = "row">
                              	<div class = "large-3 columns" style = "margin-top: 15px;">
                              		 <img class = "hospital-img" src="Assets/clickHealth2.png">
                              	</div>
                              	<div class = "large-9 columns">
                              		<label>
	                              	 <%=hospitalName%><br>
	                              	 <%=hospitalAddress%>
	                              	</label>
                              	</div>
                              </div>
                              <hr>
                              <label> 
                               	 Doctor Name: <%=doctorName%><br>
                               	 Specialization: <%=doctorSpec%><br><br>
                               	 Schedule Day: <%=schedDay%><br>
                               	 Start Time: <%=startTime%><br>    
                               	 End Time: <%=endTime%> <br><br>
                               	 Contact Info:
	                            	<ul>
	                            	<% 
	                            		Iterator cList = c.getUserContacts(d.getUserID());
	                            		String userContact = "";
	                            		if(cList.hasNext() == false)
	                            			userContact = "No User Contacts";
	                            		else
	                            		{
	                            			UserContact uc = (UserContact) cList.next();
	                            			String type = uc.getType();
	                            			String contact = uc.getContactInfo();
	                            			userContact = type + " : "+ contact;
	                            		
	                            	%>
		                            	<li><%=userContact%></li>
		                           <%   } %>
		                            </ul>          	
                         	  </label>
	                     </div> 
	                     <div id ="form-right-content" class="large-7 columns">
                            <label> 
                            	Name: <%=user.getFirstname()+" "+user.getLastname()%><br>
                            	Contact Info:
                            	<ul>
                            	<% 
                            		Iterator cList2 = c.getUserContacts(user.getUserID());
                            		String userContact2 = "";
                            		if(cList2.hasNext() == false)
                            			userContact2 = "No User Contacts";
                            		else
                            		{
                            			UserContact uc = (UserContact) cList2.next();
                            			String type = uc.getType();
                            			String contact = uc.getContactInfo();
                            			userContact2 = type + " : "+ contact;
                            		
                            	%>
	                            	<li><%=userContact2%></li>
	                           <%   } %>
	                            </ul>
                            </label>
	                        <label>Date of Appointment: </label>
	                        <input id="date" name = "date" type = "date">
	                         <small id = "dateTimeError" name = "dateError"> </small>
	                            
	                        <label>Start Time: </label>
		                    <input id="startTime" name = "startTime" type="text" required>
		                     <small id = "startTimeError" name = "startTimeError"> </small>
		                    <small class="error">Schedule start time is required.</small>
	                            
                            <label>Area of Concern: </label>
                            <div class = "dropdown-options">
	                            <select   id="dropdown" name = "dropdown">
	                                <option>Vaccination</option>
	                                <option>Check-up</option>
	                                <option>Therapy</option>
	                                <option>Medication</option>
	                            </select>
	                         </div>
                            <label>Remarks: </label>
                            <textarea id="remarks" name = "remarks" cols = "45" rows = "6"></textarea>
                            <input type="submit" id = "subm" name = "subm" class="contact-button" value="SUBMIT">
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
          <script src="Foundation/js/vendor/modernizr.js"></script>
          <script src="Foundation/js/foundation/foundation.alert.js"></script>
          <script src="jquery.datetimepicker.js"></script>
		  <script src = "javascript.js"></script> 
		  
		  <script>
		    
		    $('#subm').click(function() {
		        var scheddate = $('#date').val();
		        var schedtime = $('#startTime').val();
<<<<<<< HEAD
                        alert("time issssss "+ $('#startTime').val());
=======
>>>>>>> 2247597c1c6cc7e71ed7cede9f922fbcbce3d8f9
		        $.ajax({
		            url: 'CheckSchedServlet',
		            data: {"schedDate": scheddate,
		            		"schedTime": schedtime,
<<<<<<< HEAD
=======
		            		"schedDoctor": "<%=d.getLicenseID()%>",
>>>>>>> 2247597c1c6cc7e71ed7cede9f922fbcbce3d8f9
		            	  },
		            error: function(data) {
		            },
		            success: function(data) {
		            	alert(data);
		            },
		            type: 'POST'
		        });
		    });
		  
		  </script>
		  
    </body>
</html>
