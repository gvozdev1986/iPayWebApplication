package by.htp.hvozdzeu.web.command.impl.message;

import by.htp.hvozdzeu.service.MessageContactService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.counting.CountUnreadMessage.countUnreadMessage;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.MESSAGE_ID;

public class CheckReadMessageCommandImpl implements BaseCommand {

    private MessageContactService messageContactService = ServiceFactory.getMessageContactService();
    private static final String COUNT_MESSAGES_ATTRIBUTE_NAME = "countUnreadMessage";

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long messageId = Long.valueOf(request.getParameter(MESSAGE_ID));
        messageContactService.checkMessageAsRead(messageId);

        request.getSession().setAttribute(COUNT_MESSAGES_ATTRIBUTE_NAME, countUnreadMessage());
        return PagePathConstantPool.REDIRECT_LIST_MESSAGE;
    }
}
