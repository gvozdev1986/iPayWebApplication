<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<%@include file="head.jsp" %>
<link rel="stylesheet" href="../css/bootstrap-datepicker3.min.css">
<script type="text/javascript" src="../js/bootstrap-datepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/bootstrap-datepicker.ru.js" charset="UTF-8"></script>
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

    .custom_button {
        min-width: 100px;
        max-width: 100px;
    }

    ::-webkit-scrollbar {
        width: 0px;
        height: 0px;
    }

    ::-webkit-scrollbar-button {
        background: #ccc
    }

    ::-webkit-scrollbar-track-piece {
        background: #888
    }

    ::-webkit-scrollbar-thumb {
        background: #eee
    }

    .card-header {
        padding: .75rem 1.25rem;
        margin-bottom: 0;
        position: relative;
        background: url("/img/j.jpg") no-repeat center center;
        width: 100%;
        height: 6%;
        background-size: 100% 100%;
        color: #fff;
    }

    .validate {
        font-size: 9px;
        color: red;
    }

    .cont-form {
        height: 80%;
        overflow-y: scroll;
        padding: 5px;
        margin-top: 63px;
    }
</style>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <a class="navbar-brand"
           href="ServletController?command=greeting_page_view"> <img
                src="img/logo.png" width="30" height="30"
                class="d-inline-block align-top" alt=""> iPay
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">

            </ul>
        </div>
        <form class="form-inline my-2 my-lg-0" action="ServletController"
              method="post">
            <jsp:include page="localization_btn.jsp"/>
        </form>
    </nav>
</header>
<div class="container cont-form">
    <div class="row" style="margin-right: -10px; margin-left: -10px;">
        <div class="col-md-12 order-md-1">
            <h4 class="mb-3"><fmt:message key="form_label"/></h4>
            <form action="ServletController" method="post">
                <div class="form-row">
                    <div class="col-md-4 mb-3 input-group-sm">
                        <label for="login"><i class="fas fa-user-plus"></i>
                            <fmt:message key="form_login"/><span style="color: #fe0c00;">*</span>
                        </label>
                        <input type="text"
                               class="form-control"
                               id="login"
                               placeholder="<fmt:message key="form_login" />"
                               value="${returnValidateErrorMap.returnLoginValidateError}"
                               name="username"
                               required
                               oninput="validateInputLogin();"
                               autocomplete="off">
                        <span class="validate">${validateErrorMap.loginValidateError}</span>
                    </div>
                    <div class="col-md-4 mb-3 input-group-sm">
                        <label for="pswd"><i class="fas fa-key"></i>
                            <fmt:message key="form_password"/><span style="color: #fe0c00;">*</span>
                        </label>
                        <input type="password"
                               class="form-control"
                               id="pswd"
                               placeholder="<fmt:message key="form_password" />" value=""
                               name="password"
                               required
                               oninput="validateInputPassword();"
                               autocomplete="off">
                        <span class="validate">${validateErrorMap.passwordValidateError}</span>
                    </div>
                    <div class="col-md-4 mb-3 input-group-sm">
                        <label for="pswd_verify"><i class="fas fa-check"></i>
                            <fmt:message key="form_verify"/><span style="color: #fe0c00;">*</span>
                        </label>
                        <input type="password"
                               class="form-control"
                               id="pswd_verify"
                               placeholder="<fmt:message key="form_verify" />" value=""
                               name="password_verify"
                               required
                               autocomplete="off">
                        <span class="validate">${validateErrorMap.passwordVerifyValidateError}</span>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-4 mb-3 input-group-sm">
                        <label for="last_name"><i class="fas fa-pencil-alt"></i>
                            <fmt:message key="form_last_name"/><span style="color: #fe0c00;">*</span>
                        </label>
                        <input type="text"
                               class="form-control"
                               id="last_name"
                               placeholder="<fmt:message key="form_last_name" />"
                               value="${returnValidateErrorMap.returnLastNameValidateError}"
                               name="last_name"
                               required
                               autocomplete="off">
                        <span class="validate">${validateErrorMap.lastNameValidateError}</span>
                    </div>
                    <div class="col-md-3 mb-3 input-group-sm">
                        <label for="first_name"><i class="fas fa-pencil-alt"></i>
                            <fmt:message key="form_first_name"/><span style="color: #fe0c00;">*</span>
                        </label>
                        <input type="text"
                               class="form-control"
                               id="first_name"
                               placeholder="<fmt:message key="form_first_name" />"
                               value="${returnValidateErrorMap.returnFirstNameValidateError}"
                               name="first_name"
                               required
                               autocomplete="off">
                        <span class="validate">${validateErrorMap.firstNameValidateError}</span>
                    </div>
                    <div class="col-md-3 mb-3 input-group-sm">
                        <label for="patronymic"><i class="fas fa-pencil-alt"></i>
                            <fmt:message key="form_patronymic"/><span style="color: #fe0c00;">*</span>
                        </label>
                        <input type="text"
                               class="form-control"
                               id="patronymic"
                               placeholder="<fmt:message key="form_patronymic" />"
                               value="${returnValidateErrorMap.returnPatronymicValidateError}"
                               name="patronymic"
                               required
                               autocomplete="off">
                        <span class="validate">${validateErrorMap.patronymicValidateError}</span>
                    </div>
                    <div class="col-md-2 mb-3 input-group-sm">
                        <label for="date_birth"><i class="far fa-calendar-alt"></i>
                            <fmt:message key="form_date_birth"/><span style="color: #fe0c00;">*</span>
                        </label>
                        <input type="text"
                               name="date_birth"
                               placeholder="<fmt:message key="form_date_birth" />"
                               value="${returnValidateErrorMap.returnDateBirthValidateError}"
                               class="form-control datepicker"
                               id="date_birth"
                               style=""
                               required
                               autocomplete="off">
                        <span class="validate">${validateErrorMap.dateBirthValidateError}</span>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-4 mb-3 input-group-sm">
                        <label for="home_phone"><i class="fas fa-phone"></i>
                            <fmt:message key="form_home_phone"/><span style="color: #fe0c00;">*</span>
                        </label>
                        <input type="tel"
                               class="form-control"
                               id="home_phone"
                               placeholder="X-XXX-XXX-XX-XX"
                               value="${returnValidateErrorMap.returnHomePhoneValidateError}"
                               name="home_phone"
                               required
                               autocomplete="off">
                        <span class="validate">${validateErrorMap.homePhoneValidateError}</span>
                    </div>
                    <div class="col-md-4 mb-3 input-group-sm">
                        <label for="mobile_phone"><i class="fas fa-mobile-alt"></i>
                            <fmt:message key="form_mobile_phone"/><span style="color: #fe0c00;">*</span>
                        </label>
                        <input type="tel"
                               class="form-control"
                               id="mobile_phone"
                               placeholder="X-XXX-XXX-XX-XX"
                               value="${returnValidateErrorMap.returnMobilePhoneValidateError}"
                               name="mobile_phone"
                               required
                               autocomplete="off">
                        <span class="validate">${validateErrorMap.mobilePhoneValidateError}</span>
                    </div>
                    <div class="col-md-4 mb-3 input-group-sm">
                        <label for="email"><i class="fas fa-envelope-open"></i>
                            <fmt:message key="form_email"/><span style="color: #fe0c00;">*</span>
                        </label>
                        <input type="email"
                               class="form-control"
                               id="email"
                               placeholder="<fmt:message key="form_email" />"
                               value="${returnValidateErrorMap.returnEmailValidateError}"
                               name="email"
                               required
                               oninput="validateInputEmail();"
                               autocomplete="off">
                        <span class="validate">${validateErrorMap.emailValidateError}</span>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-12 mb-3 input-group-sm">
                        <label for="address"><i class="fas fa-map-pin"></i>
                            <fmt:message key="form_address"/><span style="color: #fe0c00;">*</span>
                        </label>
                        <textarea class="form-control"
                                  aria-label="With textarea"
                                  id="address"
                                  name="address"
                                  placeholder="<fmt:message key="form_address" />"
                                  required
                                  autocomplete="off">${returnValidateErrorMap.returnAddressValidateError}</textarea>
                        <span class="validate">${validateErrorMap.addressValidateError}</span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value=""
                               id="invalidCheck" onclick="agree();" required> <label
                            class="form-check-label" for="invalidCheck"> <fmt:message
                            key="reg_agree_checkbox"/>
                    </label>
                    </div>
                </div>
                <div class="form-inline">
                    <button class="btn btn-success form-control mr-sm-2 btn-sm custom_button"
                            type="submit"
                            name="command"
                            value="log_out"><i class="fas fa-undo"></i> Cancel
                    </button>
                    <button
                            class="btn btn-success form-control my-sm-0 my-2 btn-sm custom_button"
                            id="save_btn" type="submit" name="command"
                            value="save_registration" disabled>
                        <i class="fas fa-check"></i>
                        <fmt:message key="reg_save_btn"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<ctg:footer/>
<script src="../js/jquery.maskedinput.js"></script>
<script>
    $(document).ready(function () {
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            language: '${locale}'
        });
    });

    function agree() {
        save_btn = document.getElementById("save_btn");
        save_btn.disabled = !(save_btn.disabled !== false);
    }

    $(function () {
        $("#mobile_phone").mask("9-999-999-99-99");
        $("#home_phone").mask("9-999-999-99-99");
    });

    function validateInputPassword() {
        var str = document.getElementById('pswd').value;
        if (/[a-zA-Z]/.test(str)) {
            document.getElementById('pswd').style.background = "#fff";
        } else {
            document.getElementById('pswd').style.background = "#ffad99";
        }
    }

    function validateInputEmail() {
        var str = document.getElementById('email').value;
        if (/^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$/.test(str)) {
            document.getElementById('email').style.background = "#fff";
        } else {
            document.getElementById('email').style.background = "#ffad99";
        }
    }

    function validateInputLogin() {
        var str = document.getElementById('login').value;
        if (/[0-9a-zA-Z_@$]{5,}/.test(str)) {
            document.getElementById('login').style.background = "#fff";
        } else {
            document.getElementById('login').style.background = "#ffad99";
        }
    }

</script>