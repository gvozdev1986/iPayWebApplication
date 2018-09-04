package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.MessageContact;
import by.htp.hvozdzeu.service.IMessageContactService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

public class MessageDetailViewCommandImpl implements BaseCommand {

    private IMessageContactService iMessageContactService = ServiceFactory.getMessageContactService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long messageId = Long.valueOf(request.getParameter("messageId"));

        MessageContact messageContact = iMessageContactService.findById(messageId);

        request.getSession().setAttribute("messageContact", messageContact);
        return PagePathConstantPool.MESSAGE_DETAIL_VIEW;
    }
}
