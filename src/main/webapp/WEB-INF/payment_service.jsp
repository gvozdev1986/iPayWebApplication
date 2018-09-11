<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<jsp:include page="user_navbar.jsp"/>
<style>
    .message-box {
        border-color: #00ad7e;
        border-style: solid;
        border-radius: 5px;
        text-align: center;
        vertical-align: middle;
        width: 50%;
    }
</style>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="user_menu.jsp"/>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Payment</h1>
                <div class="btn-toolbar mb-2 mb-md-0">PAYMENT SERVICE</div>
            </div>
            <div class="container">
                <form action="ServletController" method="post">
                    <input type="hidden" name="clientId" value="${client.id}"/>
                    <div class="form-row">
                        <div class="col-md-4 mb-3 input-group-sm">
                            <label for="idCardPayment"><i class="far fa-credit-card"></i>
                                Card</label> <select class="custom-select" id="idCardPayment"
                                                     name="idCard" style="line-height: 14px; height: 31px;">
                            <c:forEach items="${cards}" var="creditCards">
                                <c:if test="${not creditCards.block}">
                                    <option value="${creditCards.id}">${creditCards.cardNumber}
                                        / ${creditCards.balanceBankAccount}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        </div>
                        <div class="col-md-6 mb-3 input-group-sm">
                            <label for="idService"><i class="fab fa-servicestack"></i>
                                Service</label> <select class="custom-select" id="idService"
                                                        name="idService" style="line-height: 14px; height: 31px;">
                            <c:forEach items="${groups}" var="paymentDatas">
                                <option value="${paymentDatas.id}">${paymentDatas.paymentDataName}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <div class="col-md-2 mb-3 input-group-sm">
                            <label for="sum"><i class="fas fa-sort-numeric-up"></i>
                                Sum</label> <input type="number" class="form-control" id="sum"
                                                   placeholder="0.00" name="sum" step="0.01" required
                                                   style="text-align: right;">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-10 mb-3 input-group-sm">
                            <label for="description"><i class="far fa-comment-alt"></i>
                                Description</label> <input type="text" class="form-control"
                                                           id="description" placeholder="Description" name="description"
                                                           required>
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
                                    id="save_btn" type="submit" name="command"
                                    value="save_pay_payment">
                                <i class="fas fa-shopping-cart"></i> Pay
                            </button>
                        </div>
                    </div>
                </form>
                <div class="message-box">
                    <p style="margin: 2px;">${messageSavePayment}</p>
                </div>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>

