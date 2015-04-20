package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Notification;
import controller.Controller;

/**
 * Servlet implementation class ApproveReschedServlet
 */
@WebServlet("/ApproveReschedServlet")
public class ApproveReschedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveReschedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("PPROVE RESCHED!");
		
		Controller c = new Controller();
		String rejectId = request.getParameter("rejectID");
		String approveId = request.getParameter("approveID");
		String url = request.getParameter("url");
		
		int id = 0;
		int success = -8;
		System.out.println("************REJECTID: "+ rejectId);
		System.out.println("****************approveID: "+ approveId);
		if(approveId != null && approveId.equals("")== false)
		{
			
			id = Integer.parseInt(approveId);
			c.changeAppointmentStat(id, "pending");
			
			success = 0;
			
		} 
		else if(rejectId != null && rejectId.equals("")== false)
		{
			id = Integer.parseInt(rejectId);
			c.changeAppointmentStat(id, "rejected");
			success = 2;
		}
		else if(rejectId == null && rejectId == null)
		{
			success = -1;
		}

		System.out.println("REQUETS URL: "+  url);
		response.sendRedirect(url+"?Success="+ success);
	}

}
