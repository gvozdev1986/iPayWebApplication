package by.htp.hvozdzeu.util;

import java.util.ResourceBundle;

/**
 * Class for getting properties
 */
public class DecoderProperties {

    private static final String DECODER_PROPERTY = "decoder_config";
    private static final String SECRET_KEY = "secret.key";

    /**
     * Private constructor
     */
    private DecoderProperties() {
    }

    /**
     * The method for getting property by key
     * @return String secret key for decrypt
     */
    public static String getSecretKey(){
        ResourceBundle rb = ResourceBundle.getBundle(DECODER_PROPERTY);
        return rb.getString(SECRET_KEY);
    }

}
