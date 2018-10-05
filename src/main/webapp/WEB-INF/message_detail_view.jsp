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

    .btn-success:hover {
        color: #fff;
        background-color: #41c7a3;
        border-color: #41c7a3;
    }
</style>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="admin_menu.jsp"/>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Detail message #${messageContact.id}</h1>
                <div class="btn-toolbar mb-2 mb-md-0">MESSAGE DETAIL</div>
            </div>
            <div class="container" style="height: 70%; padding: 5px;">
                <form action="ServletController" method="get">
                    <input type="hidden" name="messageId" value="${messageContact.id}">
                    <input type="hidden" name="messageEmailTo" value="${messageContact.emailContact}">
                    <input type="hidden" name="contactToReply" value="${messageContact.nameContact}">
                    <div class="form-row">
                        <div class="form-group col-md-6 input-group-sm">
                            <label><fmt:message key="messages_table_contact_data"/>: ${messageContact.date} <fmt:message
                                    key="messages_table_contact_time"/>: ${messageContact.time}</label>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6 input-group-sm">
                            <label for="name"><i class="fas fa-user-edit"></i> <fmt:message
                                    key="messages_table_contact_name"/></label>
                            <input type="text"
                                   class="form-control"
                                   value="${messageContact.nameContact}"
                                   id="name"
                                   readonly>
                        </div>
                        <div class="form-group col-md-3 input-group-sm">
                            <label for="email"><i class="fas fa-envelope-open"></i> <fmt:message
                                    key="messages_table_contact_email"/></label>
                            <input type="text"
                                   class="form-control"
                                   value="${messageContact.emailContact}"
                                   id="email"
                                   readonly>
                        </div>
                        <div class="form-group col-md-3 input-group-sm">
                            <label for="tel"><i class="fas fa-mobile-alt"></i> <fmt:message
                                    key="messages_table_contact_tel"/></label>
                            <input type="text"
                                   class="form-control"
                                   value="${messageContact.phoneContact}"
                                   id="tel"
                                   readonly>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12 input-group-sm">
                            <label for="message"><i class="fas fa-comments"></i> <fmt:message
                                    key="messages_table_contact_message"/></label>
                            <textarea class="form-control" id="message"
                                      style="margin-top: 0px; margin-bottom: 0px; height: 150px;"
                                      rows="3"
                                      readonly>${messageContact.messageFromContact}</textarea>
                        </div>
                    </div>
                    <button type="submit"
                            name="command"
                            value="mail_sender_view"
                            class="btn btn-success btn-sm">
                        <i class="fas fa-reply"></i> Reply
                    </button>
                    <c:if test = "${messageContact.checkRead}">
                        <button type="submit"
                                name="command"
                                value="save_message_read"
                                class="btn btn-success btn-sm"
                                disabled>
                            <i class="fas fa-check"></i> As read
                        </button>
                    </c:if>
                    <c:if test = "${not messageContact.checkRead}">
                        <button type="submit"
                                name="command"
                                value="save_message_read"
                                class="btn btn-success btn-sm">
                            <i class="fas fa-check"></i> As read
                        </button>
                    </c:if>
                    <button type="button"
                            value="mail_sender_view"
                            onclick="PrintReport('#messageDetail');"
                            class="btn btn-success btn-sm">
                        <i class="far fa-file-powerpoint"></i> Print
                    </button>
                </form>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>
<div id="messageDetail" style="visibility: hidden">
    <table id="tablePrintReport">
        <tr>
            <td><fmt:message key="date"/></td>
            <td>${messageContact.date}</td>
        </tr>
        <tr>
            <td><fmt:message key="time"/></td>
            <td>${messageContact.time}</td>
        </tr>
        <tr>
            <td><fmt:message key="messages_table_contact_name"/></td>
            <td>${messageContact.nameContact}</td>
        </tr>
        <tr>
            <td><fmt:message key="messages_table_contact_email"/></td>
            <td>${messageContact.emailContact}</td>
        </tr>
        <tr>
            <td><fmt:message key="messages_table_contact_tel"/></td>
            <td>${messageContact.phoneContact}</td>
        </tr>
        <tr>
            <td><fmt:message key="messages_table_contact_message"/></td>
            <td>${messageContact.messageFromContact}</td>
        </tr>
    </table>
</div>
<script>
    function PrintReport(elem) {
        Popup($(elem).html());
    }

    function Popup(data) {
        var printReportWindow = window.open('', 'messageDetail');
        printReportWindow.document.write('<html><head><title><fmt:message key="messages_table_contact_message"/> # ${messageContact.id}</title>');
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