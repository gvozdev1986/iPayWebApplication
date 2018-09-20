package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.specification.BankAccountSpecification;
import by.htp.hvozdzeu.model.BankAccount;

/**
 * Interface for BankAccount entity with extends parameterized
 * interface CRUD DAO as BankAccount and specification for BankAccount.
 */
public interface BankAccountDAO extends CrudDAO<BankAccount>, BankAccountSpecification {
}
