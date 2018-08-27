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

public class SaveTransfer implements Command {

	private static final String NON_ANOTHER_CARD = "0000 0000 0000 0000";
	private static final Long CODE_TRANSFER = 22L;

	private IPaymentService iPaymentService = ServiceFactory.getPaymentService();
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	private IBankAccountService iBankAccountService = ServiceFactory.getBankAccountService();
	private IPaymentDataService iPaymentDataService = ServiceFactory.getPaymentDataService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Long cardNumberFromId = Long.valueOf(request.getParameter("idCardFromTransf"));
		Long cardNumberToId = Long.valueOf(request.getParameter("idCardToTransf"));
		String anotherCardNumber = request.getParameter("anotherCardInput");
		BigDecimal sum = new BigDecimal(request.getParameter("sumCardTransf"));
		String description = request.getParameter("descriptionCardTransf");
		String code = request.getParameter("code");

		CreditCard cardFromVerifyCode = iCreditCardService.findById(cardNumberFromId);
		String vCode = cardFromVerifyCode.getVerifyCode();

		if (code.equals(vCode)) {
			if (anotherCardNumber.equals(NON_ANOTHER_CARD)) {
				if (cardNumberFromId.equals(cardNumberToId)) {
					request.getSession().setAttribute("messageFromTransfer",
							"You can't tranfer money to the same credit card.");
					return RedirectPageUrl.TRANSFER_VIEW.getUrl();
				} else {

					if (sum.intValue() <= 0 && sum != null) {
						request.getSession().setAttribute("messageFromTransfer", "The transfer amount can't be 0.");
						return RedirectPageUrl.TRANSFER_VIEW.getUrl();
					} else {

						BankAccount bankAccountFrom = iBankAccountService.findByCardId(cardNumberFromId);
						BigDecimal balanceFrom = bankAccountFrom.getBalanceBankAccount();
						Long bankAccountFromId = bankAccountFrom.getId();

						BankAccount bankAccountTo = iBankAccountService.findByCardId(cardNumberToId);
						BigDecimal balanceTo = bankAccountTo.getBalanceBankAccount();
						Long bankAccountToId = bankAccountTo.getId();

						CreditCard cardFrom = iCreditCardService.findById(cardNumberFromId);
						CreditCard cardTo = iCreditCardService.findById(cardNumberToId);

						if (balanceFrom.intValue() > sum.intValue()) {
							String fullWriteOffDescription = description + " (Transfer money from bank account ["
									+ bankAccountFrom.getNameAccount() + "] " + "credit card ["
									+ cardFrom.getCardNumber() + "]";

							String fullReFillDescription = description + " (Transfer money to bank account ["
									+ bankAccountTo.getNameAccount() + "] " + "credit card [" + cardTo.getCardNumber()
									+ "]";

							writeOffBalance(fullWriteOffDescription, sum, cardNumberFromId);
							reFillBalance(fullReFillDescription, sum, cardNumberToId);

							BigDecimal newBalanceMinus = balanceFrom.subtract(sum);
							iBankAccountService.updateBalance(newBalanceMinus, bankAccountFromId);

							BigDecimal newBalancePlus = balanceTo.add(sum);
							iBankAccountService.updateBalance(newBalancePlus, bankAccountToId);

							Client client = (Client) request.getSession().getAttribute("client");
							Long clientId = client.getId();
							List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);
							List<PaymentData> paymentDatas = iPaymentDataService.read();

							request.getSession().setAttribute("client", client);
							request.getSession().setAttribute("cards", creditCards);
							request.getSession().setAttribute("groups", paymentDatas);
							request.getSession().setAttribute("messageFromTransfer",
									"The operation was successful money transfer.");
							return RedirectPageUrl.TRANSFER_VIEW.getUrl();

						} else {
							request.getSession().setAttribute("messageFromTransfer", "Insufficient funds.");
							return RedirectPageUrl.TRANSFER_VIEW.getUrl();
						}

					}

				}

			} else {

				CreditCard anotherCreditCard = iCreditCardService.findByCreditCardNumber(anotherCardNumber);
				Long anotherCardNumberId = anotherCreditCard.getId();
				if (cardNumberFromId.equals(anotherCardNumberId)) {
					request.getSession().setAttribute("messageFromTransfer",
							"You can't tranfer money to the same credit card.");
					return RedirectPageUrl.TRANSFER_VIEW.getUrl();
				} else if (anotherCreditCard.isBlock()) {
					request.getSession().setAttribute("messageFromTransfer",
							"This card is locked, transaction is not possible.");
					return RedirectPageUrl.TRANSFER_VIEW.getUrl();
				} else {

					if (sum.intValue() <= 0 && sum != null) {
						request.getSession().setAttribute("messageFromTransfer", "The transfer amount can't be 0.");
						return RedirectPageUrl.TRANSFER_VIEW.getUrl();
					} else {

						BankAccount bankAccountFrom = iBankAccountService.findByCardId(cardNumberFromId);
						BigDecimal balanceFrom = bankAccountFrom.getBalanceBankAccount();
						Long bankAccountFromId = bankAccountFrom.getId();

						BankAccount bankAccountTo = iBankAccountService.findByCardId(anotherCardNumberId);
						BigDecimal balanceTo = bankAccountTo.getBalanceBankAccount();
						Long bankAccountToId = bankAccountTo.getId();

						CreditCard cardFrom = iCreditCardService.findById(cardNumberFromId);
						CreditCard cardTo = iCreditCardService.findById(cardNumberToId);

						if (balanceFrom.intValue() > sum.intValue()) {
							String fullWriteOffDescription = description + " (Transfer money from bank account ["
									+ bankAccountFrom.getNameAccount() + "] " + "credit card ["
									+ cardFrom.getCardNumber() + "]";

							String fullReFillDescription = description + " (Transfer money to bank account ["
									+ bankAccountTo.getNameAccount() + "] " + "credit card [" + cardTo.getCardNumber()
									+ "]";

							writeOffBalance(fullWriteOffDescription, sum, cardNumberFromId);
							reFillBalance(fullReFillDescription, sum, cardNumberToId);

							BigDecimal newBalanceMinus = balanceFrom.subtract(sum);
							iBankAccountService.updateBalance(newBalanceMinus, bankAccountFromId);

							BigDecimal newBalancePlus = balanceTo.add(sum);
							iBankAccountService.updateBalance(newBalancePlus, bankAccountToId);

							Client client = (Client) request.getSession().getAttribute("client");
							Long clientId = client.getId();
							List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);
							List<PaymentData> paymentDatas = iPaymentDataService.read();

							request.getSession().setAttribute("client", client);
							request.getSession().setAttribute("cards", creditCards);
							request.getSession().setAttribute("groups", paymentDatas);
							request.getSession().setAttribute("messageFromTransfer",
									"The operation was successful money transfer.");
							return RedirectPageUrl.TRANSFER_VIEW.getUrl();

						} else {
							request.getSession().setAttribute("messageFromTransfer", "Insufficient funds.");
							return RedirectPageUrl.TRANSFER_VIEW.getUrl();
						}

					}

				}

			}
		} else {
			request.getSession().setAttribute("messageFromTransfer", "Not right security code.");
			return RedirectPageUrl.TRANSFER_VIEW.getUrl();
		}

	}

	private void writeOffBalance(String fullWriteOffDescription, BigDecimal sum, Long cardNumberFromId)
			throws DAOException {
		Payment paymentWriteOff = new Payment.Builder().datePayment(new Date(System.currentTimeMillis()).toLocalDate())
				.timePayment(new Time(System.currentTimeMillis()).toLocalTime())
				.descriptionPayment(fullWriteOffDescription).paymentService(CODE_TRANSFER).amountPayment(sum)
				.creditCard(cardNumberFromId).build();
		iPaymentService.create(paymentWriteOff);
	}

	private void reFillBalance(String fullReFillDescription, BigDecimal sum, Long cardNumberToId) throws DAOException {
		Payment paymentReFill = new Payment.Builder().datePayment(new Date(System.currentTimeMillis()).toLocalDate())
				.timePayment(new Time(System.currentTimeMillis()).toLocalTime())
				.descriptionPayment(fullReFillDescription).paymentService(CODE_TRANSFER).amountPayment(sum)
				.creditCard(cardNumberToId).build();
		iPaymentService.create(paymentReFill);
	}

}
