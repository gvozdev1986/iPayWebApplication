<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
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

    .column {
        width: 25%;
        font-weight: 600;
    }

    .block-status {
        font-weight: 600;
        color: red;
    }

    .unblock-status {
        font-weight: 600;
        color: green;
    }
</style>
<div class="container-fluid">
    <div class="row">
        <%@include file="admin_menu.jsp" %>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2"><fmt:message key="client_details_label"/></h1>
                <div class="btn-toolbar mb-2 mb-md-0"><fmt:message key="detail_about"/> ${clientDetail.id}</div>
            </div>
            <div class="container">
                <table class="table table-bordered table-hover table-sm table-striped"
                       style="width: 100%; font-size: 9pt;">
                    <tr>
                        <td colspan="2" style="text-align: center;"><fmt:message key="client_detail_label_table"/></td>
                    </tr>
                    <tr>
                        <td class="column"><fmt:message key="form_first_name"/></td>
                        <td>${clientDetail.lastName}</td>
                    </tr>
                    <tr>
                        <td class="column"><fmt:message key="form_last_name"/></td>
                        <td>${clientDetail.firstName}</td>
                    </tr>
                    <tr>
                        <td class="column"><fmt:message key="form_patronymic"/></td>
                        <td>${clientDetail.patronymic}</td>
                    </tr>
                    <tr>
                        <td class="column"><fmt:message key="form_date_birth"/></td>
                        <td>${clientDetail.dateBirth}</td>
                    </tr>
                    <tr>
                        <td class="column"><fmt:message key="form_home_phone"/></td>
                        <td>${clientDetail.phoneHome}</td>
                    </tr>
                    <tr>
                        <td class="column"><fmt:message key="form_mobile_phone"/></td>
                        <td>${clientDetail.phoneMobile}</td>
                    </tr>
                    <tr>
                        <td class="column"><fmt:message key="form_email"/></td>
                        <td>${clientDetail.email}</td>
                    </tr>
                    <tr>
                        <td class="column"><fmt:message key="form_address"/></td>
                        <td>${clientDetail.address}</td>
                    </tr>
                    <tr>
                        <c:if test="${not clientDetail.available}">
                            <td class="column"><fmt:message key="status"/></td>
                            <td><span class="block-status"><fmt:message key="blocked_status"/></span></td>
                        </c:if>

                        <c:if test="${clientDetail.available}">
                            <td class="column"><fmt:message key="status"/></td>
                            <td><span class="unblock-status"><fmt:message key="unblocked_status"/></span></td>
                        </c:if>
                    </tr>
                </table>
                <form action="ServletController" method="post">
                    <input type="hidden" id="cardId" name="userId" value="${clientDetail.id}"/>
                    <div class="form-inline">
                        <!-- PRINT BUTTON -->
                        <button class="btn btn-sm btn-success form-control mr-sm-2 custom_button"
                                style="border-radius: 0px; border-radius: .25rem; margin-left: 5px;"
                                type="button"
                                onclick="PrintReport('#detailUser');"
                                id="print-Btn">
                            <i class="far fa-file-powerpoint"></i> <fmt:message key="print_btn"/>
                        </button>
                        <c:if test="${not clientDetail.available}">
                            <button class="btn btn-sm btn-success form-control mr-sm-2 custom_button"
                                    type="submit"
                                    name="command"
                                    value="unblock_user">
                                <i class="fas fa-lock-open"></i> Unblock
                            </button>
                        </c:if>
                        <c:if test="${clientDetail.available}">
                            <button
                                    class="btn btn-sm btn-success form-control mr-sm-2 custom_button"
                                    type="submit"
                                    name="command"
                                    value="block_user">
                                <i class="fas fa-lock"></i> Block
                            </button>
                        </c:if>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>
<div id="detailUser" style="visibility: hidden;">
    <table class="table table-bordered table-hover table-sm table-striped"
           style="width: 100%; font-size: 9pt;">
        <tr>
            <td colspan="2" style="text-align: center;"><fmt:message key="client_detail_label_table"/></td>
        </tr>
        <tr>
            <td class="column"><fmt:message key="form_first_name"/></td>
            <td>${clientDetail.lastName}</td>
        </tr>
        <tr>
            <td class="column"><fmt:message key="form_last_name"/></td>
            <td>${clientDetail.firstName}</td>
        </tr>
        <tr>
            <td class="column"><fmt:message key="form_patronymic"/></td>
            <td>${clientDetail.patronymic}</td>
        </tr>
        <tr>
            <td class="column"><fmt:message key="form_date_birth"/></td>
            <td>${clientDetail.dateBirth}</td>
        </tr>
        <tr>
            <td class="column"><fmt:message key="form_home_phone"/></td>
            <td>${clientDetail.phoneHome}</td>
        </tr>
        <tr>
            <td class="column"><fmt:message key="form_mobile_phone"/></td>
            <td>${clientDetail.phoneMobile}</td>
        </tr>
        <tr>
            <td class="column"><fmt:message key="form_email"/></td>
            <td>${clientDetail.email}</td>
        </tr>
        <tr>
            <td class="column"><fmt:message key="form_address"/></td>
            <td>${clientDetail.address}</td>
        </tr>
        <tr>
            <c:if test="${not clientDetail.available}">
                <td class="column"><fmt:message key="status"/></td>
                <td><span class="block-status"><fmt:message key="blocked_status"/></span></td>
            </c:if>

            <c:if test="${clientDetail.available}">
                <td class="column"><fmt:message key="status"/></td>
                <td><span class="unblock-status"><fmt:message key="unblocked_status"/></span></td>
            </c:if>
        </tr>
    </table>
</div>
<script>
    function PrintReport(elem) {
        Popup($(elem).html());
    }

    function Popup(data) {
        var printReportWindow = window.open('', '');
        printReportWindow.document.write('<html><head><title><fmt:message key="client_detail_report"/></title>');
        printReportWindow.document.write('<link rel="stylesheet" href="../css/report.css" type="text/css" />');
        printReportWindow.document.write('</head><body>');
        printReportWindow.document.write(data);
        printReportWindow.document.write('</body></html>');
        printReportWindow.document.close();
        printReportWindow.focus();
        printReportWindow.print();
        printReportWindow.close();
    }
</script>
