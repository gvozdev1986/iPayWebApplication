package by.htp.hvozdzeu.web.util;

public final class PagePathConstantPool {

	private PagePathConstantPool() {

	}	
		
	public static final String REDIRECT_ADMIN_URL = "/ServletController?command=admin_panel_view";
	public static final String REDIRECT_USER_URL = "/ServletController?command=client_panel_view";
	public static final String REDIRECT_GUEST_URL = "/ServletController?command=redirect_guest";
	public static final String REDIRECT_PAYMENT_DATA_LIST = "/ServletController?command=list_services_view";
    public static final String REDIRECT_REGISTRATION_FORM = "/ServletController?command=login_page_view";
    public static final String REDIRECT_LIST_CARD_CLIENT = "/ServletController?command=credit_card_view";
    public static final String REDIRECT_PERSONAL_DATA_VIEW = "/ServletController?command=personal_data_view";
    public static final String REDIRECT_SAVE_PAY_PAYMENT = "/ServletController?command=payment_service_view";
    public static final String REDIRECT_SAVE_TRANSFER = "/ServletController?command=transfer_view";
    public static final String REDIRECT_UPDATE_CLIENT_PSWD = "/ServletController?command=update_client_password_view";
    public static final String REDIRECT_LIST_MESSAGE = "/ServletController?command=list_message_view";
    public static final String REDIRECT_MESSAGE_DETAIL = "/ServletController?command=view_detail_message";

    public static final String PAYMENT_HISTORY_PAGINATION = "/WEB-INF/payment_history_pagination.jsp";
    public static final String MAIL_SENDER_VIEW = "/WEB-INF/mail_sender_view.jsp";
    public static final String LOAD_ADMIN_PANEL = "/WEB-INF/admin_panel.jsp";
    public static final String LOAD_CLIENT_PANEL = "/WEB-INF/user_panel.jsp";
    public static final String MESSAGE_LIST_VIEW = "/WEB-INF/list_message_view.jsp";
    public static final String PAYMENT_DATA_DETAIL_VIEW = "/WEB-INF/payment_data_detail_view.jsp";
    public static final String NEW_SERVICE_DATA_VIEW = "/WEB-INF/add_payment_data.jsp";
    public static final String LIST_SERVICE_VIEW = "/WEB-INF/list_payment_data_view.jsp";
	public static final String GREETING_PAGE_VIEW = "/WEB-INF/greeting.jsp";
	public static final String LOGIN_PAGE_VIEW = "/WEB-INF/login.jsp";
	public static final String LIST_CLIENT_VIEW = "/WEB-INF/list_user_view.jsp";
	public static final String CLIENT_DETAIL_VIEW = "/WEB-INF/view_detail_user.jsp";
	public static final String PAYMENT_HISTORY_VIEW = "/WEB-INF/list_payment_history.jsp";
	public static final String CREDIT_CARD_VIEW = "/WEB-INF/cards.jsp";
	public static final String PAYMENT_SERVICE_VIEW = "/WEB-INF/payment_service.jsp";
	public static final String TRANSFER_VIEW = "/WEB-INF/card_money_transfer.jsp";
	public static final String PERSONAL_DATA_VIEW = "/WEB-INF/user_personal_data.jsp";
	public static final String UPDATE_CLIENT_PSWD_VIEW = "/WEB-INF/change_password.jsp";
	public static final String DETAIL_BLOCKED_CARD = "/WEB-INF/detail_blocked_card.jsp";
	public static final String LIST_CARD_VIEW = "/WEB-INF/list_card_view.jsp";
	public static final String REGISTRATION_PAGE_VIEW = "/WEB-INF/registration.jsp";
	public static final String INDEX_PAGE_LOAD = "/index.jsp";
	public static final String BLOCKED_CREDIT_CARDS = "/WEB-INF/list_blocked_cards.jsp";
    public static final String PAGE_ERROR = "/error/error.jsp";
    public static final String MESSAGE_DETAIL_VIEW = "/WEB-INF/message_detail_view.jsp";

}
