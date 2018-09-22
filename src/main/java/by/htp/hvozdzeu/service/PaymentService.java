package by.htp.hvozdzeu.service;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.Payment;
import by.htp.hvozdzeu.model.report.PaymentReport;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {

    Payment create(Payment payment) throws DAOException;

    Payment findById(Long paymentId) throws DAOException;

    List<Payment> getAllPayments() throws DAOException;

    boolean deleteById(Long paymentId) throws DAOException;

    List<PaymentReport> findPaymentByCardAndBetweenDate(Long cardId, LocalDate startDate, LocalDate endDate,
                                                        Integer countRowOnPage, Integer displacement) throws DAOException;

}
