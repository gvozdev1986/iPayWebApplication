package by.htp.hvozdzeu.dao.mapper;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.enums.TypeCard;
import by.htp.hvozdzeu.model.report.StatusCardReport;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardRowMapper {

    private static final String CREDIT_CARD_ID = "Id";
    private static final String CREDIT_CARD_CLIENT = "Client";
    private static final String CREDIT_CARD_NUMBER = "CardNumber";
    private static final String CREDIT_CARD_FIRST_NAME = "CardFirstName";
    private static final String CREDIT_CARD_LAST_NAME = "CardLastName";
    private static final String CREDIT_CARD_VALID_DATE = "ValidDate";
    private static final String CREDIT_CARD_TYPE_CARD = "TypeCard";
    private static final String CREDIT_CARD_VERIFY_CODE = "VerifyCode";
    private static final String CREDIT_CARD_BLOCK = "Block";
    private static final String CREDIT_CARD_AVAILABLE = "Available";


    protected CreditCard buildCreditCardRowMapper(ResultSet resultSet) throws SQLException {
        return CreditCard.getBuilder()
                .id(resultSet.getLong(CREDIT_CARD_ID))
                .client(resultSet.getLong(CREDIT_CARD_CLIENT))
                .cardNumber(resultSet.getString(CREDIT_CARD_NUMBER))
                .cardFirstName(resultSet.getString(CREDIT_CARD_FIRST_NAME))
                .cardLastName(resultSet.getString(CREDIT_CARD_LAST_NAME))
                .validDate(resultSet.getString(CREDIT_CARD_VALID_DATE))
                .typeCard(TypeCard.valueOf(resultSet.getString(CREDIT_CARD_TYPE_CARD)))
                .verifyCode(resultSet.getString(CREDIT_CARD_VERIFY_CODE))
                .block(resultSet.getBoolean(CREDIT_CARD_BLOCK))
                .available(resultSet.getBoolean(CREDIT_CARD_AVAILABLE))
                .build();
    }
    
    private static final String STATUS_CARD_ID = "Id";
    private static final String STATUS_CARD_NUMBER = "CardNumber";
    private static final String STATUS_CARD_FIRST_NAME = "CardFirstName";
    private static final String STATUS_CARD_LAST_NAME = "CardLastName";
    private static final String STATUS_CARD_VALID_DATE = "ValidDate";
    private static final String STATUS_CARD_TYPE_CARD = "TypeCard";
    private static final String STATUS_CARD_VERIFY_CODE = "VerifyCode";
    private static final String STATUS_CARD_BLOCK = "Block";
    private static final String STATUS_CARD_BALANCE = "BalanceBankAccount";
    private static final String STATUS_CARD_NAME_ACCOUNT = "NameAccount";


    protected StatusCardReport buildStatusCreditCardRowMapper(ResultSet resultSet) throws SQLException {
        return StatusCardReport.getBuilder()
        		.id(resultSet.getLong(STATUS_CARD_ID))
        		.cardNumber(resultSet.getString(STATUS_CARD_NUMBER))
        		.cardFirstName(resultSet.getString(STATUS_CARD_FIRST_NAME))
        		.cardLastName(resultSet.getString(STATUS_CARD_LAST_NAME))
        		.validDate(resultSet.getString(STATUS_CARD_VALID_DATE))
        		.typeCard(resultSet.getString(STATUS_CARD_TYPE_CARD))
        		.verifyCode(resultSet.getString(STATUS_CARD_VERIFY_CODE))
        		.block(resultSet.getBoolean(STATUS_CARD_BLOCK))
        		.balanceBankAccount(resultSet.getBigDecimal(STATUS_CARD_BALANCE))
        		.nameAccount(resultSet.getString(STATUS_CARD_NAME_ACCOUNT))
                .build();
    }

}
