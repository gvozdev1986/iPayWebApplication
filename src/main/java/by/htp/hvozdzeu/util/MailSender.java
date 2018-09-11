package by.htp.hvozdzeu.util;

import by.htp.hvozdzeu.web.command.impl.mail.MailThread;
import by.htp.hvozdzeu.web.command.impl.mail.ReplyEmailCommandImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

public class MailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplyEmailCommandImpl.class);

    private MailSender() {
    }

    public static void mailSender(HttpServletRequest request, String emailToReply, String subjectToReply, String messageToReply){
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
            mailThread.start();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }



}
