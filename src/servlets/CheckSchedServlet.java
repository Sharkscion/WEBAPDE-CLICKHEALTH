package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

@WebServlet("/CheckSchedServlet")
public class CheckSchedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckSchedServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Controller c = new Controller();

        String date = request.getParameter("schedDate");
        String time = request.getParameter("schedTime");
        System.out.println("date is " + date);
        System.out.println("time is " + time);
        String respMess = "";
        respMess += "date is " + date + "\ntime is " + time;

        
        // check if the schedule typed is part of the days of doc
        // check if available yung time (hindi pending)
        // check if the schedule typed is in the time of doc

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(respMess);

	}

}
