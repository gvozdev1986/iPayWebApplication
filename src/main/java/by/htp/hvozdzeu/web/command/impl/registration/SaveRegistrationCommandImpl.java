package by.htp.hvozdzeu.web.command.impl.registration;

import by.htp.hvozdzeu.dao.util.RebasePassword;
import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.IUserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static by.htp.hvozdzeu.web.util.HttpRequestParamValidator.*;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class SaveRegistrationCommandImpl implements BaseCommand, Serializable {

    private static final long serialVersionUID = 5303125927072996595L;

    private IUserService iUserService = ServiceFactory.getUserService();
    private RebasePassword rebasePassword = new RebasePassword();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        String verifyPswd = request.getParameter("password_verify");

        Map<String, String> validateErrorMap = new HashMap<>();
        Map<String, String> validateReturnData = new HashMap<>();

        String username = request.getParameter("username");
        User userCheckLogin = iUserService.findByLogin(username);

        if(userCheckLogin == null){
            if (!validateLogin(request.getParameter(REQUEST_PARAM_LOGIN))) {
                validateErrorMap.put("loginValidateError", "Incorrect login.");
                validateReturnData.put("returnLoginValidateError", request.getParameter(REQUEST_PARAM_LOGIN));
            } else {
                validateReturnData.put("returnLoginValidateError", request.getParameter(REQUEST_PARAM_LOGIN));
            }
        } else {
            validateErrorMap.put("loginValidateError", "This login is already taken.");
            validateReturnData.put("returnLoginValidateError", request.getParameter(REQUEST_PARAM_LOGIN));
        }

        if (!validatePassword(request.getParameter(REQUEST_PARAM_PASS))) {
            validateErrorMap.put("passwordValidateError", "Incorrect password.");
            validateReturnData.put("returnPasswordValidateError", request.getParameter(REQUEST_PARAM_PASS));
        }

        if (!verifyPswd.equals(request.getParameter(REQUEST_PARAM_PASS))) {
            validateErrorMap.put("passwordVerifyValidateError", "Passwords do not match.");
        }

        if (validateLastMiddleFirstName(request.getParameter(REQUEST_PARAM_FIRST_NAME))) {
            validateErrorMap.put("firstNameValidateError", "Incorrect first name.");
            validateReturnData.put("returnFirstNameValidateError", request.getParameter(REQUEST_PARAM_FIRST_NAME));
        } else {
            validateReturnData.put("returnFirstNameValidateError", request.getParameter(REQUEST_PARAM_FIRST_NAME));
        }

        if (validateLastMiddleFirstName(request.getParameter(REQUEST_PARAM_LAST_NAME))) {
            validateErrorMap.put("lastNameValidateError", "Incorrect last name.");
            validateReturnData.put("returnLastNameValidateError", request.getParameter(REQUEST_PARAM_LAST_NAME));
        } else {
            validateReturnData.put("returnLastNameValidateError", request.getParameter(REQUEST_PARAM_LAST_NAME));
        }

        if (validateLastMiddleFirstName(request.getParameter(REQUEST_PARAM_PATRONYMIC))) {
            validateErrorMap.put("patronymicValidateError", "Incorrect patronymic.");
            validateReturnData.put("returnPatronymicValidateError", request.getParameter(REQUEST_PARAM_PATRONYMIC));
        } else {
            validateReturnData.put("returnPatronymicValidateError", request.getParameter(REQUEST_PARAM_PATRONYMIC));
        }

        if (!validateDate(request.getParameter(REQUEST_PARAM_DATE_BIRTH))) {
            validateErrorMap.put("dateBirthValidateError", "Incorrect date of birth.");
            validateReturnData.put("returnDateBirthValidateError", request.getParameter(REQUEST_PARAM_DATE_BIRTH));
        } else {
            validateReturnData.put("returnDateBirthValidateError", request.getParameter(REQUEST_PARAM_DATE_BIRTH));
        }

        if (validatePhone(request.getParameter(REQUEST_PARAM_HOME_PHONE))) {
            validateErrorMap.put("homePhoneValidateError", "Incorrect home phone.");
            validateReturnData.put("returnHomePhoneValidateError", request.getParameter(REQUEST_PARAM_HOME_PHONE));
        } else {
            validateReturnData.put("returnHomePhoneValidateError", request.getParameter(REQUEST_PARAM_HOME_PHONE));
        }

        if (validatePhone(request.getParameter(REQUEST_PARAM_MOBILE_PHONE))) {
            validateErrorMap.put("mobilePhoneValidateError", "Incorrect mobile phone.");
            validateReturnData.put("returnMobilePhoneValidateError", request.getParameter(REQUEST_PARAM_MOBILE_PHONE));
        } else {
            validateReturnData.put("returnMobilePhoneValidateError", request.getParameter(REQUEST_PARAM_MOBILE_PHONE));
        }

        if (!validateAddress(request.getParameter(REQUEST_PARAM_ADDRESS))) {
            validateErrorMap.put("addressValidateError", "Incorrect address.");
            validateReturnData.put("returnAddressValidateError", request.getParameter(REQUEST_PARAM_ADDRESS));
        } else {
            validateReturnData.put("returnAddressValidateError", request.getParameter(REQUEST_PARAM_ADDRESS));
        }

        if (!validateEmail(request.getParameter(REQUEST_PARAM_EMAIL))) {
            validateErrorMap.put("emailValidateError", "Incorrect email.");
            validateReturnData.put("returnEmailValidateError", request.getParameter(REQUEST_PARAM_EMAIL));
        } else {
            validateReturnData.put("returnEmailValidateError", request.getParameter(REQUEST_PARAM_EMAIL));
        }

        if (validateErrorMap.size() == 0) {

            User user = new User.Builder()
                    .login(request.getParameter(REQUEST_PARAM_LOGIN))
                    .password(rebasePassword.rebasePSWD(request.getParameter(REQUEST_PARAM_PASS)))
                    .firstName(request.getParameter(REQUEST_PARAM_FIRST_NAME))
                    .lastName(request.getParameter(REQUEST_PARAM_LAST_NAME))
                    .patronymic(request.getParameter(REQUEST_PARAM_PATRONYMIC))
                    .dateBirth(Date.valueOf(request.getParameter(REQUEST_PARAM_DATE_BIRTH)).toLocalDate())
                    .phoneHome(request.getParameter(REQUEST_PARAM_HOME_PHONE))
                    .phoneMobile(request.getParameter(REQUEST_PARAM_MOBILE_PHONE))
                    .address(request.getParameter(REQUEST_PARAM_ADDRESS))
                    .email(request.getParameter(REQUEST_PARAM_EMAIL))
                    .build();

            iUserService.create(user);
            return PagePathConstantPool.REDIRECT_REGISTRATION_FORM;

        } else {

            request.getSession().setAttribute("validateErrorMap", validateErrorMap);
            request.getSession().setAttribute("returnValidateErrorMap", validateReturnData);
            return PagePathConstantPool.REGISTRATION_PAGE_VIEW;

        }
    }
}
