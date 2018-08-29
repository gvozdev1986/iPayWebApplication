package by.htp.hvozdzeu.web.util;

public enum RedirectPageUrl {
	
	//LOAD VIEWS
	ADMIN_PANEL_VIEW("/WEB-INF/admin_panel.jsp"),
	CLIENT_PANEL_VIEW("/WEB-INF/client_panel.jsp"),
	GREETING_PAGE_VIEW("/WEB-INF/greeting.jsp"),
	LOGIN_PAGE_VIEW("/WEB-INF/login.jsp"),
	
	//CLIENT_MESSAGES
	CLIENT_MESSAGES("/WEB-INF/messages.jsp"),
	LIST_CLIENT_VIEW("/WEB-INF/list_client_view.jsp"),
		
	//MENU
	PAYMENT_HISTORY_VIEW("/WEB-INF/payment_history.jsp"),
	CREDIT_CARD_VIEW("/WEB-INF/cards.jsp"),
	PAYMENT_SERVICE_VIEW("/WEB-INF/payment_service.jsp"),
	TRANSFER_VIEW("/WEB-INF/card_money_transfer.jsp"),
	PERSONAL_DATA_VIEW("/WEB-INF/client_personal_data.jsp"),
	UPDATE_CLIENT_PASSWORD_VIEW("/WEB-INF/change_password.jsp"),
	
	//ERORR
	SESSION_TIME_OUT("/WEB-INF/error/session_timeout.jsp"),
	
	//DETAIL BLOCKED CARD
	DETAIL_BLOCKED_CARD("/WEB-INF/detail_blocked_card.jsp"),
	LIST_CARD_VIEW("/WEB-INF/list_card_view.jsp"),
	
	//REGISTRATION
	REGISTRATION_PAGE_VIEW("/WEB-INF/registration.jsp"),
	
	//LOAD PAGES
	INDEX_PAGE_LOAD("/index.jsp"),
	
	//CREDIT CARDS ADMIN VIEW
	BLOCKED_CREDIT_CARDS("/WEB-INF/blocked_cards.jsp");
	
	private String url;
	
	private RedirectPageUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	

}
