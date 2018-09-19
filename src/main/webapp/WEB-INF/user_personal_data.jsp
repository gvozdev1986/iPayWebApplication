<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<%@ taglib prefix="ctg" uri="customtags" %>
<jsp:include page="user_navbar.jsp"/>
<style>
    .invalid-field {
        color: #FF0000;
        font-size: 9pt;
        font-style: italic;
    }

    .save-msg {
        font-size: 16px;
        font-weight: 600;
        color: #00ad7e;
    }
</style>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="user_menu.jsp"/>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2"><fmt:message key="personal_data_label"/></h1>
                <div class="btn-toolbar mb-2 mb-md-0"><fmt:message key="personal_data_group"/></div>
            </div>
            <div class="container">
                <form action="ServletController" method="post">
                    <input type="hidden" name="clientId" value="${user.id}"/>
                    <div class="form-row">
                        <div class="col-md-6 mb-3 input-group-sm">
                            <label for="last_name"><i class="fas fa-pencil-alt"></i> <fmt:message key="form_last_name"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="last_name"
                                   placeholder="<fmt:message key="form_last_name"/>"
                                   value="${user.lastName}"
                                   name="last_name"
                                   required>
                            <c:if test="${not empty invalidLastName}">
                                <span class="invalid-field">${invalidLastName}</span>
                            </c:if>
                        </div>
                        <div class="col-md-6 mb-3 input-group-sm">
                            <label for="first_name"><i class="fas fa-pencil-alt"></i> <fmt:message key="form_first_name"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="first_name"
                                   placeholder="<fmt:message key="form_first_name"/>"
                                   value="${user.firstName}"
                                   name="first_name"
                                   required>
                            <c:if test="${not empty invalidFirstname}">
                                <span class="invalid-field">${invalidFirstname}</span>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-8 mb-3 input-group-sm">
                            <label for="patronymic"><i class="fas fa-pencil-alt"></i> <fmt:message key="form_patronymic"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="patronymic"
                                   placeholder="<fmt:message key="form_patronymic"/>"
                                   value="${user.patronymic}"
                                   name="patronymic"
                                   required>
                            <c:if test="${not empty invalidPatronymic}">
                                <span class="invalid-field">${invalidPatronymic}</span>
                            </c:if>
                        </div>
                        <div class="col-md-4 mb-3 input-group-sm">
                            <label for="date_birth"><i class="far fa-calendar-alt"></i> <fmt:message key="form_date_birth"/></label>
                            <input type="date"
                                   class="form-control"
                                   id="date_birth"
                                   placeholder="<fmt:message key="form_date_birth"/>"
                                   value="${user.dateBirth}"
                                   name="date_birth"
                                   required>
                            <c:if test="${not empty invalidDate}">
                                <span class="invalid-field">${invalidDate}</span>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-4 mb-3 input-group-sm">
                            <label for="home_phone"><i class="fas fa-phone"></i> <fmt:message key="form_home_phone"/></label>
                            <input type="tel"
                                   class="form-control"
                                   id="home_phone"
                                   placeholder="<fmt:message key="form_home_phone"/>"
                                   value="${user.phoneHome}"
                                   name="home_phone"
                                   required>
                            <c:if test="${not empty invalidHomePhone}">
                                <span class="invalid-field">${invalidHomePhone}</span>
                            </c:if>
                        </div>
                        <div class="col-md-4 mb-3 input-group-sm">
                            <label for="mobile_phone"><i class="fas fa-mobile-alt"></i> <fmt:message key="form_mobile_phone"/></label>
                            <input type="tel"
                                   class="form-control"
                                   id="mobile_phone"
                                   placeholder="<fmt:message key="form_mobile_phone"/>"
                                   value="${user.phoneMobile}"
                                   name="mobile_phone"
                                   required>
                            <c:if test="${not empty invalidMobilePhone}">
                                <span class="invalid-field">${invalidMobilePhone}</span>
                            </c:if>
                        </div>
                        <div class="col-md-4 mb-3 input-group-sm">
                            <label for="email"><i class="fas fa-envelope-open"></i> <fmt:message key="form_email"/></label>
                            <input type="email"
                                   class="form-control"
                                   id="email"
                                   placeholder="<fmt:message key="form_email"/>"
                                   value="${user.email}"
                                   name="email"
                                   required>
                            <c:if test="${not empty invalidEmail}">
                                <span class="invalid-field">${invalidEmail}</span>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3 input-group-sm">
                            <label for="address"><i class="fas fa-map-pin"></i> <fmt:message key="form_address"/></label>
                            <textarea class="form-control"
                                      aria-label="With textarea"
                                      placeholder="<fmt:message key="form_address"/>"
                                      id="address"
                                      name="address"
                                      required>${user.address}</textarea>
                            <c:if test="${not empty invalidAddress}">
                                <span class="invalid-field">${invalidAddress}</span>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-inline">
                        <button class="btn btn-success form-control mr-sm-2 btn-sm custom_button"
                                id="save_btn"
                                type="submit"
                                name="command"
                                value="save_changed_personal_data">
                            <i class="fas fa-save"></i> <fmt:message key="save_btn"/>
                        </button>
                        <span class="save-msg">${messageUpdateClient}</span>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>