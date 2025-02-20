public class RoomDetails{
    private int roomid;
    private String roomtype;
    private int price;
    private boolean availability;

    public RoomDetails(){
        roomid = 0;
        roomtype = "";
        price = 0;
        availability = false;
    }

    public RoomDetails(int roomid, String roomtype, int price, boolean availability){
        this.roomid = roomid;
        this.roomtype = roomtype;
        this.price = price;
        this.availability = availability;
    }

    public int getRoomId() {
        return roomid;
    }

    public void setRoomId(int roomid){
        this.roomid = roomid;
    }

    public String getRoomType(){
        return roomtype;
    }
    
    public void setRoomType(String roomtype){
        this.roomtype = roomtype;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public boolean getAvailability(){
        return availability;
    }

    public void setAvailability(boolean availability){
        this.availability = availability;
    }   

    public String toString(){
        return "Room ID: "+ roomid +", room type: "+ roomtype +" , room price: "+ price + ", room availability: "+ availability +"...";
    }

}