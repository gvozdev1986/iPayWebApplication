package by.htp.hvozdzeu.service;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.Payment;
import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.model.report.SumPaymentReportChartPie;

import java.time.LocalDate;
import java.util.List;

public interface IPaymentService {

    Payment create(Payment payment) throws DAOException;
    Payment update(Payment payment, Long id) throws DAOException;
    Payment findById(Long id) throws DAOException;
    List<Payment> read() throws DAOException;
    boolean deleteById(Long id) throws DAOException;
    List<PaymentReport> findPaynemtByCardAndBetweenDate(Long cardId, LocalDate startDate, LocalDate endDate) throws DAOException;
    List<SumPaymentReportChartPie> findPaynemtByCardAndBetweenDateChartPie(Long cardId, LocalDate startDate, LocalDate endDate) throws DAOException;
    
}
