package by.htp.hvozdzeu.web.command.impl.registration;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.util.RebasePassword;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.service.IClientService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import by.htp.hvozdzeu.web.util.RedirectPageUrl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class SaveRegistration implements Command {

	private IClientService iClientService = ServiceFactory.getClientService();

	private RebasePassword rebasePassword = new RebasePassword();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Client client = new Client.Builder()
				.login(request.getParameter("username"))
				.password(rebasePassword.rebasePSWD(request.getParameter("password")))
				.firstName(request.getParameter("first_name"))
				.lastName(request.getParameter("last_name"))
				.patronymic(request.getParameter("patronymic"))
				.dateBirth(Date.valueOf(request.getParameter("date_birth")).toLocalDate())
				.phoneHome(request.getParameter("home_phone"))
				.phoneMobile(request.getParameter("mobile_phone"))
				.address(request.getParameter("address"))
				.email(request.getParameter("email"))
				.build();

		iClientService.create(client);

		return RedirectPageUrl.LOGIN_PAGE_VIEW.getUrl();
	}
}
