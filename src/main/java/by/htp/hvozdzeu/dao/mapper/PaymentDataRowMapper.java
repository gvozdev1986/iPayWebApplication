package by.htp.hvozdzeu.dao.mapper;

import by.htp.hvozdzeu.model.PaymentData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDataRowMapper {

    private static final String PAYMENT_SERVICE_ID = "Id";
    private static final String PAYMENT_SERVICE_CODE = "PaymentDataCode";
    private static final String PAYMENT_SERVICE_NAME = "PaymentDataName";
    private static final String PAYMENT_SERVICE_GROUP = "PaymentDataGroup";
    private static final String PAYMENT_SERVICE_DESCRIPTION = "PaymentDataDescription";
    private static final String PAYMENT_SERVICE_AVAILABLE = "Available";

    protected PaymentData buildPaymentServiceRowMapper(ResultSet resultSet) throws SQLException {
        return PaymentData.getBuilder()
                .id(resultSet.getLong(PAYMENT_SERVICE_ID))
                .paymentDataCode(resultSet.getString(PAYMENT_SERVICE_CODE))
                .paymentDataName(resultSet.getString(PAYMENT_SERVICE_NAME))
                .paymentDataGroup(resultSet.getString(PAYMENT_SERVICE_GROUP))
                .paymentDataDescription(resultSet.getString(PAYMENT_SERVICE_DESCRIPTION))
                .available(resultSet.getBoolean(PAYMENT_SERVICE_AVAILABLE))
                .build();
    }


}
