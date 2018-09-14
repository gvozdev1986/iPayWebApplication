package by.htp.hvozdzeu.web.command.impl.user;

import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.IUserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class CheckNewAccountCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckNewAccountCommandImpl.class);
    private static final String MESSAGE_CHECK_REGISTRATION = "checkRegistrationMessage";
    private static final String MESSAGE_CHECK_SUCCESS = "success_check";
    private static final String CHECK_CODE = "checkCode";
    private static final String MESSAGE_CHECK_UN_SUCCESS = "success_un_check";
    private static final String MESSAGE_ALREADY_CHECKED = "success_already_check";
    private IUserService iUserService = ServiceFactory.getUserService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        String checkCode = request.getParameter(CHECK_CODE);

        User user = iUserService.findByRegCode(checkCode);
        iUserService.unblockUser(user.getId());

        if (!user.isAvailable()) {
            request.getSession().setAttribute(MESSAGE_CHECK_REGISTRATION, MESSAGE_CHECK_SUCCESS);
            LOGGER.debug("Account has been activated.");
        } else if (user.isAvailable()) {
            request.getSession().setAttribute(MESSAGE_CHECK_REGISTRATION, MESSAGE_ALREADY_CHECKED);
            LOGGER.debug("Account already activated.");
        } else {
            LOGGER.debug("Error activation account.");
            request.getSession().setAttribute(MESSAGE_CHECK_REGISTRATION, MESSAGE_CHECK_UN_SUCCESS);
        }

        return PagePathConstantPool.REDIRECT_CHECK_ACCOUNT_VIEW;
    }

}
