package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Controller c = new Controller();
        User user = c.validateUser(request.getParameter("logusername"), request.getParameter("logpassword"));
        System.out.println("Username " + request.getParameter("logusername"));
        System.out.println("Password " + request.getParameter("logpassword"));

       // System.out.println("From db " + user.getType());
        
        if (user != null)
        {
        	Cookie loginCookie = new Cookie("user",user.getUsername());
            response.addCookie(loginCookie);
            loginCookie.setMaxAge(30*60);
            if (user.getType().equals("patient"))
            {
                response.sendRedirect("hospitals.jsp");
            }
            else if (user.getType().equals("doctor"))
            {
            	//request.getSession().setAttribute("doctor", request.getParameter("logusername"));
                response.sendRedirect("doctor-appointment-requests.jsp");
            	//response.sendRedirect("trial.jsp");
            }
        }
        else
        {
            response.sendRedirect("loginError.jsp");
        }
    }

}
