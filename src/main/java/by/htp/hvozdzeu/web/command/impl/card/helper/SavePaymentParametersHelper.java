package by.htp.hvozdzeu.web.command.impl.card.helper;

import by.htp.hvozdzeu.model.CreditCard;

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
     * @param creditCard       CreditCard with credit card data found by ID
     * @return Map with write-off parameters
     */
    public static Map<Object, Object> writeOffParametersMapper(Map<String, Object> attributes, CreditCard creditCard) {
        Map<Object, Object> parametersMap = new HashMap<>();
        parametersMap.put(PARAMETER_CARD_NUMBER, encrypt(creditCard.getCardNumber(), getSecretKey()));
        parametersMap.put(PARAMETER_AMOUNT, attributes.get(PARAMETER_AMOUNT));
        parametersMap.put(PARAMETER_VC_CODE, encrypt((String) attributes.get(VERIFY_CODE), getSecretKey()));
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
     * The method for creating map for query transfer money from card to card
     *
     * @param request HttpServletRequest request
     * @return Map with parameters
     */
    public static Map<String, Object> requestTransferMapper(HttpServletRequest request) {
        Map<String, Object> attributeMap = new HashMap<>();
        attributeMap.put("cardNumberFromId", Long.valueOf(request.getParameter("idCardFromTransf")));
        attributeMap.put("cardNumberToId", Long.valueOf(request.getParameter("idCardToTransf")));
        attributeMap.put("amount", new BigDecimal(request.getParameter("sumCardTransf")));
        attributeMap.put("description", request.getParameter("descriptionCardTransf"));
        attributeMap.put("verifyCode", request.getParameter("code"));
        return attributeMap;
    }

    /**
     * The method for creating map for query transfer money from card to card
     *
     * @param request HttpServletRequest request
     * @return Map with parameters
     */
    public static Map<String, Object> requestTransferAlienCardMapper(HttpServletRequest request) {
        Map<String, Object> attributeMap = new HashMap<>();
        attributeMap.put("cardNumberFromId", Long.valueOf(request.getParameter("idCardFromTransf")));
        attributeMap.put("cardNumberToId", request.getParameter("idCardToTransf"));
        attributeMap.put("amount", new BigDecimal(request.getParameter("sumCardTransf")));
        attributeMap.put("description", request.getParameter("descriptionCardTransf"));
        attributeMap.put("verifyCode", request.getParameter("code"));
        return attributeMap;
    }

    /**
     * The method creating Map with parameters for transfer money
     *
     * @param attributes       Map with request attributes
     * @param creditCardFrom   CreditCard entity with credit card data found by ID from will be write-off money
     * @param creditCardTo     CreditCard entity with credit card data found by ID to will be refill money
     * @return Map with write-off parameters
     */
    public static Map<Object, Object> transferParametersMapper(Map<String, Object> attributes,
                                                               CreditCard creditCardFrom, CreditCard creditCardTo) {
        Map<Object, Object> parametersMap = new HashMap<>();
        parametersMap.put("cardNumberFrom", encrypt(creditCardFrom.getCardNumber(), getSecretKey()));
        parametersMap.put("cardNumberTo", encrypt(creditCardTo.getCardNumber(), getSecretKey()));
        parametersMap.put(PARAMETER_AMOUNT, attributes.get(PARAMETER_AMOUNT));
        parametersMap.put(PARAMETER_VC_CODE, encrypt((String) attributes.get(VERIFY_CODE), getSecretKey()));
        parametersMap.put(PARAMETER_APP_SECRET_CODE, getAppCode());
        return parametersMap;
    }

    /**
     * The method creating Map with parameters for transfer money
     *
     * @param attributes       Map with request attributes
     * @param creditCardFrom   CreditCard entity with credit card data found by ID from will be write-off money
     * @param creditCardTo     CreditCard entity with credit card data found by ID to will be refill money
     * @return Map with write-off parameters
     */
    public static Map<Object, Object> transferAlienCardParametersMapper(Map<String, Object> attributes,
                                                               CreditCard creditCardFrom, String creditCardTo) {
        Map<Object, Object> parametersMap = new HashMap<>();
        parametersMap.put("cardNumberFrom", encrypt(creditCardFrom.getCardNumber(), getSecretKey()));
        parametersMap.put("cardNumberTo", encrypt(creditCardTo, getSecretKey()));
        parametersMap.put(PARAMETER_AMOUNT, attributes.get(PARAMETER_AMOUNT));
        parametersMap.put(PARAMETER_VC_CODE, encrypt((String) attributes.get(VERIFY_CODE), getSecretKey()));
        parametersMap.put(PARAMETER_APP_SECRET_CODE, getAppCode());
        return parametersMap;
    }

}
