/*
package abushakir.Example;

import abushakir.calander.Bahirehasab;
import abushakir.calander.ETC;
import abushakir.calander.etDateTime;
import javafx.util.Duration;
import abushakir.util.Calander_Exceptions;

import java.util.Arrays;

public class Example {
    public static void main(String[] args) {
        etDateTime now = new etDateTime();
        now = now.now();
        System.out.println(now.getDate(now.getYear(),now.getMonth(), now.getDay()));
        System.out.println(now.getTime(now.getHour(),now.getMinute(),now.getSecond()));

        etDateTime covidFirstConfirmed = new etDateTime(2012,  7,  4);
        etDateTime covidFirstConfirmedEpoch = covidFirstConfirmed.fromMillisecondsSinceEpoch(covidFirstConfirmed.moment);

        etDateTime covidFirstDeath = etDateTime.Parse("2012-07-26 23:00:00");
        System.out.println(covidFirstDeath.to_String());

        /// Comparison of two EtDatetime Instances

        Duration daysWithOutDeath = covidFirstConfirmed.differenceInDays(covidFirstDeath);
        System.out.println(daysWithOutDeath);  //22*24*360000


        System.out.println(covidFirstDeath.isAfter(covidFirstConfirmed));

        System.out.println(covidFirstDeath.isBefore(now));

        System.out.println(covidFirstConfirmed.isAtSameMomentAs(covidFirstConfirmedEpoch));

        */
/**
         * Ethiopian Calendar Module [ETC]
         *//*


        ETC ethiopianCalendar = new ETC( 2011,  13,  4);

        try {
            System.out.println(Arrays.toString(ethiopianCalendar.monthDays(true, true)));    // Iterable Object of the given month
            System.out.println(Arrays.toString(ethiopianCalendar.monthDays()));        // => [2012, 7, 1, 1]
        } catch (
                Calander_Exceptions.MonthNumberException e) {
            e.printStackTrace();
        }
        System.out.println(ethiopianCalendar.nextMonth().getMonth()); // => ETC instance of nextMonth, same year
        System.out.println(ethiopianCalendar.previousYear().getYear()); // => ETC instance of prevYear, same month


        */
/**
         * Bahire Hasab Module [BahireHasab]
         *//*



        Bahirehasab bh2 = new Bahirehasab(); // Get's the current year
        Bahirehasab bh = new Bahirehasab(2012);
        try {
            System.out.println(bh.getSingleBealOrTsom("ትንሳኤ").toString()); // {month: ሚያዝያ, date: 16}
        } catch (Calander_Exceptions.BealNameException e) {
            e.printStackTrace();
        }

        System.out.println(bh.getAllAtswamat("ትንሳኤ",69).toString()); // => List of All fasting and Movable holidays
        System.out.println(bh.getEvange(true)); // => ሉቃስ
    }
}
*/
