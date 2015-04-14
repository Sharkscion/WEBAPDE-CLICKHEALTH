<%@page import="model.Hospital"%>
<%@page import="model.User"%>
<%@page import="controller.Controller"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <link rel = "stylesheet" type="text/css" href="CSS/style-menu.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-scrollbar.css">
        <link rel = "stylesheet" type="text/css" href="CSS/style-hospital.css">
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
                    <li><a href="user-appointments.jsp">APPOINTMENTS</a></li>
                    <li class="divider"></li>
                    <li class = "active-button"><a  href = "#">HOSPITALS</a></li>
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
                            <label id = "left-bar-name"><%=uName%></label>
                            <a href = "user-account-settings.jsp">
                            	<label id = "left-bar-account">Account Settings</label></a>
                            <a href= "index.jsp" id = "left-bar-logout">Logout </a> <br>
                            
                        </div>
                    </div>
                </div>
                <div id = "mid-content" class = "row hospital-content">
                    <div class = "large-12 columns">
                        <%
                    		
                   			Iterator<Hospital> hospitals = con.getAllHospitals();
                        	while(hospitals.hasNext())
                        	{
                        		Hospital hospital = hospitals.next();
                        %>
	                        <div class = "row">
	                            <div class = "large-2 columns">
	                                 <img class = "hospital-img" src="Assets/clickHealth2.png">
	                            </div>
	                           <div class = "large-10 columns">
	                                <h4><%=hospital.getName() %></h4>
	                                <p><%=hospital.getStreet()%></p>
	                            </div>
	                        </div>
	                         <hr>
                         <%
                         }
                         %>
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
