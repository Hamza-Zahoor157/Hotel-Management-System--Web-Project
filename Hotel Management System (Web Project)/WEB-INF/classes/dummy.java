import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class dummy extends HttpServlet{

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{        
	

    PrintWriter out = response.getWriter();
	Connection con = null;
	Statement st = null;

	out.println("<h1>Cheen tapakkk dam dam</h1>");
	
	out.close();
	}


}