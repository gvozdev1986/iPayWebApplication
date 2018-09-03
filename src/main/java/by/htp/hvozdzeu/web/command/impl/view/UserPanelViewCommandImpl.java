package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.PaymentData;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.IPaymentDataService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserPanelViewCommandImpl implements BaseCommand {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	private IPaymentDataService iPaymentDataService = ServiceFactory.getPaymentDataService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		User user = (User) request.getSession().getAttribute("user");
		Long userId = user.getId();
		List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(userId);
		List<PaymentData> paymentDates = iPaymentDataService.read();
		
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("cards", creditCards);
		request.getSession().setAttribute("groups", paymentDates);
		return PagePathConstantPool.LOAD_CLIENT_PANEL;

	}

}
