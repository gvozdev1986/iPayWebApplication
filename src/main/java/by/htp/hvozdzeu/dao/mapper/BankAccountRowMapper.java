package by.htp.hvozdzeu.dao.mapper;

import by.htp.hvozdzeu.model.BankAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountRowMapper {

    private static final String BANK_ACCOUNT_ID = "Id";
    private static final String BANK_ACCOUNT_NAME = "NameAccount";
    private static final String BANK_ACCOUNT_CREDIT_CARD = "CreditCard";
    private static final String BANK_ACCOUNT_STATUS = "StatusBankAccount";
    private static final String BANK_ACCOUNT_BALANCE = "BalanceBankAccount";
    private static final String BANK_ACCOUNT_AVAILABLE = "Available";

    protected BankAccount buildBankAccountRowMapper(ResultSet resultSet) throws SQLException {
        return BankAccount.getBuilder()
                .id(resultSet.getLong(BANK_ACCOUNT_ID))
                .nameAccount(resultSet.getString(BANK_ACCOUNT_NAME))
                .creditCard(resultSet.getLong(BANK_ACCOUNT_CREDIT_CARD))
                .statusBankAccount(resultSet.getBoolean(BANK_ACCOUNT_STATUS))
                .balanceBankAccount(resultSet.getBigDecimal(BANK_ACCOUNT_BALANCE))
                .available(resultSet.getBoolean(BANK_ACCOUNT_AVAILABLE))
                .build();
    }

}
