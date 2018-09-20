package by.htp.hvozdzeu.service;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.PaymentData;

import java.util.List;

public interface PaymentDataService {

    PaymentData create(PaymentData paymentData) throws DAOException;

    PaymentData update(PaymentData paymentData, Long id) throws DAOException;

    PaymentData findById(Long id) throws DAOException;

    List<PaymentData> read() throws DAOException;

    boolean deleteById(Long id) throws DAOException;

    List<PaymentData> pagination(Integer start, Integer count) throws DAOException;

    List<PaymentData> findByParameter(String param) throws DAOException;

    Long maxIndex() throws DAOException;

}
