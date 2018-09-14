package by.htp.hvozdzeu.dao.mapper;

import by.htp.hvozdzeu.model.MailAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MailAccountRowMapper {

    private static final String MAIL_ACCOUNT_ID = "Id";
    private static final String MAIL_ACCOUNT_CLIENT = "MailLogin";
    private static final String MAIL_ACCOUNT_PSWD = "MailPswd";


    protected MailAccount buildMailAccountRowMapper(ResultSet resultSet) throws SQLException {
        return new MailAccount.Builder()
                .id(resultSet.getLong(MAIL_ACCOUNT_ID))
                .mailLogin(resultSet.getString(MAIL_ACCOUNT_CLIENT))
                .mailPswd(resultSet.getString(MAIL_ACCOUNT_PSWD))
                .build();
    }
    


}
