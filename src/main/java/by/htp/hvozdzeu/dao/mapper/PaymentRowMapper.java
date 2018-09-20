package by.htp.hvozdzeu.dao.mapper;

import by.htp.hvozdzeu.model.Payment;
import by.htp.hvozdzeu.model.report.PaymentReport;
import by.htp.hvozdzeu.model.report.SumPaymentReportChartPie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMapper {
	
	
	private static final String CHART_PIE_AMOUNT = "Amount";
    private static final String CHART_PIE_GROUP = "PaymentDataGroup";
    private static final String CHART_PIE_SUM = "Sum";
    
    protected SumPaymentReportChartPie buildChartPiePaymentRowMapper(ResultSet resultSet) throws SQLException {
        return SumPaymentReportChartPie.getBuilder()
                .amount(resultSet.getInt(CHART_PIE_AMOUNT))
                .group(resultSet.getString(CHART_PIE_GROUP))
                .sum(resultSet.getBigDecimal(CHART_PIE_SUM))               
                .build();

    }	

    private static final String PAYMENT_ID = "Id";
    private static final String PAYMENT_DATE = "DatePayment";
    private static final String PAYMENT_TIME = "TimePayment";
    private static final String PAYMENT_DESCRIPTION = "DescriptionPayment";
    private static final String PAYMENT_SERVICE = "PaymentData";
    private static final String PAYMENT_AMOUNT = "AmountPayment";
    private static final String PAYMENT_CREDIT_CARD = "CreditCard";
    private static final String PAYMENT_AVAILABLE = "Available";
	private static final String PAYMENT_ORDER_NO = "OrderNo";

    protected Payment buildPaymentRowMapper(ResultSet resultSet) throws SQLException {
        return Payment.getBuilder()
                .id(resultSet.getLong(PAYMENT_ID))
                .datePayment(resultSet.getDate(PAYMENT_DATE).toLocalDate())
                .timePayment(resultSet.getTime(PAYMENT_TIME).toLocalTime())
                .descriptionPayment(resultSet.getString(PAYMENT_DESCRIPTION))
                .paymentService(resultSet.getLong(PAYMENT_SERVICE))
                .amountPayment(resultSet.getBigDecimal(PAYMENT_AMOUNT))
                .creditCard(resultSet.getLong(PAYMENT_CREDIT_CARD))
                .available(resultSet.getBoolean(PAYMENT_AVAILABLE))
                .orderNo(resultSet.getString(PAYMENT_ORDER_NO))
                .build();

    }
    
    private static final String PAYMENT_REPORT_ID = "Id";
	private static final String PAYMENT_REPORT_DATE = "DatePayment";
	private static final String PAYMENT_REPORT_TIME = "TimePayment";
	private static final String PAYMENT_REPORT_DESCRIPTION = "DescriptionPayment";
	private static final String PAYMENT_REPORT_AMOUNT = "AmountPayment";
	private static final String PAYMENT_REPORT_DATA_NAME = "PaymentDataName";
	private static final String PAYMENT_REPORT_DATA_GROUP = "PaymentDataGroup";
	private static final String PAYMENT_REPORT_DATA_DESCRIPTION = "PaymentDataDescription";
    
	protected PaymentReport buildPaymentReportRowMapper(ResultSet resultSet) throws SQLException {
		return PaymentReport.getBuilder()
                .id(resultSet.getLong(PAYMENT_REPORT_ID))
				.datePayment(resultSet.getDate(PAYMENT_REPORT_DATE).toLocalDate())
				.timePayment(resultSet.getTime(PAYMENT_REPORT_TIME).toLocalTime())
				.descriptionPayment(resultSet.getString(PAYMENT_REPORT_DESCRIPTION))
				.amountPayment(resultSet.getBigDecimal(PAYMENT_REPORT_AMOUNT))
				.paymentDataName(resultSet.getString(PAYMENT_REPORT_DATA_NAME))
				.paymentDataGroup(resultSet.getString(PAYMENT_REPORT_DATA_GROUP))
				.paymentDataDescription(resultSet.getString(PAYMENT_REPORT_DATA_DESCRIPTION))
                .build();

	}
    
}
