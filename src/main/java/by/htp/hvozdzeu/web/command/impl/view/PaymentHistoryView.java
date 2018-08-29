package by.htp.hvozdzeu.web.command.impl.view;

import static by.htp.hvozdzeu.web.pagination.NavigationEvent.PAGINATION_LIST;

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

public class PaymentHistoryView implements Command {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Client client = (Client) request.getSession().getAttribute("client");
		Long clientId = client.getId();
		List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);
		
		request.getSession().setAttribute(PAGINATION_LIST, "payment_history_view");		
		request.getSession().setAttribute("client", client);
		request.getSession().setAttribute("cards", creditCards);
		return RedirectPageUrl.PAYMENT_HISTORY_VIEW.getUrl();
	}

}
