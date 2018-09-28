package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.dao.exception.DAOException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static by.htp.hvozdzeu.web.command.impl.card.helper.TransferHelper.sendEmailAboutTransfer;

public class SaveTransferAlienCreditCardCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveTransferAlienCreditCardCommandImpl.class);
    private static final Long CODE_TRANSFER = 22L;
    private static final String MESSAGE_TRANSFER_INFORMATION = "messageFromTransfer";
    private PaymentService paymentService = ServiceFactory.getPaymentService();
    private CreditCardService creditCardService = ServiceFactory.getCreditCardService();
    private BankAccountService bankAccountService = ServiceFactory.getBankAccountService();
    private PaymentDataService paymentDataService = ServiceFactory.getPaymentDataService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long cardNumberFromId = Long.valueOf(request.getParameter("idCardFromTransf"));
        String anotherCardNumber = request.getParameter("anotherCardInput");
        BigDecimal sum = new BigDecimal(request.getParameter("sumCardTransf"));
        String description = request.getParameter("descriptionCardTransf");
        String code = request.getParameter("code");

        CreditCard cardFromVerifyCode = creditCardService.findById(cardNumberFromId);
        String vCode = cardFromVerifyCode.getVerifyCode();

        CreditCard anotherCreditCard = creditCardService.findByCreditCardNumber(anotherCardNumber);

        if(anotherCreditCard == null){
            request.getSession().setAttribute(MESSAGE_TRANSFER_INFORMATION, "Alien credit card not found!");
            return PagePathConstantPool.REDIRECT_SAVE_TRANSFER_ALIEN_CARD;
        }

        Long anotherCardNumberId = anotherCreditCard.getId();

        if (cardNumberFromId.equals(anotherCardNumberId)) {
            request.getSession().setAttribute(MESSAGE_TRANSFER_INFORMATION, "You can't transfer money to the same credit card.");
            return PagePathConstantPool.REDIRECT_SAVE_TRANSFER_ALIEN_CARD;
        }

        if (anotherCreditCard.isBlock()) {
            request.getSession().setAttribute(MESSAGE_TRANSFER_INFORMATION, "This card is locked, transaction is not possible.");
            return PagePathConstantPool.REDIRECT_SAVE_TRANSFER_ALIEN_CARD;
        }

        if (cardNumberFromId.equals(anotherCardNumberId)) {
            request.getSession().setAttribute(MESSAGE_TRANSFER_INFORMATION, "You can't transfer money to the same credit card.");
            return PagePathConstantPool.REDIRECT_SAVE_TRANSFER_ALIEN_CARD;
        }

        if (sum.intValue() == 0 || sum.intValue() < 0) {
            request.getSession().setAttribute(MESSAGE_TRANSFER_INFORMATION, "The transfer amount can't be 0.");
            return PagePathConstantPool.REDIRECT_SAVE_TRANSFER_ALIEN_CARD;
        }

        if (code.equals(vCode)) {

            BankAccount bankAccountFrom = bankAccountService.findByCardId(cardNumberFromId);
            BigDecimal balanceFrom = bankAccountFrom.getBalanceBankAccount();
            Long bankAccountFromId = bankAccountFrom.getId();

            BankAccount bankAccountTo = bankAccountService.findByCardId(anotherCardNumberId);
            BigDecimal balanceTo = bankAccountTo.getBalanceBankAccount();
            Long bankAccountToId = bankAccountTo.getId();

            CreditCard cardFrom = creditCardService.findById(cardNumberFromId);
            CreditCard cardTo = creditCardService.findById(anotherCardNumberId);

            if (balanceFrom.intValue() > sum.intValue()) {
                String fullWriteOffDescription = description
                        + " (Transfer money from bank account ["
                        + bankAccountFrom.getNameAccount()
                        + "] " + "credit card ["
                        + cardFrom.getCardNumber()
                        + "]";

                String fullReFillDescription = description
                        + " (Transfer money to bank account ["
                        + bankAccountTo.getNameAccount()
                        + "] "
                        + "credit card ["
                        + cardTo.getCardNumber()
                        + "]";

                writeOffBalance(fullWriteOffDescription, sum, cardNumberFromId);
                reFillBalance(fullReFillDescription, sum, anotherCardNumberId);

                BigDecimal newBalanceMinus = balanceFrom.subtract(sum);
                bankAccountService.updateBalance(newBalanceMinus, bankAccountFromId);

                BigDecimal newBalancePlus = balanceTo.add(sum);
                bankAccountService.updateBalance(newBalancePlus, bankAccountToId);

                User user = (User) request.getSession().getAttribute("user");
                Long clientId = user.getId();
                List<StatusCardReport> creditCards = creditCardService.findCreditCardByIdClient(clientId);
                List<PaymentData> paymentDates = paymentDataService.getAllPaymentsData();

                try {
                    sendEmailAboutTransfer(request, user, sum, cardFrom, description);
                } catch (IOException e) {
                    LOGGER.error("Error send email.");
                }

                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("cards", creditCards);
                request.getSession().setAttribute("groups", paymentDates);
                request.getSession().setAttribute(MESSAGE_TRANSFER_INFORMATION, "The operation was successful money transfer to alien credit card.");
                return PagePathConstantPool.REDIRECT_SAVE_TRANSFER_ALIEN_CARD;
            } else {
                request.getSession().setAttribute(MESSAGE_TRANSFER_INFORMATION, "Insufficient funds.");
                return PagePathConstantPool.REDIRECT_SAVE_TRANSFER_ALIEN_CARD;
            }


        } else {
            request.getSession().setAttribute(MESSAGE_TRANSFER_INFORMATION, "Secret code doesn't much.");
            return PagePathConstantPool.REDIRECT_SAVE_TRANSFER_HIS_CARD;
        }

    }


    private void writeOffBalance(String fullWriteOffDescription, BigDecimal sum, Long cardNumberFromId) throws DAOException {
        Payment paymentWriteOff = Payment.getBuilder()
                .datePayment(new Date(System.currentTimeMillis()).toLocalDate())
                .timePayment(new Time(System.currentTimeMillis()).toLocalTime())
                .descriptionPayment(fullWriteOffDescription)
                .paymentService(CODE_TRANSFER).amountPayment(sum)
                .creditCard(cardNumberFromId).build();
        paymentService.create(paymentWriteOff);
    }

    private void reFillBalance(String fullReFillDescription, BigDecimal sum, Long cardNumberToId) throws DAOException {
        Payment paymentReFill = Payment.getBuilder()
                .datePayment(new Date(System.currentTimeMillis()).toLocalDate())
                .timePayment(new Time(System.currentTimeMillis()).toLocalTime())
                .descriptionPayment(fullReFillDescription)
                .paymentService(CODE_TRANSFER).amountPayment(sum)
                .creditCard(cardNumberToId).build();
        paymentService.create(paymentReFill);
    }

}
