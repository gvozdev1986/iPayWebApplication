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

public class ViewMessageContact implements Command {

    private static final String VIEW_ADMIN_PANEL = "/WEB-INF/message.jsp";

    private IMessageContactService iMessageContactService = ServiceFactory.getMessageContactService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        MessageContact messageContact = iMessageContactService.findById(id);

        System.out.println(messageContact);

        HttpSession session = request.getSession();
        session.setAttribute("messageClient", messageContact);
        response.sendRedirect(VIEW_ADMIN_PANEL);
        
        return null;

    }

}
