<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="Resource"/>
<jsp:include page="user_navbar.jsp"/>
<style>
    .card-head {
        padding: .75rem 1.25rem;
        margin-bottom: 0;
        position: relative;
        background: url("/img/lg.png") no-repeat center center;
        width: 100%;
        height: 12%;
        background-size: 100% 100%;
        color: #fff;
    }
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
<div class="container-fluid">
    <div class="row">
        <jsp:include page="user_menu.jsp"/>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">
                    <fmt:message key="credit_card_label"/>
                </h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <fmt:message key="brief_credit_card_label"/>
                </div>
            </div>
            <form action="ServletController" method="get" style="margin-left: 5px; margin-bottom: 0px;">
                <div class="form-inline">
                    <div class="input-group input-group-sm mb-2" style="margin-left: 15px;">
                        <button class="btn btn-sm btn-success mr-sm-2"
                                type="submit"
                                name="command"
                                value="new_credit_card">
                            <i class="fas fa-plus"></i> <fmt:message key="add_btn"/>
                        </button>
                    </div>
                </div>
            </form>
            <div class="container" style="height: 60%; overflow-y: scroll;">
                <div class="row">
                    <c:forEach items="${cards}" var="creditCards">
                        <div class="col-md-4">
                            <div class="card mb-4 shadow-sm credit-card-form" style="border-color: #00ad7e;">
                                <div class="card-head" style="height: 150px; width: 100%; display: block; border-color: #00ad7e;" data-holder-rendered="true">
                                    <div class="form-row" style="height: 25px;">
                                        <div class="form-group col-md-6">
                                            <c:if test="${creditCards.block}">
                                                <i class="fas fa-lock" id="blink"></i>
                                            </c:if>
                                            <c:if test="${not creditCards.block}">
                                                <i class="fas fa-lock-open"></i>
                                            </c:if>
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label style="text-align: right; width: 100%;">${creditCards.typeCard}<img style="width: 25px; margin-top: -4px;" src="img/${creditCards.typeCard}.png" alt="card"></label>
                                        </div>
                                    </div>
                                    <div class="form-row" style="height: 50px;">
                                        <div class="form-group col-md-12">
                                            <label style="width: 100%; text-align: center; font-size: 30px;">${creditCards.cardNumber}</label>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-12">
                                            <label style="margin-bottom: -10px;">${creditCards.cardLastName} ${creditCards.cardFirstName}</label>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label style="width: 100%; text-align: left; font-size: 11px; line-height: 28px;">${creditCards.nameAccount}</label>
                                        </div>
                                        <div class="form-group col-md-4">
                                            <div style="background-color: #00ad7e; border-radius: 25px; height: 25px; width: auto;">
                                                <label style="width: 100%; text-align: center;">${creditCards.balanceBankAccount}</label>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-4">
                                            <label style="width: 100%; text-align: right;">${creditCards.validDate}</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body" style="height: 54px;">
                                    <p class="card-text"></p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <form action="ServletController" method="post">
                                                <input type="hidden" id="cardId_${creditCards.id}"
                                                       name="cardId" value="${creditCards.id}"/>
                                                <div class="form-inline">
                                                    <c:if test="${not creditCards.block}">
                                                        <button class="btn btn-success form-control mr-sm-2 btn-sm custom_button"
                                                                style="margin-top: -46px; margin-left: -10px; margin-bottom: -27px;"
                                                                onclick="getCardId(document.getElementById('cardId_${creditCards.id}').value);"
                                                                type="button" data-toggle="modal"
                                                                data-target="#agreeModal">
                                                            <i class="fas fa-lock"></i>
                                                            <fmt:message key="block_btn"/>
                                                        </button>
                                                    </c:if>
                                                </div>
                                            </form>
                                        </div>
                                        <c:if test="${creditCards.block}">
                                            <small class="text-muted" style="margin-top: -17px;"><fmt:message key="this_card_has_been_blocked"/></small>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </main>
    </div>
    <ctg:footer/>
    <!-- MODAL AGREE BLOCK CARD -->
    <div class="modal fade" id="agreeModal" tabindex="-1" role="dialog"
         aria-labelledby="agreeModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="agreeModalLabel">
                        <fmt:message key="block_card_label_modal"/>
                    </h5>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <a><fmt:message key="question_block_card"/></a>
                </div>
                <div class="modal-footer">
                    <form action="ServletController" method="post">
                        <input type="hidden" id="cardIdModal" name="cardId" value=""/>
                        <div class="form-inline">
                            <button type="button"
                                    class="btn btn-sm btn-secondary form-control mr-sm-2 custom_button"
                                    data-dismiss="modal">
                                <i class="fas fa-times"></i>
                                <fmt:message key="cancel_btn"/>
                            </button>
                            <button
                                    class="btn btn-sm btn-success form-control mr-sm-2 custom_button"
                                    type="submit" name="command" value="block_card">
                                <i class="fas fa-lock"></i>
                                <fmt:message key="block_btn"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script>
        function getCardId(cardId) {
            document.getElementById('cardIdModal').value = cardId;
        }
    </script>