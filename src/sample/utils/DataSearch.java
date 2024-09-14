package sample.utils;

import sample.dto.HotelInf;
import sample.controllers.HotelService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class DataSearch {

    public HotelInf searchHotelBaseOnID(ArrayList<HotelInf> arr, String id) {
        //tìm id từ trong list, nếu có id thì trả về thông tin cả ht, không thì trả null
        for (HotelInf hotelInfo : arr) {
            if (id.equalsIgnoreCase(hotelInfo.getHotelID())) {
                return hotelInfo;
            }
        }
        return null;
    }

    public void searchHotelById(ArrayList<HotelInf> hotels) {
        System.out.print("Enter Hotel_Id to search: ");
        String id = new Scanner(System.in).nextLine();

        ArrayList<HotelInf> result = new ArrayList<>();
        for (HotelInf hotel : hotels) {
            if (hotel.getHotelID().contains(id.toUpperCase())) {
                result.add(hotel);
            }
        }

        displaySearchResult(result);
    }

    public void searchHotelByName(ArrayList<HotelInf> hotels) {
        System.out.print("Enter Hotel_Name to search: ");
        String name = new Scanner(System.in).nextLine();
        
        ArrayList<HotelInf> result = new ArrayList<>();
        for (HotelInf hotel : hotels) {
            if (hotel.getHotelName().equalsIgnoreCase(name)) {
                result.add(hotel);
            }
        }
        displaySearchResult(result);
    }

    public void displaySearchResult(ArrayList<HotelInf> result) {
        if (result.isEmpty()) {
            System.out.println("No matching hotels found.");
        } else {
            // Sort the result by Hotel_Id in descending order
            result.sort(Comparator.comparing(HotelInf::getHotelID).reversed());

            System.out.println("Search results:");
            for (HotelInf hotel : result) {
                new HotelService().frame(hotel);
            }
        }
    }
}
