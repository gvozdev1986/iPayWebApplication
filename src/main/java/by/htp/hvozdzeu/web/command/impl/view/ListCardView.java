package by.htp.hvozdzeu.web.command.impl.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;

import static by.htp.hvozdzeu.web.util.RedirectPageUrl.*;
import static by.htp.hvozdzeu.web.util.RequestAttributeEntity.*;

public class ListCardView implements Command {
	
    private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {
		
		List<CreditCard> creditCards = iCreditCardService.read();
		
		Integer count = creditCards.size();
		Integer pageRowSize = 1;	
		Integer page = 1; //Integer.valueOf(request.getParameter("page"));			
		Integer pages = count / pageRowSize;
																	//LIMIT 10 OFFSET 0
		List<CreditCard> pagination = iCreditCardService.pagination(pageRowSize, count);
		
		System.out.println(pagination);
				
		request.getSession().setAttribute(REQUEST_ATTRIBUTE_CREDIT_CARD, pagination);
		return LIST_CARD_VIEW.getUrl();
	}

}
