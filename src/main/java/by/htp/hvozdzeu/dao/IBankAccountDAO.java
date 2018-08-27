package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.specification.BankAccountSpecification;
import by.htp.hvozdzeu.model.BankAccount;

public interface IBankAccountDAO extends ICrudDAO<BankAccount>, BankAccountSpecification {
}
