package by.htp.hvozdzeu.web.util;

import static by.htp.hvozdzeu.web.util.RedirectPageUrl.PERSONAL_DATA_VIEW;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HttpRequestParamValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestParamValidator.class);

	private static final String ERROR_REQUEST_PARAMETER_IS_NULL = "Request parameter is not initialized!";

	public static final String INVALID_FIRST_NAME_ATTRIBUTE = "invalidFirstname";
	public static final String INVALID_LAST_NAME_ATTRIBUTE = "invalidLastName";
	public static final String INVALID_PATRONYMIC_ATTRIBUTE = "invalidPatronymic";
	public static final String INVALID_DATE_ATTRIBUTE = "invalidDate";
	public static final String INVALID_HOME_PHONE_ATTRIBUTE = "invalidHomePhone";
	public static final String INVALID_MOBILE_PHONE_ATTRIBUTE = "invalidMobilePhone";
	public static final String INVALID_EMAIL_ATTRIBUTE = "invalidEmail";
	public static final String INVALID_ADDRESS_ATTRIBUTE = "invalidAddress";

	public static final String INVALID_FIRST_NAME_MESSAGE = "Invalid first name";
	public static final String INVALID_LAST_NAME_MESSAGE = "Invalid last name";
	public static final String INVALID_PATRONYMIC_MESSAGE = "Invalid patronymic";
	public static final String INVALID_DATE_MESSAGE = "Invalid date";
	public static final String INVALID_HOME_PHONE_MESSAGE = "Invalid home phone";
	public static final String INVALID_MOBILE_PHONE_MESSAGE = "Invalid mobile phone";
	public static final String INVALID_EMAIL_MESSAGE = "Invalid email";
	public static final String INVALID_ADDRESS_MESSAGE = "Invalid address";	

	private static final String ONLY_LETTERS_REGEX = "[A-Za-zА-Яа-яЁё]{1,50}";
	private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	private static final String PHONE_NUMBER_REGEX = ".*(8-[0-9]{3}-([0-9]{3}(-[0-9]{2}){2}))"; // 8-029-147-36-24
	private static final String DATE_REGEX = "((19|20)\\d\\d)\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])";
	private static final String POSITIVE_NUMBER_REGEX = "([1-9])([0-9]*)";
	private static final String USER_NAME_REGEX = "[0-9a-zA-Z_@$]{5,}";
	private static final String PSWD_REGEX = "(?=.*[a-zA-z])(?=.*[0-9]).{5,}";

	public static void validateParamNotNull(String s) {
		if (s == null) {
			LOGGER.error(ERROR_REQUEST_PARAMETER_IS_NULL);
		}
	}

	public static boolean validateEmail(String email) {
		LOGGER.info("Process validation email {}", email);
		validateParamNotNull(email);
		return matchToRegexCaseIns(email, EMAIL_REGEX);
	}

	public static boolean validateLogin(String login) {
		LOGGER.info("Process validation login {}", login);
		validateParamNotNull(login);
		return matchToRegex(login, USER_NAME_REGEX);
	}
	
	public static boolean validatePassword(String password) {
		LOGGER.info("Process validation password {}", password);
		validateParamNotNull(password);
		return matchToRegex(password, PSWD_REGEX);
	}
	
	public static boolean validateOnlyText(String text) {
		LOGGER.info("Process validation only text {}", text);
		validateParamNotNull(text);
		return matchToRegex(text, ONLY_LETTERS_REGEX);
	}

	public static boolean validatePhoneNumber(String phoneNumber) {
		LOGGER.info("Process validation phone number {}", phoneNumber);
		validateParamNotNull(phoneNumber);
		return matchToRegex(phoneNumber, PHONE_NUMBER_REGEX);
	}

	public static boolean validateDate(String date) {
		LOGGER.info("Process validation date {}", date);
		validateParamNotNull(date);
		return matchToRegex(date, DATE_REGEX);
	}

	public static boolean validatePositiveId(String value) {
		LOGGER.info("Process validation positive ID {}", value);
		validateParamNotNull(value);
		if (matchToRegex(value, POSITIVE_NUMBER_REGEX)) {
			try {
				Long.valueOf(value);
			} catch (NumberFormatException e) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	private static boolean matchToRegexCaseIns(String input, String regex) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(input);
		return m.matches();
	}

	private static boolean matchToRegex(String input, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		return m.matches();
	}

	public static String sendAtrributeInvalidMessage(HttpServletRequest request, String attributeName, String message) {
		LOGGER.info("Create message attribute with name: {}", message);
		request.getSession().setAttribute(attributeName, message);
		return PERSONAL_DATA_VIEW.getUrl();
	}

	public static void removeAttributeInvalideMessage(HttpServletRequest request, String message) {
		LOGGER.info("Remove message attribute with name: {}", message);
		request.getSession().removeAttribute(message);
	}

}
