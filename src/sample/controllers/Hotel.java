package sample.controllers;


public class Hotel {

    HotelService hotel = new HotelService();

    public Hotel() {
    }

    //1. thêm hotel mới
    public void addHotel() {
        hotel.addHotel();
    }

    //2. kiểm tra hotel 
    public void checkExist() {
        hotel.checkExistsHotel();
    }

    //3. cập nhật thông tin
    public void updateHotelInfo() {
        hotel.updateHotelInformation();
    }

    //4. xóa hotel
    public void deleteHotel() {
        hotel.deleteHotel();
    }

    //5. tìm kiếm hotel
    public void searchHotel() {
        hotel.searchHotel();
    }

    //6. hiển thị ds hotel
    public void displayHotel() {
        hotel.displayHotel();
    }
    
    //7. Hiển thị thông tin hotel cuối cùng trong ds
    public void displayLastHotelInfo() {
        hotel.displayLastHotelInfo();
    }
}
