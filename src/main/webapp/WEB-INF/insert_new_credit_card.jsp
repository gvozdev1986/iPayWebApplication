<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<%@include file="user_navbar.jsp" %>
<script src="../js/jquery.maskedinput.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app.css">
<div class="container-fluid">
    <div class="row">
        <%@include file="user_menu.jsp" %>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2"><fmt:message key="new_credit_card_label"/></h1>
                <div class="btn-toolbar mb-2 mb-md-0"><fmt:message key="client_menu_accounting"/></div>
            </div>
            <div class="container" style="height: 60%; overflow-y: scroll; padding: 5px;">
                <div class="row">
                    <div class="col-md-8">
                        <form autocomplete="off" action="ServletController" method="post" name="createCreditCard">
                            <div class="form-row">
                                <div class="form-group col-md-6 input-group-sm">
                                    <label for="cardFirstName"><i class="fas fa-user"></i>
                                        <fmt:message key="first_name_card_label"/><span style="color: #fe0c00;">*</span>
                                    </label>
                                    <input type="text"
                                           class="form-control"
                                           id="cardFirstName"
                                           name="cardFirstName"
                                           placeholder="<fmt:message key="first_name_card_label"/>"
                                           autocomplete="off"
                                           oninvalid="this.setCustomValidity('<fmt:message key="please_enter_valid_first_name"/>')"
                                           onchange="validateFirstName();"
                                           required>
                                </div>
                                <div class="form-group col-md-6 input-group-sm">
                                    <label for="cardLastName"><i class="fas fa-user"></i>
                                        <fmt:message key="last_name_card_label"/><span style="color: #fe0c00;">*</span>
                                    </label>
                                    <input type="text"
                                           class="form-control"
                                           id="cardLastName"
                                           name="cardLastName"
                                           placeholder="<fmt:message key="last_name_card_label"/>"
                                           autocomplete="off"
                                           oninvalid="this.setCustomValidity('<fmt:message key="please_enter_valid_last_name"/>')"
                                           onchange="validateLastName();"
                                           required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-3 input-group-sm">
                                    <label for="cardNumber"><i class="far fa-credit-card"></i>
                                        <fmt:message key="credit_card_number_label"/><span style="color: #fe0c00;">*</span>
                                    </label>
                                    <input type="text"
                                           style="text-align: center;"
                                           class="form-control"
                                           id="cardNumber"
                                           name="cardNumber"
                                           placeholder="XXXX XXXX XXXX XXXX"
                                           autocomplete="off"
                                           oninvalid="this.setCustomValidity('<fmt:message key="please_enter_valid_card_number"/>')"
                                           oninput="setCustomValidity('')"
                                           required>
                                    <span class="validate">${messageCheckCreditCard}</span>
                                </div>
                                <div class="form-group col-md-1 input-group-sm">
                                    <label for="cardValidMonth">
                                        <fmt:message key="month"/><span style="color: #fe0c00;">*</span>
                                    </label>
                                    <input type="text"
                                           style="text-align: center;"
                                           maxlength="2"
                                           class="form-control"
                                           id="cardValidMonth"
                                           name="cardValidMonth"
                                           placeholder="XX"
                                           autocomplete="off"
                                           onchange="validateInputValidMonth()"
                                           required>
                                </div>
                                <div class="form-group col-md-1 input-group-sm">
                                    <label for="cardValidYear">
                                        <fmt:message key="year"/><span style="color: #fe0c00;">*</span>
                                    </label>
                                    <input type="text"
                                           style="text-align: center;"
                                           maxlength="2"
                                           class="form-control"
                                           id="cardValidYear"
                                           name="cardValidYear"
                                           placeholder="XX"
                                           autocomplete="off"
                                           onchange="validateInputValidYear()"
                                           required>
                                </div>
                                <div class="form-group col-md-4 input-group-sm">
                                    <label for="creditCardType">
                                        <i class="fas fa-list"></i>
                                        <fmt:message key="type_card_label"/><span style="color: #fe0c00;">*</span>
                                    </label>
                                    <select class="custom-select"
                                            id="creditCardType"
                                            name="creditCardType"
                                            style="line-height: 14px; height: 31px;">
                                        <option value="BELCARD">BELCARD</option>
                                        <option value="MASTERCARD">MASTERCARD</option>
                                        <option value="MASTERCARD_MAESTRO">MASTERCARD MAESTRO</option>
                                        <option value="VISA">VISA</option>
                                        <option value="VISA_ELECTRON">VISA ELECTRON</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3 input-group-sm">
                                    <label for="secretCode"><i class="fas fa-user-secret"></i>
                                        <fmt:message key="cv_code"/><span style="color: #fe0c00;">*</span>
                                    </label>
                                    <input type="password"
                                           class="form-control"
                                           id="secretCode"
                                           name="secretCode"
                                           placeholder="Secret code"
                                           autocomplete="off"
                                           required>
                                </div>
                            </div>
                            <div>
                                <a>${messageErrorInsertNewCreditCard}</a>
                            </div>
                            <div class="form-inline">
                                <!--<button class="btn btn-success form-control mr-sm-2 btn-sm custom_button"
                                        id="cancel_btn"
                                        type="submit"
                                        name="command"
                                        value="">
                                    <i class="fas fa-angle-left"></i> Cancel
                                </button>-->
                                <button class="btn btn-success form-control mr-sm-2 btn-sm custom_button"
                                        id="save_new_btn"
                                        type="submit"
                                        name="command"
                                        value="save_new_credit_card">
                                    <i class="fas fa-check"></i> <fmt:message key="save_btn"/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>
<script>

    $(function () {
        $("#mobile_phone").mask("9-999-999-99-99");
        $("#home_phone").mask("9-999-999-99-99");
        $("#cardNumber").mask("9999 9999 9999 9999");

        $("#cardValidMonth").mask("99");
        $("#cardValidYear").mask("99");
    });

    function validateInputValidMonth() {
        var str = document.getElementById('cardValidMonth').value;
        if (/(0[1-9]|1[012])/.test(str)) {
            document.getElementById('cardValidMonth').style.background = "#fff";
            document.getElementById('save_new_btn').removeAttribute('disabled');
        } else {
            document.getElementById('cardValidMonth').style.background = "#ffad99";
            document.getElementById('save_new_btn').setAttribute('disabled', 'disabled');
        }
    }

    function validateFirstName() {
        var str = document.getElementById('cardFirstName').value;
        if (/^[а-яА-ЯёЁa-zA-Z]+$/.test(str)) {
            document.getElementById('cardFirstName').style.background = "#fff";
            document.getElementById('save_new_btn').removeAttribute('disabled');
        } else {
            document.getElementById('cardFirstName').style.background = "#ffad99";
            document.getElementById('save_new_btn').setAttribute('disabled', 'disabled');
        }
    }

    function validateLastName() {
        var str = document.getElementById('cardLastName').value;
        if (/^[а-яА-ЯёЁa-zA-Z]+$/.test(str)) {
            document.getElementById('cardLastName').style.background = "#fff";
            document.getElementById('save_new_btn').removeAttribute('disabled');
        } else {
            document.getElementById('cardLastName').style.background = "#ffad99";
            document.getElementById('save_new_btn').setAttribute('disabled', 'disabled');
        }
    }

    function validateInputValidYear() {
        var d = new Date();
        var n = d.getFullYear();
        var s = n.toString().substr(2,2);
        var str = document.getElementById('cardValidYear').value;
        if (/[0-9]{2}/.test(str) && str > s) {
            document.getElementById('cardValidYear').style.background = "#fff";
            document.getElementById('save_new_btn').removeAttribute('disabled');
        } else {
            document.getElementById('cardValidYear').style.background = "#ffad99";
            document.getElementById('save_new_btn').setAttribute('disabled', 'disabled');
        }
    }

</script>
