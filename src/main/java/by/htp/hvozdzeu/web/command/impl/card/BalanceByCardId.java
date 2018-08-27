package by.htp.hvozdzeu.web.command.impl.card;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.BankAccount;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.service.IBankAccountService;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import by.htp.hvozdzeu.web.command.CommandDirector;
import by.htp.hvozdzeu.web.util.RedirectPageUrl;

public class BalanceByCardId implements Command {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandDirector.class);
	private IBankAccountService iBankAccountService = ServiceFactory.getBankAccountService();
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {
		
		Long cardId = Long.valueOf(request.getParameter("cardId"));
		LOGGER.info("GET BALANCE CARD BY ID {}", cardId);
		
		BankAccount bankAccount = iBankAccountService.findByCardId(cardId);
		
		LOGGER.info("BANK ACCOUNT {}", bankAccount);
		
		Client client = (Client) request.getSession().getAttribute("client");
		Long clientId = client.getId();
		List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);

		request.getSession().setAttribute("bankAccount", bankAccount);
		request.setAttribute("cards", creditCards);
		return RedirectPageUrl.CREDIT_CARD_VIEW.getUrl();
	}

}
