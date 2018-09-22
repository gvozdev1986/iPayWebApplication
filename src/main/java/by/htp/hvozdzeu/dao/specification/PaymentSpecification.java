package by.htp.hvozdzeu.dao.specification;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.report.PaymentReport;

import java.time.LocalDate;
import java.util.List;

public interface PaymentSpecification {

    List<PaymentReport> findPaymentByCardAndBetweenDate(Long cardId, LocalDate startDate, LocalDate endDate,
                                                        Integer countRowOnPage, Integer displacement) throws DAOException;

}
