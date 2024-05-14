package Hotel.Servise;

import Hotel.Classes.*;
import Hotel.Database.Database;
import Hotel.Enum.Role;
import Hotel.Utills.Util;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class UserServise extends Util {
    private static User currentUser;
    private static OldData oldData=OldData.getInstance();
    private static Database database=Database.getInstance();
    public static void servise(User user){
        currentUser=user;
        while (true){
            switch (integer("""
                0 exit
                1 creat order
                2 manage balance
                3 protest user
                4 show hotels
                5 show date
                """)){
                case 0->{
                    System.out.println("See you soon "+currentUser.getName());
                    currentUser=null;
                    return;
                }
                case 1->{
                    if (currentUser.getBalance()>0){
                        creadOrder();
                    }else System.out.println("Wrong balance");
                }
                case 2->{
                    showUserBalance();
                }
                case 3-> {
                    protest();
                }
                case 4->{
                    for (String s : database.hotels.keySet()) {
                        System.out.println(s);
                    }
                }
                case 5->{
                    showDate();
                }
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

    private static void protest() {
        int l=1;
        ArrayList<User> users=new ArrayList<>();
        for (User user : database.users) {
            if(!Objects.equals(user.getUsername(),currentUser.getUsername())&&user.getRole().equals(Role.USER)){
                System.out.println((l++)+" -> "+user);
                users.add(user);
            }
        }
        if(users.isEmpty()){
            System.out.println("Empty");
            return;
        }
        while (true){
            l=integer("Choose any");
            if(l>0&&l<=users.size()){
                break;
            }else System.out.println("Wrong index");
        }
        User user = users.get(l - 1);
        while (true){
            l=integer("Enter evaluation");
            if(l>0&&l<=10){
                break;
            }else System.out.println("Wrong number");
        }
        user.setBall(l);
    }

    private static void showUserBalance() {
        System.out.println("Balance bo'limi ========");
        while (true){
            showBalanceMenu();
            switch (integer("choose: ")){
                case 0-> {
                    return;
                }
                case 1->{
                    System.out.println( "My Balance: " + currentUser.getBalance());
                }
                case 2->{
                    Double addMoney =aDouble("Enter price: ");
                    currentUser.setBalance(currentUser.getBalance()+addMoney);
                }
            }
        }
    }
    static void showBalanceMenu() {
        System.out.println("""
                0 exit
                1 myBalance
                2 add money
                """);
    }

    private static void creadOrder() {
        ArrayList<String > keys=new ArrayList<>();
        for (String s : database.hotels.keySet()) {
            System.out.println((keys.size()+1)+" ->"+s);
            keys.add(s);
        }
        if (keys.isEmpty()){
            System.out.println("Empty");
            return;
        }
        int l=0;
        while (true){
            l=integer("Choose any");
            if(l>0&&l<=keys.size()){
                break;

            }else System.out.println("Wrong index");
        }
        Hotel hotel = database.hotels.get(keys.get(l - 1));
        System.out.printf("The highest floor of this building is %d\n",hotel.getHight());
        while (true){
            l=integer("Enter floor");
            if(l>0&&l<= hotel.getHight()){
                break;
            }else System.out.println("Wrong floor");
        }
        ArrayList<Room> rooms=new ArrayList<>();
        for (Room room : hotel.getHotel()) {
            if(room.getHight()==l){
                System.out.println(room);
                rooms.add(room);
            }
        }
        Room room1=null;
        while (true){
            int roomNumber = integer("Enter room number");
            for (Room room : rooms) {
                if (room.getRoomNumber() == roomNumber) {
                    room1 = room;
                    break;
                }
            }
            if (room1 != null) {
                break;
            }else
                System.out.println("Wrong number");
        }

        Integer start = EnterDate.enterDate("start ");
        if (start<oldData.getDays()){
            System.out.println("Wrong date");
            return;
        }
        Integer finish = EnterDate.enterDate("finish ");
//        System.out.println(start);
//        System.out.println(finish);
        if (start>finish){
            System.out.println("Ishlamadi");
            System.out.println("Wrong date");
            return;
        }
        room1.setQueue(new Queue(currentUser,start,finish));
        currentUser.setBalance(currentUser.getBalance()- (finish-start)*room1.getPrice());
        Optional<User> user = Database.getUser("admin", "admin");
        User user1 = user.get();
        user1.setBalance(user1.getBalance()+(finish-start)*room1.getPrice());
    }


}
