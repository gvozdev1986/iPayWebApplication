package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UnblockCardCommandImpl implements BaseCommand{
	
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	
	private static final String COUNT_BLOCKED_CREDIT_CARD = "countBlockedCreditCard";
	private static final String LIST_BLOCKED_CREDIT_CARD = "listBlockedCreditCard";

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {
		Long cardId = Long.valueOf(request.getParameter("cardId"));
		iCreditCardService.unblockCreditCard(cardId);
		
		List<CreditCard> creditBlockedCards = iCreditCardService.blockedCreditCard();
		
		Integer countBlockedCreditCard = iCreditCardService.blockedCreditCard().size();
		
		request.getSession().setAttribute(COUNT_BLOCKED_CREDIT_CARD, countBlockedCreditCard);
		request.getSession().setAttribute(LIST_BLOCKED_CREDIT_CARD, creditBlockedCards);		
		return PagePathConstantPool.DETAIL_BLOCKED_CARD;
	}

}
