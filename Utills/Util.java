package Hotel.Utills;

import java.util.Scanner;

public class Util {
    private static Scanner scanner=new Scanner(System.in);
    private static Scanner strScanner=new Scanner(System.in);
    public static String str(String str){
        System.out.println(str);
        return strScanner.nextLine();
    }
    public static Integer integer(String str){
        System.out.println(str);
        try{
            return scanner.nextInt();
        }catch (Exception e){
            scanner.nextLine();
            return integer(str);
        }
    }
    public static Double aDouble(String str){
        System.out.println(str);
        try{
            return scanner.nextDouble();
        }catch (Exception e){
            scanner.nextLine();
            return aDouble(str);
        }
    }
}
