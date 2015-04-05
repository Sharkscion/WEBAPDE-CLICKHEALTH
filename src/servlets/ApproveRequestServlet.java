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
		String msg = null;
		
		System.out.println("REJECT ID: "+ rejectId);
		System.out.println("APPROV ID: "+ approveId);
		System.out.println("requestID: "+ requestId);
		
		if(approveId != null)
		{
			c.changeAppointmentStat(Integer.parseInt(approveId), "pending");
			msg = "This appointment has successfully been approved!";
		} 
		else if(rejectId != null)
		{
			c.changeAppointmentStat(Integer.parseInt(rejectId), "rejected");
			msg = "This appointment have been rejected! <br>"
			    + "A notification will be send to the patient concerning the status of the appointment!";
		}
		else if(rejectId == null && approveId == null)
		{
			msg = "Error: The status of this appointment has failed to change.";
		}
		request.setAttribute("successMessage", msg);
		//response.sendRedirect("doctor-appointment-requests.jsp");
		request.getRequestDispatcher("doctor-appointment-requests.jsp").forward(request, response);
	}

}
