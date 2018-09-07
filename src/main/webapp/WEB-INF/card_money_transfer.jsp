<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<jsp:include page="user_navbar.jsp" />
<div class="container-fluid">
	<div class="row">
		<jsp:include page="user_menu.jsp" />
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<h1 class="h2">Transfer to a card</h1>
			<div class="btn-toolbar mb-2 mb-md-0">TRANSFER MONEY</div>
		</div>
		<div class="container">
			<div class="form-group">
				<div class="form-check">
					<input class="form-check-input"
						   type="checkbox" value=""
                           id="anotherCardCheck"
                           onclick="anotherCard();"
                           required>
                    <label class="form-check-label"
                           for="anotherCardCheck"> Another's card</label>
				</div>
			</div>
			<form action="ServletController" method="post" name="transferForm">
				<input type="hidden" name="clientId" value="${client.id}" />
				<div class="form-row">
					<div class="col-md-5 mb-3 input-group-sm">
						<label for="idCardFromTransf"><i
							class="far fa-credit-card"></i> Card from</label>
						<select	class="custom-select" id="idCardFromTransf"
							name="idCardFromTransf" style="line-height: 14px; height: 31px;">
							<c:forEach items="${cards}" var="creditCards">
								<c:if test="${not creditCards.block}">
									<option value="${creditCards.id}">${creditCards.cardNumber}
										/ ${creditCards.balanceBankAccount}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div id="yourCardBlock" class="col-md-5 mb-3 input-group-sm">
						<label for="idCardToTransf"><i class="far fa-credit-card"></i>
							Card to</label> <select class="custom-select" id="idCardToTransf"
							name="idCardToTransf" style="line-height: 14px; height: 31px;">
							<c:forEach items="${cards}" var="creditCards">
								<c:if test="${not creditCards.block}">
									<option value="${creditCards.id}">${creditCards.cardNumber}
										/ ${creditCards.balanceBankAccount}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>

					<div id="anotherCardBlock" class="col-md-5 mb-3 input-group-sm"
						style="display: none">
						<label for="anotherCardInput"><i
							class="far fa-credit-card"></i> Another card</label> <input type="text"
							class="form-control" id="anotherCardInput"
							placeholder="XXXX XXXX XXXX XXXX" name="anotherCardInput"
							value="0000 0000 0000 0000"
							pattern="[0-9]{4}\s[0-9]{4}\s[0-9]{4}\s[0-9]{4}" required>
					</div>

					<div class="col-md-2 mb-3 input-group-sm">
						<label for="sumCardTransf"><i
							class="fas fa-sort-numeric-up"></i> Sum transaction</label> <input
							type="number" class="form-control" id="sumCardTransf"
							placeholder="0.00" name="sumCardTransf" step="0.01" required
							style="text-align: right;">
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-10 mb-3 input-group-sm">
						<label for="descriptionCardTransf"><i
							class="far fa-comment-alt"></i> Description</label> <input type="text"
							class="form-control" id="descriptionCardTransf"
							placeholder="Description" name="descriptionCardTransf" required>
					</div>
					<div class="col-md-1 mb-3 input-group-sm">
						<label for="code"><i class="fas fa-user-secret"></i> Code</label>
						<input type="password" class="form-control" id="code"
							placeholder="Code" name="code" required>
					</div>
					<div class="col-md-1 mb-3 input-group-sm">
						<div style="margin-top: 31px;"></div>
						<button
							class="btn btn-success form-control my-sm-0 my-2 btn-sm custom_button"
							id="save_btn" type="submit" name="command" value="save_transfer">
							<i class="fas fa-exchange-alt"></i> Save
						</button>
					</div>
				</div>
			</form>
			<p>${messageFromTransfer}</p>
		</div>
		</main>
	</div>
</div>
<ctg:footer />
<script>

	var cc = transferForm.anotherCardInput;
	for (var i in ['input', 'change', 'blur', 'keyup']) {
	    cc.addEventListener('input', formatAnotherCardInput, false);
	}
	function formatAnotherCardInput() {
	    var anotherCardInput = this.value.replace(/[^\d]/g, '').substring(0,16);
	    anotherCardInput = anotherCardInput != '' ? anotherCardInput.match(/.{1,4}/g).join(' ') : '';
	    this.value = anotherCardInput;
	}

	function anotherCard() {
		var x = document.getElementById("anotherCardCheck").checked;		
		yourCardBlock = document.getElementById("yourCardBlock");
		anotherCardBlock = document.getElementById("anotherCardBlock");		
		if(x){
			yourCardBlock.style.display = "none";
			anotherCardInput.value = "";
			anotherCardBlock.style.display = "block";
		} else {
			yourCardBlock.style.display = "block";
			anotherCardInput.value = "0000 0000 0000 0000";
			anotherCardBlock.style.display = "none";
		}
	}
</script>
