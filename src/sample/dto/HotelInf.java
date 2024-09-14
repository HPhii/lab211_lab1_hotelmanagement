package sample.dto;

import java.io.Serializable;

public class HotelInf implements Comparable<HotelInf>, Serializable {

    private String hotelID;
    private String hotelName;
    private int hotelRoomAvai;
    private String hotelAddr;
    private String hotelPhone;
    private int hotelRating;

    public HotelInf(String hotelID, String hotelName, int hotelRoomAvai, String hotelAddr, String hotelPhone, int hotelRating) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.hotelRoomAvai = hotelRoomAvai;
        this.hotelAddr = hotelAddr;
        this.hotelPhone = hotelPhone;
        this.hotelRating = hotelRating;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelRoomAvai() {
        return hotelRoomAvai;
    }

    public void setHotelRoomAvai(int hotelRoomAvai) {
        this.hotelRoomAvai = hotelRoomAvai;
    }

    public String getHotelAddr() {
        return hotelAddr;
    }

    public void setHotelAddr(String hotelAddr) {
        this.hotelAddr = hotelAddr;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public int getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(int hotelRating) {
        this.hotelRating = hotelRating;
    }
    
    //format lại các dữ liệu
    @Override
    public String toString() {
        return String.format("|%9s|%17s|%5d|%20s|%11s|%4d star|\n", hotelID, hotelName, hotelRoomAvai, hotelAddr, hotelPhone, hotelRating);
    }
    
    //Sắp xếp thứ tự các hotel theo ID
    @Override
    public int compareTo(HotelInf o) {
        if (this.getHotelID().compareTo(o.getHotelID()) > 0){
            return 1;
        } else if (this.getHotelID().compareTo(o.getHotelID()) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
