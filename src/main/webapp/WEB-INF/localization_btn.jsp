<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<style>
    .btn-success:not (:disabled ):not (.disabled ).active, .btn-success:not
	(:disabled ):not (.disabled ):active, .show > .btn-success.dropdown-toggle {
        color: #fff;
        background-color: #00ad7e;
        border-color: #00ad7e;
    }

    .dropdown-item {
        width: 100%;
        padding: .25rem 1.5rem;
        clear: both;
        font-weight: 400;
        color: #212529;
        text-align: inherit;
        white-space: nowrap;
        background-color: transparent;
        border: 0;
    }

    .dropdown-menu {
        position: absolute;
        top: 100%;
        left: 0;
        z-index: 1000;
        display: none;
        float: left;
        min-width: 6rem;
        padding: .5rem 0;
        margin: .125rem 0 0;
        font-size: 1rem;
        color: #212529;
        text-align: left;
        list-style: none;
        background-color: #fff;
        background-clip: padding-box;
        border: 1px solid rgba(0, 0, 0, .15);
        border-radius: .25rem;
    }

    .img-m-top {
        margin-top: -4px;
    }
</style>
<div class="dropdown">
    <button class="btn btn-success dropdown-toggle mr-sm-2"
            type="button"
            id="dropdownMenuButton"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false">
        <i class="fas fa-globe"></i>
        <c:if test="${sessionScope.locale == 'en'}">
            EN
        </c:if>
        <c:if test="${sessionScope.locale == 'ru'}">
            RU
        </c:if>
    </button>
    <div id="locale_btn" class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="width: 20px;">
        <c:if test="${locale == 'en'}">
            <!--<a id="urlLocale" class="dropdown-item" href="ServletController?command=change_locale&locale=ru">
            <img class="img-m-top" src="img/flug/ru.png"> RU</a>-->
            <a id="urlLocale" class="dropdown-item" href="">

                <c:set var="locale" value="ru" scope="session" />

                <img class="img-m-top" src="img/flug/ru.png"> RU
            </a>
        </c:if>
        <c:if test="${locale == 'ru'}">
            <!--<a id="urlLocale" class="dropdown-item" href="ServletController?command=change_locale&locale=en">
            <img class="img-m-top" src="img/flug/gb.png"> EN</a>-->
            <a id="urlLocale" class="dropdown-item" href="">

                <c:set var="locale" value="en" scope="session" />

                <img class="img-m-top" src="img/flug/gb.png"> EN
            </a>
        </c:if>
    </div>
</div>