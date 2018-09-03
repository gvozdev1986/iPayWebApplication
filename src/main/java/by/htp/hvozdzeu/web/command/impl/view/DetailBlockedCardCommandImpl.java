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

public class DetailBlockedCardCommandImpl implements BaseCommand {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	private IBankAccountService iBankAccountService = ServiceFactory.getBankAccountService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		Long cardId = Long.valueOf(request.getParameter("cardId"));

		CreditCard creditCard = iCreditCardService.findById(cardId);
		BankAccount bankAccount = iBankAccountService.findByCardId(cardId);

		request.getSession().setAttribute("bankAccount", bankAccount);
		request.getSession().setAttribute("creditCard", creditCard);
		return PagePathConstantPool.DETAIL_BLOCKED_CARD;
	}

}
