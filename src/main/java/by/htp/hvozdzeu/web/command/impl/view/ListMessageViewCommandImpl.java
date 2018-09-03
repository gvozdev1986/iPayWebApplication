package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.MessageContact;
import by.htp.hvozdzeu.service.IMessageContactService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ListMessageViewCommandImpl implements BaseCommand {

    private IMessageContactService iMessageContactService = ServiceFactory.getMessageContactService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        List<MessageContact> messageContacts = iMessageContactService.read();
        request.getSession().setAttribute("messageContacts", messageContacts);
        return PagePathConstantPool.MESSAGE_LIST_VIEW;

    }
}
