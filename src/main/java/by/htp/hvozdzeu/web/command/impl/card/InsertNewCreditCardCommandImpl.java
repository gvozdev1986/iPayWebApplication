package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.BankAccount;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.model.enums.TypeCard;
import by.htp.hvozdzeu.service.IBankAccountService;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static by.htp.hvozdzeu.util.MailSender.mailSender;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class InsertNewCreditCardCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsertNewCreditCardCommandImpl.class);

    private static final String MESSAGE_CHECK_CREDIT_CARD = "messageCheckCreditCard";
    private static final String MESSAGE_CHECK_CREDIT_CARD_VALUE = "Such a credit card exists.";
    private static final String MESSAGE_ERROR_INSERT_NEW_CREDIT_CARD = "messageErrorInsertNewCreditCard";
    private static final String MESSAGE_ERROR_INSERT_NEW_CREDIT_CARD_VALUE = "Error insert credit cards";

    private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
    private IBankAccountService iBankAccountService = ServiceFactory.getBankAccountService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        User user = (User) request.getSession().getAttribute(REQUEST_PARAM_USER);

        Long userId = user.getId();
        String firstNameCreditCard = request.getParameter(REQUEST_PARAM_CARD_FIRST_NAME);
        String lastNameCreditCard = request.getParameter(REQUEST_PARAM_CARD_LAST_NAME);
        String cardNumber = request.getParameter(REQUEST_PARAM_CARD_NUMBER);
        String cardValidMonth = request.getParameter(REQUEST_PARAM_CARD_VALID_MONTH);
        String cardValidYear = request.getParameter(REQUEST_PARAM_CARD_VALID_YEAR);
        String creditCardType = request.getParameter(REQUEST_PARAM_CARD_TYPE);
        String secretCode = request.getParameter(REQUEST_PARAM_CARD_SECRET_CODE);

        CreditCard checkCreditCard = iCreditCardService.findByCreditCardNumber(cardNumber);

        if (checkCreditCard != null) {

            request.getSession().setAttribute(MESSAGE_CHECK_CREDIT_CARD, MESSAGE_CHECK_CREDIT_CARD_VALUE);

            return PagePathConstantPool.ADD_NEW_CREDIT_CARD;

        } else {

            LOGGER.debug("First step. Create credit card.");
            String validDate = cardValidMonth + "/" + cardValidYear;
            CreditCard creditCard = new CreditCard.Builder()
                    .client(userId)
                    .cardNumber(cardNumber)
                    .cardFirstName(firstNameCreditCard)
                    .cardLastName(lastNameCreditCard)
                    .validDate(validDate)
                    .typeCard(TypeCard.valueOf(creditCardType))
                    .verifyCode(secretCode)
                    .block(false)
                    .build();

            Long newCreditCardId = iCreditCardService.createReturnId(creditCard);

            if (newCreditCardId != null && newCreditCardId != 0) {

                String numberAccountFirstPart = cardNumber.split(" ")[0];
                String numberAccountSecondPart = cardNumber.split(" ")[3];
                String nameAccount = CODE_NAME_ACCOUNT + numberAccountFirstPart + numberAccountSecondPart;

                LOGGER.debug("Second step. Create bank account.");
                BankAccount bankAccount = new BankAccount.Builder()
                        .creditCard(newCreditCardId)
                        .nameAccount(nameAccount)
                        .statusBankAccount(false)
                        .balanceBankAccount(BigDecimal.valueOf(0.00))
                        .available(false)
                        .build();

                iBankAccountService.create(bankAccount);

                String emailToReply = user.getEmail();
                String subjectToReply = "Information about insert new credit card.";
                String messageToReply = "Hello. " +
                        "Your card # " + creditCard.getCardNumber() + " has been inserted. " +
                        "And create new bank account [" + nameAccount + "] with code your locale position. " +
                        "For additional information, please return to administrator.";
                mailSender(request, emailToReply, subjectToReply, messageToReply, null);

                return PagePathConstantPool.REDIRECT_LIST_CARD_CLIENT;

            } else {
                request.getSession().setAttribute(MESSAGE_ERROR_INSERT_NEW_CREDIT_CARD, MESSAGE_ERROR_INSERT_NEW_CREDIT_CARD_VALUE);
                return PagePathConstantPool.ADD_NEW_CREDIT_CARD;
            }

        }


    }
}
