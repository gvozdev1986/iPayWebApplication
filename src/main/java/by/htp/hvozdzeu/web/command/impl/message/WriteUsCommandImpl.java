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

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {
        String nameContact = request.getParameter("name_contact");
        String emailContact = request.getParameter("email_contact");
        String phoneContact = request.getParameter("phone_contact");
        String messageFromContact = request.getParameter("message_contact");

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
