package by.htp.hvozdzeu.web.filter;

import by.htp.hvozdzeu.web.controller.ServletController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServletController.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("Authorization filter has been create");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		String inputCommand = req.getParameter("command");

		if (inputCommand != null && !inputCommand.isEmpty()) {
			chain.doFilter(request, response);
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		LOGGER.debug("Authorization filter has been destroy");
	}

}
