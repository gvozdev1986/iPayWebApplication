package by.htp.hvozdzeu.web.util;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.PaymentData;
import by.htp.hvozdzeu.service.PaymentDataService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static by.htp.hvozdzeu.util.HideSymbolsCreditCard.hideSymbolsCreditCard;

public class BillPaymentBuilder {

    private static PaymentDataService paymentDataService = ServiceFactory.getPaymentDataService();

    private BillPaymentBuilder() {
    }

    public static void billBuilder(HttpServletRequest request,
                                   CreditCard creditCard,
                                   Long serviceId,
                                   String orderNo,
                                   BigDecimal amount,
                                   String description) throws DAOException {

        PaymentData paymentData = paymentDataService.findById(serviceId);

        request.getSession().setAttribute("billPrintButtonVisible", true);
        request.getSession().setAttribute("billDate", LocalDate.now());
        request.getSession().setAttribute("billTime", LocalTime.now());
        request.getSession().setAttribute("billCreditCard", hideSymbolsCreditCard(creditCard.getCardNumber()));
        request.getSession().setAttribute("billService", paymentData.getPaymentDataName());
        request.getSession().setAttribute("billOrder", orderNo);
        request.getSession().setAttribute("billAmount", amount);
        request.getSession().setAttribute("billDescription", description);
    }

}
