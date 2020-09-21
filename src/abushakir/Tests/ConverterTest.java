package abushakir.Tests;

import abushakir.util.Calander_Exceptions;
import abushakir.util.Converter;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {

    Converter c = new Converter();

    @Test
    public void convert_1_10_ToGeez() throws Calander_Exceptions.EthiopicNumberException {
        assertEquals("፬",c.convert_1_10_ToGeez(4));
    }

    @Test
    public void convert_11_100_ToGeez() throws Calander_Exceptions.EthiopicNumberException {
        assertEquals("፲፭",c.convert_11_100_ToGeez(15));
    }

    @Test
    public void convert_111_1000_ToGeez() throws Calander_Exceptions.EthiopicNumberException {
        assertEquals("፻፭",c.convert_111_1000_ToGeez(105));
    }

    @Test
    public void convertToEthiopic() throws Exception{
        assertEquals("፳፻፲፫",c.convertToGeez(2013));
    }
}