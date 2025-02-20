import java.sql.*;
import java.util.*;

public class RoomDAO{
    private Connection conn;
    private Statement st;

    public RoomDAO() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/webproject", "root","root");
        st = conn.createStatement();
    }

    public int addRoomDetails(RoomDetails roomdetails) throws SQLException{
        String query="INSERT into roomdetails(roomid,roomtype,price,availability) values("+ roomdetails.getRoomId() +", '"+ roomdetails.getRoomType() +"', "+ roomdetails.getPrice() +", "+ roomdetails.getAvailability() +");";
        int rs = st.executeUpdate(query);
        return rs;
    }
    
    public int DeleteRoomDetails(RoomDetails roomdetails) throws SQLException{
        String query="DELETE FROM roomdetails WHERE roomid = " + roomdetails.getRoomId() + "";
	    int rs = st.executeUpdate(query);
        return rs;
    }

    public int UpdateRoomDetails(RoomDetails roomdetails, int oldroomid) throws SQLException{
        String query="UPDATE roomdetails SET roomid = " + roomdetails.getRoomId() + ",roomtype = '" + roomdetails.getRoomType() + "', price = " + roomdetails.getPrice() + ", availability = "+ roomdetails.getAvailability() +" WHERE roomid = " + oldroomid + ";"; 
	    int rs = st.executeUpdate(query);
        return rs;
    }

    public ArrayList<Booking> ViewReservationDetailsFun()throws SQLException{

        ArrayList <Booking> arr = new ArrayList<>();
        String query = "SELECT * FROM reservations";
		ResultSet rs = st.executeQuery(query);
        Booking booking = null;
        while(rs.next()){
			int bookingid = rs.getInt("bookingid");
			String customername = rs.getString("customername");
			int roomid = rs.getInt("roomid");
            booking = new Booking(bookingid,customername,roomid);
            arr.add(booking);
        }
        return arr;
    }

   
    public ArrayList<RoomDetails> SearchRoomsFun(RoomDetails roomdetails, int maxprice, int minprice)throws SQLException{

        ArrayList <RoomDetails> arr = new ArrayList<>();
        String query = "SELECT * FROM roomdetails WHERE roomtype = '" + roomdetails.getRoomType() +"' AND price BETWEEN "+ minprice +" AND "+ maxprice +" AND availability = 1";
		ResultSet rs = st.executeQuery(query);
        
        RoomDetails rd = null;
        while(rs.next()){
			int roomid = rs.getInt("roomid");
			String roomtype = rs.getString("roomtype");
			int price = rs.getInt("price");
            boolean availability = rs.getBoolean("availability");

            rd = new RoomDetails(roomid,roomtype,price,availability);
            arr.add(rd);
        }
        return arr;
    }
		
    public String BookRoomFun(Booking booking) throws SQLException{
        String res = ""; 
        String checkcustomer = "SELECT * FROM customer where name = '" + booking.getCustomerName() + "';";
		ResultSet rs1 = st.executeQuery(checkcustomer);
        
        if(!rs1.next()){
             res = "invalid customer";
             return res;
        }

        String checkquery="SELECT * FROM roomdetails WHERE roomid = "+ booking.getRoomId() +";";
		ResultSet rs2 = st.executeQuery(checkquery);

        if(!rs2.next()){
            res = "invalid room";
            return res;
        }

        String updatequery="UPDATE roomdetails SET availability = 0 WHERE roomid = "+ booking.getRoomId() +";";
		int i = st.executeUpdate(updatequery);

        if(i == 0){
            res = "room not available";
            return res;
        }
 
        String bookquery="INSERT into reservations(customername,roomid) values('" + booking.getCustomerName() + "', "+ booking.getRoomId() +");";
		int j = st.executeUpdate(bookquery);

        if(j == 0){
            res = "room not booked";
            return res;
        }
        res = "room booked";
        return res;
		
    }


    public String CancelBookingFun(Booking booking) throws SQLException{
        String res = "";
        String query = "SELECT * FROM reservations WHERE bookingid = " + booking.getBookingId() + ";";
		ResultSet rs = st.executeQuery(query);
        int roomid = 0;
        if(!rs.next()){
            res = "invalid booking";
            return res;
        }
        else{
            roomid = rs.getInt("roomid");
        }

        String updatequery = "UPDATE roomdetails SET availability = 1 WHERE roomid = " + roomid + "";
		int j = st.executeUpdate(updatequery);

        if(j == 0){
            res = "room not updated";
            return res;
        }
        String cancelquery = "DELETE FROM reservations WHERE bookingid = " + booking.getBookingId() + "";
        int i = st.executeUpdate(cancelquery);

        if(i == 0){
            res = "booking not cancelled";
            return res;
        }
        res = "booking cancelled";
        return res;
    }

    public boolean LoginAdminFun(Credentials credentials) throws SQLException{
        String query = "SELECT * FROM admin WHERE name = '" + credentials.getName() +"' AND password = '" + credentials.getPassword() + "';";
		ResultSet rs = st.executeQuery(query);

        if(rs.next()){
            return true;
        }
        else{
            return false;
        }
    }

    
    public boolean LoginCustomerFun(Credentials credentials) throws SQLException{
        String query = "SELECT * FROM customer WHERE name = '" + credentials.getName() +"' AND password = '" + credentials.getPassword() + "';";
		ResultSet rs = st.executeQuery(query);
        if(rs.next()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean SignUpCustomerFun(Credentials credentials) throws SQLException{
       String query="INSERT into customer(name,password) values('"+ credentials.getName() +"', '"+ credentials.getPassword() +"');";
		int rs = st.executeUpdate(query);

        if(rs == 0){
            return false;
        }
        else{
            return true;
        }
    }

}