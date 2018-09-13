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

public class InsertNewCreditCardCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsertNewCreditCardCommandImpl.class);
    private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
    private IBankAccountService iBankAccountService = ServiceFactory.getBankAccountService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        User user = (User) request.getSession().getAttribute("user");

        Long userId = user.getId();
        String firstNameCreditCard = request.getParameter("cardFirstName");
        String lastNameCreditCard = request.getParameter("cardLastName");
        String cardNumber = request.getParameter("cardNumber");
        String cardValidMonth = request.getParameter("cardValidMonth");
        String cardValidYear = request.getParameter("cardValidYear");
        String creditCardType = request.getParameter("creditCardType");
        String secretCode = request.getParameter("secretCode");

        CreditCard checkCreditCard = iCreditCardService.findByCreditCardNumber(cardNumber);

        if (checkCreditCard != null) {

            request.getSession().setAttribute("messageCheckCreditCard", "Such a credit card exists.");

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
                String nameAccount = "BA_" + numberAccountFirstPart + numberAccountSecondPart;

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
                mailSender(request, emailToReply, subjectToReply, messageToReply);

                return PagePathConstantPool.REDIRECT_LIST_CARD_CLIENT;

            } else {
                request.getSession().setAttribute("messageErrorInsertNewCreditCard", "Error insert credit cards");
                return PagePathConstantPool.ADD_NEW_CREDIT_CARD;
            }

        }


    }
}
