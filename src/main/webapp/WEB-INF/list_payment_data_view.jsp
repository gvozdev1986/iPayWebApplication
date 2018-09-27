<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<jsp:include page="admin_navbar.jsp"/>

<style>
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

    ​
    .header-table-column {
        vertical-align: middle;
        font-weight: bold;
        color: white;
        background-color: #00ad7e;
    }
    .header-table-column {
        vertical-align: middle;
        font-weight: bold;
        color: white;
        background-color: #00ad7e;
    }
</style>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="admin_menu.jsp"/>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Services</h1>
                <div class="btn-toolbar mb-2 mb-md-0">LIST OF SERVICES</div>
            </div>
            <form action="" method="" style="margin-left: 5px; margin-bottom: 0px;">
                <div class="form-inline">
                    <div class="input-group input-group-sm mb-2">
                        <button class="btn btn-sm btn-success mr-sm-2"
                                type="submit"
                                name="command"
                                value="new_service_data_view">
                            <i class="fas fa-plus"></i> Add
                        </button>
                        <!--<input type="text"
                               name="param"
                               class="form-control"
                               placeholder="Search...">
                        <div class="input-group-append">
                            <button class="btn btn-success"
                                    type="submit"
                                    name="command"
                                    value="find_service_data_by_param"
                                    id="search-Btn">
                                <i class="fas fa-search"></i> Search
                            </button>
                            <button class="btn btn-success"
                                    type="submit"
                                    name="command"
                                    value="list_services_view"
                                    id="clear-Btn">
                                <i class="fas fa-times"></i> Clear
                            </button>
                        </div>-->
                    </div>
                </div>
            </form>
            <div class="container" style="height: 50%; overflow-y: scroll; padding: 5px;">
                <table class="table table-bordered table-sm"
                       style="width: 100%; font-size: 9pt;">
                    <tr class="header-table-column" style="text-align: center; vertical-align: middle;">
                        <td style="vertical-align: middle; font-weight: bold;" class="header-table-column">#</td>
                        <td class="header-table-column"></td>
                        <td class="header-table-column" style="vertical-align: middle; font-weight: bold;"><fmt:message
                                key="service_data_code"/></td>
                        <td class="header-table-column" style="vertical-align: middle; font-weight: bold;"><fmt:message
                                key="service_data_name"/></td>
                        <td class="header-table-column" style="vertical-align: middle; font-weight: bold;"><fmt:message
                                key="service_data_group"/></td>
                        <td class="header-table-column" style="vertical-align: middle; font-weight: bold;"><fmt:message
                                key="service_data_description"/></td>
                        <td class="header-table-column" style="vertical-align: middle; font-weight: bold;">A</td>
                    </tr>
                    <c:forEach items="${paymentData}" var="pagination" varStatus="loop">
                        <tr>
                            <td style="vertical-align: middle; text-align: right; max-width: 10px;">${loop.index + 1}</td>
                            <td style="vertical-align: middle; width: 25px;">
                                <a href="ServletController?command=payment_data_detail_view&paymentDataServiceId=${pagination.id}"
                                   style="cursor: pointer;
							   margin-top: -7px; color: black">
                                    <i class="fas fa-search"></i>
                                </a>
                            </td>
                            <td>${pagination.paymentDataCode}</td>
                            <td>${pagination.paymentDataName}</td>
                            <td>${pagination.paymentDataGroup}</td>
                            <td>${pagination.paymentDataDescription}</td>

                            <c:if test="${pagination.available}">
                                <td style="text-align: center; vertical-align: middle;">
                                    <i class="fas fa-lock-open"></i>
                                </td>
                            </c:if>

                            <c:if test="${not pagination.available}">
                                <td style="text-align: center; vertical-align: middle;">
                                    <i class="fas fa-lock"></i>
                                </td>
                            </c:if>

                        </tr>
                    </c:forEach>
                </table>
            </div>
            <!-- PAGINATION -->
            <jsp:include page="pagination.jsp"/>
        </main>
    </div>
</div>
<ctg:footer/>