package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.model.response.Response;
import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static by.htp.hvozdzeu.rest.ResponseManager.getResponse;
import static by.htp.hvozdzeu.rest.URLConstantPool.QUERY_TYPE_PUT;
import static by.htp.hvozdzeu.rest.URLConstantPool.URL_WRITE_OFF_BALANCE;
import static by.htp.hvozdzeu.web.command.impl.card.helper.SavePaymentParametersHelper.requestMapper;
import static by.htp.hvozdzeu.web.command.impl.card.helper.SavePaymentParametersHelper.writeOffParametersMapper;
import static by.htp.hvozdzeu.web.command.impl.card.helper.SendPaymentInformationHelper.writeOffBalance;
import static by.htp.hvozdzeu.web.command.impl.message.MessageCreateCreditCard.sendNotificationCreateCreditCard;
import static by.htp.hvozdzeu.web.util.BillPaymentBuilder.billBuilder;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.REQUEST_PARAM_USER;

/**
 * The class for send request to server and get result which use for save transaction data.
 */
public class SavePayPaymentCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(SavePayPaymentCommandImpl.class);

    private static final String MESSAGE_SAVE_PAYMENT = "messageSavePayment";
    private static final String MESSAGE_SAVE_SUCCESSFUL = "operation_was_successful";
    private static final String MESSAGE_SAVE_ERROR_AUTH = "not_correct_cv_code";
    private static final String MESSAGE_SAVE_NOT_ENOUGH_MONEY = "not_enough_money";
    private Map<String, Object> attributes;
    private Map<Object, Object> writeOffParameters;
    private CreditCardService creditCardService = ServiceFactory.getCreditCardService();

    /**
     * Constructor
     */
    public SavePayPaymentCommandImpl() {
        attributes = new HashMap<>();
        writeOffParameters = new HashMap<>();
    }

    /**
     * The method (command) for send request and save transaction data
     *
     * @param request HttpServletRequest getting parameters
     * @return URL for redirect or forward
     * @throws CommandException Exception
     */
    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {
        User user = (User) request.getSession().getAttribute(REQUEST_PARAM_USER);
        attributes = requestMapper(request);
        CreditCard creditCard = creditCardService.findById((Long) attributes.get("cardId"));
        writeOffParameters = writeOffParametersMapper(attributes, creditCard);
        Response responseWriteOffCreditCard = getResponse(URL_WRITE_OFF_BALANCE, writeOffParameters, QUERY_TYPE_PUT);
        LOGGER.debug("Write-off from credit card: {}", responseWriteOffCreditCard.getMessage());
        if (responseWriteOffCreditCard.getMessage().equals(MESSAGE_SAVE_NOT_ENOUGH_MONEY)) {
            request.getSession().setAttribute(MESSAGE_SAVE_PAYMENT, MESSAGE_SAVE_NOT_ENOUGH_MONEY);
            return PagePathConstantPool.REDIRECT_SAVE_PAY_PAYMENT;
        }
        if (responseWriteOffCreditCard.isStatus()) {
            sendNotificationCreateCreditCard(request, user, creditCard.getCardNumber());
            LOGGER.debug("Send information about create new credit card.");

            writeOffBalance(
                    new BigDecimal(String.valueOf(attributes.get("amount"))),
                    creditCard.getCardNumber(),
                    (Long) attributes.get("serviceId"),
                    (String) attributes.get("description"),
                    (String) attributes.get("orderNo")
            );
            LOGGER.debug("Save transaction in system about write-off and refill balance.");

            billBuilder(
                    request,
                    creditCard,
                    (Long) attributes.get("serviceId"),
                    (String) attributes.get("orderNo"),
                    new BigDecimal(String.valueOf(attributes.get("amount"))),
                    (String) attributes.get("description")
            );

            request.getSession().setAttribute(MESSAGE_SAVE_PAYMENT, MESSAGE_SAVE_SUCCESSFUL);
            return PagePathConstantPool.REDIRECT_SAVE_PAY_PAYMENT;
        } else {
            request.getSession().setAttribute(MESSAGE_SAVE_PAYMENT, MESSAGE_SAVE_ERROR_AUTH);
            return PagePathConstantPool.REDIRECT_SAVE_PAY_PAYMENT;
        }

    }



}
