package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.UserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static by.htp.hvozdzeu.util.HideSymbolsCreditCard.hideSymbolsCreditCard;
import static by.htp.hvozdzeu.util.MailHtmlConstructor.mailConstructor;
import static by.htp.hvozdzeu.util.MailSender.mailSender;

public class UnblockCardCommandImpl implements BaseCommand{

	private static final Logger LOGGER = LoggerFactory.getLogger(UnblockCardCommandImpl.class);

	private CreditCardService creditCardService = ServiceFactory.getCreditCardService();
	private UserService userService = ServiceFactory.getUserService();
	
	private static final String COUNT_BLOCKED_CREDIT_CARD = "countBlockedCreditCard";
	private static final String LIST_BLOCKED_CREDIT_CARD = "listBlockedCreditCard";

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {
		Long cardId = Long.valueOf(request.getParameter("cardId"));
		creditCardService.unblockCreditCard(cardId);
		List<CreditCard> creditBlockedCards = creditCardService.blockedCreditCard();
		Integer countBlockedCreditCard = creditCardService.blockedCreditCard().size();

        CreditCard creditCard = creditCardService.findById(cardId);
        User user = userService.findById(creditCard.getClient());

		String emailToReply = user.getEmail();
		String subjectToReply = "Information about unblocking credit card.";
		String message = "Your card # " + hideSymbolsCreditCard(creditCard.getCardNumber())
				+ " has been unblocked. You can use you credit card in our system."
                + " For additional information please return to administrator.";

		String messageToReply = null;
		try {
			messageToReply = mailConstructor(request, user.getLastName(), user.getFirstName(), user.getPatronymic(), message);
		} catch (IOException e) {
			LOGGER.error("Error send email.");
		}
		mailSender(request, emailToReply, subjectToReply, messageToReply, null);

		request.getSession().setAttribute(COUNT_BLOCKED_CREDIT_CARD, countBlockedCreditCard);
		request.getSession().setAttribute(LIST_BLOCKED_CREDIT_CARD, creditBlockedCards);		
		return PagePathConstantPool.DETAIL_BLOCKED_CARD;
	}

}
