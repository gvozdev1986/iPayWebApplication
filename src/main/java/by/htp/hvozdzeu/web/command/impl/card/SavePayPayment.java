package by.htp.hvozdzeu.web.command.impl.card;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.BankAccount;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.Payment;
import by.htp.hvozdzeu.model.PaymentData;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.service.IBankAccountService;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.IPaymentDataService;
import by.htp.hvozdzeu.service.IPaymentService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import by.htp.hvozdzeu.web.util.RedirectPageUrl;

public class SavePayPayment implements Command {

	private IPaymentService iPaymentService = ServiceFactory.getPaymentService();
	private IBankAccountService iBankAccountService = ServiceFactory.getBankAccountService();
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	private IPaymentDataService iPaymentDataService = ServiceFactory.getPaymentDataService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

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

				Client client = (Client) request.getSession().getAttribute("client");
				Long clientId = client.getId();
				List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);
				List<PaymentData> paymentDatas = iPaymentDataService.read();
				request.getSession().setAttribute("client", client);
				request.getSession().setAttribute("cards", creditCards);
				request.getSession().setAttribute("groups", paymentDatas);				
				request.getSession().setAttribute("messageSavePayment", "The operation was successful.");
				return RedirectPageUrl.PAYMENT_SERVICE_VIEW.getUrl();

			} else {
				request.getSession().setAttribute("messageSavePayment", "Insufficient funds.");
				return RedirectPageUrl.PAYMENT_SERVICE_VIEW.getUrl();
			}

		} else {
			request.getSession().setAttribute("messageSavePayment", "Not right security code.");
		}

		return RedirectPageUrl.PAYMENT_SERVICE_VIEW.getUrl();
	}

}
