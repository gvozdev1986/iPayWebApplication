<div class="hide-bill-block" id="paymentBill">
    <div id="billBlock">
        <div id="billCutLineHeader" class="line"></div>
        <div id="billHead" class="bill">
            <fmt:message key="bill"/>
        </div>
        <div id="billBody">
            <div id="billDate">
                <span class="billField"><fmt:message key="date"/>:</span>
                <span class="billFieldValue">${billDate}</span>
            </div>
            <div id="billTime">
                <span class="billField"><fmt:message key="time"/>:</span>
                <span class="billFieldValue">${billTime}</span>
            </div>
            <div id="billCreditCardNumber">
                <span class="billField"><fmt:message key="credit_card_number_label"/>:</span>
                <span class="billFieldValue">${billCreditCard}</span>
            </div>
            <div id="billService">
                <span class="billField"><fmt:message key="service_field"/>:</span>
                <span class="billFieldValue">${billService}</span>
            </div>
            <div id="billOrder">
                <span class="billField"><fmt:message key="order_field"/>:</span>
                <span class="billFieldValue">${billOrder}</span>
            </div>
            <div id="billAmount">
                <span class="billField"><fmt:message key="amount"/>:</span>
                <span class="billFieldValue">${billAmount}</span>
            </div>
            <div id="billDescription">
                <span class="billField"><fmt:message key="description_field"/>:</span>
                <span class="billFieldValue">${billDescription}</span>
            </div>
        </div>
        <div id="billCutLineFooter" class="line"></div>
    </div>
</div>
