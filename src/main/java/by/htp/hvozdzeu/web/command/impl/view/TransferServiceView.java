package by.htp.hvozdzeu.web.command.impl.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.model.PaymentData;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.IPaymentDataService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import by.htp.hvozdzeu.web.util.RedirectPageUrl;

public class TransferServiceView implements Command {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	private IPaymentDataService iPaymentDataService = ServiceFactory.getPaymentDataService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Client client = (Client) request.getSession().getAttribute("client");
		Long clientId = client.getId();
		List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);
		List<PaymentData> paymentDatas = iPaymentDataService.read();

		request.getSession().setAttribute("client", client);
		request.getSession().setAttribute("cards", creditCards);
		request.getSession().setAttribute("groups", paymentDatas);
		return RedirectPageUrl.TRANSFER_VIEW.getUrl();
	}

}
