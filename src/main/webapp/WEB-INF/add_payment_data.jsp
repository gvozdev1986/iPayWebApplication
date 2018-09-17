<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<jsp:include page="admin_navbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="admin_menu.jsp"/>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">New service</h1>
                <div class="btn-toolbar mb-2 mb-md-0">ADD SERVICE</div>
            </div>
            <div class="container" style="height: 70%; padding: 5px;">
                <form action="ServletController" method="post">
                    <div class="form-row">
                        <div class="form-group col-md-2 input-group-sm">
                            <label for="codeSymbols">LC</label>
                            <select name="codeLC" class="custom-select input-group-sm"
                                    style="height: 31px; line-height: 12px;"
                                    id="codeSymbols">
                                <option selected >Choose...</option>
                                <option value="BY_">BY - Local services</option>
                                <option value="SD_">SD - Service data</option>
                                <option value="TM_">TM - Transfer money</option>
                            </select>
                        </div>
                        <div class="form-group col-md-1 input-group-sm">
                            <label for="serviceDateCode">Code</label>
                            <input type="text"
                                   name="serviceDateCode"
                                   class="form-control"
                                   id="serviceDateCode"
                                   style="text-align: right;"
                                   readonly="readonly"
                                   value="${idCodeForSave}"
                                   placeholder="Code">
                        </div>
                        <div class="form-group col-md-6 input-group-sm">
                            <label for="serviceDateName">Name</label>
                            <input type="text"
                                   name="serviceDateName"
                                   class="form-control"
                                   id="serviceDateName"
                                   placeholder="Name">
                        </div>
                        <div class="form-group col-md-3 input-group-sm">
                            <label for="serviceDateGroup">Group</label>
                            <input type="text"
                                   class="form-control"
                                   name="serviceDateGroup"
                                   id="serviceDateGroup"
                                   placeholder="Croup">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12 input-group-sm">
                            <label for="serviceDataDescription">Description</label>
                            <textarea class="form-control"
                                      id="serviceDataDescription"
                                      rows="3"
                                      name="serviceDataDescription"
                                      placeholder="Description"></textarea>
                        </div>
                    </div>
                    <input type="hidden"
                           id=""
                           name=""
                           value="" />
                    <div class="form-inline">
                        <input type="hidden" name="cardId" value="" />
                        <button class="btn btn-sm btn-secondary form-control mr-sm-2 custom_button"
                                type="submit"
                                name="command"
                                value="list_services_view">
                            <i class="fas fa-chevron-left"></i> Cancel
                        </button>
                        <button class="btn btn-sm btn-success form-control mr-sm-2 custom_button"
                                type="submit"
                                name="command"
                                value="save_payment_data">
                            <i class="far fa-save"></i> Save
                        </button>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>