package by.htp.hvozdzeu.web.command.impl.user;

import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.IUserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindClientByParametersCommandImpl implements BaseCommand {

	private IUserService iUsertService = ServiceFactory.getUserService();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		String param = request.getParameter("param");
		List<User> creditCards = iUsertService.findByParameter(param);
		request.getSession().setAttribute("users", creditCards);
		return PagePathConstantPool.LIST_CLIENT_VIEW;

	}

}
