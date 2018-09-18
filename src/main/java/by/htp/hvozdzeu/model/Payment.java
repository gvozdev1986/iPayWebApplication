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

    public Payment() {
    }

    public Payment(Builder builder) {
        this.setId(builder.id);
        this.datePayment = builder.datePayment;
        this.timePayment = builder.timePayment;
        this.descriptionPayment = builder.descriptionPayment;
        this.paymentService = builder.paymentService;
        this.amountPayment = builder.amountPayment;
        this.creditCard = builder.creditCard;
        this.available = builder.available;
        this.orderNo = builder.orderNo;
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

    public static class Builder {
        private Long id;
        private LocalDate datePayment;
        private LocalTime timePayment;
        private String descriptionPayment;
        private Long paymentService;
        private BigDecimal amountPayment;
        private Long creditCard;
        private boolean available;
        private String orderNo;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder datePayment(LocalDate datePayment) {
            this.datePayment = datePayment;
            return this;
        }

        public Builder timePayment(LocalTime timePayment) {
            this.timePayment = timePayment;
            return this;
        }

        public Builder descriptionPayment(String descriptionPayment) {
            this.descriptionPayment = descriptionPayment;
            return this;
        }

        public Builder paymentService(Long paymentService) {
            this.paymentService = paymentService;
            return this;
        }

        public Builder amountPayment(BigDecimal amountPayment) {
            this.amountPayment = amountPayment;
            return this;
        }

        public Builder creditCard(Long creditCard) {
            this.creditCard = creditCard;
            return this;
        }

        public Builder available(Boolean available) {
            this.available = available;
            return this;
        }

        public Builder orderNo(String orderNo) {
            this.orderNo = orderNo;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
