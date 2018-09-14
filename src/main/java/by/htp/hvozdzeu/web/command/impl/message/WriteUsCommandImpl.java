package by.htp.hvozdzeu.web.command.impl.message;

import by.htp.hvozdzeu.model.MessageContact;
import by.htp.hvozdzeu.service.IMessageContactService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

public class WriteUsCommandImpl implements BaseCommand {

    private IMessageContactService iMessageContactService = ServiceFactory.getMessageContactService();
    private static final String NAME_CONTACT = "name_contact";
    private static final String EMAIL_CONTACT = "email_contact";
    private static final String PHONE_CONTACT = "phone_contact";
    private static final String MESSAGE_CONTACT = "message_contact";

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {
        String nameContact = request.getParameter(NAME_CONTACT);
        String emailContact = request.getParameter(EMAIL_CONTACT);
        String phoneContact = request.getParameter(PHONE_CONTACT);
        String messageFromContact = request.getParameter(MESSAGE_CONTACT);

        MessageContact messageContact = new MessageContact.Builder()
                .nameContact(nameContact)
                .emailContact(emailContact)
                .phoneContact(phoneContact)
                .messageFromContact(messageFromContact)
                .build();
        
        iMessageContactService.create(messageContact);
        
        return PagePathConstantPool.GREETING_PAGE_VIEW;
    }
}
