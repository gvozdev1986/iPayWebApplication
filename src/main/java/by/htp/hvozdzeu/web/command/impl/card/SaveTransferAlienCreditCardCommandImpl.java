package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.*;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.service.IBankAccountService;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.IPaymentDataService;
import by.htp.hvozdzeu.service.IPaymentService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static by.htp.hvozdzeu.web.command.impl.card.helper.TransferHelper.sendEmailAboutTransfer;

public class SaveTransferAlienCreditCardCommandImpl implements BaseCommand {

    private static final Long CODE_TRANSFER = 22L;
    private static final String MESSAGE_TRANSFER_INFORMATION = "messageFromTransfer";
    private IPaymentService iPaymentService = ServiceFactory.getPaymentService();
    private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
    private IBankAccountService iBankAccountService = ServiceFactory.getBankAccountService();
    private IPaymentDataService iPaymentDataService = ServiceFactory.getPaymentDataService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long cardNumberFromId = Long.valueOf(request.getParameter("idCardFromTransf"));
        String anotherCardNumber = request.getParameter("anotherCardInput");
        BigDecimal sum = new BigDecimal(request.getParameter("sumCardTransf"));
        String description = request.getParameter("descriptionCardTransf");
        String code = request.getParameter("code");

        CreditCard cardFromVerifyCode = iCreditCardService.findById(cardNumberFromId);
        String vCode = cardFromVerifyCode.getVerifyCode();

        CreditCard anotherCreditCard = iCreditCardService.findByCreditCardNumber(anotherCardNumber);
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

            BankAccount bankAccountFrom = iBankAccountService.findByCardId(cardNumberFromId);
            BigDecimal balanceFrom = bankAccountFrom.getBalanceBankAccount();
            Long bankAccountFromId = bankAccountFrom.getId();

            BankAccount bankAccountTo = iBankAccountService.findByCardId(anotherCardNumberId);
            BigDecimal balanceTo = bankAccountTo.getBalanceBankAccount();
            Long bankAccountToId = bankAccountTo.getId();

            CreditCard cardFrom = iCreditCardService.findById(cardNumberFromId);
            CreditCard cardTo = iCreditCardService.findById(anotherCardNumberId);

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
                iBankAccountService.updateBalance(newBalanceMinus, bankAccountFromId);

                BigDecimal newBalancePlus = balanceTo.add(sum);
                iBankAccountService.updateBalance(newBalancePlus, bankAccountToId);

                User user = (User) request.getSession().getAttribute("user");
                Long clientId = user.getId();
                List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);
                List<PaymentData> paymentDates = iPaymentDataService.read();

                sendEmailAboutTransfer(request, user, sum, cardFrom, description);

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
        Payment paymentWriteOff = new Payment.Builder()
                .datePayment(new Date(System.currentTimeMillis()).toLocalDate())
                .timePayment(new Time(System.currentTimeMillis()).toLocalTime())
                .descriptionPayment(fullWriteOffDescription)
                .paymentService(CODE_TRANSFER).amountPayment(sum)
                .creditCard(cardNumberFromId).build();
        iPaymentService.create(paymentWriteOff);
    }

    private void reFillBalance(String fullReFillDescription, BigDecimal sum, Long cardNumberToId) throws DAOException {
        Payment paymentReFill = new Payment.Builder()
                .datePayment(new Date(System.currentTimeMillis()).toLocalDate())
                .timePayment(new Time(System.currentTimeMillis()).toLocalTime())
                .descriptionPayment(fullReFillDescription)
                .paymentService(CODE_TRANSFER).amountPayment(sum)
                .creditCard(cardNumberToId).build();
        iPaymentService.create(paymentReFill);
    }

}
