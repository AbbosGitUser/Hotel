package Hotel.Classes;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Room {
    private final Integer roomNumber;
    private final Integer hight;
    private Queue queue;
    private LinkedList<Queue> queues=new LinkedList<>();
    private final Double price;

    public Room(Integer roomNumber, Integer hight, Double price) {

        this.roomNumber = roomNumber;
        this.hight = hight;
        this.price = price;
    }

   public LinkedList<Queue> getQueues(){
        return queues;
   }

    public Queue getNext() {
        return queues.getFirst();
    }

    public void setQueue(Queue queue) {
        Condition condition=new ReentrantLock().newCondition();
        if(queue.getStart()<OldData.getInstance().getDays()){
            System.out.println("wrong date");
            return;
        }
        if(queues.isEmpty()){
            queues.add(queue);
            try{
                condition.signalAll();
                System.out.println("ISHLADI");
            }catch (Exception e){}
            return;
        }if (queues.size()==1){
            if (queues.get(0).getStart()>queue.getFinish()) {
                queues.add(0,queue);
                return;
            }
            else if(queues.get(0).getFinish()<queue.getStart()){
                queues.add(queue);
                return;
            }else {
                System.out.println("The time slot you want to subscribe to is busy");
                return;
            }
        }

        for (int i = 0; i < queues.size()-1; i++) {
            Queue queue1 = queues.get(i);
            if(queue1.getStart()<=queue.getStart()){
                if (aniqla(i,queue1,queue)) {
                    queues.add(i+1,queue);
                    System.out.println("Completed");
                    return;
                }
            }
        }
        System.out.println("The time slot you want to subscribe to is busy");
    }

    private boolean aniqla(int i, Queue queue1, Queue queue) {
        if(queue1.getFinish()>=queue.getStart()){
            return false;
        }
        Queue queue2 = queues.get(i + 1);
        if (queue2.getStart()>=queue.getFinish()){
            return true;
        }
        return false;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public Integer getHight() {
        return hight;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", hight=" + hight +
                '}';
    }

    public Double getPrice() {
        return price;
    }
}
