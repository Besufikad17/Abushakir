package abushakir.util;

import java.util.HashMap;
import static abushakir.util.Util.*;
public class Constants {

    public static String[] evangelists = {"ዮሐንስ", "ማቴዎስ", "ማርቆስ", "ሉቃስ"};
    public static final int ameteFida = 5500;
    public static final int tinteAbekte = 11;
    public static final int tinteMetkih = 19;
    public static final double maxMillisecondsSinceEpoch = 8640000000000000.0;
    public static final int ethiopicEpoch = 2796;
    public static final int unixEpoch = 719163;
    public static final double dayMilliSec = 86400000.0;
    public static final int hourMilliSec = 3600000;
    public static final int minMilliSec = 60000;
    public static final int secMilliSec = 1000;

    /* Name of weekdays in amharic. */

    public static final String[] weekdays = {"ሰኞ","ማግሰኞ","ረቡዕ","ሐሙስ","አርብ","ቅዳሜ","እሁድ"};

     /* Number of days in Geez numbers. */

    public static final String[] dayNumbers = {"፩","፪","፫","፬","፭","፮","፯","፰","፱","፲","፲፩","፲፪","፲፫","፲፬","፲፭","፲፮","፲፯","፲፰","፲፱","፳","፳፩","፳፪","፳፫","፳፬","፳፭","፳፮","፳፯","፳፰","፳፱","፴"};

    /* Name of months in amharic. */

    public static final String[] months = {"መስከረም","ጥቅምት","ኅዳር","ታኅሳስ","ጥር","የካቲት","መጋቢት","ሚያዝያ","ግንቦት","ሰኔ","ኃምሌ","ነሐሴ","ጷጉሜን"};

    /* Weekday's Tewsak */

    public static HashMap<String,Integer> yeeletTewsak = getyeeletTewsak();

    /* Holiday's Tewsak */

    public static HashMap<String, Integer> yebealTewsak = getyebealTewsak();

    /* Geez Numbers */

    public static HashMap<Integer,String> geezNumbers = getGeezNumbers();
}
