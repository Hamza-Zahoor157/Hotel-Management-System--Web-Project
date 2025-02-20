import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CancelReservationForm extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userType") != "customer"){
            out.println("<html><body><h1>Session Expired! Please login again...</h1>");
            out.println("<a href = 'CustomerLogin.html'> Login</a></body></html>");
            return;
     }

        out.println("<html>");
        out.println("<head><title>Cancel Reservation</title></head>");
        out.println("<body>");
        out.println("<h1>Cancel Reservation</h1>");
        out.println("<form action='CancelReservation' method='post'>");
        out.println("Enter BookingId: <input type='number' name='bookingid' id='bookingid' min = '1'><br><br>");
        out.println("<input type='submit' value='Cancel Reservation'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
