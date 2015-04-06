package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

/**
 * Servlet implementation class ApproveRequestServlet
 */
@WebServlet("/ApproveRequestServlet")
public class ApproveRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Controller c = new Controller();
		
		String rejectId = request.getParameter("rejectID");
		String approveId = request.getParameter("approveID");
		String requestId = request.getParameter("requestID");
	
		int success =0;
		System.out.println("REJECT ID: "+ rejectId);
		System.out.println("APPROV ID: "+ approveId);
		System.out.println("requestID: "+ requestId);
		
		if(approveId != null && approveId.equals("")== false)
		{
			c.changeAppointmentStat(Integer.parseInt(approveId), "pending");
			success = 1;
		} 
		else if(rejectId != null && rejectId.equals("")== false)
		{
			c.changeAppointmentStat(Integer.parseInt(rejectId), "rejected");
			success = 2;
		}
		else if(rejectId == null && approveId == null)
		{
			success = -1;
		}

		response.sendRedirect("doctor-appointment-requests.jsp?Success="+ success);
	}

}
