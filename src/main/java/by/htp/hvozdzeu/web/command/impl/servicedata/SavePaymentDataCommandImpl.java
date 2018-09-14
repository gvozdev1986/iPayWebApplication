package by.htp.hvozdzeu.web.command.impl.servicedata;

import by.htp.hvozdzeu.model.PaymentData;
import by.htp.hvozdzeu.service.IPaymentDataService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class SavePaymentDataCommandImpl implements BaseCommand {

    private IPaymentDataService iPaymentService = ServiceFactory.getPaymentDataService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        String literalCode = request.getParameter(REQUEST_PARAM_CODE_LC);
        String numericCode = request.getParameter(REQUEST_PARAM_SERVICE_DATA_CODE);

        String serviceDateCode = literalCode + numericCode;
        String serviceDateName = request.getParameter(REQUEST_PARAM_DATE_NAME);
        String serviceDateGroup = request.getParameter(REQUEST_PARAM_DATE_GROUP);
        String serviceDataDescription = request.getParameter(REQUEST_PARAM_DATA_DESCRIPTION);

        PaymentData paymentData = new PaymentData.Builder()
                .paymentDataCode(serviceDateCode)
                .paymentDataName(serviceDateName)
                .paymentDataGroup(serviceDateGroup)
                .paymentDataDescription(serviceDataDescription)
                .build();

        iPaymentService.create(paymentData);


        return PagePathConstantPool.REDIRECT_PAYMENT_DATA_LIST;
    }
}
