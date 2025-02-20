import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class Welcome extends HttpServlet{

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{        
	
	

    PrintWriter out = response.getWriter();
	Connection con = null;
	Statement st = null;

	out.println("<html><head><title>Common Home Page</title></head>");
	
	HttpSession session = request.getSession(false);
	if(session == null){
		out.println("<h1>Session Expired! Please login again...</h1>");
	    out.println("<a href = 'index.html'> Login</a>");
		return;
	}

	String name = (String) session.getAttribute("name");
	String userType = (String) session.getAttribute("userType");
	out.println("<body><h3>Welcome " + name + " to the Home page... ("+ userType +")</h3>");

	
	if("admin".equals(userType)){
	out.println("<h1>Admin Dashboard</h1>");
		out.println("<h3>Possible Functionalities:</h3>");
		out.println("<ul style= 'list-style: none;'>");
		out.println("<li>");
		out.println("<a href='AddRoomForm' style = 'text-decoration:none; display:block; padding-top:10px;'>Click here to Add Room</a>");
		// out.println("<form action='AddRoomForm' method='get'>");
		// out.println("<input type='submit' value='Add Room Details'>");
		// out.println("</form>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href='RemoveRoomForm' style = 'text-decoration:none;  display:block; padding-top:10px;'>Click here to Remove Room</a>");
		// out.println("<form action='RemoveRoomForm' method='get'>");
		// out.println("<input type='submit' value='Remove Room Details'>");
		// out.println("</form>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href='UpdateRoomForm' style = 'text-decoration:none;  display:block; padding-top:10px;'>Click here to Update Room</a>");
		// out.println("<form action='UpdateRoomForm' method='get'>");
		// out.println("<input type='submit' value='Update Room Details'>");
		// out.println("</form>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href='ViewRoomsForm' style = 'text-decoration:none;  display:block; padding-top:10px;'>Click here to View Room</a>");
		// out.println("<form action='ViewRoomsForm' method='get'>");
		// out.println("<input type='submit' value='View Reservation Details'>");
		// out.println("</form>");
		out.println("</li>");
		out.println("</ul>");		
	}
	else if("customer".equals(userType)){
		out.println("<h1>Customer Dashboard</h1>");
		out.println("<h3>Possible Functionalities:</h3>");
		out.println("<ul style= 'list-style: none;'>");
		out.println("<li>");
		out.println("<a href='SearchRoomsForm' style = 'text-decoration:none;  display:block; padding-top:10px;'>Click here to Search Available Rooms</a>");
		// out.println("<form action='SearchRoomsForm' method='get'>");
		// out.println("<input type='submit' value='Search Rooms' name = 'searchrooms'/>");
		// out.println("</form>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href='BookRoomForm' style = 'text-decoration:none;  display:block; padding-top:10px;'>Click here to Book Room</a>");
		// out.println("<form action='BookRoomForm' method='get'>");
		// out.println("<input type='submit' value='Book Room' name = 'bookroom'/>");
		// out.println("</form>");
		out.println("</li>");
		out.println("<li>");
		out.println("<a href='CancelReservationForm' style = 'text-decoration:none;  display:block; padding-top:10px;'>Click here to Cancel Reservation</a>");
		// out.println("<form action='CancelReservationForm' method='get'>");
		// out.println("<input type='submit' value='Cancel Reservation' name = 'cancelreservatiom'/>");
		// out.println("</form>");
		out.println("</li>");
		out.println("</ul>");
	}
	else{
		out.println("Invalid user typee....");
	}


	out.println("<h3>Click below if you want to Logout:</h3>");
	out.println("<form action='Logout' method='get'>");
	out.println("<input type='submit' value='Logout'>");
	out.println("</form></body></html>");

	out.close();
	}


}