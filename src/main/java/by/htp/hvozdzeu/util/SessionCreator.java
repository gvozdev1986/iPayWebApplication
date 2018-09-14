package by.htp.hvozdzeu.util;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.MailAccount;
import by.htp.hvozdzeu.service.IMailAccountService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionCreator.class);
    private IMailAccountService iMailAccountService = ServiceFactory.getiMailAccountService();
    private String userName;
    private String userPassword;
    private Properties sessionProperties;

    public SessionCreator(Properties configProperties) {
        String smtpHost = configProperties.getProperty("mail.smtp.host");
        String smtpPost = configProperties.getProperty("mail.smtp.port");

        try {
            userName = getMailLogin();
            userPassword = getMailPswd();
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }


        sessionProperties = new Properties();
        sessionProperties.setProperty("mail.transport.protocol", "smtp");
        sessionProperties.setProperty("mail.host", smtpHost);
        sessionProperties.put("mail.smtp.auth", "true");
        sessionProperties.put("mail.smtp.port", smtpPost);
        sessionProperties.put("mail.smtp.ssl.enable", "true");
        sessionProperties.put("mail.smtp.socketFactory.port", smtpPost);
        sessionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sessionProperties.put("mail.smtp.socketFactory.fallback", "false");
        sessionProperties.put("mail.smtp.quitwait", "false");
    }


    private String getMailLogin() throws DAOException {
        MailAccount mailAccount = iMailAccountService.read();
        return mailAccount.getMailLogin();
    }

    private String getMailPswd() throws DAOException {
        MailAccount mailAccount = iMailAccountService.read();
        return mailAccount.getMailPswd();
    }


    Session createSession() {
        return Session.getInstance(sessionProperties, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }

        });
    }

}
