package by.htp.hvozdzeu.web.command.impl.card.helper;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static by.htp.hvozdzeu.util.HideSymbolsCreditCard.hideSymbolsCreditCard;
import static by.htp.hvozdzeu.util.MailHtmlConstructor.mailConstructor;
import static by.htp.hvozdzeu.util.MailSender.mailSender;

/**
 * Class for information client about write-off money from credit card *
 */
public class TransferHelper {

    private TransferHelper() {
    }

    /**
     * Method for send email with information about write-off money
     * @param request       HTTPRequest - Http request
     * @param user          User - Entity User
     * @param sum           BigDecimal - amount many wrote-off from credit card
     * @param cardFrom      CreditCard - Entity credit card
     * @param description   Description - Brief about write-off
     */
    public static void sendEmailAboutTransfer(HttpServletRequest request, User user, BigDecimal sum, CreditCard cardFrom, String description) {
        String emailToReply = user.getEmail();
        String subjectToReply = "Information about the write-off of funds on your credit card.";
        String message = "Hello. "
                + "From your card # "
                + hideSymbolsCreditCard(cardFrom.getCardNumber())
                + " has been wrote "
                + sum
                + " for "
                + description;
        String messageToReply = mailConstructor(user.getLastName(), user.getFirstName(), user.getPatronymic(), message);
        mailSender(request, emailToReply, subjectToReply, messageToReply, null);
    }

}
