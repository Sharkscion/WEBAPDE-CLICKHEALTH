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
        
    </head>
    <body id = "scroll-style" class = "page-content">
        <%
            Controller c = new Controller();
            Iterator iterator = c.getDoctors();
            
            Doctor doc= (Doctor) session.getAttribute("doctor");
            System.out.println("name = " + doc.getFirstname());
            System.out.println("name = " + doc.getLastname());
            System.out.println("ID = " + doc.getUserID());
            int id=-1;
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("user")){
                    id=Integer.parseInt(cookie.getValue());
                }
            }
            User user = (User) c.getUserInstance(String.valueOf(id));
            Cookie cookieDoc = new Cookie("doctor", String.valueOf(doc.getUserID()));
            response.addCookie(cookieDoc);
            
            session.setAttribute("currentUser", user);
            session.setAttribute("doctor", doc);
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
                <div id = "mid-content" class = "row">
                    <form action="AppointmentServlet" method="post">
                        <div id="form-content" class="large-12 columns">
                        <div class="row" >
                            <div class="large-12 columns">
                            <p>Hospital Name: MakatiMed<br>Hospital Address: Ayala Ave, Makati<br> Doctor Name: Dr. <%=doc.getFirstname()+" "+doc.getLastname()%><br>Specialization: <%=doc.getSpecialization()%><br>Specialization Details: Pediatrics is the field of medicine for children. Areas of Concern include fever, colds, vaccinations, etc.</p>
                            </div>
                        </div>
                            <hr>
                        <div class="row">
                            <div class="large-12 columns">
                            <p>Name: <%=user.getFirstname()+" "+user.getLastname()%><br>Contact Number: 09xx-xxx-xxxx<br>Email: <%=user.getEmail()%></p>
                            
                            <form action = "ContactDocServlet" method = "post">
                            <label>Date of Appointment: </label><input id="date" name = "date" type = "date">
                            
                            <label>Area of Concern: </label>
                            <select id="dropdown" name = "dropdown">
                                <option>Vaccination</option>
                                <option>Check-up</option>
                                <option>Therapy</option>
                                <option>Medication</option>
                            </select>
                            
                            <label>Remarks: </label><br><textarea id="textarea" name = "textarea" cols = "45" rows = "6"></textarea>
                            
                            <input type="submit" class = "contact-button" value="Submit">
                            </form>
                            
                            </div>
                        </div>
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
        
    </body>
</html>
