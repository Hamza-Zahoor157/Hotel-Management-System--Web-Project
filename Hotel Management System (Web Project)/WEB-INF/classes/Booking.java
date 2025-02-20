public class Booking{
    private int bookingid;
    private String customername;
    private int roomid;
    
    public Booking(int bookingid, String customername, int roomid){
        this.bookingid = bookingid;
        this.customername = customername;
        this.roomid = roomid;
    }

    public Booking(){
        this.bookingid = 0;
        this.customername = "";
        this.roomid = 0;
    }

    public int getBookingId() {
        return bookingid;
    }
    public void setBookingId(int bookingid) {
        this.bookingid = bookingid;
    }

    public String getCustomerName() {
        return customername;
    }
    public void setCustomerName(String customername){
        this.customername = customername;
    }
    
    public int getRoomId() {
        return roomid;
    }

    public void setRoomId(int roomid){
        this.roomid = roomid;
    }

    public String toString(){
        return "Booking ID: " + bookingid + ", Customer Name: " + customername + ", Room ID: " + roomid + "....";
    }

}