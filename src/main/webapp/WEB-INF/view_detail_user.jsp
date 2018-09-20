<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="Resource" />
<jsp:include page="admin_navbar.jsp" />

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
.column {
	width: 25%;
	font-weight: 600;
}
.block-status{
	font-weight: 600;
	color: red;
}
.unblock-status{
	font-weight: 600;
	color: green;
}
</style>
<div class="container-fluid">
	<div class="row">
		<jsp:include page="admin_menu.jsp" />
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<h1 class="h2">Client details</h1>
			<div class="btn-toolbar mb-2 mb-md-0">DETAILS ABOUT CLIEN # ${clientDetail.id}</div>
		</div>
			<div class="container">
				<table
				class="table table-bordered table-hover table-sm table-striped"
				style="width: 100%; font-size: 9pt;">
				<tr>
					<td colspan="2" style="text-align: center;">About client's account</td>
				</tr>
				<tr>
					<td class="column">Last Name</td>
					<td>${clientDetail.lastName}</td>
				</tr>
				<tr>
					<td class="column">First name</td>
					<td>${clientDetail.firstName}</td>
				</tr>
				<tr>
					<td class="column">Patronymic</td>
					<td>${clientDetail.patronymic}</td>
				</tr>
				<tr>
					<td class="column">Date of birth</td>
					<td>${clientDetail.dateBirth}</td>
				</tr>
				<tr>
					<td class="column">Home phone</td>					
					<td>${clientDetail.phoneHome}</td>
				</tr>
				<tr>
					<td class="column">Mobile phone</td>					
					<td>${clientDetail.phoneMobile}</td>
				</tr>
				<tr>
					<td class="column">email</td>
					<td>${clientDetail.email}</td>
				</tr>
				<tr>
					<td class="column">Address</td>
					<td>${clientDetail.address}</td>
				</tr>
				<tr>
					<c:if test="${not clientDetail.available}">
						<td class="column">Status</td>
						<td><span class="block-status">BLOCKED</span></td>
					</c:if>
					
					<c:if test="${clientDetail.available}">
						<td class="column">Status</td>
						<td><span class="unblock-status">UNBLOCKED</span></td>
					</c:if>
				</tr>
			</table>
			<form action="ServletController" method="post">
				<input type="hidden" id="cardId" name="userId"	value="${clientDetail.id}" />
				<div class="form-inline">
					<button
						class="btn btn-sm btn-secondary form-control mr-sm-2 custom_button"
						type="submit" 
						name="command" 
						value="list_client_view">
						<i class="fas fa-arrow-left"></i> Back
					</button>
					<c:if test="${not clientDetail.available}">
						<button
							class="btn btn-sm btn-success form-control mr-sm-2 custom_button"
							type="submit" 
							name="command" 
							value="unblock_user">
							<i class="fas fa-lock-open"></i> Unblock
						</button>
					</c:if>
					<c:if test="${clientDetail.available}">
						<button
							class="btn btn-sm btn-success form-control mr-sm-2 custom_button"
							type="submit" 
							name="command" 
							value="block_user">
							<i class="fas fa-lock"></i> Block
						</button>
					</c:if>
				</div>
			</form>
			</div>
		</main>
	</div>
</div>
<ctg:footer />
