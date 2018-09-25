package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.service.CreditCardService;
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
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class BlockCardCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockCardCommandImpl.class);
    private static final String MSG_EVENT_NAME = "eventMessage";
    private static final String MSG_EVENT_VALUE = "blocked_card_message";
	private CreditCardService creditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		Long cardId = Long.valueOf(request.getParameter(REQUEST_CARD_ID));

		creditCardService.blockCreditCard(cardId);
		
		User user = (User) request.getSession().getAttribute(REQUEST_PARAM_USER);
		Long clientId = user.getId();
		List<StatusCardReport> creditCards = creditCardService.findCreditCardByIdClient(clientId);

        CreditCard creditCard = creditCardService.findById(cardId);

		String emailToReply = user.getEmail();
		String subjectToReply = "Information about blocking credit card.";

		String message = "Your card # " + hideSymbolsCreditCard(creditCard.getCardNumber())
                + " has been blocked. For additional information," +
                " please return to administrator.";

        String messageToReply = null;
        try {
            messageToReply = mailConstructor(request, user.getLastName(), user.getFirstName(), user.getPatronymic(), message);
        } catch (IOException e) {
            LOGGER.error("Error send email");
        }
        mailSender(request, emailToReply, subjectToReply, messageToReply, null);

		request.getSession().setAttribute(MSG_EVENT_NAME, MSG_EVENT_VALUE);
		request.setAttribute(REQUEST_CARDS, creditCards);
		return PagePathConstantPool.REDIRECT_LIST_CARD_CLIENT;
	}

}
