package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.MessageContact;
import by.htp.hvozdzeu.service.MessageContactService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.MESSAGE_ID;

public class MessageDetailViewCommandImpl implements BaseCommand {

    private static final String MESSAGE_CONTACT = "messageContact";
    private MessageContactService messageContactService = ServiceFactory.getMessageContactService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long messageId = Long.valueOf(request.getParameter(MESSAGE_ID));
        MessageContact messageContact = messageContactService.findById(messageId);
        request.getSession().setAttribute(MESSAGE_CONTACT, messageContact);
        return PagePathConstantPool.MESSAGE_DETAIL_VIEW;
    }
}
