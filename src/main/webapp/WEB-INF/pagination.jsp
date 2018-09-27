<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="Resource"/>
<c:if test="${countPage >= 1}">
    <form action="ServletController" method="get">
        <input id="pageHiddenInput" type="hidden" name="page" value="${page}"/>
        <input id="navigationBtnInput" type="hidden" name="navigationBtn" value="none"/>
        <input id="additional_param_1" type="hidden" name="additional_param_1" value="${returnCardId}">
        <input id="additional_param_2" type="hidden" name="additional_param_2" value="${returnDateStart}">
        <input id="additional_param_3" type="hidden" name="additional_param_3" value="${returnDateEnd}">
        <label for="pag"><fmt:message key="page"/> ${page + 1} <fmt:message key="from"/> ${countPage}</label>
        <nav id="pag" aria-label="Page navigation example">
            <ul class="pagination pagination-sm">
                <div class="form-group input-group-sm">
                    <select class="form-control" name="countRowOnPage">
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="20">20</option>
                    </select>
                    <script type="text/javascript">
                        var val = text = ${countRowOnPage};
                        $("select option[value=" + val + "]").attr('selected', 'true').text(text);
                    </script>
                    <input id="nextInput" type="hidden" name="nextInput"/>
                    <input id="previousInput" type="hidden" name="previousInput"/>
                </div>
                <c:choose>
                    <c:when test="${firstPage != page}">
                        <button class="btn btn-outline-secondary form-control btn-sm"
                                onclick="navigationBtnFunc('${previous}');"
                                type="submit"
                                name="command"
                                value="${paginationList}"
                                id="previous_btn"
                                style="height: 31px; width: 70px; vertical-align: middle; text-align: center;">
                            <fmt:message key="revious_btn"/>
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-outline-secondary form-control btn-sm"
                                type="button"
                                id="previous_btn"
                                style="height: 31px; width: 70px; vertical-align: middle; text-align: center;" disabled>
                            <fmt:message key="revious_btn"/>
                        </button>
                    </c:otherwise>
                </c:choose>
                <c:forEach items="${paginationBtns}" var="paginationBtns">
                    <c:if test="${paginationBtns eq '...'}">
                        <li class="page-item">
                            <button class="btn btn-outline-secondary form-control btn-sm"
                                    type="button"
                                    style="height: 31px; width: 31px; vertical-align: middle; text-align: center;">
                                    ${paginationBtns}
                            </button>
                        </li>
                    </c:if>
                    <c:if test="${paginationBtns ne '...'}">
                        <li class="page-item">
                            <button class="btn btn-outline-secondary form-control btn-sm"
                                    type="submit"
                                    id="${paginationBtns}"
                                    name="command"
                                    onclick="getPage(${paginationBtns - 1});"
                                    value="${paginationList}"
                                    style="height: 31px; width: 31px; vertical-align: middle; text-align: center;">
                                    ${paginationBtns}
                            </button>
                        </li>
                    </c:if>
                </c:forEach>
                <c:choose>
                    <c:when test="${lastPage != (page + 1)}">
                        <input type="hidden" name="nextBtn" value="1"/>
                        <button class="btn btn-outline-secondary form-control btn-sm"
                                type="submit"
                                id="next_btn"
                                name="command"
                                onclick="navigationBtnFunc('${next}');"
                                value="${paginationList}"
                                style="height: 31px; width: 80px; vertical-align: middle; text-align: center;">
                            <fmt:message key="next_btn"/>
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-outline-secondary form-control btn-sm"
                                type="button"
                                id="next_btn"
                                style="height: 31px; width: 80px; vertical-align: middle; text-align: center;"
                                disabled>
                            <fmt:message key="next_btn"/>
                        </button>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </form>
</c:if>

