package sample.utils;

import sample.dto.HotelInf;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DataValidationCheck {

    private final Scanner sc = new Scanner(System.in);
    private final DataSearch sd = new DataSearch();

    public DataValidationCheck() {
    }

    public String formatTitleCase(String input) {
        String[] words = input.toLowerCase().split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }
        return result.toString().trim();
    }

    //dùng để check id khi add id của ks mới vào
    public String inputHotelID(ArrayList<HotelInf> arr) {
        String id = "";
        do {
            System.out.print("Enter ID of hotel: ");
            id = sc.nextLine().toUpperCase();
            //kiểm tra id vừa nhập có tồn tại hay chưa bằng hàm search
            if (sd.searchHotelBaseOnID(arr, id) != null) {
                System.out.println("Duplicated code. Try with another one");
                //nếu người dùng không nhập gì thì thông báo nhập lại
            } else if (id.trim().isEmpty()) {
                System.out.println("ID can't not empty!");
                //nếu input id valid thì trả về id đã được uppercase
            } else {
                return id.toUpperCase();
            }
        } while (true);
    }

    public String inputNameUpdate(String msg, HotelInf hotel) {
        String name = "";
        System.out.println(msg);
        //chuỗi tên là kí tự từ a-z, \\ cho phép nhập khoảng trắng 
        //và $ kết thúc bằng chữ cái (không thể là khoảng trắng)
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        do {
            name = sc.nextLine();
            //kiểm tra nếu user chỉ nhập khoảng trắng thì sẽ trả về tên ks mặc định và không update thêm gì
            if (name.trim().isEmpty()) {
                return hotel.getHotelName();
                //kiểm tra input có match với pattern hay không
                // ! để đảo ngược kết quả của chuỗi so sánh, nếu match thì trả về false để thoát vòng lặp
                //      tạo đối tg matcher base on regex, .matches để so sánh
            } else if (!pattern.matcher(name).matches()) {
                System.out.println("Please enter the correct format of the name");
            } else {
                return formatTitleCase(name);
            }
        } while (true);
    }

    public int inputAvaRoomUpdate(String msg, int x, int y, HotelInf hotel) {
        System.out.println(msg);
        boolean check = true;
        int input;
        try {
            while (check) {
                String room = sc.nextLine();
                if (room.trim().isEmpty()) {
                    input = hotel.getHotelRoomAvai();
                    return input;
                }
                input = Integer.parseInt(room);
                if (input < x || input > y) {
                    System.out.println("This number must be " + x + "to" + y);
                    check = true;
                } else {
                    return input;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("This must be number");
            check = true;
        }
        return 0;
    }

    public String inputAddressUpdate(String msg, HotelInf hotel) {
        String addr = "";
        System.out.println(msg);
        Pattern pattern = Pattern.compile("^[A-Za-z0-9, ]+$");
        do {
            addr = sc.nextLine();
            if (addr.trim().isEmpty()) {
                return hotel.getHotelAddr();
            } else if (!pattern.matcher(addr).matches()) {
                System.out.println("Please enter the correct format of the address");
            } else {
                return formatTitleCase(addr);
            }
        } while (true);
    }

    public String inputPhoneUpdate(String msg, HotelInf hotel) {
        String phone = "";
        System.out.println(msg);
        Pattern pattern = Pattern.compile("0\\d{9}");
        do {
            phone = sc.nextLine();
            if (phone.trim().isEmpty()) {
                return hotel.getHotelPhone();
            } else if (!pattern.matcher(phone).matches()) {
                System.out.println("Please enter the correct format of the phone");
            } else {
                return phone;
            }
        } while (true);
    }

    public int inputHotelRatingUpdate(String msg, int x, int y, HotelInf hotel) {
        System.out.println(msg);
        boolean check = true;
        int input;
        try {
            while (check) {
                String string = sc.nextLine();
                if (string.trim().isEmpty()) {
                    input = hotel.getHotelRating();
                    return input;
                }
                input = Integer.parseInt(string);
                if (input < x || input > y) {
                    System.out.println("This number must be " + x + "to" + y);
                    check = true;
                } else {
                    return input;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("This must be number");
            check = true;
        }
        return 0;
    }
}
