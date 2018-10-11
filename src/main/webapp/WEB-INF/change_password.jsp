<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@include file="user_navbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="user_menu.jsp" %>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2"><fmt:message key="change_password_label"/></h1>
                <div class="btn-toolbar mb-2 mb-md-0"><fmt:message key="change_password_group"/></div>
            </div>
            <div class="container">
                <form action="ServletController" method="post">
                    <input type="hidden" name="userId" value="${user.id}"/>
                    <div class="form-row">
                        <div class="col-md-3 mb-3 input-group-sm">
                            <label for="current_password"><i class="fas fa-key"></i>
                                <fmt:message key="current_password"/><span style="color: #fe0c00;">*</span></label>
                            <input type="hidden" name="current_password" value="" id="hiddenCurrentLoginPSWD">
                            <input type="password"
                                   class="form-control"
                                   id="current_password"
                                   placeholder="<fmt:message key="current_password"/>"
                                   oninput="codeCurrentPswd();"
                                   autocomplete="off"
                                   required>
                        </div>
                        <div class="col-md-4 mb-3 input-group-sm">
                            <label for="new_password"><i class="fas fa-key"></i>
                                <fmt:message key="new_password"/><span style="color: #fe0c00;">*</span></label>
                            <input type="hidden" name="new_password" value="" id="hiddenLoginPSWD">
                            <input type="password"
                                   class="form-control"
                                   id="new_password"
                                   autocomplete="off"
                                   oninput="codeNewPswd();"
                                   placeholder="<fmt:message key="new_password"/>"
                                   onchange="validateInputPassword();"
                                   required>
                        </div>
                        <div class="col-md-4 mb-3 input-group-sm">
                            <label for="verify_new_password"><i class="fas fa-key"></i>
                                <fmt:message key="verify_password"/><span style="color: #fe0c00;">*</span></label>
                            <input type="hidden" name="verify_new_password" value="" id="hiddenVerifyLoginPSWD">
                            <input type="password"
                                   class="form-control"
                                   id="verify_new_password"
                                   autocomplete="off"
                                   oninput="codeVerifyNewPswd();"
                                   placeholder="<fmt:message key="verify_password"/>"
                                   onchange="validateNewPswd();"
                                   required>
                        </div>
                        <div class="col-md-1 mb-3 input-group-sm">
                            <div style="margin-top: 31px;"></div>
                            <button class="btn btn-success form-control my-sm-0 my-2 btn-sm custom_button"
                                    id="save_btn"
                                    type="submit"
                                    name="command"
                                    value="update_password">
                                <i class="fas fa-save"></i> <fmt:message key="save_btn"/>
                            </button>
                        </div>
                    </div>
                </form>
                <p style="color: #fe0c00"><fmt:message key="${messageErrorSavePassword}"/></p>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>
<script>
    function validateInputPassword() {
        var str = document.getElementById('new_password').value;
        if (/[a-zA-Z]/.test(str)) {
            document.getElementById('new_password').style.background = "#fff";
            document.getElementById('save_btn').removeAttribute('disabled');
        } else {
            document.getElementById('new_password').style.background = "#ffad99";
            document.getElementById('save_btn').setAttribute('disabled', 'disabled');
        }
    }

    function validateNewPswd() {
        var pswd1 = document.getElementById('new_password').value;
        var pswd2 = document.getElementById('verify_new_password').value;
        if (pswd1 === pswd2) {
            document.getElementById('verify_new_password').style.background = "#fff";
            document.getElementById('save_btn').removeAttribute('disabled');
        } else {
            document.getElementById('verify_new_password').style.background = "#ffad99";
            document.getElementById('save_btn').setAttribute('disabled', 'disabled');
        }
    }
</script>
