package by.htp.hvozdzeu.dao.specification;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.PaymentData;

import java.util.List;

/**
 * Interface with additional methods for PaymentData implements
 */
public interface PaymentDataSpecification {

    List<PaymentData> pagination(Integer start, Integer count) throws DAOException;

    List<PaymentData> findByParameter(String param) throws DAOException;

    Long maxIndex() throws DAOException;
}
