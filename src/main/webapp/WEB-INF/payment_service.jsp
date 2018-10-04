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
                <h1 class="h2"><fmt:message key="payment_label"/></h1>
                <div class="btn-toolbar mb-2 mb-md-0"><fmt:message key="payment_service_group"/></div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-8">
                        <form action="ServletController" method="post">
                            <input type="hidden" name="clientId" value="${client.id}"/>
                            <div class="form-row">
                                <div class="col-md-6 mb-3 input-group-sm">
                                    <label for="idCardPayment"><i class="far fa-credit-card"></i> <fmt:message
                                            key="card_field"/><span style="color: red;">*</span></label>
                                    <select class="custom-select" id="idCardPayment" name="idCard"
                                            style="line-height: 14px; height: 31px;">
                                        <c:forEach items="${cards}" var="creditCards">
                                            <c:if test="${not creditCards.block}">
                                                <option value="${creditCards.id}">${creditCards.cardNumber}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-6 mb-3 input-group-sm">
                                    <label for="idService"><i class="fab fa-servicestack"></i> <fmt:message
                                            key="service_field"/><span style="color: red;">*</span></label>
                                    <div class="input-group">
                                        <input type="text"
                                               class="form-control"
                                               id="idService"
                                               style="line-height: 14px; height: 31px;"
                                               list="services"
                                               onchange="selectService();"
                                               autocomplete="off"
                                               required>
                                        <div class="input-group-append">
                                            <button class="btn btn-sm btn-success" onclick="clearService();"
                                                    type="button">
                                                <fmt:message key="clear_btn"/>
                                            </button>
                                        </div>
                                    </div>
                                    <datalist id="services" style="width: 100%">
                                        <c:forEach items="${groups}" var="paymentDatas">
                                            <option description="${paymentDatas.paymentDataDescription}"
                                                    placeholder="${paymentDatas.formatDataService}"
                                                    data-value="${paymentDatas.id}"
                                                    value="${paymentDatas.paymentDataName} (${paymentDatas.paymentDataGroup})">
                                            </option>
                                        </c:forEach>
                                    </datalist>
                                    <input type="hidden" id="serviceId" name="idService">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-md-8 mb-3 input-group-sm">
                                    <label for="orderNo"><i class="fas fa-coins"></i> <fmt:message
                                            key="order_field"/><span style="color: red;">*</span></label>
                                    <input type="text"
                                           class="form-control"
                                           id="orderNo"
                                           placeholder=" <fmt:message key="order_field"/>"
                                           name="orderNo"
                                           required
                                           autocomplete="off">
                                </div>
                                <div class="col-md-2 mb-3 input-group-sm">
                                    <label for="sum"><i class="fas fa-sort-numeric-up"></i> <fmt:message
                                            key="sum_field"/><span style="color: red;">*</span></label>
                                    <input type="number"
                                           class="form-control"
                                           id="sum"
                                           placeholder="0.00"
                                           name="sum"
                                           step="0.01"
                                           required
                                           style="text-align: right;">
                                </div>
                                <div class="col-md-2 mb-3 input-group-sm">
                                    <label for="code"><i class="fas fa-user-secret"></i> <fmt:message
                                            key="cv_code"/><span style="color: red;">*</span></label>
                                    <input type="password"
                                           class="form-control"
                                           id="code"
                                           placeholder="<fmt:message key="cv_code"/>"
                                           name="code"
                                           required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-md-8 mb-3 input-group-sm">
                                    <div id="serviceDescription" style="font-size: 10px;">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-md-12 mb-3 input-group-sm">
                                    <label for="description"><i class="far fa-comment-alt"></i> <fmt:message
                                            key="description_field"/><span style="color: red;">*</span></label>
                                    <div class="input-group">
                                        <input type="text"
                                               class="form-control"
                                               id="description"
                                               placeholder="<fmt:message key="description_field"/>"
                                               name="description"
                                               autocomplete="off"
                                               style="height: calc(1.8125rem + 2px);"
                                               required>
                                        <div class="input-group-append">
                                            <button class="btn btn-sm btn-success" onclick="clearDescription();"
                                                    type="button">
                                                <fmt:message key="clear_btn"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-md-1 mb-3 input-group-sm">
                                    <button class="btn btn-success form-control my-sm-0 my-2 btn-sm custom_button"
                                            id="save_btn"
                                            type="submit"
                                            name="command"
                                            value="save_pay_payment">
                                        <i class="fas fa-shopping-cart"></i> <fmt:message key="pay_btn"/>
                                    </button>
                                </div>
                            </div>
                        </form>
                        <div class="message-box">
                            ${messageSavePayment}
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>
<script>

    function clearService() {
        document.getElementById('idService').value = '';
        document.getElementById('serviceDescription').innerHTML = '';
    }

    function clearDescription() {
        document.getElementById('description').value = '';
    }

    function selectService() {
        var value = $('#idService').val();
        document.getElementById("serviceId").value = $('#services [value="' + value + '"]').data('value');
        document.getElementById("serviceDescription").innerHTML = $('#services [value="' + value + '"]').attr('description');
        $('#orderNo').attr('placeholder', $('#services [value="' + value + '"]').attr('placeholder'));
    }
</script>

