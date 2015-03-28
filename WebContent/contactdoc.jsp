<%@page import="model.UserContact"%>
<%@page import="java.sql.Time"%>
<%@page import="model.Hospital"%>
<%@page import="model.DoctorSchedule"%>
<%@page import="controller.Controller"%>
<%@page import="model.User"%>
<%@page import="model.Doctor"%>
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
    <body id = "scroll-style" class = "page-content">
        <%
            Controller c = new Controller();
            Iterator iterator = c.getAllDoctorSchedule();
            
            DoctorSchedule ds = (DoctorSchedule) session.getAttribute("doctorSched");
            System.out.println("DOCTOR SCHED: "+ ds);
            System.out.println("ID: "+ ds.getScheduleID());
            System.out.println("StartTime: "+ ds.getStartTime());
  
    	    String username = "";
    	    Cookie[] cookies = request.getCookies();
    	    for(Cookie cookie:cookies){
    	        if(cookie.getName().equals("user")){
    	            username = cookie.getValue();
    	        }
    	    }
    	    
    	  	 User user = c.getUser(username);
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
            Time startTime = null;
            Time endTime = null;
            
            if(ds != null)
            {
            	 Hospital h = c.getHospitalByID(ds.getHospitalScheduleID());
            	 Doctor d = c.getDoctor(ds.getDoctorScheduleID());
            	 //UserContact uc = (UserContact) c.getUserContacts(d.getUserID());
            	 
            	 hospitalName = h.getName();
            	 hospitalAddress =  h.getStreet() + ", "+ h.getCity();
            	 
            	 doctorName = "Dr. "+ d.getFirstname() + " "+ d.getLastname();
            	 doctorSpec = d.getSpecialization();
            	 schedDay = ds.getScheduleDay();
            	 startTime = ds.getStartTime();
            	 endTime = ds.getEndTime();
            }
          
        %>
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
                    <li><a style= "margin-right: 10px;" href="availabledocs.jsp">DOCTORS</a></li>
                
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
                            <label id = "left-bar-name"><%=uName %></label>
                            <a href = "account.html"><label id = "left-bar-account">Account Settings</label></a>
                            <a href= "index.jsp" id = "left-bar-logout">Logout </a> <br>
                            
                        </div>
                    </div>
                </div>
                <div id = "mid-content" class = "row">
                
                        <div id="form-content" class="large-12 columns">
                          <form data-abide action = "ContactDocServlet" method = "post">
	                        <div class="row" >
	                            <div class="large-12 columns">
		                            <p>Hospital: <%=hospitalName%><br>
		                               Hospital Address:<%=hospitalAddress%><br><br>
		                               Doctor Name: <%=doctorName%><br>
		                               Specialization: <%=doctorSpec%><br><hr>
		                               Schedule Day: <%=schedDay%><br>
		                               Start Time: <%=startTime%><br>    
		                               End Time: <%=endTime%>            	
		                            </p>
	                            </div>
	                        </div>
                            <hr>
                        <div class="row">
                            <div class="large-12 columns">
	                            <p>
		                            Name: <%=user.getFirstname()+" "+user.getLastname()%><br>
		                            Contact Number: 09xx-xxx-xxxx<br>
		                            Email: <%=user.getEmail()%>
	                            </p>

		                        <label>Date of Appointment: </label><input id="date" name = "date" type = "date">
		                            
		                        <label>
		                            	Start Time: <input id="datetimepicker" name = "startTime" type="text" required 
		                            						onChange = "setMinTime(<%=startTime%>, <%=endTime%>)"> 
			                    </label>
			                    <small class="error">Schedule start time is required.</small>
		                            
	                            <label>Area of Concern: </label>
	                            <select id="dropdown" name = "dropdown">
	                                <option>Vaccination</option>
	                                <option>Check-up</option>
	                                <option>Therapy</option>
	                                <option>Medication</option>
	                            </select>
		                            
	                            <label>Remarks: </label><br><textarea id="remarks" name = "remarks" cols = "45" rows = "6"></textarea>
	                            
	                            <input type="submit" class = "contact-button" value="Submit">
	                          </div>
	                         </div>
	                       </form>
                         </div>
                     </div>
                  </section>
              </div>

          <script src="Foundation/js/vendor/jquery.js"></script>
          <script src="Foundation/js/foundation.min.js"></script>
          <script src="Foundation/js/foundation/foundation.js"></script>
          <script src="Foundation/js/foundation/foundation.topbar.js"></script>
          <script src= "Foundation/js/foundation/foundation.reveal.js"></script> 
          <script src="jquery.datetimepicker.js"></script>
		  <script src = "javascript.js"></script> 
        
    </body>
</html>
