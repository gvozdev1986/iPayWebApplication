package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.PaymentData;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.PaymentDataService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.REQUEST_CARDS;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.REQUEST_GROUPS;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.REQUEST_PARAM_USER;

public class CreditCardViewCommandImpl implements BaseCommand {

	private CreditCardService creditCardService = ServiceFactory.getCreditCardService();
	private PaymentDataService paymentDataService = ServiceFactory.getPaymentDataService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		User user = (User) request.getSession().getAttribute(REQUEST_PARAM_USER);
		Long clientId = user.getId();
		List<CreditCard> creditCards = creditCardService.findCreditCardByIdClient(clientId);
		List<PaymentData> paymentDates = paymentDataService.getAllPaymentsData();

		request.getSession().setAttribute(REQUEST_PARAM_USER, user);
		request.getSession().setAttribute(REQUEST_CARDS, creditCards);
		request.getSession().setAttribute(REQUEST_GROUPS, paymentDates);
		return PagePathConstantPool.CREDIT_CARD_VIEW;
	}

}
