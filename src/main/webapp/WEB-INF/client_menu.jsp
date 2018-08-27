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

@
supports ((position: -webkit-sticky ) or (position: sticky )) { .sidebar-sticky
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
			<span><fmt:message key="client_menu_accounting" /></span> <a
				class="d-flex align-items-center text-muted" href="#"><i
				class="fas fa-calculator"></i></a>
		</h6>
		<ul class="nav flex-column">
			<li class="nav-item"><a class="nav-link"
				href="ServletController?command=credit_card_view"
				style="cursor: pointer"><i class="far fa-credit-card"></i> <fmt:message
						key="client_menu_credit_cards" /></a></li>
			<li class="nav-item"><a class="nav-link"
				href="ServletController?command=payment_history_view"
				style="cursor: pointer"><i class="far fa-file-alt"></i> <fmt:message
						key="client_menu_card_history" /></a></li>
		</ul>
		<h6
			class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted"
			style="cursor: pointer" data-toggle="collapse"
			href="#paymentPartMenu" role="button" aria-expanded="false"
			aria-controls="paymentPartMenu">
			<span><fmt:message key="client_menu_payment_service" /></span> <a
				class="d-flex align-items-center text-muted"><i
				class="fab fa-servicestack"></i></a>
		</h6>
		<div class="collapse" id="paymentPartMenu">
			<ul class="nav flex-column mb-2">
				<li class="nav-item"><a class="nav-link"
					href="ServletController?command=payment_service_view"
					style="cursor: pointer"><i class="fas fa-shopping-cart"></i> <fmt:message
							key="client_menu_pay_service" /></a></li>
				<li class="nav-item"><a class="nav-link"
					href="ServletController?command=transfer_view"
					style="cursor: pointer"><i class="fas fa-exchange-alt"></i> <fmt:message
							key="client_menu_transfer_card" /></a></li>
			</ul>
		</div>
		<h6
			class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted"
			style="cursor: pointer" data-toggle="collapse"
			href="#settingsPartMenu" role="button" aria-expanded="false"
			aria-controls="settingsPartMenu">
			<span><fmt:message key="client_menu_settings" /></span> <a
				class="d-flex align-items-center text-muted"><i
				class="fas fa-cogs"></i></a>
		</h6>
		<div class="collapse" id="settingsPartMenu">
			<ul class="nav flex-column mb-2">
				<li class="nav-item"><a class="nav-link"
					href="ServletController?command=personal_data_view"
					style="cursor: pointer"><i class="far fa-user-circle"></i> <fmt:message
							key="client_menu_personal_data" /></a></li>
				<li class="nav-item"><a class="nav-link"
					href="ServletController?command=update_client_password_view"
					style="cursor: pointer"><i class="fas fa-cogs"></i> <fmt:message
							key="client_menu_update_password" /></a></li>
			</ul>
		</div>


	</div>
</nav>