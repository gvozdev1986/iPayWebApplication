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
::-webkit-scrollbar {
	width: 0px;
	height: 0px;
}
::-webkit-scrollbar-button {
	background: #ccc
}
::-webkit-scrollbar-track-piece {
	background: #888
}
::-webkit-scrollbar-thumb {
	background: #eee
}​
.btn-success:hover {
	color: #fff;
	background-color: #41c7a3;
	border-color: #41c7a3;
}
.header-table-column {
	vertical-align: middle;
	font-weight: bold;
	color: white;
	background-color: #00ad7e;
}
</style>
<div class="container-fluid">
	<div class="row">
		<jsp:include page="admin_menu.jsp" />
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<h1 class="h2"><fmt:message key="credit_card_label"/></h1>
			<div class="btn-toolbar mb-2 mb-md-0"><fmt:message key="list_credit_cards"/></div>
		</div>
		<div class="container"
			style="height: 70%; overflow-y: scroll; padding: 5px;">
			<!--<form>
				<div class="input-group input-group-sm mb-3">
					<input type="text" name="param" class="form-control"
						placeholder="Search..." aria-label="search input"
						aria-describedby="inputGroup-sizing-sm">
					<div class="input-group-append">
						<button class="btn btn-success" 
								type="submit" 
								name="command"
								value="find_card_by_param" 
								id="search-Btn">
							<i class="fas fa-search"></i> Search
						</button>
						<button class="btn btn-success" type="submit" name="command"
							value="list_card_view" id="clear-Btn">
							<i class="fas fa-times"></i> Clear
						</button>
					</div>
				</div>
			</form>-->
			<table class="table table-bordered table-striped table-sm" style="width: 100%; font-size: 9pt;">
				<thead>
					<tr class="header-table-column" style="text-align: center; vertical-align: middle;">
						<td style="vertical-align: middle; font-weight: bold;">#</td>
						<td style="vertical-align: middle; font-weight: bold;"><fmt:message key="form_first_name"/></td>
						<td style="vertical-align: middle; font-weight: bold;"><fmt:message key="last_name_card_label"/></td>
						<td style="vertical-align: middle; font-weight: bold;"><fmt:message key="credit_card_number_label"/></td>
						<td style="vertical-align: middle; font-weight: bold;"><fmt:message key="valid_card_label"/></td>
						<td style="vertical-align: middle; font-weight: bold;"><fmt:message key="type_card_label"/></td>
						<td style="vertical-align: middle; font-weight: bold; width: 5px;">B</td>
						<td style="vertical-align: middle; font-weight: bold; width: 5px;">A</td>
					</tr>
				</thead>
				<c:forEach items="${creditCard}" var="pagination" varStatus="loop">
					<tr>
						<td style="vertical-align: middle; text-align: right;">${(page * countRowOnPage) + (loop.index + 1)}</td>
						<td>${pagination.cardFirstName}</td>
						<td>${pagination.cardLastName}</td>
						<td>${pagination.cardNumber}</td>
						<td	style="vertical-align: middle; width: 10px; text-align: right;">${pagination.validDate}</td>
						<td>${pagination.typeCard}</td>

						<c:if test="${pagination.block}">
							<td style="text-align: center; vertical-align: middle;"><i
								class="fas fa-lock"></i></td>
						</c:if>

						<c:if test="${not pagination.block}">
							<td style="text-align: center; vertical-align: middle;"><i
								class="fas fa-unlock"></i></td>
						</c:if>

						<c:if test="${pagination.available}">
							<td style="text-align: center; vertical-align: middle;"><i
								class="far fa-trash-alt"></i></td>
						</c:if>

						<c:if test="${not pagination.available}">
							<td style="text-align: center; vertical-align: middle;"><i
								class="fas fa-chart-line"></i></td>
						</c:if>

					</tr>
				</c:forEach>
			</table>				
			<!-- PAGINATION -->
				<jsp:include page="pagination.jsp" />			
			</div>
		</main>
	</div>
</div>
<ctg:footer />
