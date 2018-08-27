package by.htp.hvozdzeu.web.command.impl.card;

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

public class UnblockCard implements Command{
	
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	
	private static final String COUNT_BLOCKED_CREDIT_CARD = "countBlockedCreditCard";
	private static final String LIST_BLOCKED_CREDIT_CARD = "listBlockedCreditCard";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Long cardId = Long.valueOf(request.getParameter("cardId"));
		iCreditCardService.unblockCreditCard(cardId);
		
		List<CreditCard> creditBlockedCards = iCreditCardService.blockedCreditCard();
		
		Integer countBlockedCreditCard = iCreditCardService.blockedCreditCard().size();
		
		request.getSession().setAttribute(COUNT_BLOCKED_CREDIT_CARD, countBlockedCreditCard);
		request.getSession().setAttribute(LIST_BLOCKED_CREDIT_CARD, creditBlockedCards);		
		return DETAIL_BLOCKED_CARD.getUrl();
	}

}
