package by.htp.hvozdzeu.web.command.impl.locale;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.model.enums.Role;
import by.htp.hvozdzeu.web.command.Command;
import by.htp.hvozdzeu.web.command.CommandDirector;
import by.htp.hvozdzeu.web.util.RedirectPageUrl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Localization implements Command {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(CommandDirector.class);
	 private static final String REQUEST_PARAM_LOCALE = "locale";
	
	private Map<String, Locale> thairLocale = new HashMap<>();
	
	public Localization() {
		thairLocale.put("en", new Locale("en"));
		thairLocale.put("ru", new Locale("ru"));
		thairLocale.put("by", new Locale("by"));
		thairLocale.put("de", new Locale("de"));
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {		
		String codeLang = request.getParameter("lang");			
		request.getSession().setAttribute(REQUEST_PARAM_LOCALE, getLocale(codeLang));			
		return getRoleReloadPage(request);	
		
	}
	
	public Locale getLocale(String localeCode) {
        LOGGER.info("GET LOCALE: {}", localeCode);
        Locale locale;
        locale = thairLocale.get(localeCode);
        return locale;
    }

	private String getRoleReloadPage(HttpServletRequest request) {
		Client client = (Client) request.getSession().getAttribute("client");
		if (client != null) {
			Role role = client.getRole();
			if (role == Role.ADMINISTRATOR) {
				return RedirectPageUrl.ADMIN_PANEL_VIEW.getUrl();
			} else if (role == Role.CLIENT) {
				return RedirectPageUrl.CLIENT_PANEL_VIEW.getUrl();
			} else {
				return RedirectPageUrl.GREETING_PAGE_VIEW.getUrl();
			}
		} else {
			return RedirectPageUrl.GREETING_PAGE_VIEW.getUrl();
		}
	}

}
