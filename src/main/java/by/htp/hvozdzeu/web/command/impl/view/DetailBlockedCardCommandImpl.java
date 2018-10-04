package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.REQUEST_CARD_ID;

public class DetailBlockedCardCommandImpl implements BaseCommand {

	private CreditCardService creditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		Long cardId = Long.valueOf(request.getParameter(REQUEST_CARD_ID));

		CreditCard creditCard = creditCardService.findById(cardId);

		request.getSession().setAttribute(CREDIT_CARD, creditCard);
		return PagePathConstantPool.DETAIL_BLOCKED_CARD;
	}

}
