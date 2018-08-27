package by.htp.hvozdzeu.web.command.impl.card;

import static by.htp.hvozdzeu.web.util.RedirectPageUrl.LIST_CARD_VIEW;
import static by.htp.hvozdzeu.web.util.RequestAttributeEntity.REQUEST_ATTRIBUTE_CREDIT_CARD;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;

public class FindCardByParameters implements Command {
	
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		String param = request.getParameter("param");
		
		List<CreditCard> creditCards = iCreditCardService.findByParameter(param);
		
		request.getSession().setAttribute(REQUEST_ATTRIBUTE_CREDIT_CARD, creditCards);
		return LIST_CARD_VIEW.getUrl();
	}

}
