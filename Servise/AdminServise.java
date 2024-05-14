package Hotel.Servise;

import Hotel.Classes.*;
//import Hotel.Classes.Room;
import Hotel.Database.Database;
import Hotel.Utills.Util;

import java.util.ArrayList;
//import java.util.HashMap;

public class AdminServise extends Util{
    static Database database=Database.getInstance();

    static User currentUser;
    public static void servise(User user){
        currentUser=user;
        while (true){
            switch (menu()){
                case 0 ->{
                    System.out.println("See you soon "+currentUser.getName());
                    currentUser=null;
                    return;
                }
                case 1 ->{
                    addHotel();
                }
                case 2 ->{
                    editHotel();
                }
                case 3->{
                    for (String s : Database.hotels.keySet()) {
                        System.out.println(s);
                    }
                    System.out.println("");
                }
                case 4 ->{
                    System.out.println(currentUser.getBalance());
                }
                case 5->{showDate();}

            }
        }
    }
    private static void showDate() {
        Integer days = OldData.getInstance().getDays();
        int year=0;
        while (days>365){
            year++;
            days-=365;
        }
        String month1 = "";
        for (String month : Months.getMonths()) {
            if (Months.getMonth(month)>days){
                month1=month;
                break;
            }else {
                days-=Months.getMonth(month);
            }
        }
        System.out.printf("Year %d , month %s, days %d\n",year,month1,days);

    }


    private static void editHotel() {
        int l=1;
        ArrayList<String> keys=new ArrayList<>();
        for (String s : Database.hotels.keySet()) {
            System.out.println((l++)+s);
            keys.add(s);
        }
        if(keys.isEmpty()){
            System.out.println("Empty");
            return;
        }
        Integer chooseAny=0;
        while (true){
             chooseAny = integer("Choose any");
            if(chooseAny>0&&chooseAny<=keys.size()){
                break;
            }else System.out.println("Wrong index");
        }
        String key = keys.get(chooseAny - 1);
        String enterNewName = str("Enter new name");
        Hotel hotel = Database.hotels.get(key);
        Database.hotels.remove(key);
        Database.hotels.put(enterNewName,hotel);
        System.out.println("Completed");
    }

    private static void addHotel() {
        String hotelName=str("Enter hotel name");
        Integer hight=integer("Enter hotel hight");
        Integer rooms=integer("Enter number of rooms on each floor");

        for (String s : Database.hotels.keySet()) {
            if(s.equals(hotelName)){
                System.out.println("A hotel of this name already exists");
                return;
            }
        }
        Database.hotels.put(hotelName,new Hotel(hight,rooms,aDouble("Enter average price")));
    }

    private static int menu() {
        return Util.integer("""
                0 exit
                1 add hotel
                2 edit hotel
                3 show hotels
                4 show balance
                5 show date
                """);
    }
}
