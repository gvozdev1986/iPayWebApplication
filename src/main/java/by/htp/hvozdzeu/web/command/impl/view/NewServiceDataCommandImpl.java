package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.service.PaymentDataService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

public class NewServiceDataCommandImpl implements BaseCommand {

    private PaymentDataService paymentService = ServiceFactory.getPaymentDataService();
    private static final String MESSAGE_ID_CODE_SAVE = "idCodeForSave";

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long maxId = paymentService.maxIndex();
        Long idCodeForSave = maxId + 1;

        request.getSession().setAttribute(MESSAGE_ID_CODE_SAVE, idCodeForSave);
        return PagePathConstantPool.NEW_SERVICE_DATA_VIEW;
    }
}
