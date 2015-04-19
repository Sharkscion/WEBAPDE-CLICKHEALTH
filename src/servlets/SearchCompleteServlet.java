package servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

@WebServlet("/SearchCompleteServlet")
public class SearchCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchCompleteServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
			Controller c = new Controller();
		
		   String searched = request.getParameter("searchbox");
		   String respMess = "";
		   
		   if(searched.equals("") == false && searched != null)
		   {
			   Iterator<String> it = c.getSpecializations(searched);
			   respMess = "<ul class = \"unorganized\">";
			   while(it.hasNext())
			   {
				   respMess += "<li>"+ it.next() + "</li>";
				   respMess += "<hr>";   
			   }
			   
			   respMess += "</ul>";
		   }
	       response.setContentType("text/plain");
	       response.setCharacterEncoding("UTF-8");
	       response.getWriter().write(respMess);
	
	}

}
