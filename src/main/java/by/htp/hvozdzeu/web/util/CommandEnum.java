package by.htp.hvozdzeu.web.util;

public enum CommandEnum {

    /**
     * Guest access
     */
	LOAD_GREETING_PAGE(UserTypeEnum.ALL),
	CHANGE_LOCALE(UserTypeEnum.ALL),
    REGISTRATION_PAGE_VIEW(UserTypeEnum.ALL),
    SAVE_REGISTRATION(UserTypeEnum.ALL),
    LOGIN_PAGE_VIEW(UserTypeEnum.ALL),
    AUTHORIZATION_USER(UserTypeEnum.ALL),
    REDIRECT_GUEST(UserTypeEnum.ALL),
    SAVE_MESSAGE_CONTACT(UserTypeEnum.ALL),
    LOG_OUT(UserTypeEnum.ALL),
    CHECK_NEW_ACCOUNT(UserTypeEnum.ALL),

    /**
     * User (client) access
     */
	REDIRECT_USER_URL(UserTypeEnum.USER),
    CLIENT_PANEL_VIEW(UserTypeEnum.USER),
    CREDIT_CARD_VIEW(UserTypeEnum.USER),
    GET_BALANCE(UserTypeEnum.USER),
    BLOCK_CARD(UserTypeEnum.USER),
    PAYMENT_HISTORY_VIEW(UserTypeEnum.USER),
    PAYMENT_HISTORY(UserTypeEnum.USER),
    PAYMENT_SERVICE_VIEW(UserTypeEnum.USER),
    //TRANSFER_VIEW(UserTypeEnum.USER),
    PERSONAL_DATA_VIEW(UserTypeEnum.USER),
    UPDATE_CLIENT_PASSWORD_VIEW(UserTypeEnum.USER),
    SAVE_CHANGED_PERSONAL_DATA(UserTypeEnum.USER),
    UPDATE_PASSWORD(UserTypeEnum.USER),
    SAVE_PAY_PAYMENT(UserTypeEnum.USER),
    SAVE_TRANSFER(UserTypeEnum.USER),
    PAYMENT_HISTORY_PAGINATION(UserTypeEnum.USER),
    NEW_CREDIT_CARD(UserTypeEnum.USER),
    SAVE_NEW_CREDIT_CARD(UserTypeEnum.USER),
    SUCCESS_REGISTRATION_VIEW(UserTypeEnum.USER),
    REPORT_PAYMENT_HISTORY_EMAIL(UserTypeEnum.USER),
    SAVE_TRANSFER_HIS_CREDIT_CARD(UserTypeEnum.USER),
    SAVE_TRANSFER_ALIEN_CREDIT_CARD(UserTypeEnum.USER),
    TRANSFER_VIEW_HIS_CARD(UserTypeEnum.USER),
    TRANSFER_VIEW_ALIEN_CARD(UserTypeEnum.USER),

    /**
     * Admin access
     */
	REDIRECT_ADMIN_URL(UserTypeEnum.ADMIN),
    ADMIN_PANEL_VIEW(UserTypeEnum.ADMIN),
    LIST_CARD_VIEW(UserTypeEnum.ADMIN),
    LIST_CLIENT_VIEW(UserTypeEnum.ADMIN),
    VIEW_DETAIL_CLIENT(UserTypeEnum.ADMIN),
    BLOCKED_CREDIT_CARDS_VIEW(UserTypeEnum.ADMIN),
    VIEW_DETAIL_BLOCKED_CARD(UserTypeEnum.ADMIN),
    UNBLOCK_CARD(UserTypeEnum.ADMIN),
    FIND_BLOCKED_CARD_BY_PARAM(UserTypeEnum.ADMIN),
    FIND_CLIENT_BY_PARAM(UserTypeEnum.ADMIN),
    FIND_CARD_BY_PARAM(UserTypeEnum.ADMIN),
    LIST_MESSAGE_VIEW(UserTypeEnum.ADMIN),
    LIST_SERVICES_VIEW(UserTypeEnum.ADMIN),
    PAYMENT_DATA_DETAIL_VIEW(UserTypeEnum.ADMIN),
    NEW_SERVICE_DATA_VIEW(UserTypeEnum.ADMIN),
    FIND_SERVICE_DATA_BY_PARAM(UserTypeEnum.ADMIN),
    SAVE_PAYMENT_DATA(UserTypeEnum.ADMIN),
    VIEW_DETAIL_MESSAGE(UserTypeEnum.ADMIN),
    SAVE_MESSAGE_READ(UserTypeEnum.ADMIN),
    REPLY_EMAIL(UserTypeEnum.ADMIN),
    MAIL_SENDER_VIEW(UserTypeEnum.ADMIN),
    BACK_DETAIL_MESSAGE(UserTypeEnum.ADMIN),
    ADMIN_PERSONAL_DATA_VIEW(UserTypeEnum.ADMIN),
    UPDATE_ADMIN_PASSWORD_VIEW(UserTypeEnum.ADMIN),
    SAVE_CHANGED_ADMIN_PERSONAL_DATA(UserTypeEnum.ADMIN),
    UPDATE_ADMIN_PASSWORD(UserTypeEnum.ADMIN),
    BACK_LIST_MESSAGE_VIEW(UserTypeEnum.ADMIN);


	private UserTypeEnum userType;

	CommandEnum(UserTypeEnum userType) {
		this.userType = userType;
	}

	public UserTypeEnum getUserType() {
		return userType;
	}
	
	public static CommandEnum valueOfOrDefault(String value) {
		for(CommandEnum command: CommandEnum.class.getEnumConstants()) {
			if(command.name().equals(value)) {
				return command;
			}
		}
		return CommandEnum.LOAD_GREETING_PAGE;
	}

}
