package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.IMessageContactService;
import by.htp.hvozdzeu.service.IUserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

public class AdminPanelViewCommandImpl implements BaseCommand {

	private IMessageContactService iMessageContactService = ServiceFactory.getMessageContactService();
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	private IUserService iClientService = ServiceFactory.getUserService();

	private static final String COUNT_MESSAGES_ATTRIBUTE_NAME = "countUnreadMessage";
	private static final String COUNT_BLOCKED_CREDIT_CARD = "countBlockedCreditCard";
	private static final String COUNT_BLOCKED_CLIENTS = "countBlockedClients";

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		Integer countUnreadMessage = iMessageContactService.unreadMessages(false).size();
		Integer countBlockedCreditCard = iCreditCardService.blockedCreditCard().size();
		Integer countBlockedClients = iClientService.listBlockedClient().size();

		request.getSession().setAttribute(COUNT_BLOCKED_CLIENTS, countBlockedClients);
		request.getSession().setAttribute(COUNT_BLOCKED_CREDIT_CARD, countBlockedCreditCard);
		request.getSession().setAttribute(COUNT_MESSAGES_ATTRIBUTE_NAME, countUnreadMessage);
		return PagePathConstantPool.LOAD_ADMIN_PANEL;

	}

}
