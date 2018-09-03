<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<jsp:include page="head.jsp"/>
<style>
    .jumbotron {
        position: relative;
        background: url("/img/j.jpg") no-repeat center center;
        width: 100%;
        height: 30%;
        background-size: 100% 100%;
    }
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

    .button.btn.btn-default.btn-xl:focus {
        outline: 0px;
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
    }​
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
            <form class="form-inline my-2 my-lg-0" action="ServletController"
                  method="get">
                <jsp:include page="localization_btn.jsp"/>
                <button class="btn btn-success form-control mr-sm-2 btn-sm"
                        type="submit" name="command" value="login_page_view">
                    <i class="fa fa-user"></i>
                    <fmt:message key="greeting_login"/>
                </button>
                <button class="btn btn-success form-control my-sm-0 my-2 btn-sm"
                        type="submit" name="command" value="registration_page_view">
                    <i class="fas fa-user-plus"></i>
                    <fmt:message key="greeting_registration"/>
                </button>
            </form>
        </div>
    </nav>
</header>
<div class="jumbotron jumbotron-fluid"
     style="margin-top: 30px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);">
    <div class="container" style="color: white; margin-top: -2%;">
        <h1 class="display-4" style="margin-top: -15px; font-size: 4.5rem;">iPay</h1>
        <p class="lead" style="font-size: 2.25rem;">
            &#60;<i><fmt:message key="greeting_slogan"/><a id="blink">_</a></i>&#62;
        </p>
    </div>
</div>
<div class="container">
    <div class="card my-2">
        <div class="card-body">
            Some content. ${sessionScope.locale}
        </div>
    </div>
    <div class="card my-2">
        <div class="card-body">
            Some content. ${sessionScope.locale}
        </div>
    </div>
    <div class="card my-2">
        <div class="card-header">
            <i class="fas fa-pencil-alt" data-toggle="collapse"
               href="#multiCollapseExample1" role="button" aria-expanded="false"
               aria-controls="multiCollapseExample1"></i>
            <fmt:message key="greeting_write_us"/>
        </div>
        <div class="collapse multi-collapse" id="multiCollapseExample1">
            <div class="card-body">
                <form action="ServletController" method="post">
                    <div class="form-row">
                        <div class="form-group col-md-4 input-group-sm">
                            <label for="name_contact"><i class="fas fa-user-edit"></i>
                                <fmt:message key="greeting_name"/></label> <input type="text"
                                                                                  class="form-control" id="name_contact"
                                                                                  placeholder="<fmt:message key="greeting_name" />"
                                                                                  name="name_contact" required>
                            <!-- <div class="valid-feedback">Looks good!</div> -->
                        </div>
                        <div class="form-group col-md-4 input-group-sm">
                            <label for="email_contact"><i
                                    class="fas fa-envelope-open"></i> <fmt:message
                                    key="greeting_email"/></label> <input type="email"
                                                                          class="form-control" id="email_contact"
                                                                          placeholder="<fmt:message key="greeting_email" />"
                                                                          name="email_contact" required>
                            <!-- <div class="valid-feedback">Looks good!</div> -->
                        </div>
                        <div class="form-group col-md-4 input-group-sm">
                            <label for="phone_contact"><i class="fas fa-mobile-alt"></i>
                                <fmt:message key="greeting_phone"/></label> <input type="tel"
                                                                                   class="form-control"
                                                                                   id="phone_contact"
                                                                                   placeholder="<fmt:message key="greeting_phone" />"
                                                                                   name="phone_contact" required>
                            <!-- <div class="valid-feedback">Looks good!</div> -->
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3 input-group-sm">
                            <label for="message_contact"><i class="fas fa-comments"></i>
                                <fmt:message key="greeting_message"/></label>
                            <textarea maxlength="1000" class="form-control" rows="10" aria-label="With textarea"
                                      id="message_contact" name="message_contact"
                                      placeholder="<fmt:message key="greeting_message" />" required></textarea>
                            <!-- <div class="valid-feedback">Looks good!</div> -->
                        </div>
                    </div>

                    <div class="form-inline">
                        <button
                                class="btn btn-success form-control my-sm-0 my-2 btn-sm custom_button"
                                id="save_btn" type="submit" name="command"
                                value="save_message_contact">
                            <i class="fas fa-share-square"></i>
                            <fmt:message key="greeting_send"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<br/>
<footer class="page-footer font-small teal pt-4"
        style="background-color: #00ad7e !important; margin-top: 40px; padding-left: 0; padding-bottom: 8px; color: #ffffff; box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);">
    <div class="container-fluid text-center text-md-left">
        <div class="row">
            <div class="col-md-6 mt-md-0 mt-3">
                <h5 class="text-uppercase font-weight-bold">
                    <fmt:message key="greeting_contacts"/>
                </h5>
                <ul class="list-unstyled">
                    <li><a href="tel:+375291473624" style="color: #ffffff"><i
                            class="fas fa-mobile-alt"></i> +375(29)147-36-24</a></li>
                    <li><a href="skype:gvozdev_1?call" style="color: #ffffff"><i
                            class="fab fa-skype"></i> gvozdev_1</a></li>
                    <li><a href="mailto:aliaksandr.hvozdzeu@gmail.com"
                           style="color: #ffffff"><i class="fas fa-at"></i>
                        aliaksandr.hvozdzeu@gmail.com</a></li>
                </ul>
            </div>
            <ctg:about/>
        </div>
    </div>
    <ctg:greeting_footer/>
</footer>





