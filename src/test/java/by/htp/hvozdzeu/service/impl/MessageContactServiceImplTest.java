package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.MessageContactDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.MessageContact;
import by.htp.hvozdzeu.service.MessageContactService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class MessageContactServiceImplTest {

    @Mock
    private MessageContactDAO daoMock = DAOFactory.getMessageContactDAO();

    @InjectMocks
    private MessageContactService service = ServiceFactory.getMessageContactService();

    private MessageContact messageContact;

    private Long id = 1L;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        messageContact = MessageContact.getBuilder()
                .nameContact("Hvozdzeu Aliaksandr")
                .date(LocalDate.of(2018, 1, 1))
                .time(LocalTime.of(12, 25))
                .emailContact("aliaksandr.hvozdzeu@gmail.com")
                .phoneContact("8-029-147-36-24")
                .messageFromContact("Mock test for message from contact.")
                .checkRead(false)
                .build();
    }

    @Test
    public void testCreateMessageContact_returnNewMessage() throws DAOException {
        when(daoMock.create(messageContact)).thenReturn(messageContact);
        assertThat(service.create(messageContact), is(notNullValue()));
        assertNotNull(messageContact.getNameContact());
        assertNotNull(messageContact.getDate());
        assertNotNull(messageContact.getTime());
        assertNotNull(messageContact.getEmailContact());
        assertNotNull(messageContact.getPhoneContact());
        assertNotNull(messageContact.getMessageFromContact());
        assertFalse(messageContact.isCheckRead());
    }

    @Test
    public void testCreateMessageContact_returnNewMessageWithId() throws DAOException {
        when(daoMock.create(messageContact)).thenAnswer((Answer<MessageContact>) invocation -> {
            Object[] arguments = invocation.getArguments();
            if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                MessageContact bankAccount = (MessageContact) arguments[0];
                bankAccount.setId(1L);
                return bankAccount;
            }
            return null;
        });
        assertThat(service.create(messageContact), is(notNullValue()));
        assertNotNull(messageContact.getNameContact());
        assertNotNull(messageContact.getDate());
        assertNotNull(messageContact.getTime());
        assertNotNull(messageContact.getEmailContact());
        assertNotNull(messageContact.getPhoneContact());
        assertNotNull(messageContact.getMessageFromContact());
        assertFalse(messageContact.isCheckRead());
    }

    @Test(expected = RuntimeException.class)
    public void testCreateMessageContact_throwsException() throws DAOException {
        when(daoMock.create(MessageContact.getBuilder().build())).thenThrow(RuntimeException.class);
        MessageContact messageContact = MessageContact.getBuilder().build();
        service.create(messageContact);
    }

    @Test
    public void testCheckMessageContactAsRead_returnBoolean() throws DAOException {
        when(daoMock.deleteById(id)).thenReturn(any(Boolean.class));
        verify(service, times(10)).checkMessageAsRead(id);
    }

    @Test
    public void testReadMessageContact_returnListMessageContact() throws DAOException {
        when(daoMock.read()).thenReturn(Arrays.asList(messageContact));

        assertThat(service.read(), is(notNullValue()));
        List<MessageContact> allMessages = service.read();
        assertEquals(1, allMessages.size());
        MessageContact message = allMessages.get(0);

        assertEquals("Hvozdzeu Aliaksandr", message.getNameContact());
        assertEquals(LocalDate.of(2018, 1, 1), message.getDate());
        assertEquals(LocalTime.of(12, 25), message.getTime());
        assertEquals("aliaksandr.hvozdzeu@gmail.com", message.getEmailContact());
        assertEquals("8-029-147-36-24", message.getPhoneContact());
        assertEquals("Mock test for message from contact.", message.getMessageFromContact());
        assertFalse(message.isCheckRead());
    }

    @Test
    public void testFindByIdMessageContact_returnMessageContact() throws DAOException {
        when(daoMock.findById(id)).thenReturn(messageContact);
        assertThat(service.findById(id), is(notNullValue()));
        assertNotNull(messageContact.getNameContact());
        assertNotNull(messageContact.getDate());
        assertNotNull(messageContact.getTime());
        assertNotNull(messageContact.getEmailContact());
        assertNotNull(messageContact.getPhoneContact());
        assertNotNull(messageContact.getMessageFromContact());
        assertFalse(messageContact.isCheckRead());
    }


}