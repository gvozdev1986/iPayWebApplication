package by.htp.hvozdzeu.dao.mapper;

import by.htp.hvozdzeu.dao.util.RebasePassword;
import by.htp.hvozdzeu.model.Client;
import by.htp.hvozdzeu.model.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper {

    private static final String CLIENT_ID = "Id";
    private static final String CLIENT_LOGIN = "Login";
    private static final String CLIENT_PSWD = "Password";
    private static final String CLIENT_FIRST_NAME = "FirstName";
    private static final String CLIENT_LAST_NAME = "LastName";
    private static final String CLIENT_PATRONYMIC = "Patronymic";
    private static final String CLIENT_DATE_BIRTH = "DateBirth";
    private static final String CLIENT_PHONE_HOME = "PhoneHome";
    private static final String CLIENT_PHONE_MOBILE = "PhoneMobile";
    private static final String CLIENT_ADDRESS = "Address";
    private static final String CLIENT_ROLE = "Role";
    private static final String CLIENT_EMAIL = "Email";
    private static final String CLIENT_AVAILABLE = "Available";
    private static final String CLIENT_IS_ONLINE = "isOnline";


    protected Client buildClientRowMapper(ResultSet resultSet) throws SQLException {
        RebasePassword rebasePassword = new RebasePassword();
        return new Client.Builder()
                .id(resultSet.getLong(CLIENT_ID))
                .login(resultSet.getString(CLIENT_LOGIN))
                .password(rebasePassword.rebasePSWD(resultSet.getString(CLIENT_PSWD)))
                .firstName(resultSet.getString(CLIENT_FIRST_NAME))
                .lastName(resultSet.getString(CLIENT_LAST_NAME))
                .patronymic(resultSet.getString(CLIENT_PATRONYMIC))
                .dateBirth(resultSet.getDate(CLIENT_DATE_BIRTH).toLocalDate())
                .phoneHome(resultSet.getString(CLIENT_PHONE_HOME))
                .phoneMobile(resultSet.getString(CLIENT_PHONE_MOBILE))
                .address(resultSet.getString(CLIENT_ADDRESS))
                .role(Role.valueOf(resultSet.getString(CLIENT_ROLE)))
                .email(resultSet.getString(CLIENT_EMAIL))
                .available(resultSet.getBoolean(CLIENT_AVAILABLE))
                .isOnline(resultSet.getBoolean(CLIENT_IS_ONLINE))
                .build();
    }


}
