package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.BankAccount;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.IBankAccountService;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.REQUEST_CARD_ID;

public class DetailBlockedCardCommandImpl implements BaseCommand {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	private IBankAccountService iBankAccountService = ServiceFactory.getBankAccountService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		Long cardId = Long.valueOf(request.getParameter(REQUEST_CARD_ID));

		CreditCard creditCard = iCreditCardService.findById(cardId);
		BankAccount bankAccount = iBankAccountService.findByCardId(cardId);

		request.getSession().setAttribute(REQUEST_BANK_ACCOUNT, bankAccount);
		request.getSession().setAttribute(CREDIT_CARD, creditCard);
		return PagePathConstantPool.DETAIL_BLOCKED_CARD;
	}

}
