package sample.utils;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputHandle {
    private final Scanner sc;
    
    public InputHandle() {
        sc = new Scanner(System.in);
    }
    
    public String getStringPattern(String welcome, String pt, String msg, String msgreg) {
        boolean check = true;
        Pattern patternMatcher = Pattern.compile(pt);
        String result = "";
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
            } else if (!patternMatcher.matcher(result).matches()) {
                System.out.println(msgreg);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }
    
    public int getInteger(String msg, int x, int y) {
        System.out.print(msg);
        boolean check = true;
        int input;
        while (check) {
            try {
                input = Integer.parseInt(sc.nextLine());
                if (input < x || input > y) {
                    System.out.println("This number must be " + x + " to " + y);
                    check = true;
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println("This must be number");
                check = true;
            }
        }
        return 0;
    }
    
    public boolean inputYN(String msg) {
        String choice;
        while (true) {
            System.out.print(msg);
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                return true;
            } else if (choice.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.err.println("Must be Y or N");
                continue;
            }
        }
    }
    
    public String getString(String welcome, String msg) {
        boolean check = true;
        String input = "";
        do {
            System.out.print(welcome);
            input = sc.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println(msg);
            } else {
                check = false;
            }
        } while (check);
        return input;
    }
    
    public String getStringCanBlank (String msg) {
        String input = "";
        System.out.println(msg);
        input = sc.nextLine();
        return input;
    }
    
    public int getIntChoice(int min, int max) {
        int choice;
        while (true) {
            try {
                System.out.print("Enter your choice (" + min + "-" + max + "): ");
                choice = Integer.parseInt(sc.nextLine());

                if (choice >= min && choice <= max) {
                    break;
                } else {
                    System.err.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a valid number.");
            }
        }
        return choice;
    }
}
