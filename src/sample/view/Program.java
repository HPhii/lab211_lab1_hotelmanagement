package sample.view;

import sample.controllers.Hotel;
import sample.controllers.Menu;

public class Program {

    public static void main(String[] args) {
        Menu menu = new Menu("------------HOTEL MANAGEMENT-----------");
        menu.addNewOption("ADD NEW HOTEL");
        menu.addNewOption("CHECK TO EXIST HOTEL");
        menu.addNewOption("UPDATE HOTEL INFORMATION");
        menu.addNewOption("DELETE HOTEL");
        menu.addNewOption("SEARCH HOTEL");
        menu.addNewOption("DISPLAY A HOTEL LIST");
        menu.addNewOption("DISPLAY LAST HOTEL");
        menu.addNewOption("EXIT THE PROGRAM\n");

        Hotel ht = new Hotel();
        int choice;
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1: {
                    ht.addHotel();
                    break;
                }
                case 2: {
                    ht.checkExist();
                    break;
                }
                case 3: {
                    ht.updateHotelInfo();
                    break;
                }
                case 4: {
                    ht.deleteHotel();
                    break;
                }
                case 5: {
                    ht.searchHotel();
                    break;
                }
                case 6: {
                    ht.displayHotel();
                    break;
                }
                case 7: {
                    ht.displayLastHotelInfo();
                    break;
                }
                default: {
                    System.out.println("Exiting program...");
                }
            }
        } while (choice >= 1 && choice <= 7);
    }
}
