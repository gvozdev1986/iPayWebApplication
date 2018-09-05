<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
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
            <form action="" method="" style="margin-left: 5px; margin-bottom: 0px;">
                <div class="form-inline">
                    <div class="input-group input-group-sm mb-2">
                        <label for="paymentHistoryCardNumber">Card number&nbsp</label>
                        <select class="custom-select mr-sm-2"
                                id="paymentHistoryCardNumber"
                                style="height: 31px; line-height: 18px; width: 200px; border-radius: 0px;">
                            <option selected>Choose...</option>
                            <option value="1">1111 1111 1111 1111</option>
                            <option value="2">2222 2222 2222 2222</option>
                            <option value="3">3333 3333 3333 3333</option>
                        </select>
                        <label for="paymentHistoryDateFrom">From&nbsp</label>
                        <div class="input-group date mr-sm-2">
                            <input type="text" class="form-control"
                                   data-date-format="yyyy-mm-dd"
                                   placeholder="yyyy-mm-dd"
                                   data-provide="datepicker"
                                   id="paymentHistoryDateFrom"
                                   style="height: 31px; width: 124px; border-radius: 0px;">
                            <div class="input-group-addon">
                            </div>
                        </div>
                        <label for="paymentHistoryDateTo">To&nbsp</label>
                        <div class="input-group date mr-sm-2">
                            <input type="text"
                                   id="paymentHistoryDateTo"
                                   data-date-format="yyyy-mm-dd"
                                   placeholder="yyyy-mm-dd"
                                   data-provide="datepicker"
                                   class="form-control" style="height: 31px; width: 124px; border-radius: 0px;">
                            <div class="input-group-addon">
                            </div>
                        </div>
                        <div class="input-group-append">
                            <button class="btn btn-success"
                                    style="border-radius: 0px;"
                                    type="submit"
                                    name="command"
                                    value="find_service_data_by_param"
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
                        <td rowspan="2" style="vertical-align: middle; font-weight: bold;">#</td>
                        <td rowspan="2" style="vertical-align: middle; font-weight: bold;">Date</td>
                        <td rowspan="2" style="vertical-align: middle; font-weight: bold;">Time</td>
                        <td rowspan="2" style="vertical-align: middle; font-weight: bold;">Brief about payment</td>
                        <td colspan="3" style="vertical-align: middle; font-weight: bold;">Service</td>
                        <td rowspan="2" style="vertical-align: middle; font-weight: bold;">Amount</td>
                    </tr>
                    <tr style="text-align: center; vertical-align: middle;">
                        <td style="vertical-align: middle; font-weight: bold;">Group</td>
                        <td style="vertical-align: middle; font-weight: bold;">Name</td>
                        <td style="vertical-align: middle; font-weight: bold;">Description</td>
                    </tr>
                    <c:forEach items="${paymentReport}" var="pagination">
                        <tr>
                            <td>${pagination.id}</td>
                            <td style="text-align: center;">${pagination.datePayment}</td>
                            <td style="text-align: center;">${pagination.timePayment}</td>
                            <td>${pagination.descriptionPayment}</td>
                            <td>${pagination.paymentDataGroup}</td>
                            <td>${pagination.paymentDataName}</td>
                            <td>${pagination.paymentDataDescription}</td>
                            <td style="text-align: right;">${pagination.amountPayment}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>
<script>
    $('.paymentHistoryDateFrom').datepicker();
    $('.paymentHistoryDateTo').datepicker();
</script>
