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
                <form action="ServletController" method="post">
                    <input type="hidden" name="messageId" value="${messageContact.id}">
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
                                      rows="3"
                                      readonly>${messageContact.messageFromContact}</textarea>
                        </div>
                    </div>
                    <button type="submit"
                            name="command"
                            value="list_message_view"
                            class="btn btn-success btn-sm">
                        <i class="fas fa-chevron-left"></i> Back
                    </button>
                    <button type="button"
                            class="btn btn-success btn-sm"
                            data-toggle="modal"
                            data-target="#modalReplyOnEmail">
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
                </form>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>
<!-- Modal reply on email-->
<div class="modal fade" id="modalReplyOnEmail" tabindex="-1" role="dialog" aria-labelledby="modalReplyOnEmail" aria-hidden="true">
    <form>
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle">Reply on email</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-row">
                        <div class="form-group col-md-12 input-group-sm">
                            <label for="emailToReply"><i class="fas fa-envelope-open"></i> <fmt:message
                                    key="messages_table_contact_email"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="emailToReply"
                                   value="${messageContact.emailContact}"
                                   readonly>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12 input-group-sm">
                            <label for="messageToReply"><i class="fas fa-comments"></i> <fmt:message
                                    key="messages_table_contact_message"/></label>
                            <textarea class="form-control"
                                      id="messageToReply"
                                      rows="3"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fas fa-chevron-left"></i> Back</button>
                    <button type="button" class="btn btn-success"><i class="far fa-share-square"></i> Send</button>
                </div>
            </div>
        </div>
    </form>
</div>
