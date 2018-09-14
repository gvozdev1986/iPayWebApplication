package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.IUserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.REQUEST_CLIENT_ID;

public class UserDetailViewCommandImpl implements BaseCommand{

	private IUserService iClientService = ServiceFactory.getUserService();
	private static final String CLIENT_DETAIL = "clientDetail";
	
	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {
		Long clientId = Long.valueOf(request.getParameter(REQUEST_CLIENT_ID));
		User user = iClientService.findById(clientId);
		
		request.getSession().setAttribute(CLIENT_DETAIL, user);
		return PagePathConstantPool.CLIENT_DETAIL_VIEW;
	}

}
