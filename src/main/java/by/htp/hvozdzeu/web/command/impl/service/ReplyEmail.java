package by.htp.hvozdzeu.web.command.impl.service;

import by.htp.hvozdzeu.web.command.Command;
import by.htp.hvozdzeu.web.util.RedirectPageUrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReplyEmail implements Command {

    private static final String FORWARD_ADMIN_PANEL = "/WEB-INF/message.jsp";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String emailTo = request.getParameter("toEmail");
        String subject = request.getParameter("subjectEmail");
        String bodyMessage = request.getParameter("bodyEmail");

        //Don't send because this is method has a bug!!!
        EmailSender emailSender = new EmailSender();
        emailSender.send(emailTo, subject, bodyMessage);

        response.sendRedirect(FORWARD_ADMIN_PANEL);
        
        return RedirectPageUrl.CLIENT_MESSAGES.getUrl();

    }
}
