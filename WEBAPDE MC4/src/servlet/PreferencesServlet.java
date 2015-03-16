package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Controller;

/**
 * Servlet implementation class PreferencesServlet
 */
@WebServlet("/PreferencesServlet")
public class PreferencesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Controller con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreferencesServlet() {
        super();
        con = Controller.getInstance();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String background = (String)request.getParameter("bgColor");
		String font = (String)request.getParameter("fontColor");
		String post = (String)request.getParameter("postColor");
		
        Cookie cookie;
        cookie = new Cookie("bgColor"+ con.getUser().getUserID(), background);
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        
        cookie = new Cookie("fontColor"+ con.getUser().getUserID(), font);
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        
        cookie = new Cookie("postColor"+ con.getUser().getUserID(), post);
        cookie.setMaxAge(60*60*24);
        //60 * 60 * 24
        
       
        response.addCookie(cookie);
        
        response.sendRedirect("loginContent.jsp");

	}

}
