<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head> 
        <link  type = "text/css" rel = "stylesheet" href = "Foundation/css/foundation.min.css">
        <link  type = "text/css" rel = "stylesheet" href = "Foundation/css/foundation.css">
        <link  type = "text/css" rel = "stylesheet" href = "Foundation/css/normalize.css">
        <link  type = "text/css" rel = "stylesheet" href = "CSS/style-starter.css">
        <link  type = "text/css" rel = "stylesheet" href = "CSS/style-scrollbar.css">

    </head>
    <body id = "scroll-style">
        <br>
        <div class = "intro-page">
             <div class="contain-to-grid sticky"> 
              <nav class="top-bar" id = "menu" data-topbar>
                  <ul class="title-area">
                    <!-- Title Area -->
                    <li class="name"><img id ="logo" src ="Assets/clickHealth2.png"/> </li>
                  </ul>
                
                    <ul class="right">
                        <li><a href= "#" data-dropdown="user-dropdown" class="radius button button-padding nav-button" >Sign Up</a></li>
                        <li><a href= "#" data-dropdown="login-dropdown" class="radius button button-padding nav-button" >Login</a></li> 
                  </ul>
              </nav>
		     </div>  
        
            <div class = "row" >
                <center>
                    <h1 id = "tagLine"> A few clicks a day keeps the sickness away!</h1>
                    <img id = "sample-screen" src = "Assets/sample_screens.png">
                </center>
            </div>

            <div class = "row bottom-bar">
                <center><img class = "line-LearnMore" src = "Assets/leftLine.png"> 
                        <a href = "#About" class = "hvr-float" style = "font-size: 15px"> Learn More</a>
                        <img class = "line-LearnMore" src = "Assets/rightLine.png">
                </center>
            </div>
        </div>

        <div class = "about-page-1">
            <a name = "About"></a>
            <div class = "row">
                <div class = "large-8 columns">
                    <h1> What is ClickHealth? </h1>
                    <p><span style ="color: #ffffff">ClickHealth</span> is your bridge to the nearest available hospitals, doctors, and health services around you. Through this application, you will be able to easily connect with the hospitals, doctors and health services you need in just a few clicks! </p>
                </div>
                <div class = "large-4 columns">
                    <img src = "Assets/locations.png">
                </div>  
            </div>
        </div>

         <div class = "about-page-2">
            <div class = "row">
                <div class = "large-12 columns">
                    <h1>What can I find in ClickHealth?</h1>
                    <span style ="color: #696660">ClickHealth</span> contains catalogs of participating hospitals, doctors, and services all around the country. These catalogs will contain the information on the hospitals and their locations, the information on the doctors (which hospital they're in, their specializations, their schedules, their contact info etc.), the information on health services available, and the form to set an appointment with the doctor you need.          
                </div>
            </div>
        </div>
        
        <div class = "about-page-3">
            <div class = "row">
                <div class = "large-12 columns dp">
                    <h1>How do I use ClickHealth?</h1>
                    <img src = "Assets/steps.png">
                </div>
            </div>
        </div>
        
        <div class = "team-page">
            <!-- /<a name = ""></a> -->
            <div class = "row">
                <h1>ClickHealth Team</h1>
            </div>
            <br>
            <div class = "row">
                <div class = "large-4 columns demo-3">
                     <figure>
                        <img src = "Assets/shay.png"/>
                        <figcaption>
                            <h2>I AM SHAYANE!</h2>
                            <p> ClickHealth's developer and designer.<br> I am a second year BS CS-ST student from De La Salle University. </p>
                        </figcaption>
                    </figure>
                </div>
                <div class = "large-4 columns demo-3">
                     <figure>
                        <img src ="Assets/xgb.png"/>
                        <figcaption>
                            <h2>I AM CHRISTIAN!</h2>
                            <p> ClickHealth's developer and designer.<br> I am a second year BS CS-ST student from De La Salle University. </p>
                        </figcaption>
                    </figure>
                </div>
                <div class = "large-4 columns demo-3">
                     <figure>
                        <img src = "Assets/winona.png"/>
                        <figcaption>
                            <h2>I AM WINONA!</h2>
                            <p> ClickHealth's developer and designer.<br> I am a second year BS CS-ST student from De La Salle University. </p>
                        </figcaption>
                    </figure>
                </div>
            </div>
            <div class = "row member-name">
                <div class = "large-4 columns demo-3"><h3>Shayane Tan </h3></div>
                <div class = "large-4 columns demo-3"><h3>Christian Cote </h3></div>
                <div class = "large-4 columns demo-3"><h3>Winona Erive </h3></div>
            </div>

            <div class = "row footer">
                ClickHealth © 2015 by Powerpuff.<br>
                De La Salle University, 2401 Taft Avenue, Malate, Manila, Philippines
            </div>
        </div>
        
        <!-- Login Drop Down -->
        <div id ="login-dropdown" class="f-dropdown small content form form-dropdown" data-dropdown-content>
          <!--  <form  action="doctor-account.html" method="get">  -->
          	<form action = "LoginServlet" method = "post" onsubmit = "return validateLogin()">	
                <label>Username</label><input type="text" id = "logusername" name="logusername" placeholder="userName123" tabindex="1"/>
                <label>Password</label><input type="password" id= "logpassword" name="logpassword" placeholder="********" tabindex="2"/>
                <input type="submit" class="form-login-button" value="Login" tabindex="3"/>
          </form>
        </div>
         <!-- User Sign In Drop Down -->
        <div id ="user-dropdown" class="f-dropdown small content form-dropdown user-form" data-dropdown-content>
            <h5>SIGN UP AS: </h5> 
            <ul>
                <li><a href = "#" data-reveal-id="signUp-patient-modal" data-reveal> Patient </a></li>
                <li><a href = "#" data-reveal-id="signUp-doctor-modal" data-reveal> Doctor </a> </li>
            </ul>
        </div>
          <!-- PATIENT SIGN UP MODAL -->
          <div id = "signUp-patient-modal" class="reveal-modal small form" data-reveal>
              <h2>Patient: Sign Up</h2> 
              <div class = "row">
                  <div class = "large-6 columns">
                      <label> Firstname: </label> <input type = "text" id = "pFName" name = "pFName" placeholder="">
                      <label> Username: </label><input type = "text" id = "pUName" name = "pUName" placeholder = "userName123">
                  </div>
                  <div class = "large-6 columns">
                      <label> Lastname: </label> <input type = "text" id = "pLName" name = "pLName">
                      <label> E-mail: </label><input type = "email" id = "pEmail" name = "pEmail" placeholder = "example@example.com">
                  </div>
              </div>
                <div class = "row" style = "margin-left: 15px;" >
                    <label> Address: </label> 
                     <div class = "large-6 columns"><input type = "text" id = "street" placeholder = "street"></div>
                     <div class = "large-6 columns"><input type = "text" id = "city" placeholder = "city"></div>
                </div>
             
              <div class = "row"><label> Password: </label><input type = "password" id = "pPassword" name = "pPassword"> </div>
              <div class = "row"><label> Confirmation Password: </label><input type = "password" id = "PConfirm_pswd" name = "PConfirm_pswd"></div>
             
               <input class ="form-submit-button" type = "button" value = "Submit" onclick = "">
              <a class="close-reveal-modal">&#215;</a>
          </div>
        
         <!--DOCTOR MODAL -->
          <div id = "signUp-doctor-modal" class="reveal-modal small form" data-reveal>
              <h2>Doctor: Sign Up</h2> 
              <div class = "row">
                  <div class = "large-6 columns">
                      <label> Firstname: </label> <input type = "text" id = "DFName" name = "DFName">
                  </div>
                  <div class = "large-6 columns">
                      <label> Lastname: </label> <input type = "text" id = "DLName" name = "DLName">
                  </div>
              </div>
              
                <div class="row">
                    <div class="large-6 columns">
                      <label> Doctor License ID: </label> <input type = "text" id = "DLicense" name = "DLicense">
                    </div>
                </div>
              <div class = "row">
                  <div clas ="large-12 columns" style = "margin-left: 15px;">
                       <label> Username: </label><input type = "text" id = "DUName" name = "DUName" placeholder = "userName123">
                  </div>
              </div>
              <div class = "row">
                  <div clas ="large-12 columns" style = "margin-left: 15px;">
                      <label> E-mail: </label><input type = "email" id = "DEmail" name = "DEmail" placeholder = "example@example.com">
                  </div>
              </div>
              <div class = "row">
                  <div class = "large-6 columns">
                    <label> Password: </label><input type = "password" id = "DPassword" name = "password">
                  </div>
                  <div class = "large-6 columns">
                    <label> Confirmation Password: </label><input type = "password" id = "DConfirm_pswd" name = "DConfirm_pswd">
                  </div>
              </div>
                  
                  <input class ="form-submit-button" type = "button" value = "Submit" onclick = "">
              
              <a class="close-reveal-modal">&#215;</a>
          </div>
        
          <script src="Foundation/js/vendor/jquery.js"></script>
          <script src="Foundation/js/foundation/foundation.js"></script>
          <script src="Foundation/js/foundation/foundation.topbar.js"></script>
          <script src= "Foundation/js/foundation/foundation.reveal.js"></script> 
          <script src="Foundation/js/foundation/foundation.dropdown.js"></script>
          <script type="text/javascript"> 
            $(document).foundation();
              
            $(document).ready(function(){
                var bodyheight = $(window).height();
                $(".windowheight").css('min-height', bodyheight);
                $(window).resize(function(){
                var bodyheight = $(window).height();
                $(".windowheight").css('min-height', bodyheight);
                });
            });

          </script>

    
          <script>
  
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
            
            function validateLogin()
            {
       			if(document.getElementById("logusername").value.length<1 || document.getElementById("logpassword").value.length<1)       			
       			{	alert("Please fill up all fields.");
       				return false;	
       			}
         		return true;
            }
          </script>

        
    </body>
    
  
</html>