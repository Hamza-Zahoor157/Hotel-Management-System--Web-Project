import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class BookRoomForm extends HttpServlet{

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
        out.println("<head><title>Book Room</title></head>");
        out.println("<body>");
        out.println("<h1>Book Room</h1>");
        out.println("<form action='BookRoom' method='post'>");
        out.println("Enter Customer Name: <input type='text' name='customername' id='customername'><br><br>");
        out.println("Enter RoomId: <input type='number' name='roomid' min = '1'><br><br>");
        out.println("<input type='submit' value='Book Room'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
