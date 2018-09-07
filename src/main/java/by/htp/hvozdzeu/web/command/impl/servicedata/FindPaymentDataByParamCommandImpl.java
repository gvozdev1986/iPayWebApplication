package by.htp.hvozdzeu.web.command.impl.servicedata;

import by.htp.hvozdzeu.model.PaymentData;
import by.htp.hvozdzeu.service.IPaymentDataService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindPaymentDataByParamCommandImpl implements BaseCommand {

    private IPaymentDataService iPaymentDataService = ServiceFactory.getPaymentDataService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        String param = request.getParameter("param");
        List<PaymentData> paymentData = iPaymentDataService.findByParameter(param);
        request.getSession().setAttribute("paymentData", paymentData);
        return PagePathConstantPool.LIST_SERVICE_VIEW;
    }
}
