<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="Resource"/>
<jsp:include page="user_navbar.jsp"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app.css">
<div class="container-fluid">
    <div class="row">
        <jsp:include page="user_menu.jsp"/>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">New credit card</h1>
                <div class="btn-toolbar mb-2 mb-md-0">NEW CREDIT CARD</div>
            </div>
            <div class="container" style="height: 60%; overflow-y: scroll; padding: 5px;">
                <div class="row">
                    <div class="col-md-4">
                        <div class="card text-center" style="height: 100%;">
                            <div class="card-header">
                                Rules of using a bank card.
                            </div>
                            <div class="card-body" style="text-align: justify; font-size: 12px; margin-left: -31px;">
                                <ol>
                                    <li>Only the cardholder can use this card.</li>
                                    <li>In case of doubtful actions, the card will be blocked.</li>
                                    <li>The holder does not have the right to transfer data to a third party.</li>
                                    <li>The secret password must be only from you and no one else.</li>
                                    <li>In case of any suspicions about the card, it is necessary to immediately block
                                        it, informing the administrator or in the personal account.
                                    </li>
                                </ol>
                            </div>
                            <div class="card-footer text-muted">
                                <div class="form-check">
                                    <input class="form-check-input"
                                           type="checkbox" value=""
                                           id="anotherCardCheck"
                                           onclick="agreeTerms();"
                                           required>
                                    <label class="form-check-label"
                                           for="anotherCardCheck"> I agree with the terms.</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <form autocomplete="off" action="ServletController" method="post" name="createCreditCard">
                            <div class="form-row">
                                <div class="form-group col-md-6 input-group-sm">
                                    <label for="cardFirstName"><i class="fas fa-user"></i> First name</label>
                                    <input type="text"
                                           class="form-control"
                                           id="cardFirstName"
                                           name="cardFirstName"
                                           placeholder="First name"
                                           autocomplete="off"
                                           required>
                                </div>
                                <div class="form-group col-md-6 input-group-sm">
                                    <label for="cardLastName"><i class="fas fa-user"></i> Last name</label>
                                    <input type="text"
                                           class="form-control"
                                           id="cardLastName"
                                           name="cardLastName"
                                           placeholder="Last name"
                                           autocomplete="off"
                                           required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-3 input-group-sm">
                                    <label for="cardNumber"><i class="far fa-credit-card"></i> Card number</label>
                                    <input type="text"
                                           style="text-align: center;"
                                           class="form-control"
                                           id="cardNumber"
                                           name="cardNumber"
                                           placeholder="XXXX XXXX XXXX XXXX"
                                           autocomplete="off"
                                           required>
                                    <span class="validate">${messageCheckCreditCard}</span>
                                </div>
                                <div class="form-group col-md-1 input-group-sm">
                                    <label for="cardValidMonth">Month</label>
                                    <input type="text"
                                           style="text-align: center;"
                                           maxlength="2"
                                           class="form-control"
                                           id="cardValidMonth"
                                           name="cardValidMonth"
                                           placeholder="00"
                                           autocomplete="off"
                                           required>
                                </div>
                                <div class="form-group col-md-1 input-group-sm">
                                    <label for="cardValidYear">Year</label>
                                    <input type="text"
                                           style="text-align: center;"
                                           maxlength="2"
                                           class="form-control"
                                           id="cardValidYear"
                                           name="cardValidYear"
                                           placeholder="00"
                                           autocomplete="off"
                                           required>
                                </div>
                                <div class="form-group col-md-4 input-group-sm">
                                    <label for="creditCardType">
                                        <i class="fas fa-list"></i> Card type</label>
                                    <select class="custom-select"
                                            id="creditCardType"
                                            name="creditCardType"
                                            style="line-height: 14px; height: 31px;">
                                        <option value="BELCARD">BELCARD</option>
                                        <option value="MASTERCARD">MASTERCARD</option>
                                        <option value="MASTERCARD_MAESTRO">MASTERCARD MAESTRO</option>
                                        <option value="VISA">VISA</option>
                                        <option value="VISA_ELECTRON">VISA ELECTRON</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3 input-group-sm">
                                    <label for="secretCode"><i class="fas fa-user-secret"></i> Secret code</label>
                                    <input type="password"
                                           class="form-control"
                                           id="secretCode"
                                           name="secretCode"
                                           placeholder="Secret code"
                                           autocomplete="false"
                                           required>
                                </div>
                            </div>
                            <div>
                                <a>${messageErrorInsertNewCreditCard}</a>
                            </div>
                            <div class="form-inline">
                                <!--<button class="btn btn-success form-control mr-sm-2 btn-sm custom_button"
                                        id="cancel_btn"
                                        type="submit"
                                        name="command"
                                        value="">
                                    <i class="fas fa-angle-left"></i> Cancel
                                </button>-->
                                <button class="btn btn-success form-control mr-sm-2 btn-sm custom_button"
                                        id="save_new_btn"
                                        type="submit"
                                        name="command"
                                        value="save_new_credit_card"
                                        disabled>
                                    <i class="fas fa-check"></i> Save
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<ctg:footer/>
<script>


    $('input[type=text]').keyup(function() {
        $(this).val($(this).val().toUpperCase());
    });

    var cc = createCreditCard.cardNumber;

    for (var i in ['input', 'change', 'blur', 'keyup']) {
        cc.addEventListener('input', formatCreateCardInput, false);
    }

    function formatCreateCardInput() {
        var cardNumber = this.value.replace(/[^\d]/g, '').substring(0, 16);
        cardNumber = cardNumber != '' ? cardNumber.match(/.{1,4}/g).join(' ') : '';
        this.value = cardNumber;
    }

    function agreeTerms() {
        save_new_btn = document.getElementById("save_new_btn");
        if (save_new_btn.disabled == false) {
            save_new_btn.disabled = true;
        } else {
            save_new_btn.disabled = false;
        }
    }
</script>
