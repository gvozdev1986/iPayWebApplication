package by.htp.hvozdzeu.web.filter;

import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.web.util.CommandEnum;
import by.htp.hvozdzeu.web.util.UserTypeEnum;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;
import static by.htp.hvozdzeu.web.util.PagePathConstantPool.PAGE_ERROR;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class AuthorizationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpSession session = req.getSession(true);

		User currentUser = (User) session.getAttribute(REQUEST_PARAM_USER);
		String inputCommand = request.getParameter(REQUEST_PARAM_COMMAND);
		CommandEnum command;
		if (inputCommand != null && !inputCommand.isEmpty()) {
			command = CommandEnum.valueOfOrDefault(inputCommand.toUpperCase());

			if (currentUser != null) {
				if (currentUser.isAdmin() && command.getUserType() == UserTypeEnum.ADMIN) {
					chain.doFilter(request, response);
				} else if (!currentUser.isAdmin() && command.getUserType() == UserTypeEnum.USER) {
					chain.doFilter(request, response);
				} else if (command.getUserType() == UserTypeEnum.ALL) {
					chain.doFilter(request, response);
				} else {
					request.getRequestDispatcher(PAGE_ERROR).forward(request, response);
				}
			} else {
				if (command.getUserType() == UserTypeEnum.ALL) {
					chain.doFilter(request, response);
				} else {
					request.getRequestDispatcher(PAGE_ERROR).forward(request, response);
				}
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}
}
