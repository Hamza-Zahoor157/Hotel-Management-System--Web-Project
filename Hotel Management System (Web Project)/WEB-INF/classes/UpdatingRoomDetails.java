import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class UpdatingRoomDetails extends HttpServlet{

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{           
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
	HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("userType") != "admin"){
       	out.println("<h1>Session Expired! Please login again...</h1>");
	    out.println("<a href = 'admin.html'> Login</a>");
		return;
	}
	
	
	try{
		RoomDAO roomDAO = new RoomDAO();
		RoomDetails roomdetails = new RoomDetails();
		

		int oldroomid = Integer.parseInt(request.getParameter("oldroomid"));
		int roomid = Integer.parseInt(request.getParameter("roomid"));
		String roomtype = request.getParameter("roomtype");
		int price = Integer.parseInt(request.getParameter("price"));
		String str_availability = request.getParameter("availability");
		
		boolean availability;
		if("true".equalsIgnoreCase(str_availability) || "1".equals(str_availability)){
			availability = true;
		}
		else if("false".equalsIgnoreCase(str_availability) || "0".equals(str_availability)){
			availability = false;
		}
		else{
			out.println("<h1>Invalid value for availability...</h1>");
			return;
		}
		
		roomdetails.setRoomId(roomid);
		roomdetails.setRoomType(roomtype);
		roomdetails.setPrice(price);
		roomdetails.setAvailability(availability);

		int res = roomDAO.UpdateRoomDetails(roomdetails, oldroomid);
		 if(res > 0){
			out.println("<h1>Record updated successfully.</h1>");
			out.println("<br><br><form action = 'Welcome' method = 'post'>Click on the button for moving to dashboard &nbsp;&nbsp;&nbsp;&nbsp; <input type = 'submit' value = 'Go to Dashboard' name = 'dashboard'> </form>");
		}
		 
		 else{
			 out.println("<h1>Record could not be found...<h1>");
			out.println("<a href='UpdateRoomForm'>CLick here to go back </a>");
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

