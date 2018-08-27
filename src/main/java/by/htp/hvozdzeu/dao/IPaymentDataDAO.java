package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.specification.PaymentDataSpecification;
import by.htp.hvozdzeu.model.PaymentData;

public interface IPaymentDataDAO extends ICrudDAO<PaymentData>, PaymentDataSpecification {
}
