package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.CREDIT_CARDS;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.PARAMETER;

public class FindCardByParametersCommandImpl implements BaseCommand {
	
	private CreditCardService creditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		String param = request.getParameter(PARAMETER);
		
		List<CreditCard> creditCards = creditCardService.findByParameter(param);
		
		request.getSession().setAttribute(CREDIT_CARDS, creditCards);
		return PagePathConstantPool.LIST_CARD_VIEW;
	}

}
