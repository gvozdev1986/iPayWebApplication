<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="Resource" />
<style>
.sidebar {
	position: fixed;
	top: 0;
	bottom: 0;
	left: 0;
	z-index: 100; /* Behind the navbar */
	padding: 48px 0 0; /* Height of navbar */
	box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);
}

.sidebar-sticky {
	position: relative; 
	top: 0;
	height: calc(100vh - 48px);
	padding-top: .5rem;
	overflow-x: hidden;
	overflow-y: auto;
	/* Scrollable contents if viewport is shorter than content. */
}

@supports ((position: -webkit-sticky ) or (position: sticky )) { .sidebar-sticky
	{ position:-webkit-sticky;
	position: sticky;
}

}
.sidebar .nav-link {
	font-weight: 500;
	color: #333;
}

.sidebar .nav-link .feather {
	margin-right: 4px;
	color: #999;
}

.sidebar .nav-link.active {
	color: #007bff;
}

.sidebar .nav-link:hover .feather, .sidebar .nav-link.active .feather {
	color: inherit;
}

.sidebar-heading {
	font-size: .75rem;
	text-transform: uppercase;
}

[role="main"] {
	padding-top: 48px; /* Space for fixed navbar */
}

.form-control-dark {
	color: #fff;
	background-color: rgba(255, 255, 255, .1);
	border-color: rgba(255, 255, 255, .1);
}

.form-control-dark:focus {
	border-color: transparent;
	box-shadow: 0 0 0 3px rgba(255, 255, 255, .25);
}
</style>
<nav class="col-md-2 d-none d-md-block bg-light sidebar">
	<div class="sidebar-sticky">
		<h6
			class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
			<span>Рассылка</span> <a class="d-flex align-items-center text-muted"
				href="#"><i class="fas fa-envelope-open"></i></a>
		</h6>
		<ul class="nav flex-column">
			<li class="nav-item"><a class="nav-link" href="ServletController?command=list_message_view"
				style="cursor: pointer"><i class="fas fa-envelope"></i>
					Сообщения <sup><span class="badge badge-danger">${countUnreadMessage}</span></sup></a></li>
		</ul>
		<h6	class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted"
			style="cursor: pointer" >
			<span>КАРТЫ</span> <a class="d-flex align-items-center text-muted"><i
				class="fas fa-credit-card"></i></a>
		</h6>
		<div>
			<ul class="nav flex-column mb-2">
				<li class="nav-item"><a class="nav-link"
					href="ServletController?command=blocked_credit_cards_view"
					style="cursor: pointer"> <i class="fas fa-ban"></i> Blocked
						credit card<sup> <span class="badge badge-danger">${countBlockedCreditCard}</span></sup></a></li>
			</ul>
			<ul class="nav flex-column mb-2">
				<li class="nav-item"><a class="nav-link"
					href="ServletController?command=list_card_view"
					style="cursor: pointer"> <i class="fas fa-credit-card"></i>
						List of cards
				</a></li>
			</ul>
		</div>
		<h6	class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted"
			style="cursor: pointer" data-toggle="collapse">
			<span>Клиеты</span> <a class="d-flex align-items-center text-muted"><i
				class="fas fa-users"></i></a>
		</h6>
		<div>
			<ul class="nav flex-column mb-2">
				<li class="nav-item"><a class="nav-link" href="ServletController?command=list_client_view"
					style="cursor: pointer"><i class="fas fa-user-tag"></i> Клиенты
						<sup><span class="badge badge-danger">${countBlockedClients}</span></sup></a></li>
			</ul>
		</div>
		<h6	class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted"
			style="cursor: pointer">
			<span>Справочник</span> <a
				class="d-flex align-items-center text-muted"><i
				class="fas fa-book"></i></a>
		</h6>
		<div>
			<ul class="nav flex-column mb-2">
				<li class="nav-item"><a class="nav-link" href="ServletController?command=list_services_view"
					style="cursor: pointer"><i class="fab fa-servicestack"></i>	Сервисы</a></li>
			</ul>
		</div>
        <h6
                class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted"
                style="cursor: pointer">
            <span><fmt:message key="client_menu_settings" /></span> <a
                class="d-flex align-items-center text-muted"><i
                class="fas fa-cogs"></i></a>
        </h6>
        <div>
            <ul class="nav flex-column mb-2">
                <li class="nav-item"><a class="nav-link"
                                        href=""
                                        style="cursor: pointer"><i class="far fa-user-circle"></i> <fmt:message
                        key="client_menu_personal_data" /></a></li>
                <li class="nav-item"><a class="nav-link"
                                        href=""
                                        style="cursor: pointer"><i class="fas fa-cogs"></i> <fmt:message
                        key="client_menu_update_password" /></a></li>
            </ul>
        </div>

	</div>
</nav>