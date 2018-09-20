package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.*;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.service.BankAccountService;
import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.PaymentDataService;
import by.htp.hvozdzeu.service.PaymentService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static by.htp.hvozdzeu.util.HideSymbolsCreditCard.hideSymbolsCreditCard;
import static by.htp.hvozdzeu.util.MailHtmlConstructor.mailConstructor;
import static by.htp.hvozdzeu.util.MailSender.mailSender;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class SavePayPaymentCommandImpl implements BaseCommand {

    private static final String MESSAGE_SAVE_PAYMENT = "messageSavePayment";

    private static final String MESSAGE_SAVE_SUCCESSFUL = "The operation was successful.";
    private static final String MESSAGE_SAVE_INSUFFICIENT = "Insufficient funds.";
    private static final String MESSAGE_SAVE_NOT_RIGHT_CODE = "Not right security code.";

    private PaymentService paymentService = ServiceFactory.getPaymentService();
    private BankAccountService bankAccountService = ServiceFactory.getBankAccountService();
    private CreditCardService creditCardService = ServiceFactory.getCreditCardService();
    private PaymentDataService paymentDataService = ServiceFactory.getPaymentDataService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long cardId = Long.valueOf(request.getParameter(REQUEST_ID_CARD));
        Long serviceId = Long.valueOf(request.getParameter(REQUEST_ID_SERVICE));
        BigDecimal sum = new BigDecimal(request.getParameter(SUM));
        String description = request.getParameter(DESCRIPTION);
        String verifyCode = request.getParameter(CODE);
        String orderNo = request.getParameter(ORDER_NO);

        CreditCard creditCard = creditCardService.findById(cardId);
        String vCode = creditCard.getVerifyCode();

        BankAccount bankAccount = bankAccountService.findByCardId(cardId);
        BigDecimal balance = bankAccount.getBalanceBankAccount();
        Long bankAccountId = bankAccount.getId();

        if (sum.intValue() == 0 || sum.intValue() < 0) {
            request.getSession().setAttribute(MESSAGE_SAVE_PAYMENT, "The transfer amount can't be 0.");
            return PagePathConstantPool.REDIRECT_SAVE_PAY_PAYMENT;
        }

        if(orderNo.isEmpty()){
            request.getSession().setAttribute(MESSAGE_SAVE_PAYMENT, "Order number cannot be empty.");
            return PagePathConstantPool.REDIRECT_SAVE_PAY_PAYMENT;
        }

        if (verifyCode.equals(vCode)) {

            if (balance.intValue() > sum.intValue()) {
                Payment payment = Payment.getBuilder()
                        .datePayment(new Date(System.currentTimeMillis()).toLocalDate())
                        .timePayment(new Time(System.currentTimeMillis()).toLocalTime())
                        .descriptionPayment(description)
                        .paymentService(serviceId)
                        .amountPayment(sum)
                        .orderNo(orderNo)
                        .creditCard(cardId).build();
                paymentService.create(payment);

                BigDecimal newBalance = balance.subtract(sum);

                bankAccountService.updateBalance(newBalance, bankAccountId);
                User user = (User) request.getSession().getAttribute(REQUEST_PARAM_USER);

                String emailToReply = user.getEmail();
                String subjectToReply = "Information about the write-off of funds.";
                String message = "Hello. " +
                        "From your card # " + hideSymbolsCreditCard(creditCard.getCardNumber()) + " has been wrote " +
                        "" + sum + " for " + description + ". Order no: " + orderNo;

                String messageToReply = mailConstructor(user.getLastName(), user.getFirstName(), user.getPatronymic(), message);
                mailSender(request, emailToReply, subjectToReply, messageToReply, null);


                Long clientId = user.getId();
                List<StatusCardReport> creditCards = creditCardService.findCreditCardByIdClient(clientId);
                List<PaymentData> paymentDates = paymentDataService.read();
                request.getSession().setAttribute(REQUEST_PARAM_USER, user);
                request.getSession().setAttribute(REQUEST_CARDS, creditCards);
                request.getSession().setAttribute(REQUEST_GROUPS, paymentDates);
                request.getSession().setAttribute(MESSAGE_SAVE_PAYMENT, MESSAGE_SAVE_SUCCESSFUL);
                return PagePathConstantPool.REDIRECT_SAVE_PAY_PAYMENT;

            } else {
                request.getSession().setAttribute(MESSAGE_SAVE_PAYMENT, MESSAGE_SAVE_INSUFFICIENT);
                return PagePathConstantPool.REDIRECT_SAVE_PAY_PAYMENT;
            }

        } else {
            request.getSession().setAttribute(MESSAGE_SAVE_PAYMENT, MESSAGE_SAVE_NOT_RIGHT_CODE);
        }

        return PagePathConstantPool.REDIRECT_SAVE_PAY_PAYMENT;
    }

}
