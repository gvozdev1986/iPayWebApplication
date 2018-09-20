package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.specification.PaymentDataSpecification;
import by.htp.hvozdzeu.model.PaymentData;

/**
 * Interface for PaymentData entity with extends parameterized
 * interface CRUD DAO as PaymentData and specification for PaymentData.
 */
public interface PaymentDataDAO extends CrudDAO<PaymentData>, PaymentDataSpecification {
}
