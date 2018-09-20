package by.htp.hvozdzeu.web.command.impl.user;

import by.htp.hvozdzeu.model.User;
import by.htp.hvozdzeu.service.UserService;
import by.htp.hvozdzeu.service.factory.ServiceFactory;
import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.exception.CommandException;
import by.htp.hvozdzeu.web.util.PagePathConstantPool;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.*;

public class SaveChangePersonalAdminDataCommandImpl implements BaseCommand {

    private static final String MSG_SUCCESS = "Update was successful.";
    private static final String MSG_UPDATE_CLIENT_DATA = "messageUpdateClient";
    private UserService userService = ServiceFactory.getUserService();

    @Override
    public String executeCommand(HttpServletRequest request) throws CommandException {

        Long clientId = Long.valueOf(request.getParameter(REQUEST_PARAM_CLIENT_ID));
        String firstName = request.getParameter(REQUEST_PARAM_FIRST_NAME);
        String lastName = request.getParameter(REQUEST_PARAM_LAST_NAME);
        String patronymic = request.getParameter(REQUEST_PARAM_PATRONYMIC);
        LocalDate dateBirth = Date.valueOf(request.getParameter(REQUEST_PARAM_DATE_BIRTH)).toLocalDate();
        String homePhone = request.getParameter(REQUEST_PARAM_HOME_PHONE);
        String mobilePhone = request.getParameter(REQUEST_PARAM_MOBILE_PHONE);
        String email = request.getParameter(REQUEST_PARAM_EMAIL);
        String address = request.getParameter(REQUEST_PARAM_ADDRESS);


        User user = User.getBuilder()
                .firstName(firstName)
                .lastName(lastName)
                .patronymic(patronymic)
                .dateBirth(dateBirth)
                .phoneHome(homePhone)
                .phoneMobile(mobilePhone)
                .email(email)
                .address(address)
                .build();

        userService.update(user, clientId);

        User clientUpdate = userService.findById(clientId);

        request.getSession().setAttribute(REQUEST_PARAM_USER, clientUpdate);
        request.getSession().setAttribute(MSG_UPDATE_CLIENT_DATA, MSG_SUCCESS);
        return PagePathConstantPool.REDIRECT_ADMIN_PERSONAL_DATA_VIEW;


    }

}
