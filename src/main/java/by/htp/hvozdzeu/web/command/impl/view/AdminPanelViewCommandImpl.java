package by.htp.hvozdzeu.web.command.impl.view;

import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.MessageContactService;
import by.htp.hvozdzeu.service.UserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

public class AdminPanelViewCommandImpl implements BaseCommand {

	private MessageContactService messageContactService = ServiceFactory.getMessageContactService();
	private CreditCardService creditCardService = ServiceFactory.getCreditCardService();
	private UserService userService = ServiceFactory.getUserService();

	private static final String COUNT_MESSAGES_ATTRIBUTE_NAME = "countUnreadMessage";
	private static final String COUNT_BLOCKED_CREDIT_CARD = "countBlockedCreditCard";
	private static final String COUNT_BLOCKED_CLIENTS = "countBlockedUsers";

	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {

		Integer countUnreadMessage = messageContactService.unreadMessages(false).size();
		Integer countBlockedCreditCard = creditCardService.blockedCreditCard().size();
		Integer countBlockedClients = userService.listBlockedClient().size();

		request.getSession().setAttribute(COUNT_BLOCKED_CLIENTS, countBlockedClients);
		request.getSession().setAttribute(COUNT_BLOCKED_CREDIT_CARD, countBlockedCreditCard);
		request.getSession().setAttribute(COUNT_MESSAGES_ATTRIBUTE_NAME, countUnreadMessage);
		return PagePathConstantPool.LOAD_ADMIN_PANEL;

	}

}
