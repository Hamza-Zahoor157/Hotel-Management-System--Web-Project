import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.util.*;

public class SearchRooms extends HttpServlet{

public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{        
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
	HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("userType") != "customer"){
     	out.println("<h1>Session Expired! Please login again...</h1>");
	    out.println("<a href = 'CustomerLogin.html'> Login</a>");
		return;
	}

    ArrayList <RoomDetails> arr = null;
	try{
		RoomDAO roomDAO = new RoomDAO();
		RoomDetails roomdetails = new RoomDetails();


		String roomtype = request.getParameter("roomtype");
		int minprice = Integer.parseInt(request.getParameter("minprice"));
		int maxprice = Integer.parseInt(request.getParameter("maxprice"));
		
		roomdetails.setRoomType(roomtype);
		RoomDetails rd = null;

		arr = roomDAO.SearchRoomsFun(roomdetails,maxprice,minprice);
		
		
		out.println("<html><head><title> Search Available Rooms </title></head>");
		out.println("<body>");
		out.println("<table border='1'><tr><th>RoomID</th><th>Room Type</th><th>Price</th><th>Availability</th></tr>");
		
		if(arr.size() == 0){
			out.println("<tr><td colspan='4'>No such room available...</td></tr>");		
		
		}
		for(int i = 0; i < arr.size(); i++){
			rd = (RoomDetails) arr.get(i);
			out.println("<tr><td>"+ rd.getRoomId() +"</td><td>"+ rd.getRoomType() +"</td><td>"+ rd.getPrice() +"</td><td>"+ rd.getAvailability() +"</td></tr>");	
		}
		out.println("</table>");
		out.println("<br><form action = 'Welcome' method = 'post'>Click on the button for moving to dashboard  &nbsp;&nbsp;&nbsp;&nbsp;<input type = 'submit' value = 'Go to Dashboard' name = 'dashboard'> </form>");
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



	