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
			<h1 class="h2"># ${creditCard.cardNumber}</h1>
			<div class="btn-toolbar mb-2 mb-md-0"><fmt:message key="list_credit_cards"/></div>
		</div>
		<div class="container" style="height: 70%; overflow-y: scroll; padding: 5px;">
			<table class="table table-bordered table-hover table-sm table-striped"	style="width: 100%; font-size: 9pt;">
				<tr>
					<td colspan="2" style="text-align: center;"><fmt:message key="information_account"/></td>
				</tr>
				<tr>
					<td><fmt:message key="first_name_card_label"/></td>
					<td>${creditCard.cardFirstName}</td>
				</tr>
				<tr>
					<td><fmt:message key="last_name_card_label"/></td>
					<td>${creditCard.cardLastName}</td>
				</tr>
				<tr>
					<td><fmt:message key="credit_card_number_label"/></td>
					<td>${creditCard.cardNumber}</td>
				</tr>
				<tr>
					<td><fmt:message key="valid_card_label"/></td>
					<td>${creditCard.validDate}</td>
				</tr>
				<tr>
					<td><fmt:message key="type_card_label"/></td>
					<td><img style="height: 20px; width: 28px; margin-top: -3px; margin-left: -2px;" src="img/${creditCard.typeCard}.png" alt="card">
						${creditCard.typeCard}</td>
				</tr>
				<tr>
					<td><fmt:message key="status"/></td>
					<c:if test="${bankAccount.statusBankAccount}">
						<td><fmt:message key="unblocked_status"/></td>
					</c:if>
					<c:if test="${not bankAccount.statusBankAccount}">
						<td><fmt:message key="blocked_status"/></td>
					</c:if>
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
						<i class="fas fa-arrow-left"></i> <fmt:message key="cancel_btn"/>
					</button>
					<button
						class="btn btn-sm btn-success form-control mr-sm-2 custom_button"
						type="submit" name="command" value="unblock_card">
						<i class="fas fa-lock-open"></i> <fmt:message key="unblock_btn"/>
					</button>
					<button
							class="btn btn-sm btn-success form-control mr-sm-2 custom_button"
							type="button"
                            onclick="PrintReport('#statusTable');"
                            value="unblock_card">
						<i class="far fa-file-powerpoint"></i> <fmt:message key="print_btn"/>
					</button>
				</div>
			</form>
            <c:if test="${not empty messageStatusUnblockCreditCard}">
                <p style="color: #fe0c00"><fmt:message key="${messageStatusUnblockCreditCard}"/></p>
            </c:if>
		</div>
		</main>
	</div>
</div>
<ctg:footer />
<div id="statusTable" style="visibility: hidden">
    <table class="table table-bordered table-hover table-sm table-striped"	style="width: 100%; font-size: 9pt;">
        <tr>
            <td colspan="2" style="text-align: center;"><fmt:message key="information_account"/></td>
        </tr>
        <tr>
            <td><fmt:message key="first_name_card_label"/></td>
            <td>${creditCard.cardFirstName}</td>
        </tr>
        <tr>
            <td><fmt:message key="last_name_card_label"/></td>
            <td>${creditCard.cardLastName}</td>
        </tr>
        <tr>
            <td><fmt:message key="credit_card_number_label"/></td>
            <td>${creditCard.cardNumber}</td>
        </tr>
        <tr>
            <td><fmt:message key="valid_card_label"/></td>
            <td>${creditCard.validDate}</td>
        </tr>
        <tr>
            <td><fmt:message key="type_card_label"/></td>
            <td><img style="height: 20px; width: 28px; margin-top: -3px; margin-left: -2px;" src="img/${creditCard.typeCard}.png" alt="card">
                ${creditCard.typeCard}</td>
        </tr>
        <tr>
            <td><fmt:message key="status"/></td>
            <c:if test="${bankAccount.statusBankAccount}">
                <td><fmt:message key="blocked_status"/></td>
            </c:if>
            <c:if test="${not bankAccount.statusBankAccount}">
                <td><fmt:message key="unblocked_status"/></td>
            </c:if>
        </tr>
        <tr>
    </table>
</div>
<script>
    function PrintReport(elem) {
        Popup($(elem).html());
    }

    function Popup(data) {
        var printReportWindow = window.open('', 'statusTable');
        printReportWindow.document.write('<html><head><title><fmt:message key="report"/></title>');
        printReportWindow.document.write('<link rel="stylesheet" href="../css/report.css" type="text/css" />');
        printReportWindow.document.write('</head><body>');
        printReportWindow.document.write(data);
        printReportWindow.document.write('</body></html>');
        printReportWindow.document.close();
        printReportWindow.focus();
        printReportWindow.print();
        printReportWindow.close();
    }
</script>