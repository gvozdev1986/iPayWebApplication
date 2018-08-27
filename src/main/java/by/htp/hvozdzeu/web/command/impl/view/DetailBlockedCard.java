package by.htp.hvozdzeu.web.command.impl.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.BankAccount;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.IBankAccountService;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import static by.htp.hvozdzeu.web.util.RedirectPageUrl.*;
import static by.htp.hvozdzeu.web.util.RequestAttributeEntity.*;

public class DetailBlockedCard implements Command {

	private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
	private IBankAccountService iBankAccountService = ServiceFactory.getBankAccountService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Long cardId = Long.valueOf(request.getParameter("cardId"));

		CreditCard creditCard = iCreditCardService.findById(cardId);
		BankAccount bankAccount = iBankAccountService.findByCardId(cardId);

		request.getSession().setAttribute(REQUEST_ATTRIBUTE_BANK_ACCOUNT, bankAccount);
		request.getSession().setAttribute(REQUEST_ATTRIBUTE_CREDIT_CARD, creditCard);
		return DETAIL_BLOCKED_CARD.getUrl();
	}

}
