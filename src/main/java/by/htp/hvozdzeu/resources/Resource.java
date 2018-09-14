package by.htp.hvozdzeu.resources;

import java.util.ResourceBundle;

public class Resource {

	private static final String RESOURCE_PATH = "Resource";
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);

    private Resource() {
    }

    public static String getStr(String key) {
		return resourceBundle.getString(key);
	}
	
}
