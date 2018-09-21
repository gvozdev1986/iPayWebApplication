package by.htp.hvozdzeu.web.util;

import by.htp.hvozdzeu.web.command.BaseCommand;
import by.htp.hvozdzeu.web.command.impl.authorization.LogInCommandImpl;
import by.htp.hvozdzeu.web.command.impl.authorization.LogOutCommandImpl;
import by.htp.hvozdzeu.web.command.impl.card.*;
import by.htp.hvozdzeu.web.command.impl.locale.LocalizationCommandImpl;
import by.htp.hvozdzeu.web.command.impl.message.BackDetailMessageCommandImpl;
import by.htp.hvozdzeu.web.command.impl.message.CheckReadMessageCommandImpl;
import by.htp.hvozdzeu.web.command.impl.message.ReplyEmailCommandImpl;
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
import java.util.EnumMap;
import java.util.Map;

import static by.htp.hvozdzeu.web.util.CommandEnum.*;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.COMMAND_START_PAGE;
import static by.htp.hvozdzeu.web.util.WebConstantDeclaration.REQUEST_PARAM_COMMAND;

public class CommandFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletController.class);
    private static final Map<CommandEnum, Object> commandFactoryMap = new EnumMap<>(CommandEnum.class);

    static {
        commandFactoryMap.put(LOAD_GREETING_PAGE, new GreetingPageViewCommandImpl());
        commandFactoryMap.put(LOG_OUT, new LogOutCommandImpl());
        commandFactoryMap.put(LOGIN_PAGE_VIEW, new LoginPageViewCommandImpl());
        commandFactoryMap.put(REDIRECT_USER_URL, new UserPanelViewCommandImpl());
        commandFactoryMap.put(REDIRECT_ADMIN_URL, new AdminPanelViewCommandImpl());
        commandFactoryMap.put(CHANGE_LOCALE, new LocalizationCommandImpl());
        commandFactoryMap.put(REGISTRATION_PAGE_VIEW, new RegistrationFormViewCommandImpl());
        commandFactoryMap.put(SAVE_REGISTRATION, new SaveRegistrationCommandImpl());
        commandFactoryMap.put(AUTHORIZATION_USER, new LogInCommandImpl());
        commandFactoryMap.put(ADMIN_PANEL_VIEW, new AdminPanelViewCommandImpl());
        commandFactoryMap.put(CLIENT_PANEL_VIEW, new UserPanelViewCommandImpl());
        commandFactoryMap.put(REDIRECT_GUEST, new GreetingPageViewCommandImpl());
        commandFactoryMap.put(LIST_CARD_VIEW, new ListCardViewCommandImpl());
        commandFactoryMap.put(LIST_CLIENT_VIEW, new ListUserViewCommandImpl());
        commandFactoryMap.put(VIEW_DETAIL_CLIENT, new UserDetailViewCommandImpl());
        commandFactoryMap.put(BLOCKED_CREDIT_CARDS_VIEW, new BlockedCreditCardsViewCommandImpl());
        commandFactoryMap.put(VIEW_DETAIL_BLOCKED_CARD, new DetailBlockedCardCommandImpl());
        commandFactoryMap.put(UNBLOCK_CARD, new UnblockCardCommandImpl());
        commandFactoryMap.put(FIND_BLOCKED_CARD_BY_PARAM, new FindBlockedCardParametersCommandImpl());
        commandFactoryMap.put(FIND_CLIENT_BY_PARAM, new FindClientByParametersCommandImpl());
        commandFactoryMap.put(FIND_CARD_BY_PARAM, new FindCardByParametersCommandImpl());
        commandFactoryMap.put(CREDIT_CARD_VIEW, new CreditCardViewCommandImpl());
        commandFactoryMap.put(GET_BALANCE, new BalanceByCardIdCommandImpl());
        commandFactoryMap.put(BLOCK_CARD, new BlockCardCommandImpl());
        commandFactoryMap.put(PAYMENT_HISTORY_VIEW, new PaymentHistoryViewCommandImpl());
        commandFactoryMap.put(PAYMENT_HISTORY, new PaymentHistoryCommandImpl());
        commandFactoryMap.put(PAYMENT_SERVICE_VIEW, new PaymentServiceViewCommandImpl());
        commandFactoryMap.put(UPDATE_CLIENT_PASSWORD_VIEW, new UpdateClientPasswordViewCommandImpl());
        commandFactoryMap.put(UPDATE_PASSWORD, new UpdatePasswordCommandImpl());
        commandFactoryMap.put(PERSONAL_DATA_VIEW, new PersonalDataViewCommandImpl());
        commandFactoryMap.put(SAVE_CHANGED_PERSONAL_DATA, new SaveChangePersonalDataCommandImpl());
        commandFactoryMap.put(LIST_MESSAGE_VIEW, new ListMessageViewCommandImpl());
        commandFactoryMap.put(SAVE_MESSAGE_CONTACT, new WriteUsCommandImpl());
        commandFactoryMap.put(LIST_SERVICES_VIEW, new ListPaymentServiceViewCommandImpl());
        commandFactoryMap.put(PAYMENT_DATA_DETAIL_VIEW, new PaymentDataDetailViewCommandImpl());
        commandFactoryMap.put(NEW_SERVICE_DATA_VIEW, new NewServiceDataCommandImpl());
        commandFactoryMap.put(FIND_SERVICE_DATA_BY_PARAM, new FindPaymentDataByParamCommandImpl());
        commandFactoryMap.put(SAVE_PAYMENT_DATA, new SavePaymentDataCommandImpl());
        commandFactoryMap.put(MAIL_SENDER_VIEW, new MailSenderViewCommandImpl());
        commandFactoryMap.put(SAVE_PAY_PAYMENT, new SavePayPaymentCommandImpl());
        commandFactoryMap.put(TRANSFER_VIEW_HIS_CARD, new TransferServiceHisViewCommandImpl());
        commandFactoryMap.put(TRANSFER_VIEW_ALIEN_CARD, new TransferServiceAlienViewCommandImpl());
        commandFactoryMap.put(SAVE_TRANSFER_HIS_CREDIT_CARD, new SaveTransferHisCreditCardCommandImpl());
        commandFactoryMap.put(SAVE_TRANSFER_ALIEN_CREDIT_CARD, new SaveTransferAlienCreditCardCommandImpl());
        commandFactoryMap.put(UNBLOCK_USER, new UnblockUserCommandImpl());
        commandFactoryMap.put(BLOCK_USER, new BlockUserCommandImpl());
        commandFactoryMap.put(VIEW_DETAIL_MESSAGE, new MessageDetailViewCommandImpl());
        commandFactoryMap.put(SAVE_MESSAGE_READ, new CheckReadMessageCommandImpl());
        commandFactoryMap.put(REPLY_EMAIL, new ReplyEmailCommandImpl());
        commandFactoryMap.put(BACK_DETAIL_MESSAGE, new BackDetailMessageCommandImpl());
        commandFactoryMap.put(PAYMENT_HISTORY_PAGINATION, new PaymentHistoryCommandImpl());
        commandFactoryMap.put(NEW_CREDIT_CARD, new NewCreditCardCommandImpl());
        commandFactoryMap.put(SAVE_NEW_CREDIT_CARD, new InsertNewCreditCardCommandImpl());
        commandFactoryMap.put(SUCCESS_REGISTRATION_VIEW, new SuccessRegistrationViewCommandImpl());
        commandFactoryMap.put(CHECK_NEW_ACCOUNT, new CheckNewAccountCommandImpl());
        commandFactoryMap.put(SAVE_CHANGED_ADMIN_PERSONAL_DATA, new SaveChangePersonalAdminDataCommandImpl());
        commandFactoryMap.put(UPDATE_ADMIN_PASSWORD, new UpdateAdminPasswordCommandImpl());
        commandFactoryMap.put(ADMIN_PERSONAL_DATA_VIEW, new AdminPersonalDataViewCommandImpl());
        commandFactoryMap.put(UPDATE_ADMIN_PASSWORD_VIEW, new UpdateAdminPasswordViewCommandImpl());
        commandFactoryMap.put(REPORT_PAYMENT_HISTORY_EMAIL, new ReportPaymentHistorySendEmailCommandImpl());
        commandFactoryMap.put(USER_LIST, new ListUserViewCommandImpl());
    }

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
        command = (BaseCommand) commandFactoryMap.get(commandName);
        return command;

    }

}
