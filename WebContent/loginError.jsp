<%@page import="model.Hospital"%>
<%@page import="controller.Controller"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<%
		Controller con = new Controller();
	%>
    <head> 
        <link  type = "text/css" rel = "stylesheet" href = "Foundation/css/foundation.min.css">
        <link  type = "text/css" rel = "stylesheet" href = "Foundation/css/foundation.css">
        <link  type = "text/css" rel = "stylesheet" href = "Foundation/css/normalize.css">
        <link  type = "text/css" rel = "stylesheet" href = "CSS/jquery.datetimepicker.css"/>
        <link  type = "text/css" rel = "stylesheet" href = "CSS/style-starter.css">
        <link  type = "text/css" rel = "stylesheet" href = "CSS/style-scrollbar.css">
    </head>
    
     <body id = "scroll-style">
        <br>
        <div class = "error-page">
            <div class="contain-to-grid sticky"> 
                <nav class="top-bar" id = "menu" data-topbar>
                    <ul class="title-area">
                        <!-- Title Area -->
                        <li class="name"><img id ="logo" src ="Assets/clickHealth2.png"/> </li>
                    </ul>

                    <ul class="right">
                        <li><a href= "#" data-dropdown="user-dropdown" class="radius button button-padding nav-button" >Sign Up</a></li>
                    </ul>
                </nav>
            </div>  
            <div id = "error-box">
                <h4>ClickHealth Login</h4><hr>
              <form data-abide action = "LoginServlet" method = "post" id = "error-form">	
                    <div class = "row">

                        <div class=  "name-field">
                            <div class = "large-2 columns" style = "margin-left: 100px;">
                                Username:            	
                            </div>
                            <div class = "large-8 columns">
                                <input type="text" id = "logusername" name="logusername" tabindex="1" required/>
                                <small class = "error">Enter your username.</small> 
                            </div>   
                        </div> 
                    </div>
                    <div class = "row">
                        <div class = "password-field">
                            <div class = "large-2 columns" style = "margin-left: 100px;">
                                Password :            	
                            </div>
                            <div class = "large-8 columns error-field">
                                 <input type="password" id= "logpassword" name="logpassword" tabindex="2" required/>
                                 <small class = "error">Enter your password.</small>
                                 
                            </div>                  
                        </div> 
                    </div>
                  <div class = "row">
                    <div style = "color: red; margin-left:40%;">Either your username or your password is invalid.</div>
                    <input type="submit" class="error-login-button" value="Login" tabindex="3"/>
                  </div>
                </form>
            </div>
        </div>
      
<!--*************************************************User Sign In Drop Down*******************************************************-->
        <div id ="user-dropdown" class="f-dropdown small content form-dropdown " data-dropdown-content>
            <div class = "row user-form">
          		<ul>
              		<li><a href = "#" data-reveal-id="signUp-patient-modal" data-reveal> PATIENT </a></li>
              		<li><a href = "#" data-reveal-id="signUp-doctor-modal" data-reveal> DOCTOR </a> </li>
          		</ul>
            </div>
        </div>
<!--************************************************PATIENT SIGN UP MODAL*********************************************************-->
        <div id = "signUp-patient-modal" class="reveal-modal small form" style = "width: 900px;" data-reveal>
            <h5>SIGN UP : PATIENT</h5> 
            <form data-abide action = "PatientRegServlet" method = "post">
            	<div class = "row">
	                <div class = "large-6 columns">
		                <div class = "row name-field">
		                	<div class = "large-2 columns">
		                		<label>Firstname:</label>             	
		                	</div>
		                    <div class = "large-8 columns">
		                		 <input type = "text" id = "pFName" name = "pFName" required pattern = "[a-zA-Z]+">
		                   		 <small class = "error">Firstname is required.</small>             	
		                	</div>
		                </div>
		                <div class = "row name-field">
			                <div class = "large-2 columns">
		                		<label>Username:</label>             	
		                	</div>
		                	 <div class = "large-8 columns">
		                		 <input type = "text" id = "pUName" name = "pUName" required pattern="[a-zA-Z]+">
		                    	<small class = "error">Username is required.</small>         	
		                	</div>
		                </div>
	                </div>
	                
	                <div class = "large-6 columns">
	                	<div class = "row name-field">
	                		<div class = "large-2 columns">
	                			<label>Lastname:</label> 
	                		</div>
	                		<div class = "large-8 columns">
	                			<input type = "text" id = "pLName" name = "pLName" required pattern = "[a-zA-Z]+">
	                    		<small class = "error">Lastname is required.</small>
	                		</div>
	                	</div>
	                    
	                    <div class = "row email-field">
	                    	<div class = "large-2 columns">
	                			 <label>E-mail:</label> 
	                		</div>
	                    	<div class = "large-8 columns">
	                			<input type = "email" id = "pEmail" name = "pEmail" placeholder = "example@example.com" required>
	                    		<small class = "error">An email address is required.</small>
	                		</div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class = "row">
	                <div class = "large-2 columns">
	                	<label>Address:</label>             	
	                </div>
	                <div class="large-6 columns name-field">
	                		<input type="text" id="street" name="street" placeholder="street" required pattern="[a-zA-Z]+" data-invalid="" aria-invalid="true">
	                		<small class="error">Street is required.</small>
	                </div>	 
	                <div class = "large-4 columns name-field" >
	                	<input type = "text" id = "city" name="city" placeholder = "city" required pattern="[a-zA-Z]+">
	                	<small class="error">City is required.</small>
	                </div>
	            </div>
	
	            <div class = "row  password-field">
	            	<div class = "large-2 columns">
	            		<label>Password:</label>	
	            	</div>
	            	<div class = "large-10 columns">
		            	<input type="password" id="pPassword" name="pPassword" aria-invalid="false" required pattern="[a-zA-Z]+">
		            	<small class="error">Your password must match the requirements</small>
	            	</div>
	            </div>
	            <div class = "row password-confirmation-field">
	            	<div class = "large-2 columns">
	            		<label>Confirmation Password:</label>	
	            	</div>
	            	<div class = "large-10 columns">
		            	<input type = "password" id = "pConfirm_pswd" name = "pConfirm_pswd" data-equalto="pPassword" required pattern="[a-zA-Z]+">
	            		<small class="error">The password did not match</small>
	            	</div>
	            </div>
	            <input class ="form-submit-button" type = "submit" value = "Submit" onclick = "">
            </form>
            <a class="close-reveal-modal">&#215;</a>
        </div>
 <!--**************************************************DOCTOR MODAL***************************************************************-->
       <div id = "signUp-doctor-modal" class="reveal-modal small form" style = "width: 1000px; height: 560px;" data-reveal>
            <h5>SIGN UP : DOCTOR</h5> 
            <form  data-abide action="DoctorRegServlet" method = "post">
           		 <div class = "row">
	                <div class = "large-6 columns" style = "padding-top: 20px;">
	                    <h6 class = "doctor-SignUp-header">-Account Info-</h6>
	                    <div class = "row">
	                        <div clas ="large-12 columns" style = "margin-left: 15px;">
	                            <label> Username: </label>
	                          	<input type = "text" id = "dUName" name = "dUName" required pattern="[a-zA-Z]+">
		                    	<small class = "error">Username is required.</small>  
	                        </div>
	                    </div>
	                    <div class = "row">
	                        <div clas ="large-12 columns" style = "margin-left: 15px;">
	                            <label> E-mail: </label>
	                            <input type = "email" id = "dEmail" name = "dEmail" placeholder = "example@example.com" required pattern="[a-zA-Z]+">
	                    		<small class = "error">An email address is required.</small>
	                        </div>
	                    </div>
	                    <div class = "row">
	                        <div class = "large-6 columns">
	                            <label> Password: </label>
	                            <input type = "password" id = "dPassword" name = "dPassword" required pattern="[a-zA-Z]+">
	                            <small class="error">Password is required</small>
	                        </div>
	                        <div class = "large-6 columns">
	                            <label> Confirmation Password: </label>
	                            <input type = "password" id = "dConfirm_pswd" name = "dConfirm_pswd" data-equalto="dPassword" required pattern="[a-zA-Z]+">
	            				<small class="error">The password did not match</small>
	                        </div>
	                    </div>
	                </div>
	                <div class = "large-6 columns">
	                    <h6 class = "doctor-SignUp-header">-Doctor Info-</h6>
	                    <div class = "row">
	                        <div class = "large-6 columns">
	                            <label> Firstname:  
	                            	<input type = "text" id = "dFName" name = "dFName" required pattern="[a-zA-Z]+">
	                            </label>
	                            <small class="error">Firstname is required.</small>
	                        </div>
	                        <div class = "large-6 columns">
	                            <label> Lastname: 
	                            	<input type = "text" id = "dLName" name = "dLName" required pattern="[a-zA-Z]+">
	                            </label> 
	                            <small class="error">Lastname is required.</small>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class = "large-12 columns">
	                            <label> Doctor License ID: </label> 
	                            <input type = "text" id = "dLicense" name = "dLicense" required pattern="integer">
	                            <small class="error">License ID must match the requirements.</small>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class = "large-12 columns">
	                            <label> Specialization: </label> 
	                            <input type = "text" id = "dSpec" name = "dSpec" required pattern="[a-zA-Z]+">
	                            <small class="error">Specialization is required.</small>
	                        </div>
	                    </div>
	                    <h6 class = "doctor-SignUp-header">-Add One Schedule Day-</h6>
	                    <div class = "row">
	                        <div class = "large-6 columns dropdown-options">
	                             <label>Hospital:</label> 
	                             <select name = "hospital">
	                             		<% 
	                             			Iterator hList = con.getAllHospitals();
	                             			while(hList.hasNext())
	                             			{
	                             				Hospital h = (Hospital) hList.next();
	                             				String hospitalName = h.getName();
	                             		%>
	                                    		<option><%=hospitalName%></option>
	                                    <%
	                             			}
	                                    %>
	                             </select>
	                        </div>
	                        <div class = "large-6 columns">
	                            <label>Day:   <small>(e.g. M-W-F)</small></label>
	                            <input type = "text" id = "schedDays" name = "schedDays" required>
	                            <small class="error">Schedule Days are required.</small>
	                        </div>
	                    </div>
	                    <div class = "row">
	                        <div class = "large-6 columns">
	                            <label>Start Time:</label>
	                            <input id="datetimepicker" name = "startTime" type="text" required = ""> 
	                            <small class="error">Schedule start time is required.</small>
	                        </div>
	                        <div class = "large-6 columns">
	                            <label>End Time:</label>
	                            <input id="datetimepicker2" name = "endTime" type="text" required> 
	                            <small class="error">Schedule end time is required.</small>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
	            <input class ="form-submit-button" type = "submit" value = "Submit" onclick = "" style = "margin-left: auto; margin-right: auto;">
			</form>
            <a class="close-reveal-modal">&#215;</a>
        </div>
<!--**************************************************SCRIPTS****************************************************************** -->
        <script src="Foundation/js/vendor/jquery.js"></script>
        <script src="Foundation/js/foundation/foundation.js"></script>
        <script src="Foundation/js/foundation/foundation.topbar.js"></script>
        <script src="Foundation/js/foundation/foundation.reveal.js"></script> 
        <script src="Foundation/js/foundation/foundation.dropdown.js"></script>
        <script src="Foundation/foundation/js/vendor/custom.modernizr.js"></script>
		<script src="Foundation/js/foundation/foundation.abide.js"></script>
        <script src="jquery.datetimepicker.js"></script>
        <script src="javascript.js"></script>
   
        
       
        
    </body>


</html>