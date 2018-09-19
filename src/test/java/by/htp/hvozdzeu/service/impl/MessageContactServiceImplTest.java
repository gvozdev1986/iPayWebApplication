package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.IMessageContactDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.MessageContact;
import by.htp.hvozdzeu.service.IMessageContactService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class MessageContactServiceImplTest {

    @Mock
    private IMessageContactDAO daoMock = DAOFactory.getMessageContactDAO();

    @InjectMocks
    private IMessageContactService service = ServiceFactory.getMessageContactService();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddMessageContact_returnNewMessage() throws DAOException {
        when(daoMock.create(any(MessageContact.class))).thenReturn(new MessageContact());
        MessageContact messageContact = new MessageContact();
        assertThat(service.create(messageContact), is(notNullValue()));
    }

    @Test
    public void testAddMessageContact_returnNewMessageWithId() throws DAOException {
        when(daoMock.create(any(MessageContact.class))).thenAnswer((Answer<MessageContact>) invocation -> {
            Object[] arguments = invocation.getArguments();
            if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                MessageContact messageContact = (MessageContact) arguments[0];
                messageContact.setId(1L);
                return messageContact;
            }
            return null;
        });
        MessageContact messageContact = new MessageContact();
        assertThat(service.create(messageContact), is(notNullValue()));
    }

    @Test(expected = RuntimeException.class)
    public void testAddMessageContact_throwsException() throws DAOException {
        when(daoMock.create(any(MessageContact.class))).thenThrow(RuntimeException.class);
        MessageContact messageContact = new MessageContact();
        service.create(messageContact);

    }

    @Test
    public void testUpdateMessageContact_returnUpdateMessageContact() throws DAOException {
        when(daoMock.update(any(MessageContact.class), any(Long.class))).thenReturn(any(MessageContact.class));
    }

    @Test
    public void testDeleteByIdMessageContact_returnBoolean() throws DAOException {
        when(daoMock.deleteById(any(Long.class))).thenReturn(any(Boolean.class));
    }

    @Test
    public void testCheckMessageContactAsRead_returnBoolean() throws DAOException {
        when(daoMock.checkMessageAsRead(any(Long.class))).thenReturn(any(Boolean.class));
    }

    @Test
    public void testPaginationMessageContact_returnListMessageContact() throws DAOException {
        List<MessageContact> messageContacts = new ArrayList<>();
        when(daoMock.pagination(any(Integer.class), any(Integer.class))).thenReturn(messageContacts);
    }

    @Test
    public void testFindByIdMessageContact_returnMessageContact() throws DAOException {
        when(daoMock.findById(any(Long.class))).thenReturn(any(MessageContact.class));
    }

    @Test
    public void testUnReadMessageContact_returnListMessageContact() throws DAOException {
        List<MessageContact> messageContacts = new ArrayList<>();
        when(daoMock.unreadMessages(false)).thenReturn(messageContacts);
    }

}