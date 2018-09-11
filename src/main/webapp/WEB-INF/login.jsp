<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="head.jsp"/>
<%@ taglib prefix="ctg" uri="customtags" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="Resource"/>

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
        max-width: 200px;
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
                <li class="nav-item active"><a class="nav-link" href="#"><i
                        class="fa fa-home"></i> Home<span class="sr-only">(current)</span></a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i
                        class="fas fa-pencil-alt"></i> Write us</a></li>
            </ul>
        </div>
        <!-- <form class="form-inline my-2 my-lg-0" action="ServletController" method="post">
			    <jsp:include page="localization_btn.jsp" />
		</form> -->
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
                                key="login_login"/></label> <input type="text" class="form-control"
                                                                   id="loginInput" name="username" value=""
                                                                   autocomplete="off"
                                                                   placeholder="<fmt:message key="login_login" />"
                                                                   required
                                                                   oninvalid="this.setCustomValidity('Please fill login field')"
                                                                   oninput="setCustomValidity('')">
                    </div>
                    <div class="form-group input-group-sm">
                        <label for="pswInput"><i class="fas fa-key"></i> <fmt:message
                                key="login_password"/></label> <input type="password"
                                                                      class="form-control" id="pswInput" name="password"
                                                                      value=""
                                                                      autocomplete="off"
                                                                      placeholder="<fmt:message key="login_password" />"
                                                                      required
                                                                      oninvalid="this.setCustomValidity('Please fill password field')"
                                                                      oninput="setCustomValidity('')">
                    </div>
                    <c:if test="${not empty info_message}">
                        <a><fmt:message key="invalid_login_or_password"/></a>
                    </c:if>
                    <c:if test="${not empty info_message_available}">
                        <a><fmt:message key="user_not_available"/></a>
                    </c:if>
                    <div class="form-inline">
                        <button class="btn btn-success form-control mr-sm-2 btn-sm custom_button"
                                type="submit" name="command" value="log_out">
                            <i class="fas fa-undo"></i>
                            <fmt:message key="login_cancel"/>
                        </button>
                        <button class="btn btn-success form-control my-sm-0 my-2 btn-sm custom_button"
                                type="submit" name="command" value="authorization_user">
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