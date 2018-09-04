package by.htp.hvozdzeu.web.command.impl.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailThread.class);

    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    public MailThread(String sendToEmail, String mailSubject, String mailText, Properties properties){
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

    private void init(){
        Session mailSession = (new SessionCreator(properties)).createSession();
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        try{
            message.setSubject(mailSubject);
            message.setContent(mailText, "text/html");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
        } catch (AddressException e) {
            LOGGER.error("Invalid address: {} ", e.getMessage());
        } catch (MessagingException e){
            LOGGER.error("Error forming message. {}", e.getMessage());
        }
    }

    @Override
    public void run(){
        init();
        try{
            Transport.send(message);
        } catch (MessagingException e){
            LOGGER.error("Error to send email. {}", e.getMessage());
        }
    }

}
