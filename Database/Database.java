package Hotel.Database;

import Hotel.Classes.Hotel;
import Hotel.Classes.OldData;
import Hotel.Classes.User;
import Hotel.Enum.Role;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Database {
    public static HashSet<User> users=new HashSet<>();
    public static HashMap<String,Hotel> hotels=new HashMap<>();
    static {
        User user =new User("admin","admin","admin", Role.ADMIN,0.0);
        User user1 =new User("user1","user1","user1",Role.USER,0.0);
        User user2=new User("user2","user2","user2",Role.USER,0.0);
        User user3 =new User("user3","user3","user3",Role.USER,0.0);
        User user4 =new User("user4","user4","user4",Role.USER,0.0);
        addUser(user);
        addUser(user1);
        addUser(user2);
        addUser(user3);
        addUser(user4);
        Hotel hotel=new Hotel(3,10,150000.0);
        addHotel("Marvarid",hotel);
    }
    private Database(){}
    public static Optional<User> getUser(String userName,String password){
        for (User user : users) {
            if(Objects.equals(user.getName(),userName) && Objects.equals(user.getPassword(),password)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
    public static Optional<Hotel> getHotel(String hotelName){
        if(hotels.containsKey(hotelName)){
           return Optional.ofNullable(hotels.get(hotelName));
        }else return Optional.empty();
    }
    public static void addUser(User user){
        users.add(user);
    }
    public static void addHotel(String hotelName,Hotel hotel){
        hotels.put(hotelName,hotel);
    }
    static Database d;
    public static Database getInstance(){
        if(d==null)return new Database();
        return d;
    }
}
