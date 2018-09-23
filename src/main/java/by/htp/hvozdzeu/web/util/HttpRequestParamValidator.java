package by.htp.hvozdzeu.web.util;

import by.htp.hvozdzeu.web.exception.ValidateNullRequestParamException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HttpRequestParamValidator {

    private static final String ERROR_REQUEST_PARAMETER_IS_NULL = "Request parameter is not initialized!";
    private static final String LOGIN_REGEX = "[0-9a-zA-Z_@$]{5,}";
    private static final String PSWD_REGEX = "(?=.*[a-zA-z])(?=.*[0-9]).{5,}";
    private static final String LAST_MIDDLE_FIRST_NAME_REGEX = "^[A-ZА-ЯЁ][A-Za-zА-Яа-яЁё]{1,50}";
    private static final String EMAIL_REGEX = "^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
    private static final String DATE_REGEX = "((19|20)\\d\\d)\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])";
    private static final String PHONE_REGEX = ".*(8-[0-9]{3}-([0-9]{3}(-[0-9]{2}){2}))";

    private HttpRequestParamValidator() {

    }

    public static void validateParamNotNull(String s) throws ValidateNullRequestParamException {
        if (s == null) {
            throw new ValidateNullRequestParamException(ERROR_REQUEST_PARAMETER_IS_NULL);
        }
    }

    public static boolean validateLogin(String login) throws ValidateNullRequestParamException {
        validateParamNotNull(login);
        return matchToRegex(login, LOGIN_REGEX);
    }

    public static boolean validatePhone(String phone) throws ValidateNullRequestParamException {
        validateParamNotNull(phone);
        return !matchToRegex(phone, PHONE_REGEX);
    }

    public static boolean validatePassword(String pass) throws ValidateNullRequestParamException {
        validateParamNotNull(pass);
        return matchToRegex(pass, PSWD_REGEX);
    }

    public static boolean validateLastMiddleFirstName(String name) throws ValidateNullRequestParamException {
        validateParamNotNull(name);
        return !matchToRegex(name, LAST_MIDDLE_FIRST_NAME_REGEX);
    }

    public static boolean validateEmail(String email) throws ValidateNullRequestParamException {
        validateParamNotNull(email);
        return !matchToRegex(email, EMAIL_REGEX);
    }

    public static boolean validateDate(String date) throws ValidateNullRequestParamException {
        validateParamNotNull(date);
        return matchToRegex(date, DATE_REGEX);
    }

    private static boolean matchToRegex(String input, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        return m.matches();
    }

}
