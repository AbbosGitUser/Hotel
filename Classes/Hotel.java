package Hotel.Classes;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;


public class Hotel implements Iterable {
    private  int l=1;
    private final ArrayList<Room> hotel = new ArrayList<>();
    public Hotel(Integer hight, Integer rooms, Double price){
        int d=hight/2;
        for (Integer i = 0; i < hight; i++) {
            for (Integer integer = 0; integer < rooms; integer++) {
                hotel.add(new Room(l++,i+1,price+(d-i+1)*(-1000)));
            }
        }
        l=hight;
    }
    public ArrayList<Room> getHotel(){
        return hotel;
    }
    public Integer getHight(){
        return l;
    }
    @Override
    public String toString() {
        int tru=0;
        int fal=0;
        for (Room room : hotel) {
            if (room.getQueues().isEmpty()) {
                tru++;
            }else fal++;
        }
        System.out.printf("The number of occupied rooms in this hotel is %d, the number of empty rooms is %d.",tru,fal);
        return "";
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
