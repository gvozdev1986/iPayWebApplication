package by.htp.hvozdzeu.web.command.impl.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionCreator {

    private String smtpHost;
    private String smtpPost;
    private String userName;
    private String userPassword;
    private Properties sessionProperties;

    public SessionCreator(Properties configProperties) {

        smtpHost = configProperties.getProperty("mail.smtp.host");
        smtpPost = configProperties.getProperty("mail.smtp.port");
        userName = configProperties.getProperty("mail.user.name");
        userPassword = configProperties.getProperty("mail.user.password");

        sessionProperties = new Properties();
        sessionProperties.setProperty("mail.transport.protocol", "smtp");
        sessionProperties.setProperty("mail.host", smtpHost);
        sessionProperties.put("mail.smtp.auth", "true");
        sessionProperties.put("mail.smtp.port", smtpPost);


        sessionProperties.put("mail.smtp.ssl.enable", "true"); ////
        //sessionProperties.put("mail.smtp.starttls.enable", "true"); ////


        sessionProperties.put("mail.smtp.socketFactory.port", smtpPost);
        sessionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sessionProperties.put("mail.smtp.socketFactory.fallback", "false");
        sessionProperties.put("mail.smtp.quitwait", "false");
    }

    public Session createSession() {
        return Session.getInstance(sessionProperties, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }

        });
    }

}
