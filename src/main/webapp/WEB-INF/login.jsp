<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="head.jsp"/>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="Resource"/>
<jsp:include page="head.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app.css">
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
    </nav>
</header>
<div class="container h-100">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="card" style="box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);">
            <div class="card-header">
                <fmt:message key="login_authorization"/>
            </div>
            <div class="card-body">
                <form action="ServletController" method="post">
                    <div class="form-group input-group-sm">
                        <label for="loginInput"><i class="fas fa-user"></i> <fmt:message
                                key="login_login"/></label>
                        <input type="text" class="form-control"
                               id="loginInput" name="username" value=""
                               autocomplete="off"
                               placeholder="<fmt:message key="login_login" />"
                               required
                               oninvalid="this.setCustomValidity('<fmt:message key="please_enter_valid_login" />')"
                               oninput="setCustomValidity('')">
                    </div>
                    <div class="form-group input-group-sm">
                        <label for="pswInput"><i class="fas fa-key"></i> <fmt:message key="login_password"/></label>
                        <input type="hidden" name="password" value="" id="hiddenPSWD">
                        <input type="password"
                               class="form-control"
                               id="pswInput"
                               oninput="code();"
                               autocomplete="off"
                               placeholder="<fmt:message key="login_password" />"
                               required
                               oninvalid="this.setCustomValidity('<fmt:message key="please_enter_valid_password" />')">
                    </div>
                    <c:if test="${not empty info_message}">
                        <p style="color: red;"><fmt:message key="invalid_login_or_password"/></p>
                    </c:if>
                    <c:if test="${not empty info_message_available}">
                        <p style="color: red;"><fmt:message key="user_not_available"/></p>
                    </c:if>
                    <div class="form-inline">
                        <button class="btn btn-success form-control mr-sm-2 btn-sm custom_button"
                                type="button"
                                onClick='location.href="http://localhost/"'>
                            <i class="fas fa-undo"></i>
                            <fmt:message key="login_cancel"/>
                        </button>
                        <button class="btn btn-success form-control my-sm-0 my-2 btn-sm custom_button"
                                type="submit"
                                name="command"
                                id="okBtn"
                                value="authorization_user">
                            <i class="fas fa-check"></i>
                            <fmt:message key="login_ok"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<ctg:footer/>