package by.htp.hvozdzeu.web.command.impl.user;

import by.htp.hvozdzeu.service.UserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.REQUEST_PARAM_USER_ID;

public class BlockUserCommandImpl implements BaseCommand {

    private UserService userService = ServiceFactory.getUserService();
    private static final String MESSAGE_EVENT_USER = "messageEventUser";
    private static final String MESSAGE_EVENT_USER_VALUE = "User has been blocked";
    private static final String COUNT_BLOCKED_CLIENTS = "countBlockedClients";

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {
        Long userId = Long.valueOf(request.getParameter(REQUEST_PARAM_USER_ID));
        userService.blockUser(userId);

        Integer countBlockedClients = userService.listBlockedClient().size();

        request.getSession().setAttribute(COUNT_BLOCKED_CLIENTS, countBlockedClients);
        request.getSession().setAttribute(MESSAGE_EVENT_USER, MESSAGE_EVENT_USER_VALUE);
        return PagePathConstantPool.REDIRECT_USER_LIST;
    }

}
