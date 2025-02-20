import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SearchRoomsForm extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userType") != "customer"){
            out.println("<html><body><h1>Session Expired! Please login again...</h1>");
            out.println("<a href = 'CustomerLogin.html'> Login</a></body></html>");
            return;
       }
        
        out.println("<html>");
        out.println("<head><title>Search Rooms</title></head>");
        out.println("<body>");
        out.println("<h1>Search Rooms</h1>");
        out.println("<form action='SearchRooms' method='post'>");
        out.println("Enter RoomType: <input type='text' name='roomtype' id='roomtype'><br><br>");
        out.println("Enter Max Price: <input type='number' name='maxprice' id='maxprice' min = '0'><br><br>");
        out.println("Enter Min Price: <input type='number' name='minprice' id='minprice' min = '0'><br><br>");
        out.println("<input type='submit' value='Search Rooms'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
