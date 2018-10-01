package by.htp.hvozdzeu.web.command.impl.message;

import by.htp.hvozdzeu.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.htp.hvozdzeu.util.HideSymbolsCreditCard.hideSymbolsCreditCard;
import static by.htp.hvozdzeu.util.MailHtmlConstructor.mailConstructor;
import static by.htp.hvozdzeu.util.MailSender.mailSender;

/**
 * The class for send message about create new credit card
 */
public class MessageCreateCreditCard {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageCreateCreditCard.class);

    /**
     * Private constructor
     */
    private MessageCreateCreditCard() {
    }

    /**
     * The method for sending message about create new credit card in system
     *
     * @param request          HttpServletRequest
     * @param user             User entity (active user)
     * @param creditCardNumber String credit card number for adding to message
     */
    public static void sendNotificationCreateCreditCard(HttpServletRequest request, User user, String creditCardNumber) {
        String subjectToReply = "Information about insert new credit card.";
        String message = "Hello. " +
                "Your card # " + hideSymbolsCreditCard(creditCardNumber) + " has been inserted. " +
                "For additional information, please return to administrator. " +
                "From your bank account will be write-off 1.00 point then " +
                "return to credit card for verification credit card in bank system.";
        String messageToReply = null;
        try {
            messageToReply = mailConstructor(request, user.getLastName(), user.getFirstName(), user.getPatronymic(), message);
        } catch (IOException e) {
            LOGGER.error("Error send email");
        }
        mailSender(request, user.getEmail(), subjectToReply, messageToReply, null);
    }

}
