<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="Resource"/>
<jsp:useBean id="date" class="java.util.Date"/>
<div id="welcome_page_panel" style="display: block;">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">
            <div style="margin-left: 5px;">
                <fmt:message key="welcome"/>
            </div>
            <div class="container" style="height: 70%; overflow-y: scroll; padding: 5px;">
                <div>
                    <c:if test="${serverStatus eq 'true'}">
                        <p></p>
                    </c:if>
                    <c:if test="${serverStatus eq 'false'}">
                        <p style="color: #fe0c00; font-size: 20px;"><fmt:message key="server_not_online"/></p>
                    </c:if>
                </div>
                <table class="table table-bordered table-hover table-sm"
                       style="width: 100%; font-size: 9pt;">
                    <tr style="background-color: #00ad7e; color: #ffffff;">
                        <td colspan="2" style="text-align: center; font-weight: 600;"><fmt:message key="currency_rates"/> <fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                    <tr>
                        <td>USD (<fmt:message key="usd"/> $)</td>
                        <td>${currencyMap.USD}<span style="font-size: 8px;">/1</span></td>
                    </tr>
                    <tr>
                        <td>EUR (<fmt:message key="eur"/> &#8364;)</td>
                        <td>${currencyMap.EUR}<span style="font-size: 8px;">/1</span></td>
                    </tr>
                    <tr>
                        <td>RUB (<fmt:message key="rus"/> P)</td>
                        <td>${currencyMap.RUB}<span style="font-size: 8px;">/100</span></td>
                    </tr>
                    <tr>
                        <td>UAH (<fmt:message key="uah"/> &#8372;)</td>
                        <td>${currencyMap.UAH}<span style="font-size: 8px;">/100</span></td>
                    </tr>
                </table>
            </div>
        </h1>
    </div>
</div>