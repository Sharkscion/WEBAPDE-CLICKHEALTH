package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

@WebServlet("/CheckContactSchedServlet")
public class CheckContactSchedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckContactSchedServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Controller c = new Controller();
		
		   String date = request.getParameter("date");
		   String respMess = "";
		   
	        DateFormat formatter = new SimpleDateFormat("hh:mm");
	        String start = request.getParameter("startTime");
	        String appDate = request.getParameter("date");
	        String doctorName = request.getParameter("doctorName");

		   
		   
		   if(date != null)
		   {
			   respMess = "sched is not available ";
		   }
		   else
		   {
			   respMess = "sched does not exist";
		   }
	      
	       response.setContentType("text/plain");
	       response.setCharacterEncoding("UTF-8");
	       response.getWriter().write(respMess);
		
		
	}

}
