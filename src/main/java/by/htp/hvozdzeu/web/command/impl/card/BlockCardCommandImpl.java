package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.htp.hvozdzeu.util.HideSymbolsCreditCard.hideSymbolsCreditCard;
import static by.htp.hvozdzeu.util.MailHtmlConstructor.mailConstructor;
import static by.htp.hvozdzeu.util.MailSender.mailSender;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class BlockCardCommandImpl implements BaseCommand {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		Long cardId = Long.valueOf(request.getParameter(REQUEST_CARD_ID));

		iCreditCardService.blockCreditCard(cardId);
		
		User user = (User) request.getSession().getAttribute(REQUEST_PARAM_USER);
		Long clientId = user.getId();
		List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);

        CreditCard creditCard = iCreditCardService.findById(cardId);

		String emailToReply = user.getEmail();
		String subjectToReply = "Information about blocking credit card.";

		String message = "Your card # " + hideSymbolsCreditCard(creditCard.getCardNumber())
                + " has been blocked. For additional information," +
                " please return to administrator.";

		String messageToReply = mailConstructor(user.getLastName(), user.getFirstName(), user.getPatronymic(), message);
		mailSender(request, emailToReply, subjectToReply, messageToReply, null);

		request.setAttribute(REQUEST_CARDS, creditCards);
		return PagePathConstantPool.REDIRECT_LIST_CARD_CLIENT;
	}

}
