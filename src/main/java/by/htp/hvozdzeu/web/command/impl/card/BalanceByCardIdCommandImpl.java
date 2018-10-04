package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class BalanceByCardIdCommandImpl implements BaseCommand {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BalanceByCardIdCommandImpl.class);
	private CreditCardService creditCardService = ServiceFactory.getCreditCardService();

	
	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {
		
		Long cardId = Long.valueOf(request.getParameter(REQUEST_CARD_ID));
		LOGGER.info("GET BALANCE CARD BY ID {}", cardId);

		User user = (User) request.getSession().getAttribute(REQUEST_PARAM_USER);
		Long clientId = user.getId();
		List<CreditCard> creditCards = creditCardService.findCreditCardByIdClient(clientId);

		request.setAttribute(REQUEST_CARDS, creditCards);
		return PagePathConstantPool.CREDIT_CARD_VIEW;
	}

}
