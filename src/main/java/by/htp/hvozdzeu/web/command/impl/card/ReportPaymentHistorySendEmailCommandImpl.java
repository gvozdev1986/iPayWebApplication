package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.PaymentService;
import by.htp.hvozdzeu.service.UserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

import static by.htp.hvozdzeu.util.GenerateReportPaymentExcel.generateExcelReport;
import static by.htp.hvozdzeu.util.HideSymbolsCreditCard.hideSymbolsCreditCard;
import static by.htp.hvozdzeu.util.MailSender.mailSender;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

/**
 * The class for generate report to Excel and sending him on email by client
 */
public class ReportPaymentHistorySendEmailCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportPaymentHistorySendEmailCommandImpl.class);

    /**
     * Max rows in report
     */
    private static final Integer MAX_COUNT_ROW_REPORT = 1000000000;

    /**
     * Min rows in report
     */
    private static final Integer MIN_COUNT_ROW_REPORT = 0;

    private static final String MESSAGE_ATTR_NAME = "messageReport";
    private static final String MESSAGE_VALUE = "successful_send_report_email";

    private UserService userService = ServiceFactory.getUserService();
    private CreditCardService creditCardService = ServiceFactory.getCreditCardService();
    private PaymentService paymentService = ServiceFactory.getPaymentService();

    /**
     * The method (command) for preparing report
     * @param request HttpServletRequest getting parameters
     * @return String url for redirect
     * @throws CommandException exception
     */
    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long cardId = Long.valueOf(request.getParameter(REQUEST_CARD_ID));
        LocalDate dateStart = LocalDate.parse(request.getParameter(DATE_START));
        LocalDate dateEnd = LocalDate.parse(request.getParameter(DATE_END));

        CreditCard creditCard = creditCardService.findById(cardId);
        User user = userService.findById(creditCard.getClient());

        List<PaymentReport> paymentReports = paymentService.findPaymentByCardAndBetweenDate(cardId, dateStart,
                dateEnd, MAX_COUNT_ROW_REPORT, MIN_COUNT_ROW_REPORT);

        generateExcelReport(paymentReports, cardId, dateStart, dateEnd);

        String emailToReply = user.getEmail();
        String subjectToReply = generateSubjectEmail(dateStart, dateEnd, creditCard.getCardNumber());
        String messageToReply = "";
        String attachmentName = generateAttachmentName(cardId);

        LOGGER.debug("Send email with attachment report");
        mailSender(request, emailToReply, subjectToReply, messageToReply, attachmentName);

        request.getSession().setAttribute(MESSAGE_ATTR_NAME, MESSAGE_VALUE);
        return PagePathConstantPool.PAYMENT_HISTORY_PAGINATION;

    }

    /**
     * The method for generate subject email
     * @param dateStart LocalDate date report from
     * @param dateEnd LocalDate date report to
     * @param creditCardNumber String number credit card
     * @return String constructed subject
     */
    private String generateSubjectEmail(LocalDate dateStart, LocalDate dateEnd, String creditCardNumber) {
        return "Payment history report from " + dateStart + " to " + dateEnd
                + ". Card number # " + hideSymbolsCreditCard(creditCardNumber);
    }

    /**
     * The method for name report file and getting path to file
     * for sending by email
     * @param cardId Long Card number
     * @return String path to file
     */
    private String generateAttachmentName(Long cardId) {
        return PATH_TO_SAVE_REPORT + cardId + FORMAT_FILE_EXCEL;
    }

}
