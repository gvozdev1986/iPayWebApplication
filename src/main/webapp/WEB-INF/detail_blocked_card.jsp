<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="Resource" />
<jsp:include page="admin_navbar.jsp" />

<style>
.card-style {
	height: 20px;
	width: 27px;
	margin-top: -3px;
}
</style>

<div class="container-fluid">
	<div class="row">
		<jsp:include page="admin_menu.jsp" />
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<h1 class="h2">Card # ${creditCard.cardNumber}</h1>
			<div class="btn-toolbar mb-2 mb-md-0">CREDIT CARD INFORMATION</div>
		</div>
		<div class="container"
			style="height: 70%; overflow-y: scroll; padding: 5px;">
			<table
				class="table table-bordered table-hover table-sm table-striped"
				style="width: 100%; font-size: 9pt;">
				<tr>
					<td colspan="2" style="text-align: center;">About client's
						bank account</td>
				</tr>
				<tr>
					<td>First name on credit card</td>
					<td>${creditCard.cardFirstName}</td>
				</tr>
				<tr>
					<td>Last name on credit card</td>
					<td>${creditCard.cardLastName}</td>
				</tr>
				<tr>
					<td>Credit card number</td>
					<td>${creditCard.cardNumber}</td>
				</tr>
				<tr>
					<td>Valid date</td>
					<td>${creditCard.validDate}</td>
				</tr>
				<tr>
					<td>Type of card</td>
					<td><img class="card-style"
						src="img/${creditCard.typeCard}.png" alt="card">
						${creditCard.typeCard}</td>
				</tr>
				<tr>
					<td>Status bank account</td>
					<c:if test="${bankAccount.statusBankAccount}">
						<td>Bank account blocked</td>
					</c:if>
					<c:if test="${not bankAccount.statusBankAccount}">
						<td>Bank account doesn't blocked</td>
					</c:if>
				</tr>
				<tr>
					<td>Code bank account</td>
					<td>${bankAccount.nameAccount}</td>
				</tr>
				<tr>
					<td>Balance</td>
					<td>${bankAccount.balanceBankAccount}</td>
				</tr>
				<tr>
			</table>
			<form action="ServletController" method="post">
				<input type="hidden" id="cardIdModal" name="cardId"
					value="${creditCard.id}" />
				<div class="form-inline">
					<input type="hidden" name="cardId" value="" />
					<button
						class="btn btn-sm btn-secondary form-control mr-sm-2 custom_button"
						type="submit" name="command" value="blocked_credit_cards_view">
						<i class="fas fa-arrow-left"></i> Back
					</button>
					<button
						class="btn btn-sm btn-success form-control mr-sm-2 custom_button"
						type="submit" name="command" value="unblock_card">
						<i class="fas fa-lock-open"></i> Unblock
					</button>
				</div>
			</form>
		</div>
		</main>
	</div>
</div>
<ctg:footer />