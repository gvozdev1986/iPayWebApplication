package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.response.Response;
import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static by.htp.hvozdzeu.rest.ParameterConstantDeclaration.*;
import static by.htp.hvozdzeu.rest.ResponseManager.getResponse;
import static by.htp.hvozdzeu.rest.URLConstantPool.*;
import static by.htp.hvozdzeu.util.ApplicationCodeProperties.getAppCode;
import static by.htp.hvozdzeu.util.Decoder.encrypt;
import static by.htp.hvozdzeu.util.DecoderProperties.getSecretKey;
import static org.junit.Assert.*;

public class SavePayPaymentCommandImplTest {

    @InjectMocks
    private CreditCardService creditCardService = ServiceFactory.getCreditCardService();

    private Map<Object, Object> parameters;

    private Map<Object, Object> writeOffParameters;

    @Before
    public void setUp() throws Exception {

        BigDecimal amount = new BigDecimal("30.15");

        String token = "3f70218c-07ab-47d9-ac63-dd56f1edb558";

        CreditCard creditCard = creditCardService.findById(2L);

        parameters = new HashMap<>();
        parameters.put(PARAMETER_CREDIT_CARD_NUMBER, encrypt(creditCard.getCardNumber(), getSecretKey()));
        parameters.put(PARAMETER_VC_CODE, encrypt(creditCard.getVerifyCode(), getSecretKey()));
        parameters.put(PARAMETER_FIRST_NAME, encrypt(creditCard.getCardFirstName(), getSecretKey()));
        parameters.put(PARAMETER_LAST_NAME, encrypt(creditCard.getCardLastName(), getSecretKey()));
        parameters.put(PARAMETER_MONTH_VALID, encrypt(creditCard.getValidDate().split("/")[0], getSecretKey()));
        parameters.put(PARAMETER_YEAR_VALID, encrypt(creditCard.getValidDate().split("/")[1], getSecretKey()));
        parameters.put(PARAMETER_APP_SECRET_CODE, getAppCode());

        writeOffParameters = new HashMap<>();
        writeOffParameters.put(PARAMETER_TOKEN_REST, token);
        writeOffParameters.put(PARAMETER_CARD_NUMBER, encrypt(creditCard.getCardNumber(), getSecretKey()));
        writeOffParameters.put(PARAMETER_AMOUNT, amount);
        writeOffParameters.put(PARAMETER_VC_CODE, encrypt(creditCard.getVerifyCode(), getSecretKey()));
        writeOffParameters.put(PARAMETER_APP_SECRET_CODE, getAppCode());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void executeCommand() {

        Response responseGetToken = getResponse(URL_GET_TOKEN, parameters, QUERY_TYPE_GET);
        System.out.println(responseGetToken.getMessage());

        if (responseGetToken.isStatus()) {
            Response responseCheckCreditCard = getResponse(URL_WRITE_OFF_BALANCE, writeOffParameters, QUERY_TYPE_PUT);
            System.out.println(">>>" + responseCheckCreditCard.isStatus());
            System.out.println(">>>" + responseCheckCreditCard.getMessage());
        }

    }
}