package by.htp.hvozdzeu.dao.mapper;

import by.htp.hvozdzeu.model.MessageContact;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageContactRowMapper {

    private static final String CONTACT_MESSAGE_ID = "Id";
    private static final String CONTACT_MESSAGE_NAME = "NameContact";
    private static final String CONTACT_MESSAGE_DATE = "Date";
    private static final String CONTACT_MESSAGE_TIME = "Time";
    private static final String CONTACT_MESSAGE_EMAIL = "EmailContact";
    private static final String CONTACT_MESSAGE_PHONE = "PhoneContact";
    private static final String CONTACT_MESSAGE = "MessageContact";
    private static final String CONTACT_MESSAGE_IS_READY = "CheckRead";

    protected MessageContact buildMessageContactRowMapper(ResultSet resultSet) throws SQLException {
        return MessageContact.getBuilder()
                .id(resultSet.getLong(CONTACT_MESSAGE_ID))
                .nameContact(resultSet.getString(CONTACT_MESSAGE_NAME))
                .date(resultSet.getDate(CONTACT_MESSAGE_DATE).toLocalDate())
                .time(resultSet.getTime(CONTACT_MESSAGE_TIME).toLocalTime())
                .emailContact(resultSet.getString(CONTACT_MESSAGE_EMAIL))
                .phoneContact(resultSet.getString(CONTACT_MESSAGE_PHONE))
                .messageFromContact(resultSet.getString(CONTACT_MESSAGE))
                .checkRead(resultSet.getBoolean(CONTACT_MESSAGE_IS_READY))
                .build();
    }

}
