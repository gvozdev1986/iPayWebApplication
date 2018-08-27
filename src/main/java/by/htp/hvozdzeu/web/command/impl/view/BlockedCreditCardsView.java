package by.htp.hvozdzeu.web.command.impl.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import static by.htp.hvozdzeu.web.util.RedirectPageUrl.*;

public class BlockedCreditCardsView implements Command {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	
	private static final String LIST_BLOCKED_CREDIT_CARD = "listBlockedCreditCard";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {
		
		List<CreditCard> creditBlockedCards = iCreditCardService.blockedCreditCard();
		
		request.getSession().setAttribute(LIST_BLOCKED_CREDIT_CARD, creditBlockedCards);
		return BLOCKED_CREDIT_CARDS.getUrl();
		
	}

}
