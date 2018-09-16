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

import static by.htp.hvozdzeu.util.HideSymbolsCreditCard.hideSymbolsCreditCard;
import static by.htp.hvozdzeu.util.MailHtmlConstructor.mailConstructor;
import static by.htp.hvozdzeu.util.MailSender.mailSender;

public class SaveTransferCommandImpl implements BaseCommand {

	private static final String NON_ANOTHER_CARD = "0000 0000 0000 0000";
	private static final Long CODE_TRANSFER = 22L;

	private IPaymentService iPaymentService = ServiceFactory.getPaymentService();
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	private IBankAccountService iBankAccountService = ServiceFactory.getBankAccountService();
	private IPaymentDataService iPaymentDataService = ServiceFactory.getPaymentDataService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

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
							"You can't transfer money to the same credit card.");
					return PagePathConstantPool.REDIRECT_SAVE_TRANSFER;
				} else {

					if (sum.intValue() <= 0 && sum != null) {
						request.getSession().setAttribute("messageFromTransfer", "The transfer amount can't be 0.");
						return PagePathConstantPool.REDIRECT_SAVE_TRANSFER;
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

							User user = (User) request.getSession().getAttribute("user");
							Long clientId = user.getId();
							List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);
							List<PaymentData> paymentDatas = iPaymentDataService.read();

							String emailToReply = user.getEmail();
							String subjectToReply = "Information about the write-off of funds on your credit card.";
							String message = "Hello. " +
									"From your card # " + hideSymbolsCreditCard(cardFrom.getCardNumber()) + " has been wrote " +
									"" + sum + " for " + description;
							String messageToReply = mailConstructor(user.getLastName(), user.getFirstName(), user.getPatronymic(), message);
							mailSender(request, emailToReply, subjectToReply, messageToReply, null);

							request.getSession().setAttribute("user", user);
							request.getSession().setAttribute("cards", creditCards);
							request.getSession().setAttribute("groups", paymentDatas);
							request.getSession().setAttribute("messageFromTransfer",
									"The operation was successful money transfer.");
							return PagePathConstantPool.REDIRECT_SAVE_TRANSFER;

						} else {
							request.getSession().setAttribute("messageFromTransfer", "Insufficient funds.");
							return PagePathConstantPool.REDIRECT_SAVE_TRANSFER;
						}

					}

				}

			} else {

				CreditCard anotherCreditCard = iCreditCardService.findByCreditCardNumber(anotherCardNumber);
				Long anotherCardNumberId = anotherCreditCard.getId();
				if (cardNumberFromId.equals(anotherCardNumberId)) {
					request.getSession().setAttribute("messageFromTransfer",
							"You can't tranfer money to the same credit card.");
					return PagePathConstantPool.REDIRECT_SAVE_TRANSFER;
				} else if (anotherCreditCard.isBlock()) {
					request.getSession().setAttribute("messageFromTransfer",
							"This card is locked, transaction is not possible.");
					return PagePathConstantPool.REDIRECT_SAVE_TRANSFER;
				} else {

					if (sum.intValue() <= 0 && sum != null) {
						request.getSession().setAttribute("messageFromTransfer", "The transfer amount can't be 0.");
						return PagePathConstantPool.REDIRECT_SAVE_TRANSFER;
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

							User user = (User) request.getSession().getAttribute("user");
							Long clientId = user.getId();
							List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);
							List<PaymentData> paymentDatas = iPaymentDataService.read();

							String emailToReply = user.getEmail();
							String subjectToReply = "Information about the write-off of funds on any credit card.";
							String message = "Hello. " +
									"From your card # " + hideSymbolsCreditCard(cardFrom.getCardNumber()) + " has been wrote " +
									"" + sum + " for " + description;
							String messageToReply = mailConstructor(user.getLastName(), user.getFirstName(), user.getPatronymic(), message);
							mailSender(request, emailToReply, subjectToReply, messageToReply, null);

							request.getSession().setAttribute("user", user);
							request.getSession().setAttribute("cards", creditCards);
							request.getSession().setAttribute("groups", paymentDatas);
							request.getSession().setAttribute("messageFromTransfer",
									"The operation was successful money transfer.");
							return PagePathConstantPool.REDIRECT_SAVE_TRANSFER;

						} else {
							request.getSession().setAttribute("messageFromTransfer", "Insufficient funds.");
							return PagePathConstantPool.REDIRECT_SAVE_TRANSFER;
						}

					}

				}

			}
		} else {
			request.getSession().setAttribute("messageFromTransfer", "Not right security code.");
			return PagePathConstantPool.REDIRECT_SAVE_TRANSFER;
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
