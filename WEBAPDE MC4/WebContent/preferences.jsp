<%@page import="control.Controller"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head></head>
    <body onload="foo()">
        <%
            Controller con = Controller.getInstance();
            String bg="", f="", p="";

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("bgColor"+ con.getUser().getUserID())) {
                        bg = cookie.getValue();
                    }
                    if (cookie.getName().equals("fontColor"+con.getUser().getUserID())) {
                        f = cookie.getValue();
                    }
                    if (cookie.getName().equals("postColor"+con.getUser().getUserID())) {
                        p = cookie.getValue();
                    }
                }
            }

            if (bg == null || bg.equals("")) {
                bg = "blue";
            }
            if (f == null || f.equals("")) {
                f = "black";
            }
            if (p == null || p.equals("")) {
                p = "white";
            }
        %>
        <div>
            <form method ="post" action = "PreferencesServlet">
                <h4>Choose your preferred theme :</h4>

                <label>Background: </label>
                <select id = "bgColor" name = "bgColor">
                    <option value="Black">Black</option>
                    <option value="White">White</option>
                    <option value="Red">Red</option>
                    <option value="Yellow">Yellow</option>
                    <option value="Green">Green</option>
                    <option value="Blue">Blue</option>
                </select>
                <br>

                <label>Font Color: </label>
                <select id= "fontColor" name = "fontColor">
                    <option value="Black">Black</option>
                    <option value="White">White</option>
                    <option value="Red">Red</option>
                    <option value="Yellow">Yellow</option>
                    <option value="Green">Green</option>
                    <option value="Blue">Blue</option>
                </select>
                <br>

                <label>Post Color: </label>
                <select id= "postColor" name = "postColor">
                    <option value="Black">Black</option>
                    <option value="White">White</option>
                    <option value="Red">Red</option>
                    <option value="Yellow">Yellow</option>
                    <option value="Green">Green</option>
                    <option value="Blue">Blue</option>
                </select>
                <br>
                <input type = "submit" value = "Submit">
            </form>
        </div>
    <script>
            function foo() {
                var element = document.getElementById('bgColor');
                for (iLoop = 0; iLoop < element.options.length; iLoop++)
                {
                    if (element.options[iLoop].value == "<%=bg%>")
                    {
                        element.options[iLoop].selected = true;
                        break;
                    }
                }
                
                element = document.getElementById('fontColor');
                for (iLoop = 0; iLoop < element.options.length; iLoop++)
                {
                    if (element.options[iLoop].value == "<%=f%>")
                    {
                        element.options[iLoop].selected = true;
                        break;
                    }
                }
                
                element = document.getElementById('postColor');
                for (iLoop = 0; iLoop < element.options.length; iLoop++)
                {
                    if (element.options[iLoop].value == "<%=p%>")
                    {
                        element.options[iLoop].selected = true;
                        break;
                    }
                }
            }
        </script>
    </body>
</html>