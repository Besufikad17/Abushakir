package abushakir.calander;


//import javafx.util.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static abushakir.util.Constants.*;
import static abushakir.util.Util.*;
import com.github.cliftonlabs.json_simple.JsonObject;
import abushakir.util.Constants;
import abushakir.util.Util;

public class etDateTime {

    /**
     * etDateTime can represent time values that are at a distance of at most
     * 100,000,000 days from epoch (1970-01-01): -271821-04-20 to 275760-09-13.
     *
     * Create a etDateTime object by using one of the constructors
     * or by parsing a correctly formatted string,
     * which complies with a subset of ISO 8601.
     * Note that hours are specified between 0 and 23,
     * as in a 24-hour clock.
     * For example:
     *
     * etDateTime now = new etDateTime.now();
     * etDateTime covid19Confirmed = new etDateTime(year: 2012, month: 7, day: 4);
     * etDateTime lockdownBegin = new etDateTime.fromMillisecondsSinceEpoch(1586215439441);
     * ```
     *
     * Once created, the value of an etDateTime object may not be changed.
     *
     * You can use properties to get
     * the individual units of an EtDatetime object.
     *
     * ```
     *  covid19Confirmed.year == 2012;
     *  covid19Confirmed.month == 7;
     *  covid19Confirmed.day == 2;
     * ```
     *
     * For convenience and readability,
     * the etDateTime class provides a constant for each day and month
     * name - for example, መስከረም and ማግሰኞ.
     * You can use these constants to improve code readability:
     *
     *
     * Day and month indexes begin at 0, and the week starts on Monday (ሰኞ).
     * That is, the constants መስከረም and ሰኞ are both 1.
     *
     * ## Comparing etDateTime objects
     *
     * The etDateTime class contains several handy methods,
     * such as isAfter, isBefore, and isAtSameMomentAs,
     * for comparing etDateTime objects.
     *
     * ```
     *  lockdownBegin.isAfter(covid19Confirmed);
     *  covid19Confirmed.isBefore(lockdownBegin);
     * ```
     *
     * ## Using etDateTime with Duration
     *
     * Use the add and subtract methods with a Duration object
     * to create a new etDateTime object based on another.
     * For example, to find the date that is sixty days (24 * 60 hours) after today,
     * write:
     *
     * ```
     * etDateTime now = new etDateTime.now();
     * etDateTime sixtyDaysFromNow = now.add(new Duration(days: 60));
     * ```
     *
     * To find out how much time is between two etDateTime objects use
     * difference, which returns a Duration object:
     *
     * ```
     * etDateTime difference = covid19Confirmed.difference(lockdownBegin);
     * ```
     * ### NOTE
     *
     * There is no UTC or TIme zone feature in this package since it's built only
     * for ethiopia.
     *
     *
     * ## Realtime Clock
     *
     * The etDateTime class provides realtime clock for Ethiopian with 6 hour
     * offset and can be integrated into JavaFx and android application.
     *
     */

    public  int fixed;
    public  double moment;
    private String dayGeez;
    private String monthGeez;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private int millisecond;

    public etDateTime(int year,int month,int day,int hour,int minute,int second,int millisecond) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millisecond = millisecond;
        this.monthGeez = months[this.month - 1];
        this.dayGeez = dayNumbers[this.day - 1];
        this.fixed = fixedFromEthiopic(year,month,day);
        this.moment = _dateToEpoch(year,month,day,hour,minute,second,millisecond);
    }

    public etDateTime(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.monthGeez = months[this.month - 1];
        this.dayGeez = dayNumbers[this.day - 1];
        this.fixed = fixedFromEthiopic(year,month,day);
    }
    public etDateTime(int year,int month){
        this.year = year;
        this.month = month;
        this.day = 1;
        this.monthGeez = months[this.month - 1];
        this.dayGeez = dayNumbers[this.day - 1];
        this.fixed = fixedFromEthiopic(year,month,1);
    }

    public etDateTime(int year){
        this.year = year;
        this.month = 1;
        this.day = 1;
        this.monthGeez = months[0];
        this.dayGeez = dayNumbers[0];
        this.fixed = fixedFromEthiopic(year,1,1);
        this.moment = _dateToEpoch(year,1,1,0,0,0,0);
    }

    public etDateTime() {
       this.year = Integer.parseInt(LocalDateTime.now().toString().substring(0,4));
       this.month = 1;
       this.day = 1;
       this.hour = 0 ;
       this.minute = 0;
       this.second = 0;
       this.millisecond = 0;
        this.monthGeez = months[0];
       this.dayGeez = dayNumbers[0];
       this.fixed = fixedFromEthiopic(year,month,day);
       this.moment = _dateToEpoch(year,month,day,hour,minute,second,millisecond);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getMillisecond() {
        return millisecond;
    }

    public String getDayGeez() {return dayGeez;}

    public String getMonthGeez() {return monthGeez;}

    public static int fixedFromEthiopic(int year, int month, int day){
        return (ethiopicEpoch - 1) + (365 * (year - 1) )+ (year / 4) + (30 * (month - 1)) + day;
    }

    /* Constructs etDateTime instance using current time and date*/

    public etDateTime now(){
       String now =  LocalDateTime.now().toString();
       etDateTime today =  Parse(now);
       int myYear = today.getYear();
       int myMonth = today.getMonth();
       int myDay = today.getDay();
       int myHour = today.getHour();
       int myMinute = today.getMinute();
       int mySecond = today.getSecond();
       int myMillisecond = today.getMillisecond();
       Util util = new Util();
       int[] res = util.gregorianToEthiopic(myYear,myMonth,myDay);
       return new etDateTime(res[0],res[1],res[2],myHour,myMinute,mySecond,myMillisecond);
    }

    /* Returns HashMap in the form of {h : hour,m : minute,s : second } */

    public HashMap<String,Integer> getTime(int Hour,int Minute,int Second){
        HashMap<String,Integer> a = new HashMap<>();
        String[] keys = {"h","m","s"};
        int[] values = {Hour,Minute,Second};
        for(int i=0; i<keys.length; i++){
            a.put(keys[i],values[i]);
        }
        return a;

    }

    /* Returns HashMap in the form of {year : year,month : month,day : day } */

    public HashMap<String,Integer> getDate(int Year,int Month,int Day){
        HashMap<String,Integer> a = new HashMap<>();
        String[] keys = {"year","month","day"};
        int[] values = {Year,Month,Day};
        for(int i=0; i<keys.length; i++){
            a.put(keys[i],values[i]);
        }
        return a;
    }

    /* Parses given formatted string to etDateTime instance
        Example.Example : "2012-02-27 13:27:00" to new etDateTime(year : 2012,month : 2,day : 27,hour : 13,minute : 27,second : 0)
    */

    public static etDateTime Parse(String s){
        List<Integer> date = new ArrayList<>();
        if (s.length() > 19){
            if (s.charAt(19) == '-'){
                s = s.substring(0,19);
                String[] result = getDate(s,7);
                date = toInt(result);
            }else{
                String[] result = getDate(s,7);
                date = toInt(result);

            }
        }else{
            String[] result = getDate(s,6);
            date = toInt(result);

        }
        if (date.size() == 6){
            return new etDateTime(date.get(0),date.get(1),date.get(2),date.get(3),date.get(4),date.get(5),0);
        }else{
            return new etDateTime(date.get(0),date.get(1),date.get(2),date.get(3),date.get(4),date.get(5),date.get(6));
        }
    }

    /* Extract etDateTime data fields (year,month,day,hour,minute,sec,millisecond) from a single formatted string */

    public static String[] Parse(String[] date,String s){
        int index = 0, count = 0;
        int i = 4;
        while(i < s.length()){
            if(!Character.isDigit(s.charAt(i))) {
                date[count] = s.substring(index, i);
                count++;
                index = i + 1;
                i = i + 3;
                if(i == s.length()){
                    date[count] = s.substring(index);
                }
            }else{
                date[count] = s.substring(index, i);
                count++;
                index = i;
                i = i + 2;
                if(i == s.length()){
                    date[count] = s.substring(index);
                }
            }
        }
        return date;
    }


    public static String[] getDate(String s , int size){
        String[] date = new String[size];
        if (s.contains(".")){
            if (s.contains("z") || s.contains("Z")){
                date[date.length - 1] = s.substring(s.indexOf(".") + 1,s.indexOf("z"));
                s = s.substring(0,s.indexOf("."));
                date = Parse(date,s);
            }else{
                date[date.length - 1] = s.substring(s.indexOf(".") + 1);
                s = s.substring(0,s.indexOf("."));
                date = Parse(date,s);
            }
        }else if(s.contains(",")){
            date[date.length - 1] = s.substring(s.indexOf(",") + 1,s.indexOf("z"));
            s = s.substring(0,s.indexOf(","));
            date = Parse(date,s);
        }else if(s.charAt(0) == '+' || s.charAt(0) == '-'){
            s = s.substring(1);
            date = Parse(date,s);
        }
        else{
            date = Parse(date,s);
        }
        return date;

    }
    /* Casts given String array to integer list */

    public static List<Integer> toInt(String[] arr){
        List<Integer> date = new ArrayList<>();
        for(int i = 0;i<arr.length;i++){
            if(arr[i] != null){
                date.add(Integer.parseInt(arr[i]));
            }else{
                date.add(0);
            }
        }
        return date;
    }

    /* The first day of the year (meskerem 1)*/

    public int yearFirstDay = yearFirstDay();

    /* First day of the month */

    public int weekday = getWeekday();

    /* Returns if the year of the current etDateTime instance is leap or not*/

    public boolean isLeap = isLeap();

    /* Returns the first day of the year (meskerem 1) */

    public int yearFirstDay() {
        Bahirehasab bh = new Bahirehasab(year);
        return (bh.ameteAlem +  bh.rabeet) % 7;
    }

    /* Returns the first day of the month */

    public int getWeekday(){
        int firstDay = yearFirstDay();
        return (firstDay + ((month - 1) * 2)) % 7;
    }

    /* Returns if the year of the current etDateTime instance is leap or not */

    public boolean isLeap(){
        if (year % 4 ==3){
            return true;
        }else{
            return false;
        }
    }


    public int getDay() {
        return day;
    }


    public static double _dateToEpoch(int year, int month, int date, int hour, int minute, int second, int millisecond) {
        return ((fixedFromEthiopic(year, month, date) - unixEpoch) * dayMilliSec) +
                (hour * hourMilliSec) +
                (minute * minMilliSec) +
                (second * secMilliSec) +
                millisecond;
    }

    public int fixedFromUnix(double ms){
        return (unixEpoch + (int)(ms / 86400000));
    }

    /* Constructs formatted string from etDateTime data fields(variables) */

    public String to_String(){
        String y = _fourDigits(year);
        String m = _twoDigits(month);
        String d = _twoDigits(day);
        String h = _twoDigits(hour);
        String min = _twoDigits(minute);
        String sec = _twoDigits(second);
        String ms = _threeDigits(millisecond);
        return y + "-" + m + "-" + d + " " + h + ":" + min + ":"+ sec + "." + ms;
    }

    /* Creates JsonObject from String HashMap which contains etDateTime variables as values  */

    public JsonObject to_Json(){
        String[] values = {_fourDigits(year),_twoDigits(month),_twoDigits(day),_twoDigits(hour),
        _twoDigits(minute),_twoDigits(second),_threeDigits(millisecond)};
        HashMap<String,String> data = getToJson(values);
        JsonObject json = new JsonObject();
        json.putAll(data);
        return json;
    }

    /* Returns ISO-8601 formatted string in the form of "yyyy-MM-ddThh-mm-ss" */

    public String toIso8601String() {
        String y = (year >= -9999 && year <= 9999) ? _fourDigits(year) : _sixDigits(year);
        String m = _twoDigits(month);
        String d = _twoDigits(day);
        String h = _twoDigits(hour);
        String min = _twoDigits(minute);
        String sec = _twoDigits(second);
        String ms = _threeDigits(millisecond);
        return y + "-" + m + "-" + d + "T" + h + ":" + min + ":"+ sec + "." + ms;
    }

    /* Returns true if current instance occurs before the other instance */

    public boolean isBefore(etDateTime other){
        return fixed < other.fixed && moment < other.moment;
    }

    /* Returns true if current instance occurs after the other instance */

    public boolean isAfter(etDateTime other){
        return fixed > other.fixed && moment > other.moment;
    }

    /* Returns true if current instance occurs at the same time as the other instance */

    public boolean isAtSameMomentAs(etDateTime other){
        return fixed == other.fixed && moment == other.moment;
    }

    /* Returns positive if current instance occurs after the other instance,
               negative if current instance occurs before the other instance ,
               0 if current instance occurs at the same time as the other instance.
     */

    public int compareTo(etDateTime other) {
        if (this.isBefore(other))
            return -1;
        else if (this.isAtSameMomentAs(other))
            return 0;
        else
            return 1;
    }


    public etDateTime fromMillisecondsSinceEpoch(long millisecondsSinceEpoch){
         moment = millisecondsSinceEpoch;
         fixed = fixedFromUnix(moment);
        return withValue(fixed,moment);
    }

    /* Creates instance of etDateTime from given fixed and moment */

    public etDateTime withValue(int fixed,double moment){
        int year = (4 * (fixed - ethiopicEpoch) + 1463) / 1461;
        int month = (((fixed - fixedFromEthiopic(year, 1, 1)) / 30) + 1);
        int day = fixed + 1 - fixedFromEthiopic(year, month, 1);
        int hour = (int)(moment / hourMilliSec) % 24;
        int minute = (int)(moment / minMilliSec) % 60;
        int second = (int)(moment / secMilliSec) % 60;
        int millisecond = (int) ((double)moment % 1000);
        return new etDateTime(year,month,day,hour,minute,second,millisecond);
    }

    /* Returns a new etDateTime instance with duration added to current instance. */

//    public etDateTime add(Duration duration){
//        int millisec = (int) (millisecond + duration.toMillis());
//        System.out.println(millisec);
//        moment = _dateToEpoch(year,month,day,hour,minute,second,millisec);
//        return withValue(fixed,moment);
//    }
//
//    /* Returns a new etDateTime instance with duration subtracted to current instance. */
//
//    public etDateTime substract(Duration duration){
//        int millisec = (int) (millisecond - duration.toMillis());
//        System.out.println(millisec);
//        moment = _dateToEpoch(year,month,day,hour,minute,second,millisec);
//        return withValue(fixed,moment);
//    }
//
//    /* Returns the day difference between current etDateTime instance and the other */
//
//    public Duration differenceInDays(etDateTime date){
//        int fixed_difference = Math.abs(getDay() - date.getDay());
//        return new Duration(fixed_difference*24*360000);
//    }
//
//    /* Returns the month difference between current etDateTime instance and the other */
//
//    public Duration differenceInMonth(etDateTime date){
//        int fixed_difference = Math.abs(getMonth() - date.getMonth());
//        return new Duration(fixed_difference*30*24*360000);
//    }
//
//    /* Returns the hour difference between current etDateTime instance and the other */
//
//    public Duration differenceInHours(etDateTime date){
//        int fixed_difference = Math.abs(getHour() - date.getHour());
//        return new Duration(fixed_difference*360000);
//    }

}

