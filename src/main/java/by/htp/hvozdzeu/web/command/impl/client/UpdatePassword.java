package by.htp.hvozdzeu.web.command.impl.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.service.IClientService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.util.PasswordEncoder;
import by.htp.hvozdzeu.web.command.Command;
import by.htp.hvozdzeu.web.util.RedirectPageUrl;

public class UpdatePassword implements Command {

	private IClientService iClientService = ServiceFactory.getClientService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {		

		PasswordEncoder passwordEncoder = new PasswordEncoder();

		Long clientId = Long.valueOf(request.getParameter("clientId"));
		String currentPassword = passwordEncoder.getEncodeData(request.getParameter("current_password"));
		String newPassword = request.getParameter("new_password");
		String veriryPassword = request.getParameter("veriry_new_password");

		Client client = iClientService.findById(clientId);
		String currentPswd = new String(client.getPassword());
		
		if (client != null) {
			if (newPassword.equals(veriryPassword) && currentPswd.equals(currentPassword)) {				
				iClientService.updatePassword(clientId, newPassword);	
				Client clientAfterUpdate = iClientService.findById(clientId);				
				request.getSession().setAttribute("client", clientAfterUpdate);
			} else {
				request.getSession().setAttribute("messageErrorSavePassword", "Passwords do not match!");
				return RedirectPageUrl.UPDATE_CLIENT_PASSWORD_VIEW.getUrl();
			}
		}
		
		request.getSession().invalidate();
		return RedirectPageUrl.INDEX_PAGE_LOAD.getUrl();
	}

}
