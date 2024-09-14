package sample.utils;

import sample.dto.HotelInf;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public FileManager() {
    }

    public boolean loadDataFromFile(List<HotelInf> hotel, String fileName) {
                                                              //đọc dữ liệu từ 1 tệp dưới dạng chuỗi
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            // Đọc danh sách khách sạn từ file và nạp vào danh sách hiện tại
            List<HotelInf> data = (List<HotelInf>) ois.readObject();
            hotel.addAll(data);
            return true;
        } catch (IOException | ClassNotFoundException e) {
            // Xử lý các ngoại lệ
            System.err.println("Error loading data from file: " + e.getMessage());
            return false;
        }
    }

    public static List<String> readFromFile(String filePath) {
        List<String> dataList = new ArrayList<>();
        try {
            // Đọc nội dung từ file vào danh sách
            Path path = Paths.get(filePath);
            dataList = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace(); //in ra lỗi và các dòng stack trace
        }
        return dataList;
    }

    public void saveNewHotelToFile(HotelInf hotel, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            // Ghi dữ liệu vào file theo định dạng
            String formattedData = String.format("%s|%s|%d|%s|%s|%d",
                    hotel.getHotelID(),
                    hotel.getHotelName(),
                    hotel.getHotelRoomAvai(),
                    hotel.getHotelAddr(),
                    hotel.getHotelPhone(),
                    hotel.getHotelRating());

            writer.println(formattedData);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void saveUpdatedDataToFile(ArrayList<HotelInf> arrHotel, String hotelPath) {
        // Viết vào file với lớp printwriter
        try (PrintWriter writer = new PrintWriter(new FileWriter(hotelPath))) {
            for (HotelInf hotel : arrHotel) {
                String formattedData = String.format("%s|%s|%d|%s|%s|%d",
                        hotel.getHotelID(),
                        hotel.getHotelName(),
                        hotel.getHotelRoomAvai(),
                        hotel.getHotelAddr(),
                        hotel.getHotelPhone(),
                        hotel.getHotelRating());

                writer.println(formattedData);
            }
            System.out.println("Updated data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving updated data to file: " + e.getMessage());
        }
    }

    public void convertDataToHotelInf(ArrayList<String> data, ArrayList<HotelInf> arrHotel) {
        for (String line : data) {
            String[] parts = line.split("\\|"); //cắt data trong file ra từng token
            if (parts.length == 6) { // xếp các token vào data tương ứng trong array
                String hotelID = parts[0].trim();
                String hotelName = parts[1].trim();
                int hotelRoomAvailable = Integer.parseInt(parts[2].trim());
                String hotelAddress = parts[3].trim();
                String hotelPhone = parts[4].trim();
                int hotelRating = Integer.parseInt(parts[5].trim());

                HotelInf hotel = new HotelInf(hotelID, hotelName, hotelRoomAvailable,
                        hotelAddress, hotelPhone, hotelRating);
                arrHotel.add(hotel);
            } else {
                System.out.println("Invalid data format: " + line + "\n");
            }
        }
    }

}
