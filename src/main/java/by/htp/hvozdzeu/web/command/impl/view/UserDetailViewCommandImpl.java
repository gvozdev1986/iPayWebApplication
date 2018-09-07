package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.IUserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

public class UserDetailViewCommandImpl implements BaseCommand{

	private IUserService iClientService = ServiceFactory.getUserService();
	
	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {
		Long clientId = Long.valueOf(request.getParameter("clientId"));		
		User user = iClientService.findById(clientId);
		
		request.getSession().setAttribute("clientDetail", user);
		return PagePathConstantPool.CLIENT_DETAIL_VIEW;
	}

}
