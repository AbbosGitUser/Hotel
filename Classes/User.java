package Hotel.Classes;

import Hotel.Enum.Role;

import java.util.ArrayList;

public class User implements Comparable<String> {

    private final String name;
    private final String username;
    private final String password;
    private final Role role;
    private Double balance;
    private ArrayList<Integer> ball;


    public User(String name, String username, String password, Role role, Double balance) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance=balance;
    }
    public String getUsername(){
        return username;
    }
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBall() {
        int l=0;
        for (Integer i : ball) {
            l+=i;
        }
        return (double)l/ball.size();
    }

    public void setBall(Integer ball) {
        this.ball.add(ball);
    }


    @Override
    public int compareTo(String o) {
        return this.username.compareTo(o);
    }
}
