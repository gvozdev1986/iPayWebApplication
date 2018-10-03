package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.PaymentData;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.model.response.Response;
import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.PaymentDataService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.htp.hvozdzeu.rest.ResponseManager.getResponse;
import static by.htp.hvozdzeu.rest.URLConstantPool.QUERY_TYPE_POST;
import static by.htp.hvozdzeu.rest.URLConstantPool.URL_CHECK_SERVER_STATUS;
import static by.htp.hvozdzeu.util.ApplicationCodeProperties.getAppCode;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class UserPanelViewCommandImpl implements BaseCommand {

    private static final String SERVER_STATUS_TRUE = "true";
    private static final String SERVER_STATUS_FALSE = "false";

	private CreditCardService creditCardService = ServiceFactory.getCreditCardService();
	private PaymentDataService paymentDataService = ServiceFactory.getPaymentDataService();
    private Map<Object, Object> parameters;

    public UserPanelViewCommandImpl() {
        parameters = new HashMap<>();
    }

    @Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		User user = (User) request.getSession().getAttribute(REQUEST_PARAM_USER);
		Long userId = user.getId();
		List<StatusCardReport> creditCards = creditCardService.findCreditCardByIdClient(userId);
		List<PaymentData> paymentDates = paymentDataService.getAllPaymentsData();


        parameters.put("appSecretCode", getAppCode());
        Response serverStatus = getResponse(URL_CHECK_SERVER_STATUS, parameters, QUERY_TYPE_POST);

        if(serverStatus == null){
            request.getSession().setAttribute(SERVER_STATUS, SERVER_STATUS_FALSE);
        } else {
            request.getSession().setAttribute(SERVER_STATUS, SERVER_STATUS_TRUE);
        }

		request.getSession().setAttribute(REQUEST_PARAM_USER, user);
		request.getSession().setAttribute(REQUEST_CARDS, creditCards);
		request.getSession().setAttribute(REQUEST_GROUPS, paymentDates);
		return PagePathConstantPool.LOAD_CLIENT_PANEL;

	}

}
