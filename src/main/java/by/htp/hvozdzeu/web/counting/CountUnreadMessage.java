package by.htp.hvozdzeu.web.counting;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.service.IMessageContactService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;

public class CountUnreadMessage {

    private static IMessageContactService iMessageContactService = ServiceFactory.getMessageContactService();

    public static Integer countUnreadMessage() throws DAOException {
        return iMessageContactService.unreadMessages(false).size();
    }

}
