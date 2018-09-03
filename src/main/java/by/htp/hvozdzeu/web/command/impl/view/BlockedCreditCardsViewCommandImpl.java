package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.htp.hvozdzeu.web.pagination.NavigationEvent.PAGINATION_LIST;

public class BlockedCreditCardsViewCommandImpl implements BaseCommand {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	private static final String LIST_BLOCKED_CREDIT_CARD = "listBlockedCreditCard";

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {
		List<CreditCard> creditBlockedCards = iCreditCardService.blockedCreditCard();

		request.getSession().setAttribute(PAGINATION_LIST, "blocked_credit_cards_view");	
		request.getSession().setAttribute(LIST_BLOCKED_CREDIT_CARD, creditBlockedCards);
		return PagePathConstantPool.BLOCKED_CREDIT_CARDS;

	}

}
