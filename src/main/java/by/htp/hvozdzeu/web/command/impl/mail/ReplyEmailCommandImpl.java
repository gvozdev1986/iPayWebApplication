package by.htp.hvozdzeu.web.command.impl.mail;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

import static by.htp.hvozdzeu.web.util.PagePathConstantPool.REDIRECT_MESSAGE_DETAIL;

public class ReplyEmailCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplyEmailCommandImpl.class);

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        String messageId = request.getParameter("messageId");
        String emailToReply = request.getParameter("emailToReply");
        String subjectToReply = request.getParameter("subjectToReply");
        String messageToReply = request.getParameter("messageToReply");

        try {
            Properties properties = new Properties();
            ServletContext context = request.getServletContext();
            String filename = context.getInitParameter("mail");
            properties.load(context.getResourceAsStream(filename));
            MailThread mailThread = new MailThread(
                    emailToReply,
                    subjectToReply,
                    messageToReply,
                    properties
            );

            LOGGER.debug(properties.toString());

            mailThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return REDIRECT_MESSAGE_DETAIL + "&messageId=" + messageId;

    }
}
