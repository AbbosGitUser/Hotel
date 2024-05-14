package Hotel.Classes;
public class OldData {
    public static Integer days=738894;
    private OldData(){}
    private static OldData d;
    public static OldData getInstance(){
        if(d==null) {
            return new OldData();
        }
        return d;
    }
    public static Integer getDays(){
        return days;
    }
    @Override
    public String toString() {
        return "OldData{" +
                "days=" + days +
                '}';
    }
}