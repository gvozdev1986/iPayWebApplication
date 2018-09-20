package by.htp.hvozdzeu.util;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.MailAccount;
import by.htp.hvozdzeu.service.MailAccountService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

class SessionCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionCreator.class);

    private static final String SMTP = "smtp";
    private static final String STATUS_TRUE = "true";
    private static final String STATUS_FALSE = "false";
    private static final String PROPERTY_SMTP_HOST = "mail.smtp.host";
    private static final String PROPERTY_SMTP_PORT = "mail.smtp.port";
    private static final String PROPERTY_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    private static final String PROPERTY_MAIL_HOST = "mail.host";
    private static final String PROPERTY_SMTP_AUTH = "mail.smtp.auth";
    private static final String PROPERTY_SMTP_SSL = "mail.smtp.ssl.enable";
    private static final String PROPERTY_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
    private static final String PROPERTY_SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
    private static final String PROPERTY_SMTP_SOCKET_FACTORY_FALLBACK = "mail.smtp.socketFactory.fallback";
    private static final String PROPERTY_SMTP_QUIT_WAIT = "mail.smtp.quitwait";
    private static final String CLASS_JAVAX_NET_SSL_SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";

    private MailAccountService mailAccountService = ServiceFactory.getMailAccountService();
    private String userName;
    private String userPassword;
    private Properties sessionProperties;

    SessionCreator(Properties configProperties) {
        String smtpHost = configProperties.getProperty(PROPERTY_SMTP_HOST);
        String smtpPost = configProperties.getProperty(PROPERTY_SMTP_PORT);

        try {
            userName = getMailLogin();
            userPassword = getMailPswd();
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
        }


        sessionProperties = new Properties();
        sessionProperties.setProperty(PROPERTY_TRANSPORT_PROTOCOL, SMTP);
        sessionProperties.setProperty(PROPERTY_MAIL_HOST, smtpHost);
        sessionProperties.put(PROPERTY_SMTP_AUTH, STATUS_TRUE);
        sessionProperties.put(PROPERTY_SMTP_PORT, smtpPost);
        sessionProperties.put(PROPERTY_SMTP_SSL, STATUS_TRUE);
        sessionProperties.put(PROPERTY_SMTP_SOCKET_FACTORY_PORT, smtpPost);
        sessionProperties.put(PROPERTY_SMTP_SOCKET_FACTORY_CLASS, CLASS_JAVAX_NET_SSL_SOCKET_FACTORY);
        sessionProperties.put(PROPERTY_SMTP_SOCKET_FACTORY_FALLBACK, STATUS_FALSE);
        sessionProperties.put(PROPERTY_SMTP_QUIT_WAIT, STATUS_FALSE);
    }


    private String getMailLogin() throws DAOException {
        MailAccount mailAccount = mailAccountService.read();
        return mailAccount.getMailLogin();
    }

    private String getMailPswd() throws DAOException {
        MailAccount mailAccount = mailAccountService.read();
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
