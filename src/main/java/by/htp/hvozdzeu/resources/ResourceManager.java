package by.htp.hvozdzeu.resources;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

public class ResourceManager {

	private static final String RESOURCE_PATH = "Resource";
	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(RESOURCE_PATH);
	
	public static String getStr(String key) {
		return RESOURCE_BUNDLE.getString(key);
	}	
	
	public static String getStrLocale(String key, HttpServletRequest request) {
		String languageTag = (String) request.getSession().getAttribute("locale");
		Locale locale = Locale.forLanguageTag(languageTag);
		ResourceBundle resourceBundleLocale = ResourceBundle.getBundle(RESOURCE_PATH, locale);
		return resourceBundleLocale.getString(key);
	}
	
	
}
