package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.PaymentData;
import by.htp.hvozdzeu.service.IPaymentDataService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

public class PaymentDataDetailViewCommandImpl implements BaseCommand {

    private IPaymentDataService iPaymentDataService = ServiceFactory.getPaymentDataService();
    private static final String PAYMENT_DATA_SERVICE_ID = "paymentDataServiceId";
    private static final String PAYMENT_DATA = "paymentData";

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long paymentDataServiceId = Long.valueOf(request.getParameter(PAYMENT_DATA_SERVICE_ID));
        PaymentData paymentData = iPaymentDataService.findById(paymentDataServiceId);

        request.getSession().setAttribute(PAYMENT_DATA, paymentData);
        request.getSession().setAttribute(PAYMENT_DATA_SERVICE_ID, paymentDataServiceId);
        return PagePathConstantPool.PAYMENT_DATA_DETAIL_VIEW;
    }
}
