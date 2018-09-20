package by.htp.hvozdzeu.web.command.impl.servicedata;

import by.htp.hvozdzeu.model.PaymentData;
import by.htp.hvozdzeu.service.PaymentDataService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.PARAMETER;

public class FindPaymentDataByParamCommandImpl implements BaseCommand {

    private PaymentDataService paymentDataService = ServiceFactory.getPaymentDataService();
    private static final String MESSAGE_PAYMENT_DATE = "paymentData";
    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        String param = request.getParameter(PARAMETER);
        List<PaymentData> paymentData = paymentDataService.findByParameter(param);
        request.getSession().setAttribute(MESSAGE_PAYMENT_DATE, paymentData);
        return PagePathConstantPool.LIST_SERVICE_VIEW;
    }
}
