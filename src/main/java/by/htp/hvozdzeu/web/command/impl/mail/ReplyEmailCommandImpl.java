package by.htp.hvozdzeu.web.command.impl.mail;

import by.htp.hvozdzeu.util.MailThread;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

import static by.htp.hvozdzeu.web.util.PagePathConstantPool.REDIRECT_MESSAGE_DETAIL;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class ReplyEmailCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplyEmailCommandImpl.class);

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        String messageId = request.getParameter(MESSAGE_ID);
        String emailToReply = request.getParameter(EMAIL_TO_REPLY);
        String subjectToReply = request.getParameter(EMAIL_SUBJECT);
        String messageToReply = request.getParameter(EMAIL_MESSAGE);

        try {
            Properties properties = new Properties();
            ServletContext context = request.getServletContext();
            String filename = context.getInitParameter(MAIL);
            properties.load(context.getResourceAsStream(filename));
            MailThread mailThread = new MailThread(
                    emailToReply,
                    subjectToReply,
                    messageToReply,
                    properties,
                    null
            );
            mailThread.start();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        return REDIRECT_MESSAGE_DETAIL + PART_URL_PARAMETER + messageId;

    }
}
