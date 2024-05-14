package Hotel.Classes;

import Hotel.Utills.Util;

import java.util.ArrayList;

public class EnterDate extends Util {
    public static Integer enterDate(String s){
        int year=0;
        year=integer("Enter "+s+" year");
        int l=1;
        ArrayList<String> months = Months.getMonths();
        int month=1;
        for (String string : months) {
            System.out.println((month++)+" ->" +string);
        }
        while (true){
            month=integer("Choose any");
            if(month>0&&month<=12){
                break;
            }else{
                System.out.println("Wrong number");
            }
        }
        int m=Months.getMonth(months.get(month-1));
        int day=0;
        while (true){
            day=integer("Enter "+s+" day");
            if(day>0&&day<=m){
                break;
            }
        }
        year*=Months.getYear();
        for (int i = 0; i < month-1; i++) {
            year+=Months.getMonth(months.get(i));
        }
        year+=day;
        return year;
    }
}
