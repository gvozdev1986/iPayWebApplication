package by.htp.hvozdzeu.web.command.impl.locale;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.REQUEST_PARAM_LOCALE;

/**
 * The class for change locale (ru_RU, en_EN)
 */
public class LocalizationCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalizationCommandImpl.class);

    /**
     * The method for change locale. This method get locale (ru or en) from parameter
     * and change it. Then get from session attribute current URI and after all event
     * redirect in this page.
     * @param request HttpServletRequest getting parameters
     * @return String url page
     * @throws CommandException exception
     */
    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {
        String locale = request.getParameter(REQUEST_PARAM_LOCALE);
        String currentUrl = request.getSession().getAttribute("currentPage").toString();
        LOGGER.debug("CHANGE LOCALE ON {}", locale);
        request.getSession().setAttribute(REQUEST_PARAM_LOCALE, locale);
        return currentUrl;
    }

}
