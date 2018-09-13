package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.service.ICreditCardService;
import by.htp.hvozdzeu.service.IPaymentService;
import by.htp.hvozdzeu.service.IUserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.util.DeleteReportFileAfterSending;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

import static by.htp.hvozdzeu.util.DeleteReportFileAfterSending.deleteFileAfterSending;
import static by.htp.hvozdzeu.util.GenerateReportPaymentExcel.generateExcelReport;
import static by.htp.hvozdzeu.util.MailSender.mailSender;

public class ReportPaymentHistorySendEmailCommandImpl implements BaseCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportPaymentHistorySendEmailCommandImpl.class);
    private IUserService iUserService = ServiceFactory.getUserService();
    private ICreditCardService iCreditCardService = ServiceFactory.getCreditCardService();
    private IPaymentService iPaymentService = ServiceFactory.getPaymentService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long cardId = Long.valueOf(request.getParameter("cardId"));
        LocalDate dateStart = LocalDate.parse(request.getParameter("dateStart"));
        LocalDate dateEnd = LocalDate.parse(request.getParameter("dateEnd"));

        CreditCard creditCard = iCreditCardService.findById(cardId);
        User user = iUserService.findById(creditCard.getClient());

        List<PaymentReport> paymentReports = iPaymentService.findPaymentByCardAndBetweenDate(cardId, dateStart, dateEnd, 100000000, 0);

        generateExcelReport(paymentReports, cardId, dateStart, dateEnd);


        String emailToReply = user.getEmail();
        String subjectToReply = "Payment history report from " + dateStart + " to " + dateEnd;
        String messageToReply = "";
        String attachmentName = "E:/PaymentHistory_" + cardId + ".xls";

        LOGGER.debug("Send email with attachment report");

        mailSender(request, emailToReply, subjectToReply, messageToReply, attachmentName);

        LOGGER.debug("Delete file report after sending");

        //deleteFileAfterSending(attachmentName);

        request.getSession().setAttribute("messageReport", "Report has been sent to email.");
        return PagePathConstantPool.PAYMENT_HISTORY_PAGINATION;

    }

}
