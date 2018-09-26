<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<jsp:include page="user_navbar.jsp"/>
<link rel="stylesheet" href="../css/bootstrap-datepicker3.min.css">
<script type="text/javascript" src="../js/bootstrap-datepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/bootstrap-datepicker.ru.js" charset="UTF-8"></script>
<style>
    .btn-success {
        color: #fff;
        background-color: #00ad7e;
        border-color: #00ad7e;
    }

    .send-report {
        font-size: 11px;
        font-weight: 600;
        color: #00ad7e;
    }

    .btn-success:hover {
        color: #fff;
        background-color: #41c7a3;
        border-color: #41c7a3;
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
        <jsp:include page="user_menu.jsp"/>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4"
              style="padding-bottom: 85px;">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2"><fmt:message key="card_history_label"/></h1>
                <div class="btn-toolbar mb-2 mb-md-0"><fmt:message key="card_history_group"/></div>
            </div>
            <form action="ServletController" method="get" style="margin-left: 5px; margin-bottom: 0px;">
                <div class="form-inline">
                    <div class="input-group input-group-sm mb-2">
                        <label for="paymentHistoryCardNumber">Card number&nbsp</label>
                        <select class="custom-select mr-sm-2"
                                id="paymentHistoryCardNumber"
                                name="cardId"
                                style="height: 31px; line-height: 18px; width: 200px; border-radius: .25rem;"
                                required>
                            <!--<option selected>Choose...</option>-->
                            <c:forEach items="${cards}" var="creditCards">
                                <option value="${creditCards.id}">${creditCards.cardNumber}</option>
                            </c:forEach>
                            <script type="text/javascript">
                                var val = ${returnCardId};
                                var text = '${returnCardNumber}';
                                $("select option[value=" + val + "]").attr('selected', 'true').text(text);
                            </script>
                        </select>
                        <label for="paymentHistoryDateFrom">From&nbsp</label>
                        <div class="input-group date mr-sm-2">
                            <input type="text"
                                   name="dateStart"
                                   placeholder="<fmt:message key="date_format"/>"
                                   value="${returnDateStart}"
                                   class="form-control datepicker"
                                   id="paymentHistoryDateFrom"
                                   style="height: 31px; width: 124px; border-radius: .25rem;"
                                   required
                                   autocomplete="off">
                            <div class="input-group-addon">
                            </div>
                        </div>
                        <label for="paymentHistoryDateTo">To&nbsp</label>
                        <div class="input-group date mr-sm-2">
                            <input type="text"
                                   name="dateEnd"
                                   id="paymentHistoryDateTo"
                                   placeholder="<fmt:message key="date_format"/>"
                                   value="${returnDateEnd}"
                                   class="form-control datepicker"
                                   style="height: 31px; width: 124px; border-radius: .25rem;"
                                   required
                                   autocomplete="off">
                            <div class="input-group-addon">
                            </div>
                        </div>
                        <div class="input-group-append">
                            <button class="btn btn-success"
                                    style="border-radius: 0px; border-radius: .25rem;"
                                    type="submit"
                                    name="command"
                                    value="payment_history"
                                    id="search-Btn">
                                <i class="fas fa-search"></i> <fmt:message key="search_btn"/>
                            </button>
                        </div>
                    </div>
                </div>
            </form>
            <div class="container" style="height: 50%; overflow-y: scroll; padding: 5px;">
                <table class="table table-bordered table-sm"
                       style="width: 100%; font-size: 9pt;">
                    <tr class="header-table-column" style="text-align: center; vertical-align: middle;">
                        <td style="vertical-align: middle; font-weight: bold; width: 5%;">#</td>
                        <td style="vertical-align: middle; font-weight: bold; width: 8%;"><fmt:message key="date"/></td>
                        <td style="vertical-align: middle; font-weight: bold; width: 6%;"><fmt:message key="time"/></td>
                        <td style="vertical-align: middle; font-weight: bold; width: 59%;"><fmt:message key="brief"/></td>
                        <td style="vertical-align: middle; font-weight: bold; width: 20%;"><fmt:message key="group"/></td>
                        <td style="vertical-align: middle; font-weight: bold; width: 2%;"><fmt:message key="amount"/></td>
                    </tr>
                    <c:forEach items="${paymentHistory}" var="pagination">
                        <tr>
                            <td style="text-align: right;">${pagination.id}</td>
                            <td style="text-align: center;">${pagination.datePayment}</td>
                            <td style="text-align: center;">${pagination.timePayment}</td>
                            <td>${pagination.descriptionPayment}</td>
                            <td>${pagination.paymentDataGroup}</td>
                            <td style="text-align: right;">${pagination.amountPayment}</td>
                        </tr>
                    </c:forEach>
                </table>
                <c:if test = "${messageReport != null}">
                    <span class="send-report"><fmt:message key="${messageReport}"/></span>
                </c:if>
            </div>
            <!-- PAGINATION -->
            <jsp:include page="pagination.jsp"/>
        </main>
    </div>
</div>
<ctg:footer/>
<script>
    $(document).ready(function () {
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            language: '${locale}'
        });
    });
</script>
