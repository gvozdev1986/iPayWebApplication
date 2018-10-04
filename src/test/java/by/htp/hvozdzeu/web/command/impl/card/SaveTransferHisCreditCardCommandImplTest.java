package by.htp.hvozdzeu.web.command.impl.card;

import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class SaveTransferHisCreditCardCommandImplTest {

    @Mock
    private CreditCardService creditCardService = ServiceFactory.getCreditCardService();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void executeCommand() {

    }
}