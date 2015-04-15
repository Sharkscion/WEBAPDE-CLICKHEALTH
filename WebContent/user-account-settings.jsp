<%@page import="model.Patient"%>
<%@page import="model.User"%>
<%@page import="controller.Controller"%>
<html>
    <head>
        <link rel = "stylesheet" type="text/css" href="CSS/style-menu.css">
        <link rel = "stylesheet" type="text/css" href="CSS/scrollbar.css">
        <link rel = "stylesheet" type="text/css" href="CSS/font-imports.css">
        <link rel = "stylesheet" type="text/css" href="Foundation/css/foundation.min.css">
        <link rel = "stylesheet" type="text/css" href="Foundation/css/foundation.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-doctor.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-accounts.css">

    </head>
    <%  
        Controller con = new Controller();
        String userID = "";
        User user;
        Patient patient = null;
        
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                userID = cookie.getValue();
            }
        }
        
        
        user = con.getUserInstance(userID);
        patient = con.getPatientInstance(user.getUsername());
    
    
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
                     <ul class="right">
	                    <li class="divider"></li>
	                    <li class ="active-button"><a href="user-appointments.jsp">APPOINTMENTS</a></li>
	                    <li class="divider"></li>
	                    <li><a  href="hospitals.jsp">HOSPITALS</a></li>
	                    <li class="divider"></li>
	                    <li style= "margin-right: 10px;"><a href="availabledocs.jsp">DOCTORS</a></li>
               		 </ul>
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
                            <label id = "left-bar-name"><%=user.getUsername() %></label>
                            <a><label id = "left-bar-account">Account Settings</label></a>
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
                                <p><%=patient.getFirstname()%> <%=patient.getLastname()%></p>
                            </div>
                            <div class="large-2 columns">
                                <button class = "account-button" onClick="getForm(1);">Edit</button>
                            </div>
                        </div>
                        <hr>
                        <div id="addressRow" class="row" >
                            <div class="large-3 columns account-label">
                                <p>Address:</p>
                            </div>
                            <div class="large-7 columns account-current">
                                <p><%=patient.getStreet()%>, <%=patient.getCity()%></p>
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
                                <p><%=patient.getUsername()%></p>
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
                                <p>***********</p>
                            </div>
                            <div class="large-2 columns">
                                <button class = "account-button" onClick="getForm(4);">Edit</button>
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
        

        <script type="text/javascript"> $(document).foundation(); </script>
        <script>
            $(document).ready(function() {
                var bodyheight = $(window).height();
                $(".windowheight").css('min-height', bodyheight);
                $(window).resize(function() {
                    var bodyheight = $(window).height();
                    $(".windowheight").css('min-height', bodyheight);
                });
            });


            $(function() {
                $('a[href*=#]:not([href=#])').click(function() {
                    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
                        var target = $(this.hash);
                        target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
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
                switch (i) {
                    case 1: nameRow(); break;
                    case 2: addressRow(); break;
                    case 3: usernameRow(); break;
                    case 4: passwordRow(); break;
                }
            }

            function returnRow(i)
            {
                <%String type = "";%>
                if (i == 1) {
                    document.getElementById('nameRow').innerHTML = retainName();
                    <%type = "name";%>
                }
                else if (i == 2) {
                    document.getElementById('addressRow').innerHTML = retainAddress();
                    <%type = "address";%>
                    alert("ADDRESS");
                }
                else if (i == 3) {
                    document.getElementById('usernameRow').innerHTML = retainUsername();
                    <%type = "username";%>
                    alert("USERNAME");
                }
                else if (i == 4) {
                    document.getElementById('passwordRow').innerHTML = retainPassword();
                    <%type = "password";%>
                    alert("PASSWORD");
                }
                alert(type);
                <%session.setAttribute("settingCategory", type);%>
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

            function addressRow() {
                alert("2");
                $("#addressRow").fadeOut(400, function() {
                    $(this).html("<form action=\"EditPatientServlet\" method=\"post\"><div class=\"row\"><div class=\"large-5 columns account-label\">"
                            + "<label>City:</label></div><div class=\"large-7 columns account-current\"><input type=\"text\" name=\"cityTxt\" placeholder=\"City\""
                            + " tabindex=\"1\"/></div></div>"
                            + "<div class=\"row\"><div class=\"large-5 columns account-label\">"
                            + "<label>Street:</label></div><div class=\"large-7 columns account-current\"><input type=\"text\" name=\"streetTxt\" placeholder=\"Street\""
                            + " tabindex=\"1\"/></div></div>"
                            +"<input type = \"hidden\" value =\"address\" name = \"settingCategory\" id = \"settingCategory\">"
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
                            + "<label>Current Password:</label></div><div class=\"large-7 columns account-current\"><input type=\"text\" id=\"currentpasswordTxt\" name=\"currentpasswordTxt\" required pattern=\"[a-zA-Z]+\" "
                            + "placeholder=\"Password\" tabindex=\"1\"/><small style = \"font-family: century gothic, sans-serif; font-size: 13px;\" id = \"currentpasswordError\" name = \"currentpasswordError\"> </small></div></div>"
                    
                            + "<div class=\"row\"><div class=\"large-5 columns account-label\">"
                            + "<label>New Password:</label></div><div class=\"large-7 columns account-current\"><input type=\"text\" name=\"newpasswordTxt\" placeholder=\"New Password\""
                            + " tabindex=\"1\"/></div></div>"
                    
                            + "<div class=\"row\"><div class=\"large-5 columns account-label\">"
                            + "<label>Confirm New Password:</label></div><div class=\"large-7 columns account-current\"><input type=\"text\" name=\"confirmpasswordTxt\" placeholder=\"Confirm Password\""
                            + " tabindex=\"1\"/></div></div>"
                    
                            +"<input type = \"hidden\" value =\"password\" name = \"settingCategory\" id = \"settingCategory\">"
                            + "<input type=\"submit\" class=\"account-button\" value=\"Submit\" tabindex=\"3\" onClick = \"confirmPassword();\"/></form>").fadeIn();
                });
                confirmPassword();
            }

            function retainName() {
                return "<div class=\"large-3 columns account-label\"><p>Name:</p></div><div class=\"large-7 columns account-current\">"
                        + "<p>Shark Tan</p></div><div class=\"large-2 columns\"><button class = \"account-button\" onClick=\"getForm(1);\">Edit</button></div>";
            }

            function retainAddress() {
                return "<div class=\"large-3 columns account-label\"><p>Address:</p></div>"
                        + "<div class=\"large-7 columns account-current\"><p>P. Sherman 42 Wallaby Way, Sydney</p>"
                        + "</div><div class=\"large-2 columns\"><button class = \"account-button\" onClick=\"getForm(2);\">Edit</button></div>";
            }

            function retainUsername() {
                return "<div class=\"large-3 columns account-label\"><p>Username:</p></div>"
                        + "<div class=\"large-7 columns account-current\"><p>SharkTan</p></div>"
                        + "<div class=\"large-2 columns\"><button class = \"account-button\" onClick=\"getForm(3);\">Edit</button></div>";
            }

            function retainPassword() {
                return "<div class=\"large-3 columns account-label\"><p>Password:</p>"
                        + "</div><div class=\"large-7 columns account-current\"><p>***********</p>"
                        + "</div><div class=\"large-2 columns\"><button class = \"account-button\" onClick=\"getForm(4);\">Edit</button></div>";
            }
        </script>
     
    </body>
</html>
