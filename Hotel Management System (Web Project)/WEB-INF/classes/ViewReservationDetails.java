import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class ViewReservationDetails extends HttpServlet{

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{        
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
	HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("userType") != "admin"){
        out.println("<html><body><h1>Session Expired! Please login again...</h1>");
        out.println("<a href = 'admin.html'> Login</a></body></html>");
        return;
    }
    ArrayList <Booking> ans = new ArrayList<>();
	try{
		out.println("<html><head><title>My First Servlet</title></head>");
		out.println("<body>");
		out.println("<table border='1'><tr><th>BookingID</th><th>Customer Name</th><th>RoomID</th></tr>");
		RoomDAO roomDAO = new RoomDAO();
		Booking booking = null;
		ans = roomDAO.ViewReservationDetailsFun();

		if(ans.size() == 0){
			out.println("<tr><td colspan='3'>No records found</td></tr>");
		}	

		for(int i = 0; i < ans.size(); i++){
			booking = (Booking) ans.get(i);
			out.println("<tr><td>"+ booking.getBookingId() +"</td><td>"+ booking.getCustomerName() +"</td><td>"+ booking.getRoomId() +"</td></tr>");	
	
		}
		out.println("</table>");
		out.println("<br><form action = 'Welcome' method = 'post'>Click on the button for moving to dashboard &nbsp;&nbsp;&nbsp;&nbsp; <input type = 'submit' value = 'Go to Dashboard' name = 'dashboard'> </form>");
		out.println("<body>");
		out.println("</html>");
		
	}
	catch(SQLException e){
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
    }
	catch(ClassNotFoundException e){
	         e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");	
	} 
	out.close(); 

	
	}


}

