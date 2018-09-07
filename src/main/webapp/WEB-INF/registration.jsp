<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<jsp:include page="head.jsp" />
<style>
.btn-success {
	color: #fff;
	background-color: #00ad7e;
	border-color: #00ad7e;
}

.btn-success:hover {
	color: #fff;
	background-color: #41c7a3;
	border-color: #41c7a3;
}

.custom_button {
	min-width: 100px;
	max-width: 100px;
}

.card-header {
	padding: .75rem 1.25rem;
	margin-bottom: 0;
	position: relative;
	background: url("img/j.jpg") no-repeat center center;
	width: 100%;
	height: 6%;
	background-size: 100% 100%;
	color: #fff;
}
</style>
<header>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<a class="navbar-brand"
			href="ServletController?command=greeting_page_view"> <img
			src="img/logo.png" width="30" height="30"
			class="d-inline-block align-top" alt=""> iPay
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#"><i
						class="fa fa-home"></i> Home<span class="sr-only">(current)</span></a></li>
				<li class="nav-item"><a class="nav-link" href="#"><i
						class="fas fa-pencil-alt"></i> Write us</a></li>
			</ul>
		</div>
	</nav>
</header>
<div class="container h-100">
	<div class="row h-100 justify-content-center align-items-center">
		<div class="card" style="box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); z-index: 50000;">
			<div class="card-header">
				<fmt:message key="form_label" />
			</div>
			<div class="card-body">
				<form action="ServletController" method="post">
					<div class="form-row">
						<div class="col-md-4 mb-3 input-group-sm">
							<label for="login"><i class="fas fa-user-plus"></i> <fmt:message
									key="form_login" /></label> <input type="text" class="form-control"
								id="login" placeholder="<fmt:message key="form_login" />"
								value="" name="username" required>
							<!-- <div class="valid-feedback">Looks good!</div> -->
						</div>
						<div class="col-md-4 mb-3 input-group-sm">
							<label for="pswd"><i class="fas fa-key"></i> <fmt:message
									key="form_password" /></label> <input type="password"
								class="form-control" id="pswd"
								placeholder="<fmt:message key="form_password" />" value=""
								name="password" required>
							<!-- <div class="valid-feedback">Looks good!</div> -->
						</div>
						<div class="col-md-4 mb-3 input-group-sm">
							<label for="pswd_verify"><i class="fas fa-check"></i> <fmt:message
									key="form_verify" /></label> <input type="password"
								class="form-control" id="pswd_verify"
								placeholder="<fmt:message key="form_verify" />" value=""
								name="password_verify" required>
							<!-- <div class="valid-feedback">Looks good!</div> -->
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-6 mb-3 input-group-sm">
							<label for="last_name"><i class="fas fa-pencil-alt"></i>
								<fmt:message key="form_last_name" /></label> <input type="text"
								class="form-control" id="last_name"
								placeholder="<fmt:message key="form_last_name" />" value=""
								name="last_name" required>
							<!-- <div class="valid-feedback">Looks good!</div> -->
						</div>
						<div class="col-md-6 mb-3 input-group-sm">
							<label for="first_name"><i class="fas fa-pencil-alt"></i>
								<fmt:message key="form_first_name" /></label> <input type="text"
								class="form-control" id="first_name"
								placeholder="<fmt:message key="form_first_name" />" value=""
								name="first_name" required>
							<!-- <div class="valid-feedback">Looks good!</div> -->
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-8 mb-3 input-group-sm">
							<label for="patronymic"><i class="fas fa-pencil-alt"></i>
								<fmt:message key="form_patronymic" /></label> <input type="text"
								class="form-control" id="patronymic"
								placeholder="<fmt:message key="form_patronymic" />" value=""
								name="patronymic" required>
							<!-- <div class="valid-feedback">Looks good!</div> -->
						</div>
						<div class="col-md-4 mb-3 input-group-sm">
							<label for="date_birth"><i class="far fa-calendar-alt"></i>
								<fmt:message key="form_date_birth" /></label> <input type="date"
								class="form-control" id="date_birth"
								placeholder="<fmt:message key="form_date_birth" />" value=""
								name="date_birth" required>
							<!-- <div class="valid-feedback">Looks good!</div> -->
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-4 mb-3 input-group-sm">
							<label for="home_phone"><i class="fas fa-phone"></i> <fmt:message
									key="form_home_phone" /></label> <input type="tel"
								class="form-control" id="home_phone"
								placeholder="<fmt:message key="form_home_phone" />" value=""
								name="home_phone" required>
							<!-- <div class="valid-feedback">Looks good!</div> -->
						</div>
						<div class="col-md-4 mb-3 input-group-sm">
							<label for="mobile_phone"><i class="fas fa-mobile-alt"></i>
								<fmt:message key="form_mobile_phone" /></label> <input type="tel"
								class="form-control" id="mobile_phone"
								placeholder="<fmt:message key="form_mobile_phone" />" value=""
								name="mobile_phone" required>
							<!-- <div class="valid-feedback">Looks good!</div> -->
						</div>
						<div class="col-md-4 mb-3 input-group-sm">
							<label for="email"><i class="fas fa-envelope-open"></i> <fmt:message
									key="form_email" /></label> <input type="email" class="form-control"
								id="email" placeholder="<fmt:message key="form_email" />"
								value="" name="email" required>
							<!-- <div class="valid-feedback">Looks good!</div> -->
						</div>
					</div>
					<div class="form-row">
						<div class="col-md-12 mb-3 input-group-sm">
							<label for="address"><i class="fas fa-map-pin"></i> <fmt:message
									key="form_address" /></label>
							<textarea class="form-control" aria-label="With textarea"
								id="address" name="address"
								placeholder="<fmt:message key="form_address" />" required></textarea>
							<!-- <div class="valid-feedback">Looks good!</div> -->
						</div>
					</div>
					<div class="form-group">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value=""
								id="invalidCheck" onclick="agree();" required> <label
								class="form-check-label" for="invalidCheck"> <fmt:message
									key="reg_agree_checkbox" />
							</label>
							<!-- <div class="valid-feedback">Looks good!</div> -->
						</div>
					</div>
					<div class="form-inline">
						<button class="btn btn-success form-control mr-sm-2 btn-sm custom_button"
								type="submit" 
								name="command"
								value="log_out"><i class="fas fa-undo"></i> Cancel
						</button>
						<button
							class="btn btn-success form-control my-sm-0 my-2 btn-sm custom_button"
							id="save_btn" type="submit" name="command"
							value="save_registration" disabled>
							<i class="fas fa-check"></i>
							<fmt:message key="reg_save_btn" />
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<ctg:footer />
<script>
	function agree() {
		save_btn = document.getElementById("save_btn");
		if (save_btn.disabled == false) {
			save_btn.disabled = true;
		} else {
			save_btn.disabled = false;
		}
	}
</script>