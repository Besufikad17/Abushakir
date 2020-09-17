package abushakir.calander;

import abushakir.util.Calander_Exceptions.*;
import abushakir.util.Constants;

import java.time.LocalDateTime;

import static abushakir.calander.etDateTime.fixedFromEthiopic;
import static abushakir.util.Constants.dayNumbers;
import static abushakir.util.Constants.ethiopicEpoch;


public class ETC {

    /**
     * The Ethiopian calendar is one of the the calendars which uses the solar
     * system to reckon years, months and days, even time. Ethiopian single year
     * consists of 365.25 days which will be 366 days with in 4 years period which
     * causes the leap year. Ethiopian calendar has 13  months from which 12 months
     * are full 30 days each and the 13th month will be 5 days or 6 days during
     * leap year.
     *
     *
     * Create [ETC] object instances which are days of certain month in a certain
     * year, using one of the constructors.
     *
     * ```
     * ETC etc = new ETC(year: 2012, month: 7, day: 4);
     * ```
     *
     * or
     *
     * ```
     * ETC today = ETC.today();
     * ```
     *
     * After creating instance of [ETC], you can navigate to the future or past
     * of the given date.
     *
     * ```
     * ETC _nextMonth = today.nextMonth;
     * ETC _prevMonth = today.prevMonth;
     * ```
     *
     * you can also get the same month of different year (the next year or
     * prev one)
     *
     * ```
     * ETC _nextYear = today.nextYear;
     * ETC _prevYear = today.prevYear;
     * ```
     *
     * All the available days within a single month can be found using
     *
     * ```
     * Object[] monthDaysIter = today.monthDays();
     * ```
     *
     * or just all of the days available in the given year can also be found using
     * ```
     * Object[] yearDaysIter = today.yearDays();
     * ```
     *
     */

    private int year;
    private int month;
    private int day;
    private etDateTime date;

    public ETC(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.date = new etDateTime(year,month,day);
    }



    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    /* Returns new instance  */

    public ETC nextMonth(){
        if (month == 13){
            month = 1;
            return new ETC(year + 1,month,day);
        }
        return new ETC(year,month + 1,day);
    }

    public ETC previousMonth(){
        if (month == 1){
            month = 13;
            return new ETC(year - 1,month,day);
        }
        return new ETC(year,month - 1,day);
    }

    public ETC nextYear(){
        return new ETC(year + 1,month,day);
    }

    public ETC previousYear(){
        return new ETC(year - 1,month,day);
    }

    /* Returns amharic name of the current ETC instance month */

    public String getMonthGeez()throws MonthNumberException {
        return Constants.months[month - 1];
    }

    /* Returns day of the current ETC instance in geez number*/

    public String getDayGeez(){
        return Constants.dayNumbers[day - 1];
    }

    /* Returns ETC instance using current time and date */

    public ETC today(){
        String now =  LocalDateTime.now().toString();
        int fixed = fixedFromEthiopic(Integer.parseInt(now.substring(0,4)),Integer.parseInt(now.substring(5,7)),Integer.parseInt(now.substring(8,10)));
        int myYear = (4 * (fixed - ethiopicEpoch) + 1463) / 1461;
        int myMonth = (((fixed - fixedFromEthiopic(myYear, 1, 1)) / 30) + 1);
        int myDay = fixed + 1 - fixedFromEthiopic(myYear, myMonth, 1);
        return new ETC(myYear,myMonth,myDay);
    }

    /* Returns month range and monthStartDay as an array. */

    public int[] monthRange(){
        int[] result = new int[2];
        result[0] = getWeekday();
        result[1] = isLeap() ? 6 : 5;
        return result;
    }

    /*Returns the first day of the year of current ETC instance */

    public int yearFirstDay() {
        int rabeet = (year + Constants.ameteFida)%7;
        return (Constants.ameteFida + rabeet)%7;
    }

    /* Returns first day of the month */

    public int getWeekday(){
        int firstDay = yearFirstDay();
        return (firstDay + ((month - 1) * 2)) % 7;
    }

    /* Returns if the year of the current etDateTime instance is leap or not*/

    public boolean isLeap(){
        if (year % 4 ==3){
            return true;
        }else{
            return false;
        }
    }

    /** Returns Object array which looks like: {year,month,day,weekday}
    *  if geezDay is true day is stored in geez number else it is stored as index
    *  if weekDayName is true weekday is stored in amharic else it is stored as index
    *
    * */

    public  Object[] monthDays(boolean geezDay, boolean weekDayName) throws MonthNumberException{
        Object[] result = new Object[4];
        int monthBeginning = monthRange()[0];
        int daysInMonth = monthRange()[1];
        for (int i = 0; i < daysInMonth; i++) {
            if (geezDay) {
                result[0] = year;
                result[1] = month;
                result[2] = Constants.dayNumbers[i];
                result[3] = weekDayName ? Constants.weekdays[monthBeginning] : monthBeginning;
            } else{
                result[0] = year;
                result[1] = month;
                result[2] = i + 1;
                result[3] = weekDayName ? Constants.weekdays[monthBeginning] : monthBeginning;
            }
            monthBeginning = (monthBeginning + 1) % 7;
        }
        return result;
    }

    public  Object[] monthDays() throws MonthNumberException{
        boolean geezDay = false;
        boolean weekDayName = false;
        Object[] result = new Object[4];
        int monthBeginning = monthRange()[0];
        int daysInMonth = monthRange()[1];
        for (int i = 0; i < daysInMonth; i++) {
            result[0] = year;
            result[1] = month;
            result[2] = i + 1;
            result[3] = monthBeginning;
            monthBeginning = (monthBeginning + 1) % 7;
        }
        return result;
    }

    /**
     * Similar method as monthDays but the difference is this one will take
     * year and month as a parameter and then generate all available days of
     * the given month of the year.
     *
     * This method is used by yearDays for generating all available days
     * for the whole year.
     */

    public Object[] _monthDays(boolean geezDay, boolean weekDayName) throws MonthNumberException{
        Object[] result = new Object[4];
        etDateTime yr = new etDateTime(this.year,this.month);
        int monthBeginning = yr.getWeekday();
        int daysInMonth = yr.getMonth() == 13 ? yr.isLeap ? 6 : 5 : 30;
        for (int i = 0; i < daysInMonth; i++) {
            if (geezDay) {
                result[0] = year;
                result[1] = month;
                result[2] = Constants.dayNumbers[i];
                result[3] = weekDayName ? Constants.weekdays[monthBeginning] : monthBeginning;
            } else{
                result[0] = year;
                result[1] = month;
                result[2] = i + 1;
                result[3] = weekDayName ? Constants.weekdays[monthBeginning] : monthBeginning;
            }
            monthBeginning = (monthBeginning + 1) % 7;
        }
        return result;
    }

    public Object[] _monthDays() throws MonthNumberException{
        boolean geezDay = false;
        boolean weekDayName = false;
        Object[] result = new Object[4];
        etDateTime yr = new etDateTime(this.year,this.month);
        int monthBeginning = yr.getWeekday();
        int daysInMonth = yr.getMonth() == 13 ? yr.isLeap ? 6 : 5 : 30;
        for (int i = 0; i < daysInMonth; i++) {
            result[0] = year;
            result[1] = month;
            result[2] = i + 1;
            result[3] = monthBeginning;
            monthBeginning = (monthBeginning + 1) % 7;
        }
        return result;
    }

    /* Returns all the available days of the year */

    public Object[] yearDays(boolean geezDay, boolean weekDayName){
        Object[] result = new Object[4];
        for (int i = 0; i < Constants.months.length; i++) {
            result[0] = year;
            result[1] = i + 1;
            result[2] = geezDay;
            result[3] = weekDayName;
        }
        return result;
    }

    public Object[] yearDays(){
        boolean geezDay = false;
        boolean weekDayName = false;
        Object[] result = new Object[4];
        for (int i = 0; i < Constants.months.length; i++) {
            result[0] = year;
            result[1] = i + 1;
            result[2] = geezDay;
            result[3] = weekDayName;
        }
        return result;
    }

}
