package by.htp.hvozdzeu.web.command.impl.card;

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

public class SavePayPaymentCommandImpl implements BaseCommand {

	private IPaymentService iPaymentService = ServiceFactory.getPaymentService();
	private IBankAccountService iBankAccountService = ServiceFactory.getBankAccountService();
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	private IPaymentDataService iPaymentDataService = ServiceFactory.getPaymentDataService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		Long cardId = Long.valueOf(request.getParameter("idCard"));
		Long serviceId = Long.valueOf(request.getParameter("idService"));
		BigDecimal sum = new BigDecimal(request.getParameter("sum"));
		String description = request.getParameter("description");
		String verifyCode = request.getParameter("code");

		CreditCard creditCard = iCreditCardService.findById(cardId);
		String vCode = creditCard.getVerifyCode();

		BankAccount bankAccount = iBankAccountService.findByCardId(cardId);
		BigDecimal balance = bankAccount.getBalanceBankAccount();
		Long bankAccountId = bankAccount.getId();

		if (verifyCode.equals(vCode)) {

			if (balance.intValue() > sum.intValue()) {
				Payment payment = new Payment.Builder().datePayment(new Date(System.currentTimeMillis()).toLocalDate())
						.timePayment(new Time(System.currentTimeMillis()).toLocalTime()).descriptionPayment(description)
						.paymentService(serviceId).amountPayment(sum).creditCard(cardId).build();
				iPaymentService.create(payment);

				BigDecimal newBalance = balance.subtract(sum);

				iBankAccountService.updateBalance(newBalance, bankAccountId);

				User user = (User) request.getSession().getAttribute("user");
				Long clientId = user.getId();
				List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);
				List<PaymentData> paymentDates = iPaymentDataService.read();
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("cards", creditCards);
				request.getSession().setAttribute("groups", paymentDates);
				request.getSession().setAttribute("messageSavePayment", "The operation was successful.");
				return PagePathConstantPool.REDIRECT_SAVE_PAY_PAYMENT;

			} else {
				request.getSession().setAttribute("messageSavePayment", "Insufficient funds.");
				return PagePathConstantPool.REDIRECT_SAVE_PAY_PAYMENT;
			}

		} else {
			request.getSession().setAttribute("messageSavePayment", "Not right security code.");
		}

		return PagePathConstantPool.REDIRECT_SAVE_PAY_PAYMENT;
	}

}
