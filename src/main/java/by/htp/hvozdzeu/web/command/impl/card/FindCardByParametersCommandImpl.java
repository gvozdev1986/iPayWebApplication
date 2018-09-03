package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindCardByParametersCommandImpl implements BaseCommand {
	
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		String param = request.getParameter("param");
		
		List<CreditCard> creditCards = iCreditCardService.findByParameter(param);
		
		request.getSession().setAttribute("creditCards", creditCards);
		return PagePathConstantPool.LIST_CARD_VIEW;
	}

}
