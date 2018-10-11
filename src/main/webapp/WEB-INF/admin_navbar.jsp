<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="Resource"/>

<%@include file="head.jsp" %>
<style>
    .btn-success {
        color: #fff;
        background-color: #00ad7e;
        border-color: #00ad7e;
    }

    .btn-success:hover {
        color: #fff;
        background-color: #00ad7e;
        border-color: #00ad7e;
    }
</style>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <a class="navbar-brand"
           href="ServletController?command=admin_panel_view"> <img
                src="../img/logo.png" width="30" height="30"
                class="d-inline-block align-top" alt=""> iPay
        </a>
        ${messageStatus}
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto" style="color: #fff;">
                <li><fmt:message key="admin"/>: ${user.getLastName()}
                    ${user.getFirstName()} ${user.getPatronymic()}</li>
            </ul>
        </div>
        <form class="form-inline my-2 my-lg-0"
              action="ServletController"
              method="get">
            <button class="btn btn-danger form-control my-sm-0 my-2 btn-sm"
                    type="submit"
                    name="command"
                    style="margin-right: 8px; width: auto;"
                    value="list_message_view">
                <i class="far fa-bell">
                    ${countUnreadMessage}
                </i>
            </button>
            <jsp:include page="localization_btn.jsp"/>
            <button class="btn btn-success form-control my-sm-0 my-2 btn-sm"
                    type="submit" name="command" value="log_out">
                <i class="fas fa-sign-out-alt"></i>
                <fmt:message key="log_out"/>
            </button>
        </form>
    </nav>
</header>