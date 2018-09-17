<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<jsp:include page="head.jsp"/>
<style>
    .btn-success {
        color: #fff;
        background-color: #00ad7e;
        border-color: #00ad7e;
        width: 80px;
        margin-top: 15px;
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
    </nav>
</header>
<div class="container cont-form">
    <div class="row">
        <div class="col-md-6 order-md-1">
            <h4 class="mb-3">SUCCESS REGISTRATION</h4>
            <form action="ServletController" method="post">
                <div class="form-row">
                    A confirmation letter was sent to the post office.
                    <br/>
                    After the exposure, the account will be available.
                </div>
                <div class="form-row">
                    <a class="navbar-brand" href="ServletController?command=greeting_page_view"><i class="fas fa-sign-out-alt"></i> Back</a>
                    <!--<button class="btn btn-success form-control mr-sm-2 btn-sm"
                            type="submit"
                            name="command"
                            value="log_out"><i class="fas fa-sign-out-alt"></i> Back
                    </button>-->
                </div>
            </form>
        </div>
    </div>
</div>
<ctg:footer/>