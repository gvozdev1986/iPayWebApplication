package by.htp.hvozdzeu.web.command.impl.card.helper;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static by.htp.hvozdzeu.rest.ParameterConstantDeclaration.*;
import static by.htp.hvozdzeu.util.ApplicationCodeProperties.getAppCode;
import static by.htp.hvozdzeu.util.Decoder.encrypt;
import static by.htp.hvozdzeu.util.DecoderProperties.getSecretKey;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

/**
 * The class helper for SavePayPaymentCommandImpl
 */
public class SavePaymentParametersHelper {

    /**
     * Private constructor
     */
    private SavePaymentParametersHelper() {
    }

    /**
     * The method creating Map with parameters for query write-off amount
     *
     * @param attributes       Map with request attributes
     * @param responseGetToken Response response from method getToken
     * @param creditCard       CreditCard with credit card data found by ID
     * @return Map with write-off parameters
     */
    public static Map<Object, Object> writeOffParametersMapper(Map<String, Object> attributes, Response responseGetToken, CreditCard creditCard) {
        Map<Object, Object> parametersMap = new HashMap<>();
        parametersMap.put(PARAMETER_TOKEN_REST, responseGetToken.getMessage());
        parametersMap.put(PARAMETER_CARD_NUMBER, encrypt(creditCard.getCardNumber(), getSecretKey()));
        parametersMap.put(PARAMETER_AMOUNT, attributes.get("amount"));
        parametersMap.put(PARAMETER_VC_CODE, encrypt((String) attributes.get("verifyCode"), getSecretKey()));
        parametersMap.put(PARAMETER_APP_SECRET_CODE, getAppCode());
        return parametersMap;
    }

    /**
     * The method creating request map
     *
     * @param request HttpServletRequest request
     * @return Map with attributes
     */
    public static Map<String, Object> requestMapper(HttpServletRequest request) {
        Map<String, Object> attributeMap = new HashMap<>();
        attributeMap.put("cardId", Long.valueOf(request.getParameter(REQUEST_ID_CARD)));
        attributeMap.put("serviceId", Long.valueOf(request.getParameter(REQUEST_ID_SERVICE)));
        attributeMap.put("amount", new BigDecimal(request.getParameter(SUM)));
        attributeMap.put("description", request.getParameter(DESCRIPTION));
        attributeMap.put("verifyCode", request.getParameter(CODE));
        attributeMap.put("orderNo", request.getParameter(ORDER_NO));
        return attributeMap;
    }

    /**
     * The method creating map for query getToken
     *
     * @param creditCard CreditCard found by ID from request
     * @return Map with parameters
     */
    public static Map<Object, Object> parameterMapper(CreditCard creditCard) {
        Map<Object, Object> parametersMap = new HashMap<>();
        parametersMap.put(PARAMETER_CREDIT_CARD_NUMBER, encrypt(creditCard.getCardNumber(), getSecretKey()));
        parametersMap.put(PARAMETER_VC_CODE, encrypt(creditCard.getVerifyCode(), getSecretKey()));
        parametersMap.put(PARAMETER_FIRST_NAME, encrypt(creditCard.getCardFirstName(), getSecretKey()));
        parametersMap.put(PARAMETER_LAST_NAME, encrypt(creditCard.getCardLastName(), getSecretKey()));
        parametersMap.put(PARAMETER_MONTH_VALID, encrypt(creditCard.getValidDate().split("/")[0], getSecretKey()));
        parametersMap.put(PARAMETER_YEAR_VALID, encrypt(creditCard.getValidDate().split("/")[1], getSecretKey()));
        parametersMap.put(PARAMETER_APP_SECRET_CODE, getAppCode());
        return parametersMap;
    }

}
