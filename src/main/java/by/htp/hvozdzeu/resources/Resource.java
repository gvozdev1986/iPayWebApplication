package by.htp.hvozdzeu.resources;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

public class Resource {

	private static final String RESOURCE_PATH = "Resource";

	private static ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);

	public static String getStr(String key) {
		return resourceBundle.getString(key);
	}

	public static String getStrLocale(String key, HttpServletRequest request) {
		String languageTag = (String) request.getSession().getAttribute(REQUEST_PARAM_LOCALE);
		Locale locale = Locale.forLanguageTag(languageTag);
		ResourceBundle resourceBundleLocale = ResourceBundle.getBundle(RESOURCE_PATH, locale);
		return resourceBundleLocale.getString(key);
	}
	
}
