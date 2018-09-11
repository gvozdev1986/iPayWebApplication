<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<jsp:include page="admin_navbar.jsp" />
<style>
.invalid-field {
	color: #FF0000;
	font-size: 9pt;
	font-style: italic;
}
</style>
<div class="container-fluid">
	<div class="row">
		<jsp:include page="admin_menu.jsp" />
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<h1 class="h2">Personal data</h1>
			<div class="btn-toolbar mb-2 mb-md-0">PERSONAL CLIENT DATA</div>
		</div>
		<div class="container">
			<form action="ServletController" method="post">
				<input type="hidden" name="clientId" value="${user.id}" />
				<div class="form-row">
					<div class="col-md-6 mb-3 input-group-sm">
						<label for="last_name"><i class="fas fa-pencil-alt"></i>
							Last name</label> <input type="text" class="form-control" id="last_name"
							placeholder="Last name" value="${user.lastName}"
							name="last_name" required>
						<c:if test="${not empty invalidLastName}">
							<span class="invalid-field">${invalidLastName}</span>
						</c:if>
					</div>
					<div class="col-md-6 mb-3 input-group-sm">
						<label for="first_name"><i class="fas fa-pencil-alt"></i>
							First name</label> <input type="text" class="form-control"
							id="first_name" placeholder="First name"
							value="${user.firstName}" name="first_name" required>
						<c:if test="${not empty invalidFirstname}">
							<span class="invalid-field">${invalidFirstname}</span>
						</c:if>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-8 mb-3 input-group-sm">
						<label for="patronymic"><i class="fas fa-pencil-alt"></i>
							Patronymic</label> <input type="text" class="form-control"
							id="patronymic" placeholder="Patronymic"
							value="${user.patronymic}" name="patronymic" required>
						<c:if test="${not empty invalidPatronymic}">
							<span class="invalid-field">${invalidPatronymic}</span>
						</c:if>
					</div>
					<div class="col-md-4 mb-3 input-group-sm">
						<label for="date_birth"><i class="far fa-calendar-alt"></i>
							Date of birth</label> <input type="date" class="form-control"
							id="date_birth" placeholder="Date of birth"
							value="${user.dateBirth}" name="date_birth" required>
						<c:if test="${not empty invalidDate}">
							<span class="invalid-field">${invalidDate}</span>
						</c:if>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-4 mb-3 input-group-sm">
						<label for="home_phone"><i class="fas fa-phone"></i> Home
							phone</label> <input type="tel" class="form-control" id="home_phone"
							placeholder="Home phone" value="${user.phoneHome}"
							name="home_phone" required>
						<c:if test="${not empty invalidHomePhone}">
							<span class="invalid-field">${invalidHomePhone}</span>
						</c:if>
					</div>
					<div class="col-md-4 mb-3 input-group-sm">
						<label for="mobile_phone"><i class="fas fa-mobile-alt"></i>
							Mobile phone</label> <input type="tel" class="form-control"
							id="mobile_phone" placeholder="Mobile phone"
							value="${user.phoneMobile}" name="mobile_phone" required>
						<c:if test="${not empty invalidMobilePhone}">
							<span class="invalid-field">${invalidMobilePhone}</span>
						</c:if>
					</div>
					<div class="col-md-4 mb-3 input-group-sm">
						<label for="email"><i class="fas fa-envelope-open"></i>
							email</label> <input type="email" class="form-control" id="email"
							placeholder="email" value="${user.email}" name="email" required>
						<c:if test="${not empty invalidEmail}">
							<span class="invalid-field">${invalidEmail}</span>
						</c:if>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-12 mb-3 input-group-sm">
						<label for="address"><i class="fas fa-map-pin"></i>
							Address</label>
						<textarea class="form-control" aria-label="With textarea"
							id="address" name="address" required>${user.address}</textarea>
						<c:if test="${not empty invalidAddress}">
							<span class="invalid-field">${invalidAddress}</span>
						</c:if>
					</div>
				</div>
				<div>
					<a>${messageUpdateClient}</a>
				</div>
				<div class="form-inline">
					<button
						class="btn btn-success form-control mr-sm-2 btn-sm custom_button"
						id="save_btn" type="submit" name="command"
						value="save_changed_admin_personal_data">
						<i class="fas fa-check"></i> Save
					</button>
					<!-- <button
						class="btn btn-success form-control my-sm-0 my-2 btn-sm custom_button"
						type="button" id="button" data-toggle="modal"
						data-target="#bockClientAccount">
						<i class="fas fa-ban"></i> Block
					</button> -->
				</div>
			</form>
		</div>
		</main>
	</div>
</div>
<ctg:footer />
<!-- Modal -->
<div class="modal hide fade in" data-keyboard="false"
	data-backdrop="static" id="bockClientAccount" tabindex="-1"
	role="dialog" aria-labelledby="bockClientAccount" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Blocking client
					account</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">Are you sure you want to block an
				client account?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btn-sm custom_button"
					data-dismiss="modal">
					<i class="fas fa-times"></i> Cancel
				</button>
				<button type="button" class="btn btn-success btn-sm custom_button">
					<i class="fas fa-ban"></i> Block
				</button>
			</div>
		</div>
	</div>
</div>
