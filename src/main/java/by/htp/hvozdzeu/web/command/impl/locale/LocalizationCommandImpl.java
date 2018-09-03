package by.htp.hvozdzeu.web.command.impl.locale;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.UserTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.util.PagePathConstantPool.*;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class LocalizationCommandImpl implements BaseCommand {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(LocalizationCommandImpl.class);
	 private static final String REQUEST_PARAM_LOCALE = "locale";
	
	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {
		String locale = request.getParameter(REQUEST_PARAM_LOCALE);


        LOGGER.debug("CHANGE LOCALE ON {}", locale);


		request.getSession().setAttribute(REQUEST_PARAM_LOCALE, locale);
		UserTypeEnum userType = (UserTypeEnum) request.getSession().getAttribute(REQUEST_PARAM_USER_TYPE);
		if (userType == UserTypeEnum.ADMIN) {
			request.getSession().setAttribute(SESSION_ATR_SESSION_PAGE_TYPE, PAGE_TYPE_ADMIN_PROFILE);
			return REDIRECT_ADMIN_URL;
		} else if (userType == UserTypeEnum.USER) {
			request.getSession().setAttribute(SESSION_ATR_SESSION_PAGE_TYPE, PAGE_TYPE_USER_PROFILE);
			return REDIRECT_USER_URL;
		} else {
			return REDIRECT_GUEST_URL;
		}
	}

}
