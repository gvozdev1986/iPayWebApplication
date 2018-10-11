<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="Resource"/>
<%@include file="admin_navbar.jsp" %>
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
    .header-table-column {
        vertical-align: middle;
        font-weight: bold;
        color: white;
        background-color: #00ad7e;
    }
    ​
</style>
<div class="container-fluid">
    <div class="row">
        <%@include file="admin_menu.jsp" %>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2"><fmt:message key="blocked_cards"/></h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <fmt:message key="list_blocked_cards"/>
                </div>
            </div>
            <div class="container" style="height: 70%; overflow-y: scroll; padding: 5px;">
                <form>
                    <div class="input-group input-group-sm mb-3" style="width: 50px;">
                        <%--<input type="text" name="param" class="form-control"--%>
                               <%--placeholder="Search..." aria-label="search input"--%>
                               <%--aria-describedby="inputGroup-sizing-sm">--%>
                        <%--<div class="input-group-append">--%>
                            <%--<button class="btn btn-success" type="submit" name="command"--%>
                                    <%--value="find_bloked_card_by_param" id="search-Btn">--%>
                                <%--<i class="fas fa-search"></i> Search--%>
                            <%--</button>--%>
                            <%--<button class="btn btn-success" type="submit" name="command"--%>
                                    <%--value="blocked_credit_cards_view" id="clear-Btn">--%>
                                <%--<i class="fas fa-times"></i> Clear--%>
                            <%--</button>--%>
                        <%--</div>--%>

                            <button class="btn btn-sm btn-success form-control mr-sm-2 custom_button"
                                    type="button"
                                    onclick="PrintReport('#statusTable');"
                                    value="unblock_card">
                                <i class="far fa-file-powerpoint"></i> <fmt:message key="print_btn"/>
                            </button>
                    </div>
                </form>
                <table class="table table-bordered table-striped table-sm" style="width: 100%; font-size: 9pt;">
                    <thead>
                        <tr class="header-table-column" style="text-align: center; vertical-align: middle;">
                            <td style="vertical-align: middle; font-weight: bold;">#</td>
                            <td style="vertical-align: middle; font-weight: bold;">A</td>
                            <td style="vertical-align: middle; font-weight: bold;"><fmt:message key="form_first_name"/></td>
                            <td style="vertical-align: middle; font-weight: bold;"><fmt:message key="form_last_name"/></td>
                            <td style="vertical-align: middle; font-weight: bold;"><fmt:message key="credit_card_number_label"/></td>
                            <td style="vertical-align: middle; font-weight: bold;"><fmt:message key="valid_card_label"/></td>
                        </tr>
                    </thead>
                    <c:forEach items="${listBlockedCreditCard}" var="creditBlockedCards" varStatus="loop">
                        <tr>
                            <td style="vertical-align: middle; text-align: right;">${(page * countRowOnPage) + (loop.index + 1)}</td>
                            <td style="vertical-align: middle; width: 25px;">
                                <a href="ServletController?command=view_detail_blocked_card&cardId=${creditBlockedCards.id}"
                                   style="cursor: pointer; margin-top: -7px; color: black"> <i class="fas fa-search"></i>
                                </a></td>
                            <td style="vertical-align: middle;">${creditBlockedCards.cardFirstName}</td>
                            <td style="vertical-align: middle;">${creditBlockedCards.cardLastName}</td>
                            <td style="vertical-align: middle; width: 25px;">${creditBlockedCards.cardNumber}</td>
                            <td style="vertical-align: middle; width: 10px; text-align: right;">${creditBlockedCards.validDate}</td>
                        </tr>
                    </c:forEach>
                </table>
                <!-- PAGINATION -->
                <jsp:include page="pagination.jsp"/>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>
<div id="statusTable" style="visibility: hidden">
    <div>
        <p style="text-align: center;">
            <fmt:message key="list_blocked_cards"/>
        </p>
    </div>
    <table class="table table-bordered table-striped table-sm" style="width: 100%; font-size: 9pt;">
        <thead>
        <tr class="header-table-column" style="text-align: center; vertical-align: middle;">
            <td style="vertical-align: middle; font-weight: bold;">#</td>
            <td style="vertical-align: middle; font-weight: bold;"><fmt:message key="form_first_name"/></td>
            <td style="vertical-align: middle; font-weight: bold;"><fmt:message key="form_last_name"/></td>
            <td style="vertical-align: middle; font-weight: bold;"><fmt:message key="credit_card_number_label"/></td>
            <td style="vertical-align: middle; font-weight: bold;"><fmt:message key="valid_card_label"/></td>
        </tr>
        </thead>
        <c:forEach items="${listBlockedCreditCard}" var="creditBlockedCards" varStatus="loop">
            <tr>
                <td style="vertical-align: middle; text-align: right;">${(page * countRowOnPage) + (loop.index + 1)}</td>
                <td style="vertical-align: middle;">${creditBlockedCards.cardFirstName}</td>
                <td style="vertical-align: middle;">${creditBlockedCards.cardLastName}</td>
                <td style="vertical-align: middle; width: 25px;">${creditBlockedCards.cardNumber}</td>
                <td style="vertical-align: middle; width: 10px; text-align: right;">${creditBlockedCards.validDate}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<script>
    function PrintReport(elem) {
        Popup($(elem).html());
    }

    function Popup(data) {
        var printReportWindow = window.open('', 'statusTable');
        printReportWindow.document.write('<html><head><title><fmt:message key="report"/></title>');
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