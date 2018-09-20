package by.htp.hvozdzeu.web.command.impl.user;

import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.UserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.PARAMETER;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.REQUEST_USERS;

public class FindClientByParametersCommandImpl implements BaseCommand {

	private UserService userService = ServiceFactory.getUserService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		String param = request.getParameter(PARAMETER);
		List<User> creditCards = userService.findByParameter(param);
		request.getSession().setAttribute(REQUEST_USERS, creditCards);
		return PagePathConstantPool.LIST_CLIENT_VIEW;

	}

}
