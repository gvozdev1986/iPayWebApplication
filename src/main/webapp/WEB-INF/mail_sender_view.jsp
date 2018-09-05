<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
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
                <h1 class="h2">Reply on email</h1>
                <div class="btn-toolbar mb-2 mb-md-0">REPLY</div>
            </div>
            <div class="container" style="height: 70%; padding: 5px;">
                <form action="ServletController" method="post">
                    <input type="hidden" name="messageId" value="${messageContact.id}">
                    <div class="form-row">
                        <div class="form-group col-md-4 input-group-sm">
                            <label for="nameToReply"><i class="fas fa-user-edit"></i></i> <fmt:message
                                    key="messages_table_contact_name"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="nameToReply"
                                   name="nameToReply"
                                   value="${messageContact.nameContact}"
                                   readonly>
                        </div>
                        <div class="form-group col-md-4 input-group-sm">
                            <label for="telToReply"><i class="fas fa-mobile-alt"></i> <fmt:message
                                    key="messages_table_contact_tel"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="telToReply"
                                   name="telToReply"
                                   value="${messageContact.phoneContact}"
                                   readonly>
                        </div>
                        <div class="form-group col-md-4 input-group-sm">
                            <label for="emailToReply"><i class="fas fa-envelope-open"></i> <fmt:message
                                    key="messages_table_contact_email"/></label>
                            <input type="text"
                                   class="form-control"
                                   id="emailToReply"
                                   name="emailToReply"
                                   value="${messageContact.emailContact}"
                                   readonly>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12 input-group-sm">
                            <label for="subjectToReply"><i class="fab fa-leanpub"></i> Subject</label>
                            <input type="text"
                                   name="subjectToReply"
                                   class="form-control"
                                   id="subjectToReply">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12 input-group-sm">
                            <label for="messageToReply"><i class="fas fa-comments"></i> <fmt:message
                                    key="messages_table_contact_message"/></label>
                            <textarea class="form-control"
                                      style="margin-top: 0px; margin-bottom: 0px; height: 150px;"
                                      name="messageToReply"
                                      id="messageToReply"
                                      rows="10"
                                      cols="45"></textarea>
                        </div>
                    </div>
                    <button type="submit"
                            class="btn btn-success btn-sm"
                            data-dismiss="modal"
                            name="command"
                            value="back_detail_message">
                        <i class="fas fa-chevron-left"></i> Back
                    </button>
                    <button type="submit"
                            name="command"
                            value="reply_email"
                            class="btn btn-success btn-sm">
                        <i class="far fa-share-square"></i> Send
                    </button>
                </form>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>
