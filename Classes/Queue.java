package Hotel.Classes;

public class Queue {
    private final User owner;
    private final Integer start;
    private final Integer finish;

    public Queue(User owner, Integer start, Integer finish) {
        this.owner = owner;
        this.start = start;
        this.finish = finish;
    }

    public User getOwner() {
        return owner;
    }

    public Integer getStart() {
        return start;
    }


    public Integer getFinish() {
        return finish;
    }

}
