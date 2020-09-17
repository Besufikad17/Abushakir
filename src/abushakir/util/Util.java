package abushakir.util;

import java.util.ArrayList;
import java.util.HashMap;

public class Util {
    /*
     * util class contains functions used to create HashMap objects, formatted Strings and to
       find the index of values from array.
     */

    public static final int JD_EPOCH_OFFSET_GREGORIAN    = 1721426;
    public static final int JD_EPOCH_OFFSET_AMETE_ALEM   = -285019; // ዓ/ዓ
    public static final int JD_EPOCH_OFFSET_AMETE_MIHRET = 1723856;
    private boolean dateIsUnset = true;

   public static HashMap<String,Integer> getyeeletTewsak(){
       HashMap<String,Integer> yeeletTewsak = new HashMap<>();
       String[] keys = {"አርብ","ሐሙስ","ረቡዕ","ማግሰኞ","ሰኞ","እሁድ","ቅዳሜ"};
       int[] values = {2,3,4,5,6,7,8};

       for(int i=0; i<keys.length; i++){
           yeeletTewsak.put(keys[i], values[i]);
       }
       return yeeletTewsak;
   }

    public static HashMap<String,Integer> getyebealTewsak(){
        HashMap<String,Integer> yebealTewsak = new HashMap<>();
        String[] keys = {"ነነዌ","ዓቢይ ጾም","ደብረ ዘይት","ሆሣዕና","ስቅለት","ትንሳኤ","ርክበ ካህናት","ዕርገት","ጰራቅሊጦስ","ጾመ ሐዋርያት","ጾመ ድህነት"};
        int[] values = {0,14,41,62,67,69,93,108,118,119,121};

        for(int i=0; i<keys.length; i++){
            yebealTewsak.put(keys[i], values[i]);
        }
        return yebealTewsak;
    }

    public static int getAmeteAlem(int year, int ameteFide){
       return year + ameteFide;
    }

    public static int getWenber(int ameteFida , int year){
       if ((((year + ameteFida) % 19) - 1) < 0){
           return 0;
       }else{
           int result = ((year + ameteFida) % 19);
           System.out.println(year+ameteFida);
           return (result - 1);
       }

    }

    public static int indexOf(String s, String[] arr){
        int index = 0;
        for (int i = 0;i<arr.length;i++){
            if (arr[i].equals(s)){
                index=i;
            }
        }
        return index;
    }

    public static HashMap<String,String> getNeweweDate(String[] vals){
       HashMap<String,String> newew = new HashMap<>();
       String[] keys = {"month","date"};
       for(int i=0; i<keys.length; i++){
           newew.put(keys[i],vals[i]);
       }
       return newew;
    }

    public static HashMap<String,String> getSingleBealorTsomDate(String[] vals){
        HashMap<String,String> singleBealorTsom  = new HashMap<>();
        String[] keys = {"month","date"};
        for(int i=0; i<keys.length; i++){
            singleBealorTsom.put(keys[i],vals[i]);
        }
        return singleBealorTsom;
    }

    public static HashMap<String, Object> getAllAtswamatwDate(Object[] values) {
       String[] keys = {"beal","date"};
       HashMap<String,Object> a = new HashMap<>();
        for(int i=0; i<keys.length; i++){
            a.put(keys[i],values[i]);
        }
        return a;
    }

    public static String _fourDigits(int n) {
        int absN = Math.abs(n);
        String sign = n < 0 ? "-" : "";
        if (absN >= 1000) return "" + n;
        if (absN >= 100) return sign + "0" + absN;
        if (absN >= 10) return sign + "00" + absN;
        return sign + "000" + absN;
    }

    public static String _sixDigits(int n) {
        assert(n < -9999 || n > 9999);
        int absN = Math.abs(n);
        String sign = n < 0 ? "-" : "+";
        if (absN >= 100000) return sign + ("" + absN);
        return sign + "0" + absN;
    }

    public static String _threeDigits(int n) {
        if (n >= 100) return "" + n;
        if (n >= 10) return "0" + n;
        return "00" + n;
    }

    public static String _twoDigits(int n) {
        if (n >= 10) return "" + n;
        return "0" + n;
    }

    public static HashMap<String,String> getToJson(String[] values){
       String[] keys = {"year","month","date","hour","min","sec","ms"};
       HashMap<String,String> a = new HashMap<>();
        for(int i=0; i<keys.length; i++){
            a.put(keys[i],values[i]);
        }
        return a;
    }

    private int mod( long i, long j ) {
        return (int)( i - ( j * quotient( i, j ) ) );
    }


    public int[] gregorianToEthiopic(int year, int month, int day) {
        int jdn = gregorianToJDN( year, month, day );

        return jdnToEthiopic( jdn, guessEraFromJDN( jdn ) );
    }
    private int guessEraFromJDN( int jdn ) {
        return ( jdn >= (JD_EPOCH_OFFSET_AMETE_MIHRET+365) )
                ? JD_EPOCH_OFFSET_AMETE_MIHRET
                : JD_EPOCH_OFFSET_AMETE_ALEM
                ;
    }
    public int[] jdnToEthiopic( int jdn, int era ) {
        long r = mod( (jdn - era), 1461 ) ;
        long n = mod( r, 365 ) + 365 * quotient( r, 1460 ) ;

        int year = 4 * quotient( (jdn - era), 1461 )
                + quotient( r, 365 )
                - quotient( r, 1460 )
                ;
        int month = quotient( n, 30 ) + 1;
        int day   = mod( n, 30 ) + 1 ;

        return new int[] { year, month, day };
    }
    public int gregorianToJDN( int year, int month, int day ) {
        int s   = quotient ( year    ,   4 )
                - quotient ( year - 1,   4 )
                - quotient ( year    , 100 )
                + quotient ( year - 1, 100 )
                + quotient ( year    , 400 )
                - quotient ( year - 1, 400 )
                ;

        int t   = quotient ( 14 - month, 12 );

        int n   = 31 * t * ( month - 1 )
                + ( 1 - t ) * ( 59 + s + 30 * (month - 3) + quotient( (3*month - 7), 5) )
                + day - 1
                ;

        int j   = JD_EPOCH_OFFSET_GREGORIAN
                + 365 * (year - 1)
                + quotient ( year - 1,   4 )
                - quotient ( year - 1, 100 )
                + quotient ( year - 1, 400 )
                + n
                ;
        return j;
    }
    private int quotient( long i, long j ) {
        return (int)Math.floor( (double)i / j );
    }

    public static HashMap<Integer,String> getGeezNumbers(){
       HashMap<Integer,String> h = new HashMap<>();
       int[] keys = {1,2,3,4,5,6,7,8,9,10,20,30,40,50,60,70,80,90,100,1000};
       String[] values = {"፩","፪", "፫", "፬", "፭", "፮", "፯", "፰", "፱", "፲", "፳", "፴", "፵","፶", "፷", "፸",
               "፹", "፺","፻", "፼"};
       for(int i = 0;i < keys.length;i++){
           h.put(keys[i],values[i]);
       }
       return h;
    }

}
