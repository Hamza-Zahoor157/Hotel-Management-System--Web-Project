import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ViewRoomsForm extends HttpServlet{
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userType") != "admin"){
            out.println("<html><body><h1>Session Expired! Please login again...</h1>");
            out.println("<a href = 'admin.html'> Login</a></body></html>");
            return;
        }
        

        out.println("<html>");
        out.println("<head><title>View Reservation Details</title></head>");
        out.println("<body>");
        out.println("<h1>View Reservation Details</h1>");
        out.println("<form action='ViewReservationDetails' method='post'>");
        out.println("Enter to view the record: <input type='submit' value='View Reservation Details'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        }
}
