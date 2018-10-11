<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<%@include file="admin_navbar.jsp" %>

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
</style>
<div class="container-fluid">
    <div class="row">
        <%@include file="admin_menu.jsp" %>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <c:if test="${paymentData.available}">
                    <td style="text-align: center; vertical-align: middle;">
                        <h1 class="h2"><i class="fas fa-lock-open"></i> Payment data service detail</h1>
                    </td>
                </c:if>
                <c:if test="${not paymentData.available}">
                    <td style="text-align: center; vertical-align: middle;">
                        <h1 class="h2"><i class="fas fa-lock"></i> Payment data service detail</h1>
                    </td>
                </c:if>
                <div class="btn-toolbar mb-2 mb-md-0">DETAIL OF SERVICE # ${paymentDataServiceId}</div>
            </div>
            <div class="container" style="height: 70%; padding: 5px;">
                <form>
                    <div class="form-row">
                        <div class="form-group col-md-4 input-group-sm">
                            <label for="serviceDateCode">Code</label>
                            <input type="text" class="form-control" id="serviceDateCode" placeholder="Code"
                                   value="${paymentData.paymentDataCode}">
                        </div>
                        <div class="form-group col-md-4 input-group-sm">
                            <label for="serviceDateName">Name</label>
                            <input type="text" class="form-control" id="serviceDateName" placeholder="Name"
                                   value="${paymentData.paymentDataName}">
                        </div>
                        <div class="form-group col-md-4 input-group-sm">
                            <label for="serviceDateGroup">Group</label>
                            <input type="text" class="form-control" id="serviceDateGroup" placeholder="Croup"
                                   value="${paymentData.paymentDataGroup}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12 input-group-sm">
                            <label for="serviceDataDescription">Description</label>
                            <textarea class="form-control" id="serviceDataDescription"
                                      rows="3">${paymentData.paymentDataDescription}</textarea>
                        </div>
                    </div>
                    <input type="hidden"
                           id=""
                           name=""
                           value=""/>
                    <div class="form-inline">
                        <input type="hidden" name="cardId" value=""/>
                        <c:if test="${paymentData.available}">
                            <button class="btn btn-sm btn-secondary form-control mr-sm-2 custom_button"
                                    type="submit"
                                    name="command"
                                    value="">
                                <i class="fas fa-lock"></i> Block
                            </button>
                        </c:if>
                        <c:if test="${not paymentData.available}">
                            <button class="btn btn-sm btn-secondary form-control mr-sm-2 custom_button"
                                    type="submit"
                                    name="command"
                                    value="">
                                <i class="fas fa-unlock"></i> Unblock
                            </button>
                        </c:if>
                        <button class="btn btn-sm btn-success form-control mr-sm-2 custom_button"
                                type="submit"
                                name="command"
                                value="">
                            <i class="far fa-save"></i> Save
                        </button>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>
