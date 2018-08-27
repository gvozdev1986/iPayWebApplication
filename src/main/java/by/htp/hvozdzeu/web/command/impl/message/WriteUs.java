package by.htp.hvozdzeu.web.command.impl.message;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.MessageContact;
import by.htp.hvozdzeu.service.IMessageContactService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import by.htp.hvozdzeu.web.util.RedirectPageUrl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WriteUs implements Command {

    private IMessageContactService iMessageContactService = ServiceFactory.getMessageContactService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {

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
        
        return RedirectPageUrl.GREETING_PAGE_VIEW.getUrl();
    }
}
