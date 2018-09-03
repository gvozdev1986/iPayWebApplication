package by.htp.hvozdzeu.web.command.impl.service;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;

public class ReplyEmailCommandImpl implements BaseCommand {

    private static final String FORWARD_ADMIN_PANEL = "/WEB-INF/message.jsp";


    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {
        String emailTo = request.getParameter("toEmail");
        String subject = request.getParameter("subjectEmail");
        String bodyMessage = request.getParameter("bodyEmail");

        //Don't send because this is method has a bug!!!
        EmailSenderCommandImpl emailSenderCommandImpl = new EmailSenderCommandImpl();
        emailSenderCommandImpl.send(emailTo, subject, bodyMessage);

        return PagePathConstantPool.CLIENT_MESSAGES;

    }
}
