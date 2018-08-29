package by.htp.hvozdzeu.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.hvozdzeu.web.command.impl.authorization.Authorization;
import by.htp.hvozdzeu.web.command.impl.authorization.LogOut;
import by.htp.hvozdzeu.web.command.impl.card.BalanceByCardId;
import by.htp.hvozdzeu.web.command.impl.card.BlockCard;
import by.htp.hvozdzeu.web.command.impl.card.FindBlockedCardParameters;
import by.htp.hvozdzeu.web.command.impl.card.FindCardByParameters;
import by.htp.hvozdzeu.web.command.impl.card.PaymentHistory;
import by.htp.hvozdzeu.web.command.impl.card.SavePayPayment;
import by.htp.hvozdzeu.web.command.impl.card.SaveTransfer;
import by.htp.hvozdzeu.web.command.impl.card.UnblockCard;
import by.htp.hvozdzeu.web.command.impl.client.FindClientByParameters;
import by.htp.hvozdzeu.web.command.impl.client.SaveChangePersonalData;
import by.htp.hvozdzeu.web.command.impl.client.UpdatePassword;
import by.htp.hvozdzeu.web.command.impl.locale.Localization;
import by.htp.hvozdzeu.web.command.impl.message.WriteUs;
import by.htp.hvozdzeu.web.command.impl.registration.SaveRegistration;
import by.htp.hvozdzeu.web.command.impl.service.ReplyEmail;
import by.htp.hvozdzeu.web.command.impl.view.AdminPanelView;
import by.htp.hvozdzeu.web.command.impl.view.BlockedCreditCardsView;
import by.htp.hvozdzeu.web.command.impl.view.ClientPanelView;
import by.htp.hvozdzeu.web.command.impl.view.CreditCardView;
import by.htp.hvozdzeu.web.command.impl.view.DetailBlockedCard;
import by.htp.hvozdzeu.web.command.impl.view.GreetingPageView;
import by.htp.hvozdzeu.web.command.impl.view.ListCardView;
import by.htp.hvozdzeu.web.command.impl.view.ListClientView;
import by.htp.hvozdzeu.web.command.impl.view.LoginPageView;
import by.htp.hvozdzeu.web.command.impl.view.PaymenServiceView;
import by.htp.hvozdzeu.web.command.impl.view.PaymentHistoryView;
import by.htp.hvozdzeu.web.command.impl.view.PersonalDataView;
import by.htp.hvozdzeu.web.command.impl.view.RegistrationFormView;
import by.htp.hvozdzeu.web.command.impl.view.TransferServiceView;
import by.htp.hvozdzeu.web.command.impl.view.UpdateClientPasswordView;

import java.util.HashMap;
import java.util.Map;

public class CommandDirector {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandDirector.class);

    private Map<String, Command> map = new HashMap<>();

    public CommandDirector() { 
        map.put("greeting_page_view", new GreetingPageView());
        map.put("login_page_view", new LoginPageView());
        map.put("authorization_user", new Authorization());
        map.put("admin_panel_view", new AdminPanelView());
        map.put("client_panel_view", new ClientPanelView());
        map.put("get_balance_by_card_id", new BalanceByCardId());      
        map.put("payment_history", new PaymentHistory());
        map.put("block_card", new BlockCard());
        map.put("unblock_card", new UnblockCard());
        map.put("save_chaged_personal_data", new SaveChangePersonalData());
        map.put("update_password", new UpdatePassword());
        map.put("save_message_contact", new WriteUs());
        map.put("save_transfer", new SaveTransfer());
        map.put("registration_page_view", new RegistrationFormView());
        map.put("save_registration", new SaveRegistration());
        map.put("log_out", new LogOut());
        map.put("save_pay_payment", new SavePayPayment());
        map.put("get_balance", new BalanceByCardId());
        map.put("send_message", new ReplyEmail());
        map.put("change_locale", new Localization());
        map.put("credit_card_view", new CreditCardView());
        map.put("payment_history_view", new PaymentHistoryView());
        map.put("payment_service_view", new PaymenServiceView());
        map.put("transfer_view", new TransferServiceView());
        map.put("personal_data_view", new PersonalDataView());
        map.put("update_client_password_view", new UpdateClientPasswordView());
        map.put("blocked_credit_cards_view", new BlockedCreditCardsView());
        map.put("find_bloked_card_by_param", new FindBlockedCardParameters());
        map.put("view_detail_blocked_card", new DetailBlockedCard());
        map.put("list_card_view", new ListCardView());
        map.put("find_card_by_param", new FindCardByParameters());
        map.put("find_client_by_param", new FindClientByParameters());
        map.put("list_client_view", new ListClientView());
    }

    public Command getCommand(String commandName) {
        LOGGER.info("Getting command: {}", commandName);
        Command command;
        command = map.get(commandName);
        return command;
    }

}
