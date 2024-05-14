package Hotel.Classes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Months {
    public static LinkedHashMap<String,Integer> months=new LinkedHashMap<>();
    static {
        months.put("Yanvar",31);
        months.put("Fevral",28);
        months.put("Mart",31);
        months.put("Aprel",30);
        months.put("May",31);
        months.put("Iyun",30);
        months.put("Iyul",31);
        months.put("Avgust",31);
        months.put("Sentyabr",30);
        months.put("Oktyabr",31);
        months.put("Noyabr",30);
        months.put("Dekabr",31);
    }
    public static ArrayList<String> getMonths(){
        ArrayList<String > m=new ArrayList<>();
        for (String s : months.keySet()) {
            m.add(s);
        }
        return m;
    }
    public static Integer getMonth(String s){
        return months.get(s);
    }
    public static Integer getYear(){
        return 365;
    }
}
