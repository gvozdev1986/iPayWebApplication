package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.specification.CreditCardSpecification;
import by.htp.hvozdzeu.model.CreditCard;

/**
 * Interface for CreditCard entity with extends parameterized
 * interface CRUD DAO as CreditCard and specification for CreditCard.
 */
public interface ICreditCardDAO extends ICrudDAO<CreditCard>, CreditCardSpecification {
}
