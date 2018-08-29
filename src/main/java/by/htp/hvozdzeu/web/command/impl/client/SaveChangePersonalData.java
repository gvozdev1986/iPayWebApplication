package by.htp.hvozdzeu.web.command.impl.client;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.service.IClientService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;


import static by.htp.hvozdzeu.web.util.HttpRequestParamValidator.*;
import static by.htp.hvozdzeu.web.util.RedirectPageUrl.*;
import static by.htp.hvozdzeu.web.util.RequestParameters.*;
import static by.htp.hvozdzeu.web.util.RequestAttributeEntity.*;

public class SaveChangePersonalData implements Command {

	private IClientService iClientService = ServiceFactory.getClientService();
	private static final String MSG_SUCCESS = "Update was successfule.";
	private static final String MSG_UNSUCCESS = "ERROR update client personal data";
	private static final String MSG_UPDATE_CLIENT_DATA = "messageUpdateClient"; 

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws DAOException, ServletException, IOException {

		Long clientId = Long.valueOf(request.getParameter(REQUEST_PARAM_CLIENT_ID));
		
		removeAttributeInvalideMessage(request, INVALID_FIRST_NAME_ATTRIBUTE);
		removeAttributeInvalideMessage(request, INVALID_LAST_NAME_ATTRIBUTE);
		removeAttributeInvalideMessage(request, INVALID_PATRONYMIC_ATTRIBUTE);
		removeAttributeInvalideMessage(request, INVALID_DATE_ATTRIBUTE);
		removeAttributeInvalideMessage(request, INVALID_HOME_PHONE_ATTRIBUTE);
		removeAttributeInvalideMessage(request, INVALID_MOBILE_PHONE_ATTRIBUTE);
		removeAttributeInvalideMessage(request, INVALID_EMAIL_ATTRIBUTE);
		removeAttributeInvalideMessage(request, INVALID_ADDRESS_ATTRIBUTE);
		
		String firstName = request.getParameter(REQUEST_PARAM_FIRST_NAME);
		if (!validateOnlyText(firstName)) {
			return sendAtrributeInvalidMessage(request, INVALID_FIRST_NAME_ATTRIBUTE, INVALID_FIRST_NAME_MESSAGE);
		}
		
		String lastName = request.getParameter(REQUEST_PARAM_LAST_NAME);
		if(!validateOnlyText(lastName)) {
			return sendAtrributeInvalidMessage(request, INVALID_LAST_NAME_ATTRIBUTE, INVALID_LAST_NAME_MESSAGE);
		}
		
		String patronymic = request.getParameter(REQUEST_PARAM_PATRONYMIC);	
		if(!validateOnlyText(patronymic)) {		
			return sendAtrributeInvalidMessage(request, INVALID_PATRONYMIC_ATTRIBUTE, INVALID_PATRONYMIC_MESSAGE);
		}
		
		LocalDate dateBirth = Date.valueOf(request.getParameter(REQUEST_PARAM_DATE_BIRTH)).toLocalDate();
		if(!validateDate(String.valueOf(dateBirth))) {		
			return sendAtrributeInvalidMessage(request, INVALID_DATE_ATTRIBUTE, INVALID_DATE_MESSAGE);
		}
		
		String homePhone = request.getParameter(REQUEST_PARAM_HOME_PHONE);
		if(!validatePhoneNumber(homePhone)) {		
			return sendAtrributeInvalidMessage(request, INVALID_HOME_PHONE_ATTRIBUTE, INVALID_HOME_PHONE_MESSAGE);
		}
		
		String mobilePhone = request.getParameter(REQUEST_PARAM_MOBILE_PHONE);
		if(!validatePhoneNumber(mobilePhone)) {		
			return sendAtrributeInvalidMessage(request, INVALID_MOBILE_PHONE_ATTRIBUTE, INVALID_MOBILE_PHONE_MESSAGE);
		}
		
		String email = request.getParameter(REQUEST_PARAM_EMAIL);
		if(!validateEmail(email)) {
			return sendAtrributeInvalidMessage(request, INVALID_EMAIL_ATTRIBUTE, INVALID_EMAIL_MESSAGE);
		}	
		
		String address = request.getParameter(REQUEST_PARAM_ADDRESS);
		if(address == null) {			
			return sendAtrributeInvalidMessage(request, INVALID_ADDRESS_ATTRIBUTE, INVALID_ADDRESS_MESSAGE);
		}

		if (validatePositiveId(String.valueOf(clientId))) {	

			Client client = new Client.Builder()
					.firstName(firstName)
					.lastName(lastName)
					.patronymic(patronymic)
					.dateBirth(dateBirth)
					.phoneHome(homePhone)
					.phoneMobile(mobilePhone)
					.email(email)
					.address(address)
					.build();

			iClientService.update(client, clientId);

			Client clientUpdate = iClientService.findById(clientId);
			
			request.getSession().setAttribute(REQUEST_ATTRIBUTE_CLIENT, clientUpdate);
			request.getSession().setAttribute(MSG_UPDATE_CLIENT_DATA, MSG_SUCCESS);
			return PERSONAL_DATA_VIEW.getUrl();
			
		} else {			
			request.getSession().setAttribute(MSG_UPDATE_CLIENT_DATA, MSG_UNSUCCESS);
			return PERSONAL_DATA_VIEW.getUrl();			
		}
		
	}	

}
