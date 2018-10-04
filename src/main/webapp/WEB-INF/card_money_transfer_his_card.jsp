<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<jsp:include page="user_navbar.jsp"/>
<style>
    .message-box {
        border-color: #00ad7e;
        border-style: solid;
        border-radius: 5px;
        text-align: center;
        vertical-align: middle;
        width: 100%;
        color: #ffffff;
        background-color: #00ad7e;
        font-weight: bold;
    }
</style>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="user_menu.jsp"/>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Transfer to your card</h1>
                <div class="btn-toolbar mb-2 mb-md-0">TRANSFER MONEY</div>
            </div>
            <div class="container">
                <div class="row h-100">
                    <div class="col-md-8">
                        <form action="ServletController" method="post" name="transferForm">
                            <input type="hidden"
                                   name="clientId"
                                   value="${client.id}"/>
                            <div class="form-row">
                                <div class="col-md-6 mb-3 input-group-sm">
                                    <label for="idCardFromTransf">
                                        <i class="far fa-credit-card"></i> <fmt:message key="card_from"/>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <select class="custom-select"
                                            id="idCardFromTransf"
                                            name="idCardFromTransf"
                                            style="line-height: 14px; height: 31px;"
                                            required>
                                        <c:forEach items="${cards}" var="creditCards">
                                            <c:if test="${not creditCards.block}">
                                                <option value="${creditCards.id}">${creditCards.cardNumber}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div id="yourCardBlock" class="col-md-6 mb-3 input-group-sm">
                                    <label for="idCardToTransf">
                                        <i class="far fa-credit-card"></i> <fmt:message key="card_to"/>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <select class="custom-select"
                                            id="idCardToTransf"
                                            name="idCardToTransf"
                                            style="line-height: 14px; height: 31px;"
                                            required>
                                        <c:forEach items="${cards}" var="creditCards">
                                            <c:if test="${not creditCards.block}">
                                                <option value="${creditCards.id}">${creditCards.cardNumber}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-md-3 mb-3 input-group-sm">
                                    <label for="sumCardTransf">
                                        <i class="fas fa-sort-numeric-up"></i> <fmt:message key="amount"/>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <input type="number"
                                           class="form-control"
                                           id="sumCardTransf"
                                           placeholder="0.00"
                                           name="sumCardTransf"
                                           step="0.01"
                                           required
                                           style="text-align: right;"
                                           autocomplete="off">
                                </div>
                                <div class="col-md-3 mb-3 input-group-sm">
                                    <label for="code">
                                        <i class="fas fa-user-secret"></i> <fmt:message key="cv_code"/>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <input type="password"
                                           class="form-control"
                                           id="code"
                                           placeholder="<fmt:message key="cv_code"/>"
                                           name="code"
                                           required
                                           autocomplete="off">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-md-12 mb-3 input-group-sm">
                                    <label for="descriptionCardTransf">
                                        <i class="far fa-comment-alt"></i> <fmt:message key="description_field"/>
                                        <span style="color: red;">*</span>
                                    </label>
                                    <input type="text"
                                           class="form-control"
                                           id="descriptionCardTransf"
                                           placeholder="<fmt:message key="description_field"/>"
                                           name="descriptionCardTransf"
                                           autocomplete="off"
                                           required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-md-1 mb-3 input-group-sm">
                                    <div style="margin-top: 31px;"></div>
                                    <button class="btn btn-success form-control my-sm-0 my-2 btn-sm custom_button"
                                            id="save_btn"
                                            type="submit"
                                            name="command"
                                            value="save_transfer_his_credit_card">
                                        <i class="fas fa-exchange-alt"></i> <fmt:message key="save_btn"/>
                                    </button>
                                </div>
                            </div>
                        </form>
                        <div class="message-box">
                            ${messageFromTransfer}
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>
<script>
    var cc = transferForm.anotherCardInput;
    for (var i in ['input', 'change', 'blur', 'keyup']) {
        cc.addEventListener('input', formatAnotherCardInput, false);
    }

    function formatAnotherCardInput() {
        var anotherCardInput = this.value.replace(/[^\d]/g, '').substring(0, 16);
        anotherCardInput = anotherCardInput != '' ? anotherCardInput.match(/.{1,4}/g).join(' ') : '';
        this.value = anotherCardInput;
    }
</script>
