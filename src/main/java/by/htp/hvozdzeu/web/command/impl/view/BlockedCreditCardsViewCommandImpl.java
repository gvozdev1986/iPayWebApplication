package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.htp.hvozdzeu.web.pagination.NavigationEvent.PAGINATION_LIST;

public class BlockedCreditCardsViewCommandImpl implements BaseCommand {

	private CreditCardService creditCardService = ServiceFactory.getCreditCardService();
	private static final String LIST_BLOCKED_CREDIT_CARD = "listBlockedCreditCard";
	private static final String PAGINATION_LIST_VALUE = "blocked_credit_cards_view";

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {
		List<CreditCard> creditBlockedCards = creditCardService.blockedCreditCard();
		request.getSession().setAttribute(PAGINATION_LIST, PAGINATION_LIST_VALUE);
		request.getSession().setAttribute(LIST_BLOCKED_CREDIT_CARD, creditBlockedCards);
		return PagePathConstantPool.BLOCKED_CREDIT_CARDS;

	}

}
