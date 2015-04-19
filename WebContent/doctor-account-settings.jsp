<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Hospital"%>
<%@page import="model.DoctorSchedule"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Doctor"%>
<%@page import="model.User"%>
<%@page import="controller.Controller"%>
<html>
    <head>
        <link rel = "stylesheet" type="text/css" href="CSS/style-menu.css">
        <link rel = "stylesheet" type="text/css" href="CSS/scrollbar.css">
        <link rel = "stylesheet" type="text/css" href="font-imports.css">
        <link rel = "stylesheet" type="text/css" href="Foundation/css/foundation.min.css">
        <link rel = "stylesheet" type="text/css" href="Foundation/css/foundation.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-doctor.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-accounts.css">
        
    </head>
    
    <%  
        Controller con = new Controller();
        String userID = "";
        User user;
        Doctor doctor = null;
        
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                userID = cookie.getValue();
            }
        }
        
        
        user = con.getUserInstance(userID);
        doctor = con.getDoctor(user.getUsername());
    
        Iterator<DoctorSchedule> schedules = con.getSchedules(doctor.getLicenseID());
    
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
                    <li class="divider"></li>
                    <li class ="active-button"><a href="doctor-appointment-requests.jsp">APPOINTMENT REQUESTS</a></li>
                    <li class="divider"></li>
                    <li  style= "margin-right: 10px"><a  href="scheduled-appointments.jsp">SCHEDULED APPOINTMENTS</a></li>
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
                            <label id = "left-bar-name">Dr. <%=user.getFirstname()%> <%=user.getLastname()%></label>
                            <a href = "doctor-account-settings.jsp"><label id = "left-bar-account">Account Settings</label></a>
                            <a href= "index.jsp" id = "left-bar-logout">Logout </a> <br>
                            
                        </div>
                    </div>
                </div>
                <div id = "mid-content" class = "row">
                        <div id="settings-content" class="large-12 columns">
                            <div id="nameRow" class="row" >
                                <div class="large-3 columns account-label">
                                    <p>Name:</p>
                                </div>
                                <div class="large-7 columns account-current">
                                    <p id="currentId"><%=user.getFirstname()%> <%=user.getLastname()%></p>
                                </div>
                                <div class="large-2 columns">
                                    <button class = "account-button" onClick="getForm(1);">Edit</button>
                                </div>
                            </div>
                            <hr>
                            <div id="specializationRow" class="row" >
                                <div class="large-3 columns account-label">
                                    <p>Specialization:</p>
                                </div>
                                <div class="large-7 columns account-current">
                                    <p id="currentSpecialization"><%=doctor.getSpecialization()%></p>
                                </div>
                                <div class="large-2 columns">
                                    <button class = "account-button" onClick="getForm(2);">Edit</button>
                                </div>
                            </div>
                            <hr>
                            <div id="usernameRow" class="row" >
                                <div class="large-3 columns account-label">
                                    <p>Username:</p>
                                </div>
                                <div class="large-7 columns account-current">
                                    <p id="currentUsername"><%=user.getUsername()%></p>
                                </div>
                                <div class="large-2 columns">
                                    <button class = "account-button" onClick="getForm(3);">Edit</button>
                                </div>
                            </div>
                            <hr>
                            <div id="passwordRow" class="row" >
                                <div class="large-3 columns account-label">
                                    <p>Password:</p>
                                </div>
                                <div class="large-7 columns account-current">
                                    <p id="currentPassword">***********</p>
                                </div>
                                <div class="large-2 columns">
                                    <button class = "account-button" onClick="getForm(4);">Edit</button>
                                </div>
                            </div>  
                            <hr>
                            
                            
                            
                            
                            <hr>
                            <div id="schedulesRow" class="row" >
                                <div class="large-3 columns account-label">
                                    <p>Schedules:</p>
                                </div>
                                <div class="large-7 columns account-current">
                                    
                                    <%while(schedules.hasNext()){
                                        DoctorSchedule ds = schedules.next();
                                        Hospital hosp = con.getHospitalByID(ds.getHospitalScheduleID());
                                        String schedDay = ds.getScheduleDay();
                                        SimpleDateFormat form = new SimpleDateFormat("hh:mm");  
                                        String startTime = form.format(ds.getStartTime());
                                        String endTime = form.format(ds.getEndTime());
                                        
                                        %>
                                        
                                    
                                    <div class="row">
                                        <div class="large-4 columns">
                                            <p id="currentSpecialization"><%=schedDay%></p>
                                        </div>
                                        <div class="large-4 columns">
                                            <p id="currentSpecialization"><%=startTime%> - <%=endTime%></p>
                                        </div>
                                        <div class="large-4 columns">
                                            <p id="currentSpecialization"><%=hosp.getName()%></p>
                                        </div>
                                    </div>
                                    <%}%>
                                    
                                </div>
                                <div class="large-2 columns">
                                    <button class = "account-button" onClick="getForm(6);">Edit</button>
                                </div>
                            </div> 
                            
                            
                            
                    </div>
                </div>
            </section>
        </div>

          <script src="Foundation/js/vendor/jquery.js"></script>
          <script src="Foundation/js/foundation.min.js"></script>
          <script src="Foundation/js/foundation/foundation.js"></script>
          <script src="Foundation/js/foundation/foundation.topbar.js"></script>
          <script src= "Foundation/js/foundation/foundation.reveal.js"></script> 
          <script src = "javascript.js"></script>
          <script type="text/javascript"> 
            $(document).foundation();
          </script>
        
          <script>
            $(document).ready(function(){
                var bodyheight = $(window).height();
                $(".windowheight").css('min-height', bodyheight);
                $(window).resize(function(){
                var bodyheight = $(window).height();
                $(".windowheight").css('min-height', bodyheight);
                });
            });

      
            $(function() {
              $('a[href*=#]:not([href=#])').click(function() {
                if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
                  var target = $(this.hash);
                  target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
                  if (target.length) {
                    $('html,body').animate({
                      scrollTop: target.offset().top
                    }, 1000);
                    return false;
                  }
                }
              });
            });
         
            function getForm(i)
            {
                switch(i){
                        case 1: nameRow();break;
                        case 2: specializationRow();break;
                        case 3: usernameRow();break;
                        case 4: passwordRow();break;
                        case 6: schedulesRow();break;
                }
            }
              
            function addSched(i)
            {
                var addSched = document.getElementById('addSchedule');
                addSched.parentNode.removeChild(addSched);
                var n = i.toString();
                var tobeplaced = i+1;
                var plus = tobeplaced.toString();
                var html = "<div class=\"row\">"
                            
                            +"<div class=\"large-2 columns\">"
                            +"<input  type=\"text\" name=\"day"+n+"\" placeholder=\"Day\" tabindex=\"1\"/>"
                            +"</div>"
                    
                            +"<div class=\"large-2 columns\">"
                            +"<input  type=\"text\" name=\"startTime"+n+"\" placeholder=\"Start time\" tabindex=\"1\"/>"
                            +"</div>"
                            +"<div class=\"large-2 columns\">"
                            +"<input  type=\"text\" name=\"endTime"+n+"\" placeholder=\"End time\" tabindex=\"1\"/>"
                            +"</div>"
                    
                            +"<div class=\"large-4 columns\">"
                            +"<input value=\"\" type=\"text\" name=\"hospital"+n+"\" placeholder=\"Hospital\" tabindex=\"1\"/>"
                            +"</div>"
                    
                            
                    
                            +"<div class=\"large-2 columns\">"
                            +"<div id=\"addSchedule\">"
                            +"<input type=\"button\" class=\"account-button\" value=\"+\" tabindex=\"3\" onClick = \"addSched("+plus+");\"/>"
                            +"</div></div>"
                    
                    
                            +"</div>";
                
                $(html).hide().appendTo("#schedulesDIV").fadeIn(1000);
            }  
              
            function returnRow(i)
            {
                switch(i){
                        case 1: document.getElementById('nameRow').innerHTML = retainName();break;
                        case 2: document.getElementById('specializationRow').innerHTML = retainSpecialization();break;
                        case 3: document.getElementById('usernameRow').innerHTML = retainUsername();break;
                        case 4: document.getElementById('passwordRow').innerHTML = retainPassword();break;
                }
            }
              
            function nameRow() {

                $("#nameRow").fadeOut(400, function() {
                    $(this).html("<form action=\"EditPatientServlet\" method=\"post\"><div class=\"row\"><div class=\"large-5 columns "
                            + "account-label\">" + "<label>First Name:</label></div><div class=\"large-7 columns account-current\">"
                            + "<input type=\"text\" name=\"firstNameTxt\" placeholder=\"First Name\" tabindex=\"1\"/></div></div><div class=\"row\">"
                            + "<div class=\"large-5 columns account-label\"><label>Last Name:</label></div><div class=\"large-7 columns account-current\">"
                            + "<input type=\"text\" name=\"lastNameTxt\" placeholder=\"Last Name\" tabindex=\"2\"/></div></div>"
                            +"<input type = \"hidden\" value =\"name\" name = \"settingCategory\" id = \"settingCategory\">"
                            + "<input type=\"submit\" class=\""
                            + "account-button\" value=\"Submit\" tabindex=\"3\" onClick = \"returnRow(1);\"/></form>").fadeIn();
                });
            }

            function specializationRow() {
                alert("2");
                $("#specializationRow").fadeOut(400, function() {
                    $(this).html("<form action=\"EditPatientServlet\" method=\"post\"><div class=\"row\"><div class=\"large-5 columns account-label\">"
                            + "<label>Specialization</label></div><div class=\"large-7 columns account-current\"><input type=\"text\" name=\"specializationTxt\" placeholder=\"Specialization\""
                            + " tabindex=\"1\"/></div></div>"
                            
                            +"<input type = \"hidden\" value =\"specialization\" name = \"settingCategory\" id = \"settingCategory\">"
                            +"<input type=\"submit\" class=\"account-button\" value=\"Submit\" tabindex=\"3\" onClick = \"returnRow(2);\"/"
                            + "></form>").fadeIn();
                });
            }

            function usernameRow() {
                alert("3");
                $("#usernameRow").fadeOut(400, function() {
                    $(this).html("<form action=\"EditPatientServlet\" method=\"post\"><div class=\"row\"><div class=\"large-5 columns account-label\">"
                            + "<label>Username:</label></div><div class=\"large-7 columns account-current\"><input type=\"text\" name=\"usernameTxt\" "
                            + "placeholder=\"Username\" tabindex=\"1\"/></div></div>"
                            +"<input type = \"hidden\" value =\"username\" name = \"settingCategory\" id = \"settingCategory\">"
                            + "<input type=\"submit\" class=\"account-button\" value=\"Submit\" tabindex=\"3\" onClick = \"returnRow(3);\"/></form>").fadeIn();
                });
            }

            function passwordRow() {
                alert("4");
                $("#passwordRow").fadeOut(400, function() {
                    $(this).html("<form action=\"EditPatientServlet\" method=\"post\"><div class=\"row\"><div class=\"large-5 columns account-label\">"
                            + "<label>Current Password:</label></div><div class=\"large-7 columns account-current\"><input type=\"password\" id=\"currentpasswordTxt\" name=\"currentpasswordTxt\"  "
                            + "placeholder=\"Password\" tabindex=\"1\"/><small style = \"font-family: century gothic, sans-serif; font-size: 13px;\" id = \"currentpasswordError\" name = \"currentpasswordError\"> </small></div></div>"
                    
                            + "<div class=\"row\"><div class=\"large-5 columns account-label\">"
                            + "<label>New Password:</label></div><div class=\"large-7 columns account-current\"><input type=\"password\" name=\"newpasswordTxt\" placeholder=\"New Password\""
                            + " tabindex=\"1\"/></div></div>"
                    
                            + "<div class=\"row\"><div class=\"large-5 columns account-label\">"
                            + "<label>Confirm New Password:</label></div><div class=\"large-7 columns account-current\"><input type=\"password\" name=\"confirmpasswordTxt\" placeholder=\"Confirm Password\""
                            + " tabindex=\"1\"/></div></div>"
                    
                            +"<input type = \"hidden\" value =\"password\" name = \"settingCategory\" id = \"settingCategory\">"
                            + "<input type=\"submit\" class=\"account-button\" value=\"Submit\" tabindex=\"3\" onClick = \"confirmPassword();\"/></form>").fadeIn();
                });
                confirmPassword();
            }
              
            
              
            function schedulesRow(){
                $("#schedulesRow").fadeOut(400,function(){
                $(this).html("<form action=\"EditPatientServlet\" method=\"post\">"
                 +"<div class=\"row\">"
                +"<div class=\"large-3 columns account-label\"><label>Schedules:</label></div>"
                +"<div class=\"large-9 columns account-current\"><div id=\"schedulesDIV\">"
        
                +"<div class=\"row\">"
                +"<div class=\"large-2 columns\">"
                +"<label>Day/s:</label>"
                +"</div>"
        
                +"<div class=\"large-2 columns\">"
                +"<label>Start time:</label>"
                +"</div>"
        
                +"<div class=\"large-2 columns\">"
                +"<label>End time:</label>"
                +"</div>"
        
                +"<div class=\"large-4 columns\">"
                +"<label>Hospital:</label>"
                +"</div>"
                
                +"<div class=\"large-2 columns\">"
                +"</div></div>"
        
                <% Iterator<DoctorSchedule> scheds = con.getSchedules(doctor.getLicenseID());
                int num=1;
                                    while(scheds.hasNext()){
                                        
                                        String i = ""+num;
                                        DoctorSchedule ds = scheds.next();
                                        Hospital hosp = con.getHospitalByID(ds.getHospitalScheduleID());
                                        String schedDay = ds.getScheduleDay();
                                        SimpleDateFormat form = new SimpleDateFormat("hh:mm");  
                                        String startTime = form.format(ds.getStartTime());
                                        String endTime = form.format(ds.getEndTime());
                                    
                            %>
                +"<div class=\"row\">"
                +"<div class=\"large-2 columns\">"
                +"<input value=\"<%=schedDay%>\" type=\"text\" name=\"day<%=i%>\" placeholder=\"Day\" tabindex=\"1\"/>"
                +"</div>"
        
                +"<div class=\"large-2 columns\">"
                +"<input value=\"<%=startTime%>\" type=\"text\" name=\"startTime<%=i%>\" placeholder=\"Day\" tabindex=\"1\"/>"
                +"</div>"
        
                +"<div class=\"large-2 columns\">"
                +"<input value=\"<%=endTime%>\" type=\"text\" name=\"endTime<%=i%>\" placeholder=\"Day\" tabindex=\"1\"/>"
                +"</div>"
        
                +"<div class=\"large-4 columns\">"
                +"<input value=\"<%=hosp.getName()%>\" type=\"text\" name=\"hospital<%=i%>\" placeholder=\"Hospital\" tabindex=\"1\"/>"
                +"</div>"
        
                <%if(!scheds.hasNext()){%>
                +"<div class=\"large-2 columns\"><div id=\"addSchedule\">"
                +"<input type=\"button\" class=\"account-button\" value=\"+\" tabindex=\"3\" onClick = \"addSched(<%=num+1%>);\"/>"
                +"</div></div>"
                <%}else{%>
                +"<div class=\"large-2 columns\"><div id=\"emptyaddSchedule\">"
                +"</div></div>"
                <%}%>
                
                +"</div>"//row
                <%num++; 
                                    }%>
                    //tobe added divs rows
                +"</div>"//schedsdiv
        
                +"</div>"
                +"<input type = \"hidden\" value =\"schedule\" name = \"settingCategory\" id = \"settingCategory\">"
                +"<input type=\"submit\" class=\"account-button\" value=\"Submit\" tabindex=\"3\" onClick = \"\"/></form>").fadeIn();  
                });
            }
 
            function retainName(){
                return "<div class=\"large-3 columns account-label\"><p>Name:</p></div><div class=\"large-7 columns account-current\">"
                +"<p>Shark Tan</p></div><div class=\"large-2 columns\"><button class = \"account-button\" onClick=\"getForm(1);\">Edit</button></div>";
            }
              
            function retainAddress(){
                return "<div class=\"large-3 columns account-label\"><p>Address:</p></div>"
                +"<div class=\"large-7 columns account-current\"><p>P. Sherman 42 Wallaby Way, Sydney</p>"
                +"</div><div class=\"large-2 columns\"><button class = \"account-button\" onClick=\"getForm(2);\">Edit</button></div>";
            }
              
            function retainUsername(){
                return "<div class=\"large-3 columns account-label\"><p>Username:</p></div>"
                +"<div class=\"large-7 columns account-current\"><p>SharkTan</p></div>"
                +"<div class=\"large-2 columns\"><button class = \"account-button\" onClick=\"getForm(3);\">Edit</button></div>";
            }
              
            function retainPassword(){
                return "<div class=\"large-3 columns account-label\"><p>Password:</p>"
                +"</div><div class=\"large-7 columns account-current\"><p>***********</p>"
                +"</div><div class=\"large-2 columns\"><button class = \"account-button\" onClick=\"getForm(4);\">Edit</button></div>";
            }
              
            function retainSpecialization(){
                return "<div class=\"large-3 columns account-label\"><p>Specialization:</p>"
                +"</div><div class=\"large-7 columns account-current\"><p>Dermatology</p>"
                +"</div><div class=\"large-2 columns\"><button class = \"account-button\" onClick=\"getForm(5);\">Edit</button></div>";
            }
        </script>
        
    </body>
</html>
