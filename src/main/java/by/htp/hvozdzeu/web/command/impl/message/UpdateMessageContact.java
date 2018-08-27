package by.htp.hvozdzeu.web.command.impl.message;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.MessageContact;
import by.htp.hvozdzeu.service.IMessageContactService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.Command;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateMessageContact implements Command {

    private static final String FORWARD_ADMIN_PANEL = "/WEB-INF/panel.jsp";

    private IMessageContactService iMessageContactService = ServiceFactory.getMessageContactService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {

        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");
        boolean isRead = Boolean.parseBoolean(request.getParameter("isRead"));

        MessageContact messageContact = new MessageContact.Builder()
                .id(id)
                .nameContact(name)
                .emailContact(email)
                .phoneContact(phone)
                .messageFromContact(message)
                .isRead(isRead)
                .build();

        iMessageContactService.update(messageContact, id);

        HttpSession session = request.getSession();
        session.setAttribute("countMessage", iMessageContactService.read().size());
        response.sendRedirect(FORWARD_ADMIN_PANEL);

        return null;
        
    }
}
