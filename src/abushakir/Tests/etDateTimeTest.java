package abushakir.Tests;

import abushakir.calander.etDateTime;
import org.junit.Test;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class etDateTimeTest {

    etDateTime ec = new etDateTime(2012,7,7);
    etDateTime e = new etDateTime(2010);
    etDateTime et = etDateTime.Parse("2012-07-07 15:12:17.500");
    etDateTime etd = new etDateTime().now();
    etDateTime ecc = new etDateTime().fromMillisecondsSinceEpoch(1585731446021L);

    @Test
    public void getYear(){
        assertEquals(2012,ec.getYear());
        assertEquals(2010,e.getYear());
        assertEquals(2012,et.getYear());
        assertEquals(2012,ecc.getYear());
    }

    @Test
    public void getMonth(){
        assertEquals(7,ec.getMonth());
        assertEquals(1,e.getMonth());
        assertEquals(7,et.getMonth());
        assertEquals(7,ecc.getMonth());
    }

    @Test
    public void getDay(){
        assertEquals(7,ec.getDay());
        assertEquals(1,e.getDay());
        assertEquals(7,et.getDay());
        assertEquals(23,ecc.getDay());
    }

    @Test
    public void getDayGeez(){
        assertEquals("፯",ec.getDayGeez());
        assertEquals("፯",et.getDayGeez());
        assertEquals("፳፫",ecc.getDayGeez());
    }

    @Test
    public void getMonthGeez() {
        assertEquals("መጋቢት", ec.getMonthGeez());
    }

    @Test
    public void to_String() {
        assertEquals("2012-07-07 15:12:17.500", et.to_String());
        assertEquals("2012-07-23 08:57:26.021",ecc.to_String());
    }

    @Test
    public void getHour() {
        assertEquals(15,et.getHour());
        assertEquals(8, ecc.getHour());
    }

    @Test
    public void getMinute() {
        assertEquals(12,et.getMinute());
        assertEquals(Integer.parseInt(String.valueOf(LocalDateTime.now().getMinute())),etd.getMinute());
        assertEquals(57,ecc.getMinute());
    }

    @Test
    public void getSecond() {
        assertEquals(17,et.getSecond());
        assertEquals(Integer.parseInt(String.valueOf(LocalDateTime.now().getSecond())),etd.getSecond());
        assertEquals(26,ecc.getSecond());
    }

    @Test
    public void getMillisecond() {
        assertEquals(500, et.getMillisecond());
        assertEquals(21,ecc.getMillisecond());
    }

    @Test
    public void isAfter() {
      assertTrue(e.isAfter(new etDateTime(2001)));
    }

    @Test
    public void isBefore() {
        assertTrue(ec.isBefore(new etDateTime(2020)));
    }

    @Test
    public void isisAtSameMomentAs() {
        assertTrue(etd.isAtSameMomentAs(new etDateTime(
                etd.getYear(),etd.getMonth(),etd.getDay(),
                etd.getHour(),etd.getMinute(),etd.getSecond(),
                etd.getMillisecond()
        )));
    }

}



