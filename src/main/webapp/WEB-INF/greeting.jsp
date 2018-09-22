<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="date" class="java.util.Date"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<jsp:include page="head.jsp"/>
<style>
    .card-head {
        padding: .75rem 1.25rem;
        margin-bottom: 0;
        position: relative;
        background: url("/img/header-part.png") no-repeat center center;
        width: 100%;
        height: 7%;
        background-size: 100% 100%;
        color: #fff;
    }
    .msgEvent{
        font-size: 16px;
        color: #00ad7e;
        margin-left: 21px;
    }
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <a class="navbar-brand"
           href="http://localhost/"> <img
                src="${pageContext.request.contextPath}/img/logo.png" width="30" height="30"
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
            <form class="form-inline my-2 my-lg-0" action="ServletController"
                  method="get">
                <jsp:include page="localization_btn.jsp"/>
                <button class="btn btn-success form-control mr-sm-2 btn-sm"
                        type="submit" name="command" value="login_page_view">
                    <i class="fa fa-user"></i>
                    <fmt:message key="greeting_login"/>
                </button>
                <button class="btn btn-success form-control my-sm-0 my-2 btn-sm"
                        id="registrationViewBtn"
                        type="submit"
                        name="command"
                        value="registration_page_view">
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
    <div class="card my-2" style="border: 1px solid #00ad7e;">
        <div class="card-body">
            <div class="row">
                <div class="col-md-4 col-sm-6">
                    <div class="progress pink">
                        <span class="progress-left"><span class="progress-bar"></span></span>
                        <span class="progress-right"><span class="progress-bar"></span></span>
                        <div class="progress-value">${countUser}</div>
                    </div>
                    <p style="width: 100%; text-align: center;"><fmt:message key="count_user"/></p>
                </div>
                <div class="col-md-4 col-sm-6">
                    <div class="progress blue">
                        <span class="progress-left"><span class="progress-bar"></span></span>
                        <span class="progress-right"><span class="progress-bar"></span></span>
                        <div class="progress-value">${countOperation}</div>
                    </div>
                    <p style="width: 100%; text-align: center;"><fmt:message key="count_operation"/></p>
                </div>
                <div class="col-md-4 col-sm-6">
                    <div class="progress green">
                        <span class="progress-left"><span class="progress-bar"></span></span>
                        <span class="progress-right"><span class="progress-bar"></span></span>
                        <div class="progress-value">${countService}</div>
                    </div>
                    <p style="width: 100%; text-align: center;"><fmt:message key="count_services"/></p>
                </div>
            </div>
        </div>
    </div>
    <div class="card my-2" style="border: 1px solid #00ad7e;">
        <div class="row" style="padding: 5px;">
            <div class="col-xl-5">
                <div class="row">
                    <div class="col-lg-6 my-1">
                        <div class="card widget-flat back-st">
                            <div class="card-head" style="text-align: center; font-size: 11px;">USD (<fmt:message
                                    key="usd"/>)
                            </div>
                            <div class="card-body">
                                <h5 class="card-title" style="text-align: right;">$ ${currencyMap.USD}<span
                                        style="font-size: 12px;">/1</span></h5>
                                <span class="card-text" style="font-size: 9px;"><fmt:formatDate value="${date}"
                                                                                                pattern="yyyy-MM-dd"/></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 my-1">
                        <div class="card widget-flat back-st">
                            <div class="card-head" style="text-align: center; font-size: 11px;">EUR (<fmt:message
                                    key="eur"/>)
                            </div>
                            <div class="card-body">
                                <h5 class="card-title" style="text-align: right;">&#8364; ${currencyMap.EUR}<span
                                        style="font-size: 12px;">/1</span></h5>
                                <span class="card-text" style="font-size: 9px;"><fmt:formatDate value="${date}"
                                                                                                pattern="yyyy-MM-dd"/></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6 my-1">
                        <div class="card widget-flat back-st">
                            <div class="card-head" style="text-align: center; font-size: 11px;">RUB (<fmt:message
                                    key="rus"/>)
                            </div>
                            <div class="card-body">
                                <h5 class="card-title" style="text-align: right;">P ${currencyMap.RUB}<span
                                        style="font-size: 12px;">/100</span></h5>
                                <span class="card-text" style="font-size: 9px;"><fmt:formatDate value="${date}"
                                                                                                pattern="yyyy-MM-dd"/></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 my-1">
                        <div class="card widget-flat back-st">
                            <div class="card-head" style="text-align: center; font-size: 11px;">UAH (<fmt:message
                                    key="uah"/>)
                            </div>
                            <div class="card-body">
                                <h5 class="card-title" style="text-align: right;">&#8372; ${currencyMap.UAH}<span
                                        style="font-size: 12px;">/100</span></h5>
                                <span class="card-text" style="font-size: 9px;"><fmt:formatDate value="${date}"
                                                                                                pattern="yyyy-MM-dd"/></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-7 my-1">
                <div class="card" style="border: 1px solid #00ad7e;">
                    <div class="card-body">
                        <div class="card-style" style="height: 43.5%;">
                            <iframe src="https://yandex.ru/map-widget/v1/?um=constructor%3A293654bbcc42abb45ce28f809716e17f93437bd8c01ba0cce4b8a98f88e2f26e&amp;source=constructor" width="100%" height="100%" frameborder="0"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="card my-2" style="border: 1px solid #00ad7e;">
        <div class="card-head">
            <i class="fas fa-pencil-alt" data-toggle="collapse"
               href="#multiCollapseExample1" role="button" aria-expanded="false"
               aria-controls="multiCollapseExample1"></i>
            <fmt:message key="greeting_write_us"/>
        </div>
        <div>
            <div class="card-body">
                <form action="ServletController" method="post">
                    <div class="form-row">
                        <div class="form-group col-md-4 input-group-sm">
                            <label for="name_contact"><i class="fas fa-user-edit"></i>
                                <fmt:message key="greeting_name"/></label>
                            <input type="text"
                                   class="form-control" id="name_contact"
                                   placeholder="<fmt:message key="greeting_name" />"
                                   value="${returnValidateErrorMap.returnNameValidateError}"
                                   name="name_contact"
                                   required>
                            <span class="validate">${validateErrorMap.nameValidateError}</span>
                        </div>
                        <div class="form-group col-md-4 input-group-sm">
                            <label for="email_contact"><i class="fas fa-envelope-open"></i> <fmt:message key="greeting_email"/></label>
                            <input type="email"
                                   class="form-control"
                                   id="email_contact"
                                   placeholder="<fmt:message key="greeting_email" />"
                                   value="${returnValidateErrorMap.returnEmailValidateError}"
                                   name="email_contact"
                                   required>
                            <span class="validate">${validateErrorMap.emailValidateError}</span>
                        </div>
                        <div class="form-group col-md-4 input-group-sm">
                            <label for="phone_contact"><i class="fas fa-mobile-alt"></i>
                                <fmt:message key="greeting_phone"/></label>
                            <input type="tel"
                                   class="form-control"
                                   id="phone_contact"
                                   placeholder="<fmt:message key="greeting_phone" />"
                                   value="${returnValidateErrorMap.returnPhoneValidateError}"
                                   name="phone_contact"
                                   required>
                            <span class="validate">${validateErrorMap.phoneValidateError}</span>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3 input-group-sm">
                            <label for="message_contact"><i class="fas fa-comments"></i>
                                <fmt:message key="greeting_message"/></label>
                            <textarea maxlength="1000" class="form-control" rows="10" aria-label="With textarea"
                                      id="message_contact" name="message_contact"
                                      placeholder="<fmt:message key="greeting_message" />" required>${returnValidateErrorMap.returnMessageContactValidateError}</textarea>
                        </div>
                    </div>
                    <div class="form-inline">
                        <button class="btn btn-success form-control my-sm-0 my-2 btn-sm custom_button"
                                id="save_btn"
                                type="submit"
                                name="command"
                                value="save_message_contact">
                            <i class="fas fa-share-square"></i>
                            <fmt:message key="greeting_send"/>
                        </button>
                        <c:if test = "${messageEvent != null}">
                            <span class="msgEvent"><fmt:message key="${messageEvent}"/></span>
                        </c:if>
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