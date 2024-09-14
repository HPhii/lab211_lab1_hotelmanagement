package sample.controllers;

import sample.dto.HotelInf;
import sample.utils.FileManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import sample.dto.IHotelService;
import sample.utils.InputHandle;
import sample.utils.DataValidationCheck;
import sample.utils.DataSearch;

public class HotelService extends HashMap<String, HotelInf> implements IHotelService {

    //đường dẫn tới file
    private final String hotelPath = new File("").getAbsolutePath() + "\\Hotel.dat";
    private ArrayList<HotelInf> arrHotel = new ArrayList<>();
    private InputHandle hi = new InputHandle();
    private DataValidationCheck dv = new DataValidationCheck();
    private FileManager hd = new FileManager();
    private DataSearch sd = new DataSearch();

    public HotelService() {
        try {
            ArrayList<String> dta = new ArrayList<>();
            dta.addAll(hd.readFromFile(hotelPath));
            hd.convertDataToHotelInf(dta, arrHotel);
            System.out.println("Data loaded successfully. Number of hotels: " + arrHotel.size());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading data from file");
        }

        if (arrHotel.isEmpty()) {
            System.out.println("Empty list, add new one");
            addHotel();
        }
    }

    private boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public void frame(HotelInf hotel) {
        String address = hotel.getHotelAddr();
        String[] parts = address.split("(?<=\\G.{70})");

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");

        // In ra dòng header
        System.out.printf("|%9s|%14s|%22s|%70s|%11s|%7s star|\n",
                hotel.getHotelID(),
                hotel.getHotelName(),
                hotel.getHotelRoomAvai(),
                parts[0], hotel.getHotelPhone(), hotel.getHotelRating());

        // Duyệt từ phần thứ 2 trở đi                
        for (int i = 1; i < parts.length; i++) {
            // In ra dòng với khoảng trắng cho các cột không cần thiết
            System.out.printf("|%9s|%14s|%22s|%70s|%11s|%12s|\n",
                    "",
                    "",
                    "",
                    parts[i], "", "");
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void addHotel() {
        String hotelID;
        String hotelName;
        int hotelRoomAvailable;
        String hotelAddress;
        String hotelPhone;
        int hotelRating;
        boolean choice = true;
        try {
            while (choice) {
                hotelID = dv.inputHotelID(arrHotel).toUpperCase();
                hotelName = hi.getStringPattern("Enter the name of hotel: ", "^[a-zA-Z\\s]+$",
                        "Hotel's name cannot be empty!", "Hotel's name cannot contain special characters!");
                hotelRoomAvailable = hi.getInteger("Enter the number of available rooms: ", 0, 10000);
                hotelAddress = hi.getString("Enter hotel address: ", "Hotel address cannot be empty!");
                hotelPhone = hi.getStringPattern("Enter hotel phone number (like 0xxxxxxxx): ", "0\\d{9}",
                        "Phone of hotel cannot be empty!", "Phone of hotel must be like 0xxxxxxxxx!");
                hotelRating = hi.getInteger("Enter hotel rating (1-6): ", 0, 7);
                arrHotel.add(new HotelInf(hotelID.toUpperCase(), dv.formatTitleCase(hotelName),
                        hotelRoomAvailable, dv.formatTitleCase(hotelAddress),
                        hotelPhone, hotelRating));

                // Save directly to "Hotel.dat"
                hd.saveNewHotelToFile(new HotelInf(hotelID.toUpperCase(), dv.formatTitleCase(hotelName),
                        hotelRoomAvailable, dv.formatTitleCase(hotelAddress), hotelPhone, hotelRating), "Hotel.dat");
                System.out.println("Hotel added successfully!!!");

                choice = hi.inputYN("Do you want to continue (Y/N): ");
            }
        } catch (Exception e) {
            System.out.println("Failed to add new hotel. Error: " + e.getMessage());
        }
    }

    @Override
    public void checkExistsHotel() {
        boolean choice = true;
        while (choice) {
            String id = hi.getStringCanBlank("Enter id of hotel you want to check: ");
            HotelInf hotel = sd.searchHotelBaseOnID(arrHotel, id);

            if (hotel != null) {
                System.out.println("Exist hotel in the current list.");
                frame(hotel);
            } else {
                // If the hotel is not found in the current list, check in the file
                ArrayList<HotelInf> arrFromFile = new ArrayList<>();
                hd.loadDataFromFile(arrFromFile, "Hotel.dat");
                HotelInf hotelFromFile = sd.searchHotelBaseOnID(arrFromFile, id);

                if (hotelFromFile != null) {
                    System.out.println("Exist hotel in the file.");
                    frame(hotelFromFile);
                } else {
                    System.out.println("No hotel found!");
                }
            }
            choice = !(hi.inputYN("Do you want to back to home main menu (Y/N): "));
        }
    }

    @Override
    public void updateHotelInformation() {
        String hotel_ID = hi.getStringCanBlank("Enter id of hotel you want to update: ").toUpperCase();
        HotelInf hotel = sd.searchHotelBaseOnID(arrHotel, hotel_ID);

        if (hotel == null) {
            System.out.println("Hotel does not exist.");
            return;
        }

        System.out.println("Found! Here is the hotel information: ");
        frame(hotel);

        String hotel_Name = dv.inputNameUpdate("Enter name you want to update (Press Enter to keep the old name): ", hotel);
        int hotel_Room_Available = dv.inputAvaRoomUpdate("Enter available rooms of hotel update (Press Enter to keep the old value): ", 0, 10000, hotel);
        String hotel_Address = dv.inputAddressUpdate("Enter hotel address you want to update (Press Enter to keep the old address): ", hotel);
        String hotel_Phone = dv.inputPhoneUpdate("Enter phone number you want to update (Press Enter to keep the old phone number): ", hotel);
        int hotel_Rating = dv.inputHotelRatingUpdate("Enter hotel rating you want to update (Press Enter to keep the old rating): ", 0, 5, hotel);

        // Update hotel information in the list
        HotelInf updatedHotel = new HotelInf(hotel_ID,
                (isBlank(hotel_Name) ? hotel.getHotelName() : hotel_Name),
                (hotel_Room_Available == 0 ? hotel.getHotelRoomAvai() : hotel_Room_Available),
                (isBlank(hotel_Address) ? hotel.getHotelAddr() : hotel_Address),
                (isBlank(hotel_Phone) ? hotel.getHotelPhone() : hotel_Phone),
                (hotel_Rating == 0 ? hotel.getHotelRating() : hotel_Rating)
        );

        //tìm hotel trong arrayList để update, indexOf giúp lấy vị trí hotel cũ
        arrHotel.set(arrHotel.indexOf(hotel), updatedHotel);

        // Save updated data to the file
        hd.saveUpdatedDataToFile(arrHotel, hotelPath);

        System.out.println("Here is the hotel after update: ");
        frame(updatedHotel);
    }

    @Override
    public void deleteHotel() {
        String hotel_ID = hi.getStringCanBlank("Enter ID of hotel you want to delete: ");
        HotelInf hotel = sd.searchHotelBaseOnID(arrHotel, hotel_ID);

        if (hotel != null) {
            System.out.println("Found hotel:");
            frame(hotel);
            boolean confirmDelete = hi.inputYN("Do you really want to delete this hotel (Y/N): ");

            if (confirmDelete) {
                if (arrHotel.remove(hotel)) {
                    System.out.println("Delete successfully!\n");
                    hd.saveUpdatedDataToFile(arrHotel, hotelPath);
                } else {
                    System.out.println("Failed to delete the hotel.\n");
                }
            } else {
                System.out.println("Delete canceled by user.\n");
            }
        } else {
            System.out.println("Hotel not found!\n");
        }
    }

    @Override
    public void searchHotel() {
        ArrayList<HotelInf> arrTemp = new ArrayList<>(arrHotel);
        System.out.println("Select the way to search hotels: ");
        System.out.println("1. Search by Hotel_Id");
        System.out.println("2. Search by Hotel_Name");
        System.out.println("3. Exit");

        int choice;
        do {
            choice = hi.getIntChoice(1, 3);

            switch (choice) {
                case 1:
                    sd.searchHotelById(arrTemp);
                    break;
                case 2:
                    sd.searchHotelByName(arrTemp);
                    break;
                case 3:
                    System.out.println("Bye!");
                    break;
            }
        } while (choice != 3);
    }

    @Override
    public void displayHotel() {
        Collections.sort(arrHotel, Comparator.comparing(HotelInf::getHotelName).reversed());

        // Hiển thị danh sách đã sắp xếp
        System.out.printf("|%9s|%14s|%22s|%70s|%11s|%12s|\n", "Hotel_ID", "Hotel_Name", "Hotel_Room_Available", "Hotel_Address", "Hotel_Phone", "Hotel_Rating");
        for (HotelInf hotelInfomation : arrHotel) {
            frame(hotelInfomation);
        }
    }

    @Override
    public void displayLastHotelInfo() {
        ArrayList<HotelInf> arr = new ArrayList<>(arrHotel);

        if (arr.isEmpty()) {
            System.out.println("Hotel list is empty.");
            return;
        }
        HotelInf lastHotel = arr.get(arr.size() - 1);
        frame(lastHotel);
    }
}
