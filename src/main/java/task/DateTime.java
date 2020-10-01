package task;

import java.util.Hashtable;

public class DateTime {
    private static Hashtable<Integer,String> monthTable;
    static {
        monthTable = new Hashtable<>();
        monthTable.put(1, "January");
        monthTable.put(2, "February");
        monthTable.put(3, "March");
        monthTable.put(4, "April");
        monthTable.put(5, "May");
        monthTable.put(6, "June");
        monthTable.put(7, "July");
        monthTable.put(8, "August");
        monthTable.put(9, "September");
        monthTable.put(10, "October");
        monthTable.put(11, "November");
        monthTable.put(12, "December");
    }


    private final int day;
    private final int year;
    private final int month;
    private final int time;
    DateTime(int day, int year, int month, int time){
        this.day = day;
        this.year = year;
        this.month = month;
        this.time = time;
    }
    public static boolean isSameDate(DateTime dt1, DateTime dt2){
        return (dt1.day == dt2.day) && (dt1.month == dt2.month)
                && (dt1.year == dt2.year) && (dt1.time == dt2.time);
    }

    private String giveTimeString(){
        Integer clockTime = this.time % 1200;
        String timeString = clockTime.toString();
        if (clockTime < 1000){
            timeString = "0" + timeString;
        }
        else if (clockTime < 100){
            timeString = "00" + timeString;
        }
        return timeString + " " + ((this.time < 1200) ? "AM" : "PM");
    }

    @Override
    public String toString(){
        return monthTable.get(this.month) + " " + this.day + " " + this.year + " " + giveTimeString();
    }
}
