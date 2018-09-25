package by.htp.hvozdzeu.util;

import by.htp.hvozdzeu.web.command.impl.message.ReplyEmailCommandImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

/**
 * The class for prepare and sending email
 */
public class MailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplyEmailCommandImpl.class);
    private static final String INIT_MAIL_PARAMETER = "mail";

    /**
     * Private constructor
     */
    private MailSender() {
    }

    /**
     * The method for sending email
     * @param request HttpServlet request request
     * @param emailToReply String email to send
     * @param subjectToReply String subject of email
     * @param messageToReply String message of email
     * @param attachmentName String path to file of email
     */
    public static void mailSender(HttpServletRequest request, String emailToReply, String subjectToReply, String messageToReply, String attachmentName) {
        try {
            Properties properties = new Properties();
            ServletContext context = request.getServletContext();
            String filename = context.getInitParameter(INIT_MAIL_PARAMETER);
            properties.load(context.getResourceAsStream(filename));
            MailThread mailThread = new MailThread(
                    emailToReply,
                    subjectToReply,
                    messageToReply,
                    properties,
                    attachmentName
            );
            mailThread.start();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }


}
