package abushakir.calander;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import abushakir.util.Constants;
import abushakir.util.Util;
import abushakir.util.Calander_Exceptions.BealNameException;

import static abushakir.util.Constants.*;
import static abushakir.util.Util.*;

public class Bahirehasab {
    private int year;
    public int ameteAlem;
    public int rabeet;
    public int wenber;
    public int abekte;
    public int metkih;

    public Bahirehasab(int year) {
        this.year = year;
        this.ameteAlem = 5500 + this.year;
        this.rabeet = this.ameteAlem / 4;
        this.wenber = Util.getWenber(5500, this.year);
        this.abekte = this.wenber * 11 % 30;
        this.metkih = this.wenber == 0 ? 30 : this.wenber * 19 % 30;
    }

    public Bahirehasab() {
        String now = LocalDateTime.now().toString();
        int fixed = etDateTime.fixedFromEthiopic(Integer.parseInt(now.substring(0, 4)),
                Integer.parseInt(now.substring(5, 7)), Integer.parseInt(now.substring(8, 10)));
        this.year = (4 * (fixed - 2796) + 1463) / 1461;
        this.ameteAlem = 5500 + this.year;
        this.rabeet = this.ameteAlem / 4;
        this.wenber = Util.getWenber(5500, this.year);
        this.abekte = this.wenber * 11 % 30;
        this.metkih = this.wenber == 0 ? 30 : this.wenber * 19 % 30;
    }

    public String getEvange() {
        int index = (this.year + ameteFida) % 4;
        return index + "";
    }

    public String getEvange(boolean returnName) {
        int index = (this.year + ameteFida) % 4;
        return returnName ? Constants.evangelists[index] : index + "";
    }

    public String getMeskerem1(boolean returnName) {
        int result = (this.year + ameteFida + this.rabeet) % 7;
        return returnName ? Constants.weekdays[result] : result + "";
    }

    public String getMeskerem1() {
        int result = (ameteFida + this.year + this.rabeet) % 7;
        return result + "";
    }

    public int getYear() {
        return this.year;
    }

    public int getYebealMitkehWer(){
        if (metkih > 14){
            return 1;
        }else{
            return 2;
        }
    }

    /* Returns the date Tsome newewe will be at. */

    public HashMap<String,String> getNewewe(){
        String meskerem1 = getMeskerem1(true);
        int month = getYebealMitkehWer();
        int date;
        int dayTewsak = 0;
        for (Map.Entry mapElement : yeeletTewsak.entrySet()) {
            if ((mapElement.getKey()) == weekdays[(indexOf(meskerem1, weekdays) + metkih - 1) % 7]) {
                dayTewsak = (int) mapElement.getValue();
            }
        }
        String monthName = dayTewsak + metkih > 30 ? "የካቲት" : "ጥር";
        if (month == 2) {
            monthName = "የካቲት";
            String tikimt1 = weekdays[(indexOf(meskerem1, weekdays) + 2) % 7];
            String metkihElet = weekdays[(indexOf(tikimt1, weekdays) + metkih - 1) % 7];
            for (Map.Entry mapElement : yeeletTewsak.entrySet()){
                if ((mapElement.getKey()) == weekdays[(Util.indexOf(metkihElet,weekdays) + metkih - 1)% 7]){
                    dayTewsak = (int) mapElement.getValue();
                }
            }
        }
        date = metkih + dayTewsak;
        String[] values = {monthName,date % 30 == 0 ?  "" + 30 : "" + (date % 30)};
        return getNeweweDate(values);
    }

    /* Returns HashMap object which contains beal name,month and date.
       Example : {beal: ትንሳኤ, day: {month: ሚያዝያ, date: 20}}
     */

    public HashMap<String,Object> getAllAtswamat(String beal,int numOfDays){
        HashMap<String,Object> a = new HashMap<>();
        HashMap<String,String> mebajaHamer = getNewewe();
        for (Map.Entry mapElement : yebealTewsak.entrySet()){
            HashMap<String,String> h2 = new HashMap<>();
            h2.put("month",months[Util.indexOf(mebajaHamer.get("month"),months) + (Integer.parseInt(mebajaHamer.get("date")) + numOfDays) / 30]);
            Object[] values = {beal,h2};
            h2.put("date","" + ((Integer.parseInt(mebajaHamer.get("date")) + numOfDays)%30 == 0 ? 30 : (Integer.parseInt(mebajaHamer.get("date")) + numOfDays)%30));
            a = getAllAtswamatwDate(values);
        }
        return a;
    }

    /* Returns if a holiday is movable or not */

    public boolean isMovableHoliday(String name){
        if (yebealTewsak.containsKey(name)){
            return true;
        }else{
            return false;
        }
    }

    /* Returns the month and the day of given feast of the fasting or feasting (Holiday) in the form of HashMap.
       Example.Example : {month: ሚያዝያ, date: 20}
     */

    public HashMap<String,String> getSingleBealOrTsom(String name) throws BealNameException {
        String[] values = new String[2];
        boolean status = isMovableHoliday(name);
        if (status) {
            HashMap<String, String> mebajaHamer = getNewewe();
            int target = yebealTewsak.get(name);
            String[] vals = {
                    months[Util.indexOf(mebajaHamer.get("month"), months) + ((Integer.parseInt(mebajaHamer.get("date")) + target) / 30)],
                    "" + ((Integer.parseInt(mebajaHamer.get("date")) + target) % 30)
            };
            values = vals;
        }else{
            throw new BealNameException("Holiday is not a movable one. Please provide holidays between ነነዌ and ጾመ ድህነት");
        }

        return getSingleBealorTsomDate(values);
    }

}
