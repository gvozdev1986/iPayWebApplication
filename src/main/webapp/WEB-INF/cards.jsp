<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="Resource" />
<jsp:include page="client_navbar.jsp" />
<style>
.custome-flash {
	box-shadow: 0 0 20px rgba(0, 173, 126, 5);
	animation: myfirst 5s linear 0s infinite alternate;
	-webkit-animation: myfirst 1s linear 0s infinite alternate;
}

@keyframes myfirst {
	0% {
		box-shadow: 0 0 20px rgba(0, 173, 126, .5);
	}
	50%{
		box-shadow:0020pxrgba(255,255,255,.5);
	}
	100%{
		box-shadow:0020pxrgba(0,173,126,.5);
	}
}
@-webkit-keyframes myfirst {
	0% {
		box-shadow: 0 0 20px rgba(0, 173, 126, .5);
	}
	50%{
		box-shadow:0020pxrgba(255,255,255,.5);
	}
	100%{
		box-shadow:0020pxrgba(0,173,126,.5);
	}
}
.card-style {
	height: 20px;
	width: 27px;
	margin-top: -3px;
}
</style>


<div class="container-fluid">
	<div class="row">
		<jsp:include page="client_menu.jsp" />
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<h1 class="h2">
				<fmt:message key="credit_card_label" />
			</h1>
			<div class="btn-toolbar mb-2 mb-md-0">
				<fmt:message key="brief_credit_card_label" />
			</div>
		</div>
		<div class="container"
			style="height: 70%; overflow-y: scroll; padding: 5px;">
			<c:forEach items="${cards}" var="creditCards">
				<div class="card" id="card_${creditCards.id}">
					<input type="hidden" name="cardId" value="${creditCards.id}" />
					<div class="card-header">
						<i class="fas fa-info-circle custome-flash" data-toggle="collapse"
							href="#card_panel_${creditCards.id}" role="button"
							aria-expanded="false" aria-controls="multiCollapseExample1"
							style="border-radius: 25px;"> </i>
						<c:if test="${creditCards.block}">
							<i class="fas fa-lock"></i>
						</c:if>
						<c:if test="${not creditCards.block}">
							<i class="fas fa-lock-open"></i>
						</c:if>
						<fmt:message key="credit_card_number_label" />
						: <b>${creditCards.cardNumber}</b> / <b>${creditCards.balanceBankAccount}</b>
					</div>
					<div class="collapse multi-collapse"
						id="card_panel_${creditCards.id}">
						<div class="card-body ">
							<ui>
							<li><fmt:message key="last_name_card_label" />: <b>${creditCards.cardLastName}</b>
								<fmt:message key="first_name_card_label" />: <b>${creditCards.cardFirstName}</b></li>
							<li><fmt:message key="type_card_label" />: <b>${creditCards.typeCard}
							</b><img class="card-style" src="img/${creditCards.typeCard}.png"
								alt="card"></li>
							<li><fmt:message key="valid_card_label" />: <b>${creditCards.validDate}</b></li>
							<li><fmt:message key="account_card_label" />: <b>${creditCards.nameAccount}</b></li>
							<li><fmt:message key="balance_card_label" />: <b>${creditCards.balanceBankAccount}</b></li>
							</ui>
						</div>
						<ul class="list-group list-group-flush">
							<li class="list-group-item" style="height: 62px;">
								<form action="ServletController" method="post">
									<input type="hidden" id="cardId_${creditCards.id}"
										name="cardId" value="${creditCards.id}" />
									<div class="form-inline">
										<c:if test="${not creditCards.block}">
											<button
												class="btn btn-success form-control mr-sm-2 btn-sm custom_button"
												type="submit" name="command" value="get_balance">
												<i class="fas fa-sync"></i>
												<fmt:message key="refresh_btn" />
											</button>
											<button
												class="btn btn-success form-control mr-sm-2 btn-sm custom_button"
												onclick="getCardId(document.getElementById('cardId_${creditCards.id}').value);"
												type="button" data-toggle="modal" data-target="#agreeModal">
												<i class="fas fa-lock"></i>
												<fmt:message key="block_btn" />
											</button>
										</c:if>
										<c:if test="${creditCards.block}">
											<a style="margin-top: 7px;">This card has been blocked.</a>
										</c:if>
									</div>
								</form>
							</li>
						</ul>
					</div>
				</div>
				</br>
			</c:forEach>
		</div>
	</div>
	</main>
</div>
<ctg:footer />
<!-- MODAL AGREE BLOCK CARD -->
<div class="modal fade" id="agreeModal" tabindex="-1" role="dialog"
	aria-labelledby="agreeModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="agreeModalLabel">
					<fmt:message key="block_card_label_modal" />
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<a><fmt:message key="question_block_card" /></a>
			</div>
			<div class="modal-footer">
				<form action="ServletController" method="post">
					<input type="hidden" id="cardIdModal" name="cardId" value="" />
					<div class="form-inline">
						<button type="button"
							class="btn btn-sm btn-secondary form-control mr-sm-2 custom_button"
							data-dismiss="modal">
							<i class="fas fa-times"></i>
							<fmt:message key="cancel_btn" />
						</button>
						<button
							class="btn btn-sm btn-success form-control mr-sm-2 custom_button"
							type="submit" name="command" value="block_card">
							<i class="fas fa-lock"></i>
							<fmt:message key="block_btn" />
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script>
function getCardId(cardId){	
	document.getElementById('cardIdModal').value = cardId;
}
</script>