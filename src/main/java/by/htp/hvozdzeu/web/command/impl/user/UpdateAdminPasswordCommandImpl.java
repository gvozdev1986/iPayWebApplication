package by.htp.hvozdzeu.web.command.impl.user;

import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.IUserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.util.PasswordEncoder;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class UpdateAdminPasswordCommandImpl implements BaseCommand {

    private static final String MESSAGE_ERROR_SAVE_PSWD = "messageErrorSavePassword";
    private static final String MESSAGE_ERROR_SAVE_PSWD_SUCCESS = "Update password was successful!";
    private static final String MESSAGE_ERROR_SAVE_PSWD_ERROR = "Passwords do not match!";
    private IUserService iClientService = ServiceFactory.getUserService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        PasswordEncoder passwordEncoder = new PasswordEncoder();

        Long userId = Long.valueOf(request.getParameter(REQUEST_PARAM_USER_ID));
        String currentPassword = passwordEncoder.getEncodeData(request.getParameter(REQUEST_CURRENT_PARAM_PSWD));
        String newPassword = request.getParameter(REQUEST_NEW_PARAM_PSWD);
        String verifyPassword = request.getParameter(REQUEST_VERIFY_NEW_PSWD);

        User user = iClientService.findById(userId);
        String currentPswd = new String(user.getPassword());

        if (newPassword.equals(verifyPassword) && currentPswd.equals(currentPassword)) {
            iClientService.updatePassword(userId, newPassword);
            User userAfterUpdate = iClientService.findById(userId);
            request.getSession().setAttribute(REQUEST_PARAM_USER, userAfterUpdate);
            request.getSession().setAttribute(MESSAGE_ERROR_SAVE_PSWD, MESSAGE_ERROR_SAVE_PSWD_SUCCESS);
            return PagePathConstantPool.REDIRECT_UPDATE_ADMIN_PSWD;
        } else {
            request.getSession().setAttribute(MESSAGE_ERROR_SAVE_PSWD, MESSAGE_ERROR_SAVE_PSWD_ERROR);
            return PagePathConstantPool.REDIRECT_UPDATE_ADMIN_PSWD;
        }

    }

}
