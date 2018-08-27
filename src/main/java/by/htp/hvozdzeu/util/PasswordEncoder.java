package by.htp.hvozdzeu.util;

import by.htp.hvozdzeu.exception.DataException;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncoder {

    public String getEncodeData(String s) {
        if (s != null) {
            return DigestUtils.md5Hex(s);
        } else {
            throw new DataException(DataException.ERROR_MESSAGE_NO_DATA_ENCODE);
        }
    }

}
