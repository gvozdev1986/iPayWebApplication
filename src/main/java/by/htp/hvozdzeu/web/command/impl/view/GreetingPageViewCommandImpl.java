package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.service.PaymentDataService;
import by.htp.hvozdzeu.service.PaymentService;
import by.htp.hvozdzeu.service.UserService;
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
    private static final String COUNT_USER = "countUser";
    private static final String COUNT_OPERATION = "countOperation";
    private static final String CURRENCY_MAP = "currencyMap";
    private static final String COUNT_SERVICE = "countService";
    private Map<String, String> currencyMap = new HashMap<>();
    private UserService userService = ServiceFactory.getUserService();
    private PaymentService paymentService = ServiceFactory.getPaymentService();
    private PaymentDataService paymentDataService = ServiceFactory.getPaymentDataService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        try {
            currencyMap = currencyOnline();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        Integer countUser = userService.getAllUsers().size();
        Integer countOperation = paymentService.getAllPayments().size();
        Integer countService = paymentDataService.getAllPaymentsData().size();

        request.getSession().setAttribute(COUNT_USER, countUser);
        request.getSession().setAttribute(COUNT_OPERATION, countOperation);
        request.getSession().setAttribute(CURRENCY_MAP, currencyMap);
        request.getSession().setAttribute(COUNT_SERVICE, countService);
        return PagePathConstantPool.GREETING_PAGE_VIEW;

    }

}
