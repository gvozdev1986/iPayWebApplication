package by.htp.hvozdzeu.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class DecoderTest {

    private String strBefore;
    private String strAfter;
    private String strSalt;
    private String str;

    @Before
    public void setUp() {
        strSalt = "4497352d-45f4-4574-8c85-056e1afc26f4";
        strBefore = "This is test sentence.";
        strAfter = "GbSSuH9is6LSeKrUQ3RIPve/bWAJI7V9yeCDIcigUL0=";
    }

    @Test
    public void encrypt() {
        str = Decoder.encrypt(strBefore, strSalt);
        assertNotEquals(strBefore, str);
        assertNotNull(str);
    }

    @Test
    public void decrypt() {
        str = Decoder.decrypt(strAfter, strSalt);
        System.out.println(str);
        assertEquals(strBefore, str);
        assertNotNull(str);
    }
}