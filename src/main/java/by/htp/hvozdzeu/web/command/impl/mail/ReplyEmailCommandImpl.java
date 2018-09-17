package by.htp.hvozdzeu.web.command.impl.mail;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.util.MailHtmlConstructor.mailConstructor;
import static by.htp.hvozdzeu.util.MailSender.mailSender;
import static by.htp.hvozdzeu.web.util.PagePathConstantPool.REDIRECT_MESSAGE_DETAIL;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class ReplyEmailCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplyEmailCommandImpl.class);

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        String messageId = request.getParameter(MESSAGE_ID);
        String emailToReply = request.getParameter(EMAIL_TO_REPLY);
        String subjectToReply = request.getParameter(EMAIL_SUBJECT);
        String message = request.getParameter(EMAIL_MESSAGE);
        String contactToReply = request.getParameter(NAME_CONTACT_TO_REPLY);

        LOGGER.debug("Send email to client.");

        String messageToReply = mailConstructor(contactToReply, " ", " ", message);
        mailSender(request, emailToReply, subjectToReply, messageToReply, null);

        return REDIRECT_MESSAGE_DETAIL + PART_URL_PARAMETER + messageId;

    }
}
