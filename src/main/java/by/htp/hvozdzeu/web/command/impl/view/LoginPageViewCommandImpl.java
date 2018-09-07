package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

public class LoginPageViewCommandImpl implements BaseCommand {

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {
		return PagePathConstantPool.LOGIN_PAGE_VIEW;

	}

}
