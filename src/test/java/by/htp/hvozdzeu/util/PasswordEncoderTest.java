package by.htp.hvozdzeu.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordEncoderTest {

    private PasswordEncoder passwordEncoder = PasswordEncoder.getInstance();

    @Test
    public void getEncodeData() {
        String oldPswd = "12345";
        String newPswd = passwordEncoder.getEncodeData(oldPswd);
        assertNotEquals(newPswd, oldPswd);
        assertTrue(newPswd, newPswd.length() > oldPswd.length());
        assertFalse(oldPswd.contains(newPswd));
    }

}