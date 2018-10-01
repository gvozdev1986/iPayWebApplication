package by.htp.hvozdzeu.util;

import java.util.ResourceBundle;

/**
 * Class for getting properties
 */
public class ApplicationCodeProperties {

    private static final String DECODER_PROPERTY = "app_config";
    private static final String SECRET_KEY = "appSecretCode";

    /**
     * Private constructor
     */
    private ApplicationCodeProperties() {
    }

    /**
     * The method for getting property by key
     * @return String secret key for decrypt
     */
    public static String getAppCode(){
        ResourceBundle rb = ResourceBundle.getBundle(DECODER_PROPERTY);
        return rb.getString(SECRET_KEY);
    }

}
