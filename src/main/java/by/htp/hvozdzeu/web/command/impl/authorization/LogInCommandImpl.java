package by.htp.hvozdzeu.web.command.impl.authorization;

import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.resources.Resource;
import by.htp.hvozdzeu.service.IUserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.UserTypeEnum;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.util.HttpRequestParamValidator.validateParamNotNull;
import static by.htp.hvozdzeu.web.util.PagePathConstantPool.*;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class LogInCommandImpl implements BaseCommand {

    private static final String MESSAGE_VALUE = "invalid_login_or_password";
    private IUserService userService = ServiceFactory.getUserService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(REQUEST_PARAM_LOGIN);
        String pass = request.getParameter(REQUEST_PARAM_PASS);
        validateParamNotNull(login);
        validateParamNotNull(pass);
        User user = userService.checkUser(login, pass);
        return checkReceivedUser(user, request);
    }

    private String checkReceivedUser(User user, HttpServletRequest request) {
        if (user != null) {
            request.getSession().setAttribute(REQUEST_PARAM_USER, user);
            return identifyUserType(user, request);
        } else {
            request.setAttribute(REQUEST_PARAM_INFO_MESSAGE, MESSAGE_VALUE);
            return LOGIN_PAGE_VIEW;
        }
    }

    private String identifyUserType(User user, HttpServletRequest request) {
        if (user.isAdmin()) {
            request.getSession().setAttribute(REQUEST_PARAM_USER_TYPE, UserTypeEnum.ADMIN); //user
            request.getSession().setAttribute(SESSION_ATR_SESSION_PAGE_TYPE, PAGE_TYPE_ADMIN_PROFILE);
            return REDIRECT_ADMIN_URL;
        } else {
            request.getSession().setAttribute(REQUEST_PARAM_USER_TYPE, UserTypeEnum.USER);
            request.getSession().setAttribute(SESSION_ATR_SESSION_PAGE_TYPE, PAGE_TYPE_USER_PROFILE);
            return REDIRECT_USER_URL;
        }
    }

}
