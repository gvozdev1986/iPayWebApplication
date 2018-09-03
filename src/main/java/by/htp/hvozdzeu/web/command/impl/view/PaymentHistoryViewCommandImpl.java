package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.htp.hvozdzeu.web.pagination.NavigationEvent.PAGINATION_LIST;

public class PaymentHistoryViewCommandImpl implements BaseCommand {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		User user = (User) request.getSession().getAttribute("user");
		Long clientId = user.getId();
		List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);
		
		request.getSession().setAttribute(PAGINATION_LIST, "pagination");
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("cards", creditCards);
		return PagePathConstantPool.PAYMENT_HISTORY_VIEW;
	}

}
