package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.CreditCardDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.enums.TypeCard;
import by.htp.hvozdzeu.service.CreditCardService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class CreditCardServiceImplTest {


    @Mock
    private CreditCardDAO daoMock = DAOFactory.getCreditCardDao();

    @InjectMocks
    private CreditCardService service = ServiceFactory.getCreditCardService();

    private CreditCard creditCard;

    private CreditCard blockedCreditCard;

    private Long id = 1L;

    private Long clientId = 1L;

    private String cardNumber = "0000 0000 0000 0000";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        creditCard = CreditCard.getBuilder()
                .client(1L)
                .cardNumber("0000 0000 0000 0000")
                .cardFirstName("ALIAKSANDR")
                .cardLastName("HVOZDZEU")
                .validDate("12/25")
                .typeCard(TypeCard.BELCARD)
                .verifyCode("1234")
                .block(false)
                .available(true)
                .build();

        blockedCreditCard = CreditCard.getBuilder()
                .client(1L)
                .cardNumber("0000 0000 0000 0000")
                .cardFirstName("ALIAKSANDR")
                .cardLastName("HVOZDZEU")
                .validDate("12/25")
                .typeCard(TypeCard.BELCARD)
                .verifyCode("1234")
                .block(true)
                .available(true)
                .build();
    }

    @Test
    public void testCreateCreditCard_returnNewCreditCard() throws DAOException {
        when(daoMock.create(creditCard)).thenReturn(creditCard);
        assertThat(service.save(creditCard), is(notNullValue()));
        assertNotNull(creditCard.getClient());
        assertNotNull(creditCard.getCardNumber());
        assertNotNull(creditCard.getCardFirstName());
        assertNotNull(creditCard.getCardLastName());
        assertNotNull(creditCard.getValidDate());
        assertNotNull(creditCard.getTypeCard());
        assertNotNull(creditCard.getVerifyCode());
        assertFalse(creditCard.isBlock());
        assertTrue(creditCard.isAvailable());
    }

    @Test
    public void testCreateCreditCard_returnNewCreditCardWithId() throws DAOException {
        when(daoMock.create(creditCard)).thenAnswer((Answer<CreditCard>) invocation -> {
            Object[] arguments = invocation.getArguments();
            if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                CreditCard creditCard = (CreditCard) arguments[0];
                creditCard.setId(1L);
                return creditCard;
            }
            return null;
        });
        assertThat(service.save(creditCard), is(notNullValue()));
        assertNotNull(creditCard.getClient());
        assertNotNull(creditCard.getCardNumber());
        assertNotNull(creditCard.getCardFirstName());
        assertNotNull(creditCard.getCardLastName());
        assertNotNull(creditCard.getValidDate());
        assertNotNull(creditCard.getTypeCard());
        assertNotNull(creditCard.getVerifyCode());
        assertFalse(creditCard.isBlock());
        assertTrue(creditCard.isAvailable());
    }

    @Test(expected = RuntimeException.class)
    public void testCreateCreditCard_throwsException() throws DAOException {
        when(daoMock.create(CreditCard.getBuilder().build())).thenThrow(RuntimeException.class);
        CreditCard creditCard = CreditCard.getBuilder().build();
        service.save(creditCard);
    }

    @Test
    public void testUpdateCreditCard_returnUpdatedCreditCard() throws DAOException {
        CreditCard creditCard = CreditCard.getBuilder()
                .client(1L)
                .cardNumber("0000 0000 0000 0000")
                .cardFirstName("ALIAKSANDR")
                .cardLastName("HVOZDZEU")
                .validDate("12/22")
                .typeCard(TypeCard.BELCARD)
                .verifyCode("4321")
                .block(false)
                .available(true)
                .build();

        when(daoMock.update(creditCard, id)).thenReturn(creditCard);
        assertThat(service.update(creditCard, id), is(notNullValue()));
        assertNotNull(creditCard.getClient());
        assertNotNull(creditCard.getCardNumber());
        assertNotNull(creditCard.getCardFirstName());
        assertNotNull(creditCard.getCardLastName());
        assertNotNull(creditCard.getValidDate());
        assertNotNull(creditCard.getTypeCard());
        assertNotNull(creditCard.getVerifyCode());
        assertFalse(creditCard.isBlock());
        assertTrue(creditCard.isAvailable());
    }

    @Test
    public void testFindCreditCardById_returnCreditCard() throws DAOException {
        when(daoMock.findById(id)).thenReturn(creditCard);
        assertThat(service.findById(id), is(notNullValue()));
        assertNotNull(creditCard.getClient());
        assertNotNull(creditCard.getCardNumber());
        assertNotNull(creditCard.getCardFirstName());
        assertNotNull(creditCard.getCardLastName());
        assertNotNull(creditCard.getValidDate());
        assertNotNull(creditCard.getTypeCard());
        assertNotNull(creditCard.getVerifyCode());
        assertFalse(creditCard.isBlock());
        assertTrue(creditCard.isAvailable());
    }

    @Test
    public void testReadCreditCard_returnListCreditCard() throws DAOException {
        when(daoMock.read()).thenReturn(Arrays.asList(creditCard));
        assertThat(service.getAllCreditCards(), is(notNullValue()));
        List<CreditCard> allCreditCard = service.getAllCreditCards();
        assertEquals(1, allCreditCard.size());
        CreditCard creditCard = allCreditCard.get(0);
        assertEquals(Long.valueOf(1L), creditCard.getClient());
        assertEquals("0000 0000 0000 0000", creditCard.getCardNumber());
        assertEquals("ALIAKSANDR", creditCard.getCardFirstName());
        assertEquals("HVOZDZEU", creditCard.getCardLastName());
        assertEquals("12/25", creditCard.getValidDate());
        assertEquals(TypeCard.BELCARD, creditCard.getTypeCard());
        assertEquals("1234", creditCard.getVerifyCode());
        assertFalse(creditCard.isBlock());
        assertTrue(creditCard.isAvailable());
    }

    @Test
    public void testDeleteDoAvailableCreditCardById_returnBoolean() throws DAOException {
        when(daoMock.deleteById(id)).thenReturn(any(Boolean.class));
        assertThat(service.deleteById(id), is(notNullValue()));
    }

    @Test
    public void testFindCreditCardByCardNumber_returnCreditCard() throws DAOException {
        when(daoMock.findByCreditCardNumber(cardNumber)).thenReturn(creditCard);
        assertThat(service.findByCreditCardNumber(cardNumber), is(notNullValue()));
        assertNotNull(creditCard.getClient());
        assertNotNull(creditCard.getCardNumber());
        assertNotNull(creditCard.getCardFirstName());
        assertNotNull(creditCard.getCardLastName());
        assertNotNull(creditCard.getValidDate());
        assertNotNull(creditCard.getTypeCard());
        assertNotNull(creditCard.getVerifyCode());
        assertFalse(creditCard.isBlock());
        assertTrue(creditCard.isAvailable());
    }

    @Test
    public void testFindCreditCardByIdClient_returnCreditCard() throws DAOException {
        when(daoMock.findCreditCardByIdClient(clientId)).thenReturn(Arrays.asList(creditCard));
        when(daoMock.findCreditCardByIdClient(clientId)).thenReturn((List<CreditCard>) creditCard);
        assertThat(service.findCreditCardByIdClient(clientId), is(notNullValue()));
        assertNotNull(creditCard.getClient());
        assertNotNull(creditCard.getCardNumber());
        assertNotNull(creditCard.getCardFirstName());
        assertNotNull(creditCard.getCardLastName());
        assertNotNull(creditCard.getValidDate());
        assertNotNull(creditCard.getTypeCard());
        assertNotNull(creditCard.getVerifyCode());
        assertFalse(creditCard.isBlock());
        assertTrue(creditCard.isAvailable());
    }

    @Test
    public void testBlockCreditCard_returnBoolean() throws DAOException {
        when(daoMock.blockCreditCard(id)).thenReturn(any(Boolean.class));
        assertThat(service.blockCreditCard(id), is(notNullValue()));
    }

    @Test
    public void testUnblockCreditCard_returnBoolean() throws DAOException {
        when(daoMock.unblockCreditCard(id)).thenReturn(any(Boolean.class));
        assertThat(service.unblockCreditCard(id), is(notNullValue()));
    }

    @Test
    public void testBlockedCreditCard_returnBoolean() throws DAOException {
        when(daoMock.blockedCreditCard()).thenReturn(Arrays.asList(blockedCreditCard));
        assertThat(service.blockedCreditCard(), is(notNullValue()));
        List<CreditCard> allBlockedCreditCard = service.getAllCreditCards();
        assertEquals(1, allBlockedCreditCard.size());
        CreditCard creditCard = allBlockedCreditCard.get(0);
        assertEquals(Long.valueOf(1L), creditCard.getClient());
        assertEquals("0000 0000 0000 0000", creditCard.getCardNumber());
        assertEquals("ALIAKSANDR", creditCard.getCardFirstName());
        assertEquals("HVOZDZEU", creditCard.getCardLastName());
        assertEquals("12/25", creditCard.getValidDate());
        assertEquals(TypeCard.BELCARD, creditCard.getTypeCard());
        assertEquals("1234", creditCard.getVerifyCode());
        assertFalse(creditCard.isBlock());
        assertTrue(creditCard.isAvailable());
    }

    @Test
    public void findByParameter() {
    }

    @Test
    public void findBlockedByParameter() {
    }

    @Test
    public void pagination() {
    }

    @Test
    public void createReturnId() {
    }
}