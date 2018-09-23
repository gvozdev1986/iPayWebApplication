package by.htp.hvozdzeu.util;

import by.htp.hvozdzeu.exception.DataException;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class PasswordEncoderTest {

    @Mock
    PasswordEncoder passwordEncoder = PasswordEncoder.getInstance();

    @Test
    public void getEncodeData() {
        String oldPswd = "12345";
        String newPswd = passwordEncoder.getEncodeData(oldPswd);
        assertNotEquals(newPswd, oldPswd);
        assertTrue(newPswd, newPswd.length() > oldPswd.length());
        assertFalse(oldPswd.contains(newPswd));
    }

}