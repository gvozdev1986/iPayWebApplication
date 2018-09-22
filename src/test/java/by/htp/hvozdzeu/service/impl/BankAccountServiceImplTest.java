package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.BankAccountDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.BankAccount;
import by.htp.hvozdzeu.service.BankAccountService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class BankAccountServiceImplTest {

    @Mock
    private BankAccountDAO daoMock = DAOFactory.getBankAccountDao();

    @InjectMocks
    private BankAccountService service = ServiceFactory.getBankAccountService();

    private BankAccount bankAccount;

    private Long id = 1L;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bankAccount = BankAccount.getBuilder()
                .creditCard(1L)
                .statusBankAccount(true)
                .balanceBankAccount(BigDecimal.valueOf(0.25))
                .available(true)
                .nameAccount("BA_00001")
                .build();
    }

    @Test
    public void testCreateBankAccount_returnNewBankAccount() throws DAOException {
        when(daoMock.create(bankAccount)).thenReturn(bankAccount);
        assertThat(service.save(bankAccount), is(notNullValue()));
        assertNotNull(bankAccount.getCreditCard());
        assertTrue(bankAccount.isStatusBankAccount());
        assertNotNull(bankAccount.getBalanceBankAccount());
        assertTrue(bankAccount.isAvailable());
        assertNotNull(bankAccount.getNameAccount());
    }

    @Test
    public void testCreateBankAccount_returnNewBankAccountWithId() throws DAOException {
        when(daoMock.create(bankAccount)).thenAnswer((Answer<BankAccount>) invocation -> {
            Object[] arguments = invocation.getArguments();
            if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                BankAccount bankAccount = (BankAccount) arguments[0];
                bankAccount.setId(1L);
                return bankAccount;
            }
            return null;
        });
        assertThat(service.save(bankAccount), is(notNullValue()));
        assertNotNull(bankAccount.getCreditCard());
        assertTrue(bankAccount.isStatusBankAccount());
        assertNotNull(bankAccount.getBalanceBankAccount());
        assertTrue(bankAccount.isAvailable());
        assertNotNull(bankAccount.getNameAccount());
    }

    @Test(expected = RuntimeException.class)
    public void testCreateBankAccount_throwsException() throws DAOException {
        when(daoMock.create(BankAccount.getBuilder().build())).thenThrow(RuntimeException.class);
        BankAccount bankAccount = BankAccount.getBuilder().build();
        service.save(bankAccount);
    }

    @Test
    public void testUpdateBankAccountById_returnUpdatedBankAccount() throws DAOException {
        BankAccount account = BankAccount.getBuilder()
                .creditCard(1L)
                .statusBankAccount(true)
                .balanceBankAccount(BigDecimal.valueOf(0.25))
                .available(true)
                .nameAccount("BA_00002")
                .build();
        when(daoMock.update(account, id)).thenReturn(account);
        assertThat(service.update(account, id), is(notNullValue()));
        assertNotNull(bankAccount.getCreditCard());
        assertTrue(bankAccount.isStatusBankAccount());
        assertNotNull(bankAccount.getBalanceBankAccount());
        assertTrue(bankAccount.isAvailable());
        assertNotNull(bankAccount.getNameAccount());
    }

    @Test
    public void testFindBankAccountById_returnFoundBankAccount() throws DAOException {
        when(daoMock.findById(id)).thenReturn(bankAccount);
        assertThat(service.findById(id), is(notNullValue()));
        assertNotNull(bankAccount.getCreditCard());
        assertTrue(bankAccount.isStatusBankAccount());
        assertNotNull(bankAccount.getBalanceBankAccount());
        assertTrue(bankAccount.isAvailable());
        assertNotNull(bankAccount.getNameAccount());
    }

    @Test
    public void testReadBankAccount_returnListBankAccount() throws DAOException {
        when(daoMock.read()).thenReturn(Arrays.asList(bankAccount));
        assertThat(service.getAllBankAccounts(), is(notNullValue()));
        List<BankAccount> allBankAccount = service.getAllBankAccounts();
        assertEquals(1, allBankAccount.size());
        BankAccount account = allBankAccount.get(0);
        assertEquals(Long.valueOf(1L), account.getCreditCard());
        assertTrue(String.valueOf(true), account.isStatusBankAccount());
        assertEquals(BigDecimal.valueOf(0.25), account.getBalanceBankAccount());
        assertTrue(String.valueOf(true), account.isAvailable());
        assertEquals("BA_00001", account.getNameAccount());
    }

    @Test
    public void testDeleteBankAccountById_returnBoolean() throws DAOException {
        when(daoMock.deleteById(id)).thenReturn(any(Boolean.class));
        assertThat(service.deleteById(id), is(notNullValue()));
    }

    @Test
    public void testBlockBankAccountById_returnBoolean() throws DAOException {
        when(daoMock.bankAccountBlock(id)).thenReturn(any(Boolean.class));
        assertThat(service.bankAccountBlock(id), is(notNullValue()));
    }

    @Test
    public void testBankAccountUnblockById_returnBoolean() throws DAOException {
        when(daoMock.bankAccountUnBlock(id)).thenReturn(any(Boolean.class));
        assertThat(service.bankAccountUnBlock(id), is(notNullValue()));
    }

    @Test
    public void testFindByCreditCardId_returnBankAccount() throws DAOException {
        when(daoMock.findByCardId(id)).thenReturn(bankAccount);
        assertThat(service.findByCardId(id), is(notNullValue()));
        assertNotNull(bankAccount.getCreditCard());
        assertTrue(bankAccount.isStatusBankAccount());
        assertNotNull(bankAccount.getBalanceBankAccount());
        assertTrue(bankAccount.isAvailable());
        assertNotNull(bankAccount.getNameAccount());
    }

}