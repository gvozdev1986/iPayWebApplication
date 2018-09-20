package by.htp.hvozdzeu.model;

import by.htp.hvozdzeu.model.entity.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Payment extends Entity {

	private static final long serialVersionUID = 928091939887557570L;
	
	private LocalDate datePayment;
    private LocalTime timePayment;
    private String descriptionPayment;
    private Long paymentService;
    private BigDecimal amountPayment;
    private Long creditCard;
    private boolean available;
    private String orderNo;

    private Payment() {}

    public static Builder getBuilder() {
        return new Payment().new Builder();
    }

    public LocalDate getDatePayment() {
        return datePayment;
    }

    public LocalTime getTimePayment() {
        return timePayment;
    }

    public String getDescriptionPayment() {
        return descriptionPayment;
    }

    public Long getPaymentService() {
        return paymentService;
    }

    public BigDecimal getAmountPayment() {
        return amountPayment;
    }

    public Long getCreditCard() {
        return creditCard;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getOrderNo() {
        return orderNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Payment payment = (Payment) o;
        return available == payment.available &&
                Objects.equals(datePayment, payment.datePayment) &&
                Objects.equals(timePayment, payment.timePayment) &&
                Objects.equals(descriptionPayment, payment.descriptionPayment) &&
                Objects.equals(paymentService, payment.paymentService) &&
                Objects.equals(amountPayment, payment.amountPayment) &&
                Objects.equals(creditCard, payment.creditCard) &&
                Objects.equals(orderNo, payment.orderNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                datePayment,
                timePayment,
                descriptionPayment,
                paymentService,
                amountPayment,
                creditCard,
                available,
                orderNo
        );
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + getId() +
                ", datePayment=" + datePayment +
                ", timePayment=" + timePayment +
                ", descriptionPayment='" + descriptionPayment + '\'' +
                ", paymentService=" + paymentService +
                ", amountPayment=" + amountPayment +
                ", creditCard=" + creditCard +
                ", available=" + available +
                ", orderNo=" + orderNo +
                '}';
    }

    public class Builder {

        private Builder(){}

        public Builder id(Long id) {
            Payment.this.setId(id);
            return this;
        }

        public Builder datePayment(LocalDate datePayment) {
            Payment.this.datePayment = datePayment;
            return this;
        }

        public Builder timePayment(LocalTime timePayment) {
            Payment.this.timePayment = timePayment;
            return this;
        }

        public Builder descriptionPayment(String descriptionPayment) {
            Payment.this.descriptionPayment = descriptionPayment;
            return this;
        }

        public Builder paymentService(Long paymentService) {
            Payment.this.paymentService = paymentService;
            return this;
        }

        public Builder amountPayment(BigDecimal amountPayment) {
            Payment.this.amountPayment = amountPayment;
            return this;
        }

        public Builder creditCard(Long creditCard) {
            Payment.this.creditCard = creditCard;
            return this;
        }

        public Builder available(Boolean available) {
            Payment.this.available = available;
            return this;
        }

        public Builder orderNo(String orderNo) {
            Payment.this.orderNo = orderNo;
            return this;
        }

        public Payment build() {
            return Payment.this;
        }
    }
}
