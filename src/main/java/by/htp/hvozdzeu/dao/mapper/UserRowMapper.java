package by.htp.hvozdzeu.dao.mapper;

import by.htp.hvozdzeu.dao.util.RebasePassword;
import by.htp.hvozdzeu.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper {

    private static final String USER_ID = "Id";
    private static final String USER_LOGIN = "Login";
    private static final String USER_PSWD = "Password";
    private static final String USER_FIRST_NAME = "FirstName";
    private static final String USER_LAST_NAME = "LastName";
    private static final String USER_PATRONYMIC = "Patronymic";
    private static final String USER_DATE_BIRTH = "DateBirth";
    private static final String USER_PHONE_HOME = "PhoneHome";
    private static final String USER_PHONE_MOBILE = "PhoneMobile";
    private static final String USER_ADDRESS = "Address";
    private static final String USER_EMAIL = "Email";
    private static final String USER_AVAILABLE = "Available";
    private static final String USER_IS_ADMIN = "isAdmin";
    private static final String USER_REG_CODE = "RegCode";


    protected User buildUserRowMapper(ResultSet resultSet) throws SQLException {
        RebasePassword rebasePassword = new RebasePassword();
        return User.getBuilder()
                .id(resultSet.getLong(USER_ID))
                .login(resultSet.getString(USER_LOGIN))
                .password(rebasePassword.rebasePSWD(resultSet.getString(USER_PSWD)))
                .firstName(resultSet.getString(USER_FIRST_NAME))
                .lastName(resultSet.getString(USER_LAST_NAME))
                .patronymic(resultSet.getString(USER_PATRONYMIC))
                .dateBirth(resultSet.getDate(USER_DATE_BIRTH).toLocalDate())
                .phoneHome(resultSet.getString(USER_PHONE_HOME))
                .phoneMobile(resultSet.getString(USER_PHONE_MOBILE))
                .address(resultSet.getString(USER_ADDRESS))
                .email(resultSet.getString(USER_EMAIL))
                .available(resultSet.getBoolean(USER_AVAILABLE))
                .isAdmin(resultSet.getBoolean(USER_IS_ADMIN))
                .regCode(resultSet.getString(USER_REG_CODE))
                .build();
    }


}
