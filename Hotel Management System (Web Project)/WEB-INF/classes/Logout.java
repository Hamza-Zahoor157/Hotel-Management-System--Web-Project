import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class Logout extends HttpServlet{

public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{           
	PrintWriter out = response.getWriter();
	Connection con = null;
	Statement st = null;
	HttpSession session = request.getSession(false);
	if(session == null){
		out.println("<html><body><h1>You have to login first...</h1>");
		out.println("<a href = 'index.html'>You have to login first...</a></body></html>");
		return;
	}
	if(session != null){
		session.invalidate();
	}
	response.sendRedirect("index.html");
}
}