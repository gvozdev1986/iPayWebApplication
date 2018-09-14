package by.htp.hvozdzeu.dao.specification;

import java.time.LocalDate;
import java.util.List;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.model.report.SumPaymentReportChartPie;

public interface PaymentSpecification {

    List<PaymentReport> findPaymentByCardAndBetweenDate(Long cardId, LocalDate startDate, LocalDate endDate,
                                                        Integer countRowOnPage, Integer displacement) throws DAOException;

    List<SumPaymentReportChartPie> findPaymentByCardAndBetweenDateChartPie(Long cardId, LocalDate startDate,
                                                                           LocalDate endDate) throws DAOException;

}
