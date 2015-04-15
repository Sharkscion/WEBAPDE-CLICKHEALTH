

<%@page import="model.Patient"%>
<%@page import="model.Appointment"%>
<%@page import="model.Doctor"%>
<%@page import="model.User"%>
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
        <link  type = "text/css" rel = "stylesheet" href = "Foundation/css/normalize.css">
        <link rel = "stylesheet" type="text/css" href="Foundation/css/foundation.min.css">
        <link rel = "stylesheet" type="text/css" href="Foundation/css/foundation.css">

    </head>
    <%
        Controller con = new Controller();
        String userID = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                userID = cookie.getValue();
            }
        }

        User user = con.getUserInstance(userID);
        String uName = user.getFirstname() + " " + user.getLastname();
    %>
    <body id = "scroll-style" class = "page-content" onLoad="loadModalOnLoad()">
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
                            <label id = "left-bar-name">Dr. <%=uName%></label>
                            <a href = "doctor-account-settings.jsp"><label id = "left-bar-account">Account Settings</label></a>
                            <a href= "index.jsp" id = "left-bar-logout">Logout </a> <br>

                        </div>
                    </div>
                </div>

                <div id = "mid-content" class = "row appointment-content appointment-request" style = "margin-top: 40px;">
                    <div class = "row appointment-request-header">
                        <div class= "large-2 columns" style = "text-align: center;"> <h6>Time Requested</h6> </div>
                        <div class= "large-2 columns" style = "text-align: center;"> <h6>Date Requested</h6> </div>
                        <div class= "large-8 columns"> <h6>Appointment Request Info</h6></div>
                    </div>
                    <%

                        Doctor d = con.getDoctorByUserId(user.getUserID());
                        Iterator<Appointment> appointments = con.getRequestAppointment(d.getLicenseID());
                        Appointment app = null;
                        Patient patient = null;

                        while (appointments.hasNext()) {
                            app = appointments.next();
                            patient = con.getPatientByID(app.getPatientID());
                    %>
                    <div id = "request-1" class = "row request-box">
                        <div class = "large-2 columns" style = "text-align: center;"> <%=app.getRequestedTime()%></div>
                        <div class = "large-2 columns" style = "text-align: center;"><%=app.getRequestedDate()%></div>
                        <div class = "large-5 columns">
                            <p style = "font-style: oblique;">
                                Appointment with <%=patient.getFirstname() + " " + patient.getLastname()%> <br>
                                Area of Concern: <%=app.getConcern()%>
                            </p>
                        </div>
                        <div class = "large-3 columns"> 
                            <input type = "button" value = " View " id = "<%=app.getAppID()%>" name = "<%=app.getAppID()%>" onClick ="getRequestID(this);">
                            <input type ="hidden" name="requestID" id="requestID">
                        </div>
                    </div>
                    <%
                        }
                    %>

                </div>


            </section>
        </div>
        <!----------------------------------------------------- VIEW REQUEST MODAL------------------------------------------------------>

        <div class="reveal-modal small form" id ="viewRequest-modal" data-reveal>
            <%
                String success = request.getParameter("Success");
                String msg = "";
                if (success != null) {
                    if (success.equals("1")) {
                        msg = "<span style= \"color: #119525;\">This appointment has successfully been approved!</span>";
                    } else if (success.equals("2")) {
                        msg = "<span style= \"color: #119525;\">This appointment have been rejected! <br>"
                                + "A notification will be sent to the patient concerning the status of the appointment!</span>";
                    } else {
                        msg = "<span style= \"color: red;\">Error: The status of this appointment has failed to change.</span>";
                    }

            %>
            <div id="request-message"><%=msg%></div>
            <%
                }
            %>
            <%
                String appId = request.getParameter("requestID");
                int id = -1;
                if (appId != null) {
                    id = Integer.parseInt(appId);
                    Appointment a = con.getAppointment(id);
                    Patient p = con.getPatientByID(a.getPatientID());
                    String patientName = p.getFirstname() + " " + p.getLastname();

            %>
            <form action = "ApproveRequestServlet" method = "post">
                <div class ="request-modal-content">
                    <h5>Appointment with <%=patientName%> </h5>
                    <h6>Requested on <%=a.getRequestedDate()%> </h6>
                    <hr>
                    <label>	
                        Area of Concern :  <%=a.getConcern()%> <br>
                        Appointment Date:  <%=a.getAppointmentDate()%><br>
                        Appointment Time:  <%=a.getStartTime()%>
                    </label>

                    <%if (a.getRemarks().equals("") == false)%>
                    <label>
                        Remarks   : <br>
                        <%=a.getRemarks()%>
                    </label>
                    <input type="submit" class = "appointment-request-button" id = "<%=a.getAppID()%>" name = "<%=a.getAppID()%>" value="Approve" onClick = "getApproveID(this);">
                    <input type="submit" class = "appointment-request-button" id = "<%=a.getAppID()%>" name = "<%=a.getAppID()%>" value="Reject" onClick = "getRejectID(this);">

                    <input type = "hidden" name = "approveID" id = "approveID">
                    <input type = "hidden" name = "rejectID" id = "rejectID">
                </div>
            </form>
            <%
                }
            %>
            <a class="close-reveal-modal">&#215;</a>
        </div>

        <script src= "Foundation/js/vendor/jquery.js"></script>
        <script src= "Foundation/js/foundation.min.js"></script>
        <script src= "Foundation/js/foundation/foundation.js"></script>
        <script src= "Foundation/js/foundation/foundation.topbar.js"></script>
        <script src= "Foundation/js/foundation/foundation.reveal.js"></script> 
        <script src="Foundation/js/vendor/modernizr.js"></script>
        <script src="Foundation/js/foundation/foundation.alert.js"></script>
        <script src = "javascript.js"></script> 
    </body>
</html>
