<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
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
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2"><fmt:message key="transfer_alien_card_label"/></h1>
                <div class="btn-toolbar mb-2 mb-md-0"><fmt:message key="transfer_alient_card_group"/></div>
            </div>
            <div class="container">
                <form action="ServletController" method="post" name="transferForm">
                    <input type="hidden" name="clientId" value="${client.id}"/>
                    <div class="form-row">
                        <div class="col-md-5 mb-3 input-group-sm">
                            <label for="idCardFromTransf"><i
                                    class="far fa-credit-card"></i> <fmt:message key="card_from"/></label>
                            <select class="custom-select"
                                    id="idCardFromTransf"
                                    name="idCardFromTransf"
                                    style="line-height: 14px; height: 31px;">
                                <c:forEach items="${cards}" var="creditCards">
                                    <c:if test="${not creditCards.block}">
                                        <option value="${creditCards.id}">${creditCards.cardNumber}
                                            / ${creditCards.balanceBankAccount}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div id="anotherCardBlock" class="col-md-5 mb-3 input-group-sm">
                            <label for="anotherCardInput"><i class="far fa-credit-card"></i> <fmt:message key="alien_credit_card_number"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="anotherCardInput"
                                   placeholder="XXXX XXXX XXXX XXXX"
                                   name="anotherCardInput"
                                   pattern="[0-9]{4}\s[0-9]{4}\s[0-9]{4}\s[0-9]{4}"
                                   required
                                   autocomplete="off">
                        </div>
                        <div class="col-md-2 mb-3 input-group-sm">
                            <label for="sumCardTransf"><i class="fas fa-sort-numeric-up"></i> <fmt:message key="sum_transaction"/></label>
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
                    </div>
                    <div class="form-row">
                        <div class="col-md-10 mb-3 input-group-sm">
                            <label for="descriptionCardTransf"><i class="far fa-comment-alt"></i> <fmt:message key="description_field"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="descriptionCardTransf"
                                   placeholder="<fmt:message key="description_field"/>"
                                   name="descriptionCardTransf"
                                   required
                                   autocomplete="off">
                        </div>
                        <div class="col-md-1 mb-3 input-group-sm">
                            <label for="code"><i class="fas fa-user-secret"></i> <fmt:message key="code_field"/></label>
                            <input type="password"
                                   class="form-control"
                                   id="code"
                                   placeholder="<fmt:message key="code_field"/>"
                                   name="code"
                                   required
                                   autocomplete="off">
                        </div>
                        <div class="col-md-1 mb-3 input-group-sm">
                            <div style="margin-top: 31px;"></div>
                            <button class="btn btn-success form-control my-sm-0 my-2 btn-sm custom_button"
                                    id="save_btn"
                                    type="submit"
                                    name="command"
                                    value="save_transfer_alien_credit_card">
                                <i class="fas fa-save"></i> <fmt:message key="save_btn"/>
                            </button>
                        </div>
                    </div>
                </form>
                <div class="message-box">
                    ${messageFromTransfer}
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
