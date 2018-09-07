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

import static by.htp.hvozdzeu.util.MailSender.mailSender;

public class BlockCardCommandImpl implements BaseCommand {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		Long cardId = Long.valueOf(request.getParameter("cardId"));

		iCreditCardService.blockCreditCard(cardId);
		
		User user = (User) request.getSession().getAttribute("user");
		Long clientId = user.getId();
		List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);

        CreditCard creditCard = iCreditCardService.findById(cardId);

		String emailToReply = user.getEmail();
		String subjectToReply = "Information about blocked credit card.";
		String messageToReply = "Hello. Your card # " + creditCard.getCardNumber() + " has been blocked. For additional information," +
				" please return to administrator.";
		mailSender(request, emailToReply, subjectToReply, messageToReply);


		request.setAttribute("cards", creditCards);
		return PagePathConstantPool.REDIRECT_LIST_CARD_CLIENT;
	}

}
