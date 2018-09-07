package by.htp.hvozdzeu.web.command.impl.registration;

import by.htp.hvozdzeu.dao.util.RebasePassword;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.IUserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class SaveRegistrationCommandImpl implements BaseCommand {

	private IUserService iClientService = ServiceFactory.getUserService();

	private RebasePassword rebasePassword = new RebasePassword();

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {
		User user = new User.Builder()
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

		iClientService.create(user);

		return PagePathConstantPool.REDIRECT_REGISTRATION_FORM;
	}
}
