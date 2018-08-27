package by.htp.hvozdzeu.web.command.impl.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.service.IClientService;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.IMessageContactService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;

import static by.htp.hvozdzeu.web.util.RedirectPageUrl.*;

public class AdminPanelView implements Command {

	private IMessageContactService iMessageContactService = ServiceFactory.getMessageContactService();
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	private IClientService iClientService = ServiceFactory.getClientService();

	private static final String COUNT_MESSAGES_ATTRIBUTE_NAME = "countUnreadMessage";
	private static final String COUNT_BLOCKED_CREDIT_CARD = "countBlockedCreditCard";
	private static final String COUNT_BLOCKED_CLIENTS = "countBlockedClients";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Integer countUnreadMessage = iMessageContactService.unreadmessages(false).size();
		Integer countBlockedCreditCard = iCreditCardService.blockedCreditCard().size();
		Integer countBlockedClients = iClientService.blockedClient().size();

		request.getSession().setAttribute(COUNT_BLOCKED_CLIENTS, countBlockedClients);
		request.getSession().setAttribute(COUNT_BLOCKED_CREDIT_CARD, countBlockedCreditCard);
		request.getSession().setAttribute(COUNT_MESSAGES_ATTRIBUTE_NAME, countUnreadMessage);
		return ADMIN_PANEL_VIEW.getUrl();
	}

}
