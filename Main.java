package Hotel;

import Hotel.Classes.Hotel;
import Hotel.Classes.OldData;
import Hotel.Classes.User;
import Hotel.Database.Database;
import Hotel.Enum.Role;
import Hotel.Servise.AdminServise;
import Hotel.Servise.UserServise;
import Hotel.Utills.Util;

import java.time.LocalTime;
import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main extends Util {
    static {
        Thread thread=new Thread(()->{
            while (true){
                OldData oldData=OldData.getInstance();
                try {
                    Thread.sleep(1000*60);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                OldData.days=OldData.days+1;
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    static Database database=Database.getInstance();
    public static void main(String[] args) {
        while (true){
            switch (integer("""
                    0 exit
                    1 sign in
                    2 sign up
                    """)){
                case 0->{
                    System.out.println("See you soon");
                    return;
                }
                case 1->{
                    signIn();
                }
                case 2->{
                    signUp();
                }
            }
        }
    }

    private static void signUp() {
        String enterName = str("Enter name");
        String enterUserName = str("Enter user name");
        for (User user : database.users) {
            if (Objects.equals(user.getUsername(),enterUserName)) {
                System.out.println("This user already exists");
                return;
            }
        }
        database.users.add(new User(enterName, enterUserName, str("Enter password"), Role.USER, 100000.0));
        System.out.println("Welcome");
    }

    private static void signIn() {
        String enterUserName = str("Enter user name");
        String enterPassword = str("Enter password");
        for (User user : database.users) {
            if(Objects.equals(user.getUsername(),enterUserName)&&Objects.equals(user.getPassword(),enterPassword)){
                if(Objects.equals(user.getRole(), Role.USER)){
                    UserServise.servise(user);
                } else AdminServise.servise(user);
                break;
            }
        }
    }
}
