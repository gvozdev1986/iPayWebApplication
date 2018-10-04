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
import static by.htp.hvozdzeu.rest.URLConstantPool.URL_TRANSFER;
import static by.htp.hvozdzeu.web.command.impl.card.helper.SavePaymentParametersHelper.*;
import static by.htp.hvozdzeu.web.command.impl.card.helper.SendPaymentInformationHelper.refillBalance;
import static by.htp.hvozdzeu.web.command.impl.card.helper.SendPaymentInformationHelper.writeOffBalance;
import static by.htp.hvozdzeu.web.command.impl.message.MessageCreateCreditCard.sendNotificationCreateCreditCard;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.REQUEST_PARAM_USER;

public class SaveTransferHisCreditCardCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveTransferHisCreditCardCommandImpl.class);
    private static final Long CODE_TRANSFER = 22L;
    private static final String MESSAGE_SAVE_NOT_ENOUGH_MONEY = "To conduct a transaction is impossible, not enough money.";
    private static final String MESSAGE_TRANSFER_INFORMATION = "messageFromTransfer";
    private static final String ERROR_THE_SAME_CREDIT_CARD = "You can't transfer money to the same credit card.";
    private static final String MESSAGE_SAVE_SUCCESSFUL = "The operation was successful.";
    private static final String MESSAGE_SAVE_ERROR_AUTH = "CV-Code not correct.";
    private CreditCardService creditCardService = ServiceFactory.getCreditCardService();
    private Map<String, Object> attributes;
    private Map<Object, Object> transferParametersMapper;

    public SaveTransferHisCreditCardCommandImpl() {
        attributes = new HashMap<>();
        transferParametersMapper = new HashMap<>();
    }

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {
        User user = (User) request.getSession().getAttribute(REQUEST_PARAM_USER);
        attributes = requestTransferMapper(request);

        CreditCard creditCardFrom = creditCardService.findById((Long) attributes.get("cardNumberFromId"));
        CreditCard creditCardTo = creditCardService.findById((Long) attributes.get("cardNumberToId"));

        if (creditCardFrom.getCardNumber().equals(creditCardTo.getCardNumber())) {
            request.getSession().setAttribute(MESSAGE_TRANSFER_INFORMATION, ERROR_THE_SAME_CREDIT_CARD);
            return PagePathConstantPool.REDIRECT_SAVE_TRANSFER_HIS_CARD;
        }

        transferParametersMapper = transferParametersMapper(attributes, creditCardFrom, creditCardTo);

        Response responseWriteOffCreditCard = getResponse(URL_TRANSFER, transferParametersMapper, QUERY_TYPE_PUT);
        LOGGER.debug("Write-off from credit card: {}", responseWriteOffCreditCard.getMessage());

        if (responseWriteOffCreditCard.getMessage().equals(MESSAGE_SAVE_NOT_ENOUGH_MONEY)) {
            request.getSession().setAttribute(MESSAGE_TRANSFER_INFORMATION, MESSAGE_SAVE_NOT_ENOUGH_MONEY);
            return PagePathConstantPool.REDIRECT_SAVE_PAY_PAYMENT;
        }

        if (responseWriteOffCreditCard.isStatus()) {
            sendNotificationCreateCreditCard(request, user, creditCardFrom.getCardNumber());
            writeOffBalance(new BigDecimal(String.valueOf(attributes.get("amount"))),
                    creditCardFrom.getCardNumber(),
                    CODE_TRANSFER,
                    (String) attributes.get("description"),
                    (String) attributes.get("orderNo")
            );
            refillBalance(new BigDecimal(String.valueOf(attributes.get("amount"))),
                    creditCardTo.getCardNumber(),
                    CODE_TRANSFER,
                    (String) attributes.get("description"),
                    (String) attributes.get("orderNo")
            );
            LOGGER.debug("Save transaction in system about write-off and refill balance.");
            request.getSession().setAttribute(MESSAGE_TRANSFER_INFORMATION, MESSAGE_SAVE_SUCCESSFUL);
            return PagePathConstantPool.REDIRECT_SAVE_TRANSFER_HIS_CARD;
        } else {
            request.getSession().setAttribute(MESSAGE_TRANSFER_INFORMATION, MESSAGE_SAVE_ERROR_AUTH);
            return PagePathConstantPool.REDIRECT_SAVE_TRANSFER_HIS_CARD;
        }

    }
}
