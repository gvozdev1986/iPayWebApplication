package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.service.IPaymentService;
import by.htp.hvozdzeu.service.IUserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.htp.hvozdzeu.util.GettingCurrency.currencyOnline;

public class GreetingPageViewCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingPageViewCommandImpl.class);
    private Map<String, String> currencyMap = new HashMap<>();
    private IUserService iUserService = ServiceFactory.getUserService();
    private IPaymentService iPaymentService = ServiceFactory.getPaymentService();
    private static final String COUNT_USER = "countUser";
    private static final String COUNT_OPERATION = "countOperation";
    private static final String CURRENCY_MAP = "currencyMap";

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        try {
            currencyMap = currencyOnline();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        Integer countUser = iUserService.read().size();
        Integer countOperation = iPaymentService.read().size();

        request.getSession().setAttribute(COUNT_USER, countUser);
        request.getSession().setAttribute(COUNT_OPERATION, countOperation);
        request.getSession().setAttribute(CURRENCY_MAP, currencyMap);
        return PagePathConstantPool.GREETING_PAGE_VIEW;

    }

}
