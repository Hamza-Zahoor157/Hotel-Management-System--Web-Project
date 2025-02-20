import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class CancelReservation extends HttpServlet{

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{           
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
	HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("userType") != "customer"){
     	out.println("<h1>Session Expired! Please login again...</h1>");
	    out.println("<a href = 'CustomerLogin.html'> Login</a>");
		return;
	}
	
	String res = "";
	try{
		RoomDAO roomDAO = new RoomDAO();
		Booking booking = new Booking();

		int bookingid = Integer.parseInt(request.getParameter("bookingid"));
		booking.setBookingId(bookingid);

		res = roomDAO.CancelBookingFun(booking);



		if(res.equalsIgnoreCase("invalid booking")){
			out.println("<h1>Invalid BookingID...</h1>");
			out.println("<a href='CancelReservationForm'>Go back</a>");
			return;
		}
		
		if(res.equalsIgnoreCase("room not updated")){
			out.println("<h1>Room not updated...</h1>");
			out.println("<a href='CancelReservationForm'>Go back</a>");
			return;
		}
		
		if(res.equalsIgnoreCase("booking not cancelled")){
			out.println("<h1>Failed to Cancel Reservation......</h1>");
			out.println("<a href='CancelReservationForm'>Go back</a>");
			return;
		}
		if(res.equalsIgnoreCase("booking cancelled")){
			out.println("<h1>Booking Cancelled Successfully...</h1>");
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
