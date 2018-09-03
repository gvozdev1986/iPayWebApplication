package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.service.IPaymentDataService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

public class NewServiceDataCommandImpl implements BaseCommand {

    private IPaymentDataService iPaymentService = ServiceFactory.getPaymentDataService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long maxId = iPaymentService.maxIndex();
        Long idCodeForSave = maxId + 1;

        request.getSession().setAttribute("idCodeForSave", idCodeForSave);
        return PagePathConstantPool.NEW_SERVICE_DATA_VIEW;
    }
}
