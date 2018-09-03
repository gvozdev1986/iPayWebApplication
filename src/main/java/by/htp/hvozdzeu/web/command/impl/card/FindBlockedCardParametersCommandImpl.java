package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindBlockedCardParametersCommandImpl implements BaseCommand {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	
	private static final String LIST_BLOCKED_CREDIT_CARD = "listBlockedCreditCard";

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		String param = request.getParameter("param");
		List<CreditCard> creditBlockedCards = iCreditCardService.findBlockedByParameter(param);
		request.getSession().setAttribute(LIST_BLOCKED_CREDIT_CARD, creditBlockedCards);
		return PagePathConstantPool.BLOCKED_CREDIT_CARDS;

	}
	
}
