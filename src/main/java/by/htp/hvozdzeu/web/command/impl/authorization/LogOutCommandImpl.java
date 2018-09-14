package by.htp.hvozdzeu.web.command.impl.authorization;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommandImpl implements BaseCommand {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogOutCommandImpl.class);

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		LOGGER.info("LOGOUT. DELETE CURRENT SESSION");

		request.getSession().invalidate();
		return PagePathConstantPool.INDEX_PAGE_LOAD;

	}

}
