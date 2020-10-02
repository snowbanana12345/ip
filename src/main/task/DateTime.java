package main.task;
import java.util.Hashtable;

/***
 * Date time class. Java date time package is too sophisticated for this programme.
 * This class stores the day, month, year, and time.
 * It provides support for checking if two dates are the same
 * It gives a more readable string representation of a date to the user
 */
public class DateTime {
    private static final Hashtable<Integer,String> monthTable;
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

    /***
     * Checks if two date times share the same date
     * @param dt1 first date time
     * @param dt2 second date time
     * @return bool
     */
    public static boolean isSameDate(DateTime dt1, DateTime dt2){
        return (dt1.day == dt2.day) && (dt1.month == dt2.month)
                && (dt1.year == dt2.year);
    }

    private String giveTimeString(){
        int clockTime = this.time % 1200;
        String timeString = Integer.toString(clockTime);
        if (clockTime < 1000 && clockTime >= 100){
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
