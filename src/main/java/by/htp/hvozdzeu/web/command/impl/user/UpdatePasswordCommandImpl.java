package by.htp.hvozdzeu.web.command.impl.user;

import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.IUserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.util.PasswordEncoder;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

public class UpdatePasswordCommandImpl implements BaseCommand {

	private IUserService iClientService = ServiceFactory.getUserService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {	

		PasswordEncoder passwordEncoder = new PasswordEncoder();

		Long userId = Long.valueOf(request.getParameter("userId"));
		String currentPassword = passwordEncoder.getEncodeData(request.getParameter("current_password"));
		String newPassword = request.getParameter("new_password");
		String verifyPassword = request.getParameter("verify_new_password");

		User user = iClientService.findById(userId);
		String currentPswd = new String(user.getPassword());

        if (newPassword.equals(verifyPassword) && currentPswd.equals(currentPassword)) {
            iClientService.updatePassword(userId, newPassword);
            User userAfterUpdate = iClientService.findById(userId);
            request.getSession().setAttribute("user", userAfterUpdate);
            request.getSession().setAttribute("messageErrorSavePassword", "Update password wac successful!");
            return PagePathConstantPool.REDIRECT_UPDATE_CLIENT_PSWD;
        } else {
            request.getSession().setAttribute("messageErrorSavePassword", "Passwords do not match!");
            return PagePathConstantPool.REDIRECT_UPDATE_CLIENT_PSWD;
        }


	}

}
