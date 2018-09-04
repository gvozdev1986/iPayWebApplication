<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<jsp:include page="admin_navbar.jsp"/>

<style>
    .btn-success {
        color: #fff;
        background-color: #00ad7e;
        border-color: #00ad7e;
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
    .btn-success:hover {
        color: #fff;
        background-color: #41c7a3;
        border-color: #41c7a3;
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
                <h1 class="h2">Messages</h1>
                <div class="btn-toolbar mb-2 mb-md-0">LIST OF MESSAGE</div>
            </div>
            <div class="container" style="height: 70%; overflow-y: scroll; padding: 5px;">
                <table class="table table-bordered table-hover table-sm"
                       style="width: 100%; font-size: 9pt;">
                    <tr class="header-table-column" style="text-align: center; vertical-align: middle;">
                        <td style="vertical-align: middle; font-weight: bold;">#</td>
                        <td style="vertical-align: middle; font-weight: bold;"></td>
                        <td style="vertical-align: middle; font-weight: bold;"><fmt:message key="messages_table_contact_data"/></td>
                        <td style="vertical-align: middle; font-weight: bold;"><fmt:message key="messages_table_contact_time"/></td>
                        <td style="vertical-align: middle; font-weight: bold;"><fmt:message key="messages_table_contact_name"/></td>
                        <td style="vertical-align: middle; font-weight: bold;"><fmt:message key="messages_table_contact_email"/></td>
                        <td style="vertical-align: middle; font-weight: bold;"><fmt:message key="messages_table_contact_tel"/></td>
                    </tr>
                    <c:forEach items="${messageContacts}" var="messageContacts">
                        <tr>
                            <td style="vertical-align: middle; text-align: right;">${messageContacts.id}</td>

                            <c:if test="${messageContacts.checkRead}">
                                <td style="text-align: center; vertical-align: middle;"><i class="far fa-envelope-open"></i></td>
                            </c:if>

                            <c:if test="${not messageContacts.checkRead}">
                                <td style="text-align: center; vertical-align: middle;"><i class="far fa-envelope"></i></td>
                            </c:if>

                            <td style="text-align: right;">${messageContacts.date}</td>
                            <td style="text-align: right;">${messageContacts.time}</td>
                            <td>${messageContacts.nameContact}</td>
                            <td style="text-align: right;">${messageContacts.emailContact}</td>
                            <td style="text-align: right;">${messageContacts.phoneContact}</td>

                        </tr>
                    </c:forEach>
                </table>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>
