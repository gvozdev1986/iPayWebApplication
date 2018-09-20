package by.htp.hvozdzeu.web.counting;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.service.MessageContactService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;

public class CountUnreadMessage {

    private static MessageContactService messageContactService = ServiceFactory.getMessageContactService();

    public static Integer countUnreadMessage() throws DAOException {
        return messageContactService.unreadMessages(false).size();
    }

}
