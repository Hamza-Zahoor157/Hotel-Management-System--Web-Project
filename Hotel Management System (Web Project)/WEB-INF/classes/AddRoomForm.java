import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddRoomForm extends HttpServlet{
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userType") != "admin"){
            out.println("<html><body><h1>Session Expired! Please login again...</h1>");
            out.println("<a href = 'admin.html'> Login</a></body></html>");
            return;
        }
        
        out.println("<html>");
        out.println("<head><title>Adding Room Details</title></head>");
        out.println("<body>");
        out.println("<form action='AddingRoomDetails' method='post'>");
        out.println("<h1>Add Room Details</h1>");
        out.println("Enter RoomId: <input type='number' name='roomid' id='roomid' min = '1' ><br><br>");
        out.println("Enter Room Type: <input type='text' name='roomtype'><br><br>");
        out.println("Enter Room Price: <input type='number' name='price' id='price' min = '0'><br><br>");
        out.println("Enter Room Availability: <input type='text' name='availability' id='availability'><br><br>");
        out.println("<input type='submit' value='Add Room Details'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
