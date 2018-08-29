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
			<h1 class="h2">Clients</h1>
			<div class="btn-toolbar mb-2 mb-md-0">LIST OF CLIENTS</div>
		</div>
		<div class="container"
			style="height: 70%; overflow-y: scroll; padding: 5px;">
			<form>
				<div class="input-group input-group-sm mb-3">
					<input type="text" 
						   name="param" 
						   class="form-control"
						   placeholder="Search..." 
						   aria-label="search input"
						   aria-describedby="inputGroup-sizing-sm">
					<div class="input-group-append">
						<button class="btn btn-success" 
								type="submit" 
								name="command"
							    value="find_client_by_param" 
							    id="search-Btn">
							<i class="fas fa-search"></i> Search
						</button>
						<button class="btn btn-success" 
								type="submit" 
								name="command"
							    value="list_client_view" 
							    id="search-Btn">
							<i class="fas fa-times"></i> Clear
						</button>
					</div>
				</div>
			</form>
			<table class="table table-bordered table-hover table-sm table-striped" style="width: 100%; font-size: 9pt;">				
				<tr style="text-align: center; vertical-align: middle;">
					<td style="vertical-align: middle; font-weight: bold;">#</td>
					<td style="vertical-align: middle; font-weight: bold;">S</td>
					<td style="vertical-align: middle; font-weight: bold;"><fmt:message key="first_name_card_label" /></td>
					<td style="vertical-align: middle; font-weight: bold;"><fmt:message key="form_last_name" /></td>
					<td style="vertical-align: middle; font-weight: bold;"><fmt:message key="form_patronymic" /></td>
					<td style="vertical-align: middle; font-weight: bold;">email</td>
					<td style="vertical-align: middle; font-weight: bold;"><fmt:message key="form_home_phone" /></td>
					<td style="vertical-align: middle; font-weight: bold;">A</td>
				</tr>
				<c:forEach items="${clients}" var="pagination">
					<tr>
						<td style="vertical-align: middle; text-align: right;">${pagination.id}</td>
						<td style="vertical-align: middle; width: 25px;">
							<a href="ServletController?command=view_detail_client&clientId=${pagination.id}"
							   style="cursor: pointer; 
							   margin-top: -7px; color: black"> 
								<i class="fas fa-search"></i>
							</a>
						</td>
						<td>${pagination.firstName}</td>
						<td>${pagination.lastName}</td>
						<td>${pagination.patronymic}</td>
						<td>${pagination.email}</td>
						<td>${pagination.phoneMobile}</td>						
						<c:if test="${pagination.available}">
							<td style="text-align: center; vertical-align: middle;"><i class="fas fa-chart-line"></i></td>							
						</c:if>
						<c:if test="${not pagination.available}">
							<td style="text-align: center; vertical-align: middle;"><i class="far fa-trash-alt"></i></td>
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
