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
</style>
<div class="container-fluid">
	<div class="row">
		<jsp:include page="admin_menu.jsp" />
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<h1 class="h2">Credit cards</h1>
			<div class="btn-toolbar mb-2 mb-md-0">LIST OF CREDIT CARDS</div>
		</div>
		<div class="container"
			style="height: 70%; overflow-y: scroll; padding: 5px;">
			<form>
				<div class="input-group input-group-sm mb-3">
					<input type="text" name="param" class="form-control"
						placeholder="Search..." aria-label="search input"
						aria-describedby="inputGroup-sizing-sm">
					<div class="input-group-append">
						<button class="btn btn-success" type="submit" name="command"
							value="find_card_by_param" id="search-Btn">
							<i class="fas fa-search"></i> Search
						</button>
						<button class="btn btn-success" type="submit" name="command"
							value="list_card_view" id="search-Btn">
							<i class="fas fa-times"></i> Clear
						</button>
					</div>
				</div>
			</form>
			<table
				class="table table-bordered table-hover table-sm table-striped"
				style="width: 100%; font-size: 9pt;">
				<tr style="text-align: center; vertical-align: middle;">
					<td style="vertical-align: middle; font-weight: bold;">#</td>
					<td style="vertical-align: middle; font-weight: bold;">First name</td>
					<td style="vertical-align: middle; font-weight: bold;">Last	name</td>
					<td style="vertical-align: middle; font-weight: bold;">Credit card number</td>
					<td style="vertical-align: middle; font-weight: bold;">Valid</td>
					<td style="vertical-align: middle; font-weight: bold;">Type of card</td>
					<td style="vertical-align: middle; font-weight: bold; width: 5px;">B</td>
					<td style="vertical-align: middle; font-weight: bold; width: 5px;">A</td>
				</tr>
				<c:forEach items="${creditCard}" var="pagination">
					<tr>
						<td style="vertical-align: middle; text-align: right;">${pagination.id}</td>
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
			<form>
				<nav aria-label="Page navigation example">
					<ul class="pagination pagination-sm">
						<li class="page-item"><a style="color: black;" class="page-link" href="#">Previous</a></li>
						<li class="page-item"><a style="color: black;" class="page-link" href="#">1</a></li>
						<li class="page-item"><a style="color: black;" class="page-link" href="#">2</a></li>
						<li class="page-item"><a style="color: black;" class="page-link" href="#">3</a></li>
						<li class="page-item"><a style="color: black;" class="page-link" href="#">Next</a></li>
					</ul>
				</nav>	
			</form>
		</div>
		</main>
	</div>
</div>
<ctg:footer />