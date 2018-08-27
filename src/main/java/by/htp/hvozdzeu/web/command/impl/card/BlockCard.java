package by.htp.hvozdzeu.web.command.impl.card;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import by.htp.hvozdzeu.web.util.RedirectPageUrl;

public class BlockCard implements Command {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Long cardId = Long.valueOf(request.getParameter("cardId"));

		iCreditCardService.blockCreditCard(cardId);
		
		Client client = (Client) request.getSession().getAttribute("client");
		Long clientId = client.getId();
		List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);

		request.setAttribute("cards", creditCards);
		return RedirectPageUrl.CREDIT_CARD_VIEW.getUrl();
	}

}
