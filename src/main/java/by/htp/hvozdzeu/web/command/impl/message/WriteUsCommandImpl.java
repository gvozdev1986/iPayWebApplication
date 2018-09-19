package by.htp.hvozdzeu.web.command.impl.message;

import by.htp.hvozdzeu.model.MessageContact;
import by.htp.hvozdzeu.service.IMessageContactService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.exception.ValidateNullRequestParamException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static by.htp.hvozdzeu.web.util.HttpRequestParamValidator.*;

public class WriteUsCommandImpl implements BaseCommand {

    private static final String NAME_CONTACT = "name_contact";
    private static final String EMAIL_CONTACT = "email_contact";
    private static final String PHONE_CONTACT = "phone_contact";
    private static final String MESSAGE_CONTACT = "message_contact";
    private static final String MESSAGE_SUCCESS_SEND = "message_has_been_sending";
    private static final String MESSAGE_ERROR_SEND = "message_has_not_been_sending";
    private IMessageContactService iMessageContactService = ServiceFactory.getMessageContactService();
    private Map<String, String> validateErrorMap = new HashMap<>();
    private Map<String, String> validateReturnData = new HashMap<>();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Integer sizeErrorMap = validate(validateErrorMap, validateReturnData, request);

        if (sizeErrorMap == 0) {
            MessageContact messageContact = new MessageContact.Builder()
                    .nameContact(request.getParameter(NAME_CONTACT))
                    .emailContact(request.getParameter(EMAIL_CONTACT))
                    .phoneContact(request.getParameter(PHONE_CONTACT))
                    .messageFromContact(request.getParameter(MESSAGE_CONTACT))
                    .build();

            iMessageContactService.create(messageContact);
            request.getSession().removeAttribute("validateErrorMap");
            request.getSession().removeAttribute("returnValidateErrorMap");
            request.getSession().setAttribute("messageEvent", MESSAGE_SUCCESS_SEND);
            return PagePathConstantPool.REDIRECT_GUEST_URL;

        } else {

            request.getSession().setAttribute("messageEvent", MESSAGE_ERROR_SEND);
            request.getSession().setAttribute("validateErrorMap", validateErrorMap); //NOSONAR
            request.getSession().setAttribute("returnValidateErrorMap", validateReturnData); //NOSONAR
            return PagePathConstantPool.REDIRECT_GUEST_URL;

        }
    }

    private int validate(Map<String, String> validateErrorMap, Map<String, String> validateReturnData,
                         HttpServletRequest request) throws ValidateNullRequestParamException {

        String nameContact = request.getParameter(NAME_CONTACT);
        String emailContact = request.getParameter(EMAIL_CONTACT);
        String phoneContact = request.getParameter(PHONE_CONTACT);
        String messageContact = request.getParameter(MESSAGE_CONTACT);

        validateReturnData.put("returnNameValidateError", nameContact);

        if (validatePhone(phoneContact)) {
            validateErrorMap.put("phoneValidateError", "Incorrect phone.");
            validateReturnData.put("returnPhoneValidateError", phoneContact);
        } else {
            validateReturnData.put("returnPhoneValidateError", phoneContact);
        }

        if (!validateEmail(emailContact)) {
            validateErrorMap.put("emailValidateError", "Incorrect email.");
            validateReturnData.put("returnEmailValidateError", emailContact);
        } else {
            validateReturnData.put("returnEmailValidateError", emailContact);
        }

        validateReturnData.put("returnMessageContactValidateError", messageContact);

        return validateErrorMap.size();

    }
}
