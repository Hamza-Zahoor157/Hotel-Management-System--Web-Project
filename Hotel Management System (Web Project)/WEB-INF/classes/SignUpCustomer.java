import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class SignUpCustomer extends HttpServlet{

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{           
	PrintWriter out = response.getWriter();
	response.setContentType("text/html");
	
	boolean ans;		
	try{
		RoomDAO roomDAO = new RoomDAO();
		Credentials credentials = new Credentials();
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		credentials.setName(name);
		credentials.setPassword(password);
	
		ans = roomDAO.SignUpCustomerFun(credentials);

		
		 if(ans == true){
			HttpSession session = request.getSession(true);
			session.setAttribute("name", name);
			session.setAttribute("userType", "customer");
			session.setMaxInactiveInterval(60 * 60);
			RequestDispatcher rd = request.getRequestDispatcher("Welcome");
			rd.forward(request, response);
		  }
		 else{
			out.println("Record is not inserted...");
			out.println("<a href='customer.html'>Go Back</a>");
		
		}
	}
	catch(SQLException e){
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
    }
	catch(ClassNotFoundException e){
	         e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");	
	} 
    }

 
}
