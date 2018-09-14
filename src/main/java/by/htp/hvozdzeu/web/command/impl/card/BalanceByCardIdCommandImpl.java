package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.BankAccount;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.model.report.StatusCardReport;
import by.htp.hvozdzeu.service.IBankAccountService;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class BalanceByCardIdCommandImpl implements BaseCommand {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BalanceByCardIdCommandImpl.class);
	private IBankAccountService iBankAccountService = ServiceFactory.getBankAccountService();
	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	
	@Override
	public String executeCommand(HttpServletRequest request) throws CommandException {
		
		Long cardId = Long.valueOf(request.getParameter(REQUEST_CARD_ID));
		LOGGER.info("GET BALANCE CARD BY ID {}", cardId);
		
		BankAccount bankAccount = iBankAccountService.findByCardId(cardId);
		
		LOGGER.info("BANK ACCOUNT {}", bankAccount);
		
		User user = (User) request.getSession().getAttribute(REQUEST_PARAM_USER);
		Long clientId = user.getId();
		List<StatusCardReport> creditCards = iCreditCardService.findCreditCardByIdClient(clientId);

		request.getSession().setAttribute(REQUEST_BANK_ACCOUNT, bankAccount);
		request.setAttribute(REQUEST_CARDS, creditCards);
		return PagePathConstantPool.CREDIT_CARD_VIEW;
	}

}
