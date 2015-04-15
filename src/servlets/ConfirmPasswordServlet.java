package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import javax.servlet.http.Cookie;
import model.Patient;
import model.User;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/ConfirmPasswordServlet")
public class ConfirmPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Controller con = new Controller();
        Patient p;
        String userID = "";
        User u;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                userID = cookie.getValue();
            }
        }

        u = con.getUserInstance(userID);
        p = con.getPatientInstance(u.getUsername());

        String password = request.getParameter("password");
        String respMess = "";

        if (!u.getPassword().equals(password)) {
            respMess = "wrong password!";
        } else {
            
            respMess = "password accepted!";
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(respMess);

    }

}
