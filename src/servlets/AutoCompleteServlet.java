package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

/**
 * Servlet implementation class AutoCompleteServlet
 */
@WebServlet("/AutoCompleteServlet")
public class AutoCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    private ServletContext context;
    private Controller con = new Controller();
    private Iterator<String> specializations = con.getAllSpecializations();
    String specialization = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    
    public AutoCompleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
        String targetId = request.getParameter("id");
        StringBuffer sb = new StringBuffer();

        if (targetId != null)
        {
            targetId = targetId.trim().toLowerCase();
        } else {
            context.getRequestDispatcher("/hospitals.jsp").forward(request, response);
        }

        boolean namesAdded = false;
        if (action.equals("complete"))
        {

            // check if user sent empty string
            if (!targetId.equals(""))
            {

            	Iterator specs = specializations;

                while (specs.hasNext())
                {
                   
                   specialization = (String) specs.next();
                	
                    if ( // targetId matches the specialization
                    		specialization.equalsIgnoreCase(targetId)
                       )
                    {

                        sb.append("<Specialization>");
                        sb.append("<specialization>" + specialization + "</specialization>");
                        sb.append("</Specialization>");
                        namesAdded = true;
                    }
                }
            
            }
            
            if (namesAdded)
            {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<composers>" + sb.toString() + "</composers>");
            }
            else
            {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }

      
        if (action.equals("lookup"))
        {

            // put the target composer in the request scope to display 
            if ((targetId != null))
            {
                request.setAttribute("specialization", specialization);
                context.getRequestDispatcher("/hospitals.jsp").forward(request, response);
            }
        }
     
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
}
