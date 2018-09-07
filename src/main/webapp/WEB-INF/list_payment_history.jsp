<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<jsp:include page="user_navbar.jsp"/>
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
        <jsp:include page="user_menu.jsp"/>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4"
              style="padding-bottom: 85px;">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Card history</h1>
                <div class="btn-toolbar mb-2 mb-md-0">CARD HISTORY</div>
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
                            <input type="text" class="form-control"
                                   name="dateStart"
                                   data-date-format="yyyy-mm-dd"
                                   placeholder="yyyy-mm-dd"
                                   value="${returnDateStart}"
                                   data-provide="datepicker"
                                   id="paymentHistoryDateFrom"
                                   style="height: 31px; width: 124px; border-radius: .25rem;"
                                   required>
                            <div class="input-group-addon">
                            </div>
                        </div>
                        <label for="paymentHistoryDateTo">To&nbsp</label>
                        <div class="input-group date mr-sm-2">
                            <input type="text"
                                   name="dateEnd"
                                   id="paymentHistoryDateTo"
                                   data-date-format="yyyy-mm-dd"
                                   placeholder="yyyy-mm-dd"
                                   value="${returnDateEnd}"
                                   data-provide="datepicker"
                                   class="form-control"
                                   style="height: 31px; width: 124px; border-radius: .25rem;"
                                   required>
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
                                <i class="fas fa-search"></i> Search
                            </button>
                        </div>
                    </div>
                </div>
            </form>
            <div class="container" style="height: 50%; overflow-y: scroll; padding: 5px;">
                <table class="table table-bordered table-hover table-sm table-striped"
                       style="width: 100%; font-size: 9pt;">
                    <tr style="text-align: center; vertical-align: middle;">
                        <td style="vertical-align: middle; font-weight: bold; width: 5%;">#</td>
                        <td style="vertical-align: middle; font-weight: bold; width: 8%;">Date</td>
                        <td style="vertical-align: middle; font-weight: bold; width: 6%;">Time</td>
                        <td style="vertical-align: middle; font-weight: bold; width: 59%;">Brief about payment</td>
                        <td style="vertical-align: middle; font-weight: bold; width: 20%;">Group</td>
                        <td style="vertical-align: middle; font-weight: bold; width: 2%;">Amount</td>
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
            </div>
            <!-- PAGINATION -->
            <jsp:include page="pagination.jsp"/>
        </main>
    </div>
</div>
<ctg:footer/>
<link rel="stylesheet" href="../css/bootstrap-datepicker3.min.css">
<script type="text/javascript" src="../js/bootstrap-datepicker.min.js"></script>
<script>

    $('.paymentHistoryDateFrom').datepicker();
    $('.paymentHistoryDateTo').datepicker();

    // var start_date = document.getElementById("paymentHistoryDateFrom");
    // var end_date = document.getElementById("paymentHistoryDateTo");
    //
    // if (start_date.value === "" && end_date.value === "") {
    //     var date = new Date();
    //     var formattedDate = moment(date).format('YYYY-MM-DD');
    //     start_date.value = formattedDate;
    //     end_date.value = formattedDate;
    // }

</script>
