<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
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
</style>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="admin_menu.jsp"/>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Blocked cards</h1>
                <div class="btn-toolbar mb-2 mb-md-0">LIST OF BLOCKED CREDIT
                    CARDS
                </div>
            </div>
            <div class="container"
                 style="height: 70%; overflow-y: scroll; padding: 5px;">
                <form>
                    <div class="input-group input-group-sm mb-3">
                        <input type="text" name="param" class="form-control"
                               placeholder="Search..." aria-label="search input"
                               aria-describedby="inputGroup-sizing-sm">
                        <div class="input-group-append">
                            <button class="btn btn-success" type="submit" name="command"
                                    value="find_bloked_card_by_param" id="search-Btn">
                                <i class="fas fa-search"></i> Search
                            </button>
                            <button class="btn btn-success" type="submit" name="command"
                                    value="blocked_credit_cards_view" id="clear-Btn">
                                <i class="fas fa-times"></i> Clear
                            </button>
                        </div>
                    </div>
                </form>
                <table class="table table-bordered table-hover table-sm table-striped"
                       style="width: 100%; font-size: 9pt;">
                    <tr style="text-align: center; vertical-align: middle;">
                        <td style="vertical-align: middle; font-weight: bold;">#</td>
                        <td style="vertical-align: middle; font-weight: bold;">A</td>
                        <td style="vertical-align: middle; font-weight: bold;">First name</td>
                        <td style="vertical-align: middle; font-weight: bold;">Last name</td>
                        <td style="vertical-align: middle; font-weight: bold;">Card number</td>
                        <td style="vertical-align: middle; font-weight: bold;">Valid</td>
                    </tr>
                    <c:forEach items="${listBlockedCreditCard}" var="creditBlockedCards">
                        <tr>
                            <td style="vertical-align: middle; text-align: right;">${creditBlockedCards.id}</td>
                            <td style="vertical-align: middle; width: 25px;">
                                <a href="ServletController?command=view_detail_blocked_card&cardId=${creditBlockedCards.id}"
                                   style="cursor: pointer; margin-top: -7px; color: black"> <i
                                        class="fas fa-search"></i>
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