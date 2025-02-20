import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class BookRoom extends HttpServlet{

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{           
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
	HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("userType") != "customer"){
  		out.println("<h1>Session Expired! Please login again...</h1>");
	    out.println("<a href = 'CustomerLogin.html'> Login</a>");
		return;
	}
	response.setContentType("text/html");
	try{
		RoomDAO roomDAO = new RoomDAO();
		Booking booking = new Booking();
		

		String customername = request.getParameter("customername");
		int roomid = Integer.parseInt(request.getParameter("roomid"));


		booking.setCustomerName(customername);
		booking.setRoomId(roomid);

		String res = roomDAO.BookRoomFun(booking);
		
		
		if(res.equalsIgnoreCase("invalid customer")){
			out.println("<h1>There is no such customer...</h1>");
			out.println("<a href='BookRoomForm'>Go back</a>");
			return;
		}

	   
		 if(res.equalsIgnoreCase("invalid room")){
			out.println("<h1>There is no such Room available...</h1>");
			out.println("<a href='BookRoomForm'>Go back</a>");
			return;
		}
		 
		if(res.equalsIgnoreCase("room not available")){
			out.println("<h1>Room availability update failed...</h1>");
			out.println("<a href='BookRoomForm'>Go back</a>");
			return;
		}
	

		if(res.equalsIgnoreCase("room not available")){
			out.println("<h1>Booking cannot be done...</h1>");	
			out.println("<a href='BookRoomForm'>Go back</a>");
			return;
		}

		if(res.equalsIgnoreCase("room booked")){
			out.println("<h1>Room booked successfully...</h1>");
			out.println("<br><br><form action = 'Welcome' method = 'post'>Click on the button for moving to dashboard &nbsp;&nbsp;&nbsp;&nbsp; <input type = 'submit' value = 'Go to Dashboard' name = 'dashboard'> </form>");
			return;
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


