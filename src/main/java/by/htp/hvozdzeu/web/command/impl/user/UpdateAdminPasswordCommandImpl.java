package by.htp.hvozdzeu.web.command.impl.user;

import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.UserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class UpdateAdminPasswordCommandImpl implements BaseCommand {

    private static final String MESSAGE_ERROR_SAVE_PSWD = "messageErrorSavePassword";
    private static final String MESSAGE_ERROR_SAVE_PSWD_SUCCESS = "update_password_was_success";
    private static final String MESSAGE_ERROR_SAVE_PSWD_ERROR = "password_do_not_match";
    private UserService userService = ServiceFactory.getUserService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long userId = Long.valueOf(request.getParameter(REQUEST_PARAM_USER_ID));
        String currentPassword = request.getParameter(REQUEST_CURRENT_PARAM_PSWD);
        String newPassword = request.getParameter(REQUEST_NEW_PARAM_PSWD);
        String verifyPassword = request.getParameter(REQUEST_VERIFY_NEW_PSWD);

        User user = userService.findById(userId);
        String currentPswd = new String(user.getPassword());

        if (newPassword.equals(verifyPassword) && currentPswd.equals(currentPassword)) {
            userService.updatePswd(userId, newPassword);
            User userAfterUpdate = userService.findById(userId);
            request.getSession().setAttribute(REQUEST_PARAM_USER, userAfterUpdate);
            request.getSession().setAttribute(MESSAGE_ERROR_SAVE_PSWD, MESSAGE_ERROR_SAVE_PSWD_SUCCESS);
            return PagePathConstantPool.REDIRECT_UPDATE_ADMIN_PSWD;
        } else {
            request.getSession().setAttribute(MESSAGE_ERROR_SAVE_PSWD, MESSAGE_ERROR_SAVE_PSWD_ERROR);
            return PagePathConstantPool.REDIRECT_UPDATE_ADMIN_PSWD;
        }

    }

}
