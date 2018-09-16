package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.IUserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.htp.hvozdzeu.util.HideSymbolsCreditCard.hideSymbolsCreditCard;
import static by.htp.hvozdzeu.util.MailHtmlConstructor.mailConstructor;
import static by.htp.hvozdzeu.util.MailSender.mailSender;

public class UnblockCardCommandImpl implements BaseCommand{
	
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	private IUserService iUserService = ServiceFactory.getUserService();
	
	private static final String COUNT_BLOCKED_CREDIT_CARD = "countBlockedCreditCard";
	private static final String LIST_BLOCKED_CREDIT_CARD = "listBlockedCreditCard";

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {
		Long cardId = Long.valueOf(request.getParameter("cardId"));
		iCreditCardService.unblockCreditCard(cardId);
		List<CreditCard> creditBlockedCards = iCreditCardService.blockedCreditCard();
		Integer countBlockedCreditCard = iCreditCardService.blockedCreditCard().size();

        CreditCard creditCard = iCreditCardService.findById(cardId);
        User user = iUserService.findById(creditCard.getClient());

		String emailToReply = user.getEmail();
		String subjectToReply = "Information about unblocking credit card.";
		String message = "Your card # " + hideSymbolsCreditCard(creditCard.getCardNumber())
				+ " has been unblocked. You can use you credit card in our system."
                + " For additional information please return to administrator.";

		String messageToReply = mailConstructor(user.getLastName(), user.getFirstName(), user.getPatronymic(), message);
		mailSender(request, emailToReply, subjectToReply, messageToReply, null);

		request.getSession().setAttribute(COUNT_BLOCKED_CREDIT_CARD, countBlockedCreditCard);
		request.getSession().setAttribute(LIST_BLOCKED_CREDIT_CARD, creditBlockedCards);		
		return PagePathConstantPool.DETAIL_BLOCKED_CARD;
	}

}
