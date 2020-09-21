package abushakir.Tests;

import abushakir.calander.Bahirehasab;
import abushakir.util.Calander_Exceptions;
import org.junit.Test;

import static org.junit.Assert.*;

public class BahirehasabTest {

    Bahirehasab b = new Bahirehasab(2012);
    @Test
    public void metkih() {
        assertEquals(24,b.metkih);
    }

    @Test
    public void abekete() {
        assertEquals(6,b.abekte);
    }

    @Test
    public void newewe(){
        assertEquals("የካቲት",b.getNewewe().get("month"));
        assertEquals("2",b.getNewewe().get("date"));
    }

    @Test
    public void getSingleBealOrTsom() throws Calander_Exceptions.BealNameException {
        assertEquals("የካቲት",b.getSingleBealOrTsom("ዓቢይ ጾም").get("month"));
        assertEquals("16",b.getSingleBealOrTsom("ዓቢይ ጾም").get("date"));

        assertEquals("መጋቢት",b.getSingleBealOrTsom("ደብረ ዘይት").get("month"));
        assertEquals("13",b.getSingleBealOrTsom("ደብረ ዘይት").get("date"));

        assertEquals("ሚያዝያ",b.getSingleBealOrTsom("ሆሣዕና").get("month"));
        assertEquals("4",b.getSingleBealOrTsom("ሆሣዕና").get("date"));

        assertEquals("ሚያዝያ",b.getSingleBealOrTsom("ስቅለት").get("month"));
        assertEquals("9",b.getSingleBealOrTsom("ስቅለት").get("date"));
    }
}