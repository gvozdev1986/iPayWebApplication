package by.htp.hvozdzeu.web.command.impl.authorization;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.web.command.Command;
import by.htp.hvozdzeu.web.util.RedirectPageUrl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LogOut implements Command {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogOut.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		LOGGER.info("Redirect to index page.");
		
		request.getSession().invalidate();
		return RedirectPageUrl.INDEX_PAGE_LOAD.getUrl();

	}
}
