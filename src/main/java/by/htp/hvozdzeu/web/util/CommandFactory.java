package by.htp.hvozdzeu.web.util;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.command.impl.authorization.LogInCommandImpl;
import by.htp.hvozdzeu.web.command.impl.authorization.LogOutCommandImpl;
import by.htp.hvozdzeu.web.command.impl.card.*;
import by.htp.hvozdzeu.web.command.impl.locale.LocalizationCommandImpl;
import by.htp.hvozdzeu.web.command.impl.message.ReplyEmailCommandImpl;
import by.htp.hvozdzeu.web.command.impl.message.BackDetailMessageCommandImpl;
import by.htp.hvozdzeu.web.command.impl.message.CheckReadMessageCommandImpl;
import by.htp.hvozdzeu.web.command.impl.message.WriteUsCommandImpl;
import by.htp.hvozdzeu.web.command.impl.registration.SaveRegistrationCommandImpl;
import by.htp.hvozdzeu.web.command.impl.servicedata.FindPaymentDataByParamCommandImpl;
import by.htp.hvozdzeu.web.command.impl.servicedata.SavePaymentDataCommandImpl;
import by.htp.hvozdzeu.web.command.impl.user.*;
import by.htp.hvozdzeu.web.command.impl.view.*;
import by.htp.hvozdzeu.web.controller.ServletController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.COMMAND_START_PAGE;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.REQUEST_PARAM_COMMAND;

public class CommandFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletController.class);

    private CommandFactory() {

    }

    public static BaseCommand defineCommand(HttpServletRequest request) {

        BaseCommand command;
        String inputCommand = request.getParameter(REQUEST_PARAM_COMMAND);

        if (inputCommand == null || inputCommand.isEmpty()) {
            inputCommand = COMMAND_START_PAGE;
        }
        CommandEnum commandName = CommandEnum.valueOfOrDefault(inputCommand.toUpperCase());

        LOGGER.debug("GET COMMAND: {}", commandName);

        switch (commandName) {
            case LOAD_GREETING_PAGE:
                command = new GreetingPageViewCommandImpl();
                break;
            case LOG_OUT:
                command = new LogOutCommandImpl();
                break;
            case LOGIN_PAGE_VIEW:
                command = new LoginPageViewCommandImpl();
                break;
            case REDIRECT_USER_URL:
                command = new UserPanelViewCommandImpl();
                break;
            case REDIRECT_ADMIN_URL:
                command = new AdminPanelViewCommandImpl();
                break;
            case CHANGE_LOCALE:
                command = new LocalizationCommandImpl();
                break;
            case REGISTRATION_PAGE_VIEW:
                command = new RegistrationFormViewCommandImpl();
                break;
            case SAVE_REGISTRATION:
                command = new SaveRegistrationCommandImpl();
                break;
            case AUTHORIZATION_USER:
                command = new LogInCommandImpl();
                break;
            case ADMIN_PANEL_VIEW:
                command = new AdminPanelViewCommandImpl();
                break;
            case CLIENT_PANEL_VIEW:
                command = new UserPanelViewCommandImpl();
                break;
            case REDIRECT_GUEST:
                command = new GreetingPageViewCommandImpl();
                break;
            case LIST_CARD_VIEW:
                command = new ListCardViewCommandImpl();
                break;
            case LIST_CLIENT_VIEW:
                command = new ListUserViewCommandImpl();
                break;
            case VIEW_DETAIL_CLIENT:
                command = new UserDetailViewCommandImpl();
                break;
            case BLOCKED_CREDIT_CARDS_VIEW:
                command = new BlockedCreditCardsViewCommandImpl();
                break;
            case VIEW_DETAIL_BLOCKED_CARD:
                command = new DetailBlockedCardCommandImpl();
                break;
            case UNBLOCK_CARD:
                command = new UnblockCardCommandImpl();
                break;
            case FIND_BLOCKED_CARD_BY_PARAM:
                command = new FindBlockedCardParametersCommandImpl();
                break;
            case FIND_CLIENT_BY_PARAM:
                command = new FindClientByParametersCommandImpl();
                break;
            case FIND_CARD_BY_PARAM:
                command = new FindCardByParametersCommandImpl();
                break;
            case CREDIT_CARD_VIEW:
                command = new CreditCardViewCommandImpl();
                break;
            case GET_BALANCE:
                command = new BalanceByCardIdCommandImpl();
                break;
            case BLOCK_CARD:
                command = new BlockCardCommandImpl();
                break;
            case PAYMENT_HISTORY_VIEW:
                command = new PaymentHistoryViewCommandImpl();
                break;
            case PAYMENT_HISTORY:
                command = new PaymentHistoryCommandImpl();
                break;
            case PAYMENT_SERVICE_VIEW:
                command = new PaymentServiceViewCommandImpl();
                break;
            case TRANSFER_VIEW:
                command = new TransferServiceViewCommandImpl();
                break;
            case UPDATE_CLIENT_PASSWORD_VIEW:
                command = new UpdateClientPasswordViewCommandImpl();
                break;
            case UPDATE_PASSWORD:
                command = new UpdatePasswordCommandImpl();
                break;
            case PERSONAL_DATA_VIEW:
                command = new PersonalDataViewCommandImpl();
                break;
            case SAVE_CHANGED_PERSONAL_DATA:
                command = new SaveChangePersonalDataCommandImpl();
                break;
            case LIST_MESSAGE_VIEW:
                command = new ListMessageViewCommandImpl();
                break;
            case SAVE_MESSAGE_CONTACT:
                command = new WriteUsCommandImpl();
                break;
            case LIST_SERVICES_VIEW:
                command = new ListPaymentServiceViewCommandImpl();
                break;
            case PAYMENT_DATA_DETAIL_VIEW:
                command = new PaymentDataDetailViewCommandImpl();
                break;
            case NEW_SERVICE_DATA_VIEW:
                command = new NewServiceDataCommandImpl();
                break;
            case FIND_SERVICE_DATA_BY_PARAM:
                command = new FindPaymentDataByParamCommandImpl();
                break;
            case SAVE_PAYMENT_DATA:
                command = new SavePaymentDataCommandImpl();
                break;
            case MAIL_SENDER_VIEW:
                command = new MailSenderViewCommandImpl();
                break;
            case SAVE_PAY_PAYMENT:
                command = new SavePayPaymentCommandImpl();
                break;
            case SAVE_TRANSFER:
                command = new SaveTransferCommandImpl();
                break;
            case VIEW_DETAIL_MESSAGE:
                command = new MessageDetailViewCommandImpl();
                break;
            case SAVE_MESSAGE_READ:
                command = new CheckReadMessageCommandImpl();
                break;
            case REPLY_EMAIL:
                command = new ReplyEmailCommandImpl();
                break;
            case BACK_DETAIL_MESSAGE:
                command = new BackDetailMessageCommandImpl();
                break;
            case PAYMENT_HISTORY_PAGINATION:
                command = new PaymentHistoryCommandImpl();
                break;
            case NEW_CREDIT_CARD:
                command = new NewCreditCardCommandImpl();
                break;
            case SAVE_NEW_CREDIT_CARD:
                command = new InsertNewCreditCardCommandImpl();
                break;
            case SUCCESS_REGISTRATION_VIEW:
                command = new SuccessRegistrationViewCommandImpl();
                break;
            case CHECK_NEW_ACCOUNT:
                command = new CheckNewAccountCommandImpl();
                break;


            case SAVE_CHANGED_ADMIN_PERSONAL_DATA:
                command = new SaveChangePersonalAdminDataCommandImpl();
                break;
            case UPDATE_ADMIN_PASSWORD:
                command = new UpdateAdminPasswordCommandImpl();
                break;

            case ADMIN_PERSONAL_DATA_VIEW:
                command = new AdminPersonalDataViewCommandImpl();
                break;
            case UPDATE_ADMIN_PASSWORD_VIEW:
                command = new UpdateAdminPasswordViewCommandImpl();
                break;


            case REPORT_PAYMENT_HISTORY_EMAIL:
                command = new ReportPaymentHistorySendEmailCommandImpl();
                break;

            default:
                command = new GreetingPageViewCommandImpl();
                break;
        }
        return command;
    }
}
