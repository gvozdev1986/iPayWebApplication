package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.model.enums.TypeCard;
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

import static by.htp.hvozdzeu.rest.ParameterConstantDeclaration.*;
import static by.htp.hvozdzeu.rest.ResponseManager.getResponse;
import static by.htp.hvozdzeu.rest.URLConstantPool.QUERY_TYPE_GET;
import static by.htp.hvozdzeu.rest.URLConstantPool.QUERY_TYPE_POST;
import static by.htp.hvozdzeu.rest.URLConstantPool.URL_CHECK_CREDIT_CARD;
import static by.htp.hvozdzeu.util.ApplicationCodeProperties.getAppCode;
import static by.htp.hvozdzeu.util.Decoder.encrypt;
import static by.htp.hvozdzeu.util.DecoderProperties.getSecretKey;
import static by.htp.hvozdzeu.web.command.impl.card.helper.SendPaymentInformationHelper.refillBalance;
import static by.htp.hvozdzeu.web.command.impl.card.helper.SendPaymentInformationHelper.writeOffBalance;
import static by.htp.hvozdzeu.web.command.impl.message.MessageCreateCreditCard.sendNotificationCreateCreditCard;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

/**
 * The class for work with create new credit card in system.
 * The class has some steps:
 * 1. Send query with parameters and get token.
 * 2. Send query with parameters and get response with status checking.
 */
public class InsertNewCreditCardCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsertNewCreditCardCommandImpl.class);

    private static final String MESSAGE_CHECK_CREDIT_CARD = "messageCheckCreditCard";
    private static final String MESSAGE_ERROR_CHECK_CREDIT_CARD = "Error check credit card.";
    private static final String MESSAGE_CHECK_CREDIT_CARD_SUCCESS = "insert_new_credit_card";
    private static final String MESSAGE_CREDIT_CARD_ALREADY_EXIST = "This credit card has been already created.";
    private static final String MSG_EVENT_NAME = "eventMessage";
    private static final Long CODE_SERVICE_DATA = 154L;

    /**
     * Instance for CreditCardService
     */
    private CreditCardService creditCardService = ServiceFactory.getCreditCardService();

    /**
     * The (command) method for checking credit card in server and creating credit card in system
     *
     * @param request HttpServletRequest getting parameters
     * @return String URL for redirect or forward with information
     * @throws CommandException Exception
     */
    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        User user = (User) request.getSession().getAttribute(REQUEST_PARAM_USER);

        String creditCardNumber = request.getParameter(REQUEST_PARAM_CARD_NUMBER);
        String cvCode = request.getParameter(REQUEST_PARAM_CARD_SECRET_CODE);
        String firstName = request.getParameter(REQUEST_PARAM_CARD_FIRST_NAME);
        String lastName = request.getParameter(REQUEST_PARAM_CARD_LAST_NAME);
        String monthValid = request.getParameter(REQUEST_PARAM_CARD_VALID_MONTH);
        String yearValid = request.getParameter(REQUEST_PARAM_CARD_VALID_YEAR);
        String creditCardType = request.getParameter(REQUEST_PARAM_CARD_TYPE);
        String orderNo = "N/A";

        if (creditCardService.findByCreditCardNumber(creditCardNumber) != null) {
            request.getSession().setAttribute(MESSAGE_CHECK_CREDIT_CARD, MESSAGE_CREDIT_CARD_ALREADY_EXIST);
            return PagePathConstantPool.ADD_NEW_CREDIT_CARD;
        }

        Map<Object, Object> parameters = new HashMap<>();
        parameters.put(PARAMETER_CREDIT_CARD_NUMBER, encrypt(creditCardNumber, getSecretKey()));
        parameters.put(PARAMETER_VC_CODE, encrypt(cvCode, getSecretKey()));
        parameters.put(PARAMETER_FIRST_NAME, encrypt(firstName, getSecretKey()));
        parameters.put(PARAMETER_LAST_NAME, encrypt(lastName, getSecretKey()));
        parameters.put(PARAMETER_MONTH_VALID, encrypt(monthValid, getSecretKey()));
        parameters.put(PARAMETER_YEAR_VALID, encrypt(yearValid, getSecretKey()));
        parameters.put(PARAMETER_APP_SECRET_CODE, getAppCode());

        Map<Object, Object> checkCardMap = new HashMap<>();
        checkCardMap.put(PARAMETER_CARD_NUMBER, encrypt(creditCardNumber, getSecretKey()));
        checkCardMap.put(PARAMETER_VC_CODE, encrypt(cvCode, getSecretKey()));
        checkCardMap.put(PARAMETER_APP_SECRET_CODE, getAppCode());

        LOGGER.debug("Start process check credit card to server.");

        Response responseCheckCreditCard = getResponse(URL_CHECK_CREDIT_CARD, checkCardMap, QUERY_TYPE_POST);
        LOGGER.debug("Get status check credit card: {}", responseCheckCreditCard.getMessage());

        if (responseCheckCreditCard.isStatus()) {

            LOGGER.debug("Create credit card.");
            String validDate = monthValid + "/" + yearValid;
            CreditCard creditCard = CreditCard.getBuilder()
                    .client(user.getId())
                    .cardNumber(creditCardNumber)
                    .cardFirstName(firstName)
                    .cardLastName(lastName)
                    .validDate(validDate)
                    .typeCard(TypeCard.valueOf(creditCardType))
                    .verifyCode(cvCode)
                    .block(false)
                    .build();

            LOGGER.debug("Save credit card.");
            creditCardService.createReturnId(creditCard);

            sendNotificationCreateCreditCard(request, user, creditCardNumber);
            LOGGER.debug("Send information about create new credit card.");

            writeOffBalance(new BigDecimal("1.00"), creditCardNumber, CODE_SERVICE_DATA, "Automatic operation.", orderNo);
            refillBalance(new BigDecimal("1.00"), creditCardNumber, CODE_SERVICE_DATA, "Automatic operation.", orderNo);
            LOGGER.debug("Save transaction in system about write-off and refill balance.");

            request.getSession().setAttribute(MSG_EVENT_NAME, MESSAGE_CHECK_CREDIT_CARD_SUCCESS);
            return PagePathConstantPool.REDIRECT_LIST_CARD_CLIENT;

        } else {
            request.getSession().setAttribute(MESSAGE_CHECK_CREDIT_CARD, MESSAGE_ERROR_CHECK_CREDIT_CARD);
            return PagePathConstantPool.ADD_NEW_CREDIT_CARD;
        }

    }

}