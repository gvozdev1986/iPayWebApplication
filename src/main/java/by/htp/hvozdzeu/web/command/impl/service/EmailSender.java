package by.htp.hvozdzeu.web.command.impl.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    private static final String USER = "aliaksandr.hvozdzeu@gmail.com";
    private static final String PSW = "gvozdvtaranov250486";

    public void send(String toEmail, String subjectEmail, String messageEmail){

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); //465

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER, PSW);
                    }

                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subjectEmail);
            message.setText(messageEmail);
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }



    }

}
