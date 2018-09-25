package by.htp.hvozdzeu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * The class for prepare thread for send email
 */
public class MailThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailThread.class);
    private static final String TYPE_CONTEXT = "text/html";

    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private Properties properties;
    private String attachmentName;

    /**
     * Constructor with parameters for prepare email
     * @param sendToEmail String email for recipient
     * @param mailSubject String subject of email
     * @param mailText String message of email
     * @param properties Properties for settings emailSender
     * @param attachmentName String path for file of email (attachment)
     */
    MailThread(String sendToEmail, String mailSubject, String mailText, Properties properties, String attachmentName) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
        this.attachmentName = attachmentName;
    }

    /**
     * The method for initialization email
     */
    private void init() {
        Session mailSession = (new SessionCreator(properties)).createSession();
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        try {
            message.setSubject(mailSubject);
            message.setContent(mailText, TYPE_CONTEXT);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));

            if(attachmentName != null){
                FileDataSource fileDataSource = new FileDataSource(attachmentName);
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.setDataHandler(new DataHandler(fileDataSource));
                attachmentPart.setFileName(fileDataSource.getName());
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(attachmentPart);
                message.setContent(multipart);
            }

        } catch (AddressException e) {
            LOGGER.error("Invalid address: {} ", e.getMessage());
        } catch (MessagingException e) {
            LOGGER.error("Error forming message. {}", e.getMessage());
        }
    }

    /**
     * Override method for start thread and transport email
     */
    @Override
    public void run() {
        init();
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.error("Error to send email. {}", e.getMessage());
        }
    }

}
