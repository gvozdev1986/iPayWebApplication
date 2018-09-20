package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.specification.PaymentSpecification;
import by.htp.hvozdzeu.model.Payment;

/**
 * Interface for Payment entity with extends parameterized
 * interface CRUD DAO as Payment and specification for Payment.
 */
public interface IPaymentDAO extends ICrudDAO<Payment>, PaymentSpecification {
}
