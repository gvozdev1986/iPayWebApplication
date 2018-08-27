package by.htp.hvozdzeu.model;

import by.htp.hvozdzeu.model.entity.Entity;

import java.util.Objects;

public class PaymentData extends Entity {

	private static final long serialVersionUID = -4865532083547840826L;
	
	private String paymentDataCode;
    private String paymentDataName;
    private String paymentDataGroup;
    private String paymentDataDescription;
    private boolean available;

    public PaymentData() {

    }

    private PaymentData(Builder builder) {
        this.setId(builder.id);
        this.paymentDataCode = builder.paymentDataCode;
        this.paymentDataName = builder.paymentDataName;
        this.paymentDataGroup = builder.paymentDataGroup;
        this.paymentDataDescription = builder.paymentDataDescription;
        this.available = builder.available;
    }

    public String getPaymentDataCode() {
        return paymentDataCode;
    }

    public String getPaymentDataName() {
        return paymentDataName;
    }

    public String getPaymentDataGroup() {
        return paymentDataGroup;
    }

    public String getPaymentDataDescription() {
        return paymentDataDescription;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PaymentData that = (PaymentData) o;
        return available == that.available &&
                Objects.equals(paymentDataCode, that.paymentDataCode) &&
                Objects.equals(paymentDataName, that.paymentDataName) &&
                Objects.equals(paymentDataGroup, that.paymentDataGroup) &&
                Objects.equals(paymentDataDescription, that.paymentDataDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                paymentDataCode,
                paymentDataName,
                paymentDataGroup,
                paymentDataDescription,
                available
        );
    }

    @Override
    public String toString() {
        return "PaymentData{" +
                "id='" + getId() + '\'' +
                ", paymentDataCode='" + paymentDataCode + '\'' +
                ", paymentDataName='" + paymentDataName + '\'' +
                ", paymentDataGroup='" + paymentDataGroup + '\'' +
                ", paymentDataDescription='" + paymentDataDescription + '\'' +
                ", available=" + available +
                '}';
    }

    public static class Builder {
        private Long id;
        private String paymentDataCode;
        private String paymentDataName;
        private String paymentDataGroup;
        private String paymentDataDescription;
        private boolean available;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder paymentDataCode(String paymentDataCode) {
            this.paymentDataCode = paymentDataCode;
            return this;
        }

        public Builder paymentDataName(String paymentDataName) {
            this.paymentDataName = paymentDataName;
            return this;
        }

        public Builder paymentDataGroup(String paymentDataGroup) {
            this.paymentDataGroup = paymentDataGroup;
            return this;
        }

        public Builder paymentDataDescription(String paymentDataDescription) {
            this.paymentDataDescription = paymentDataDescription;
            return this;
        }

        public Builder available(boolean available) {
            this.available = available;
            return this;
        }

        public PaymentData build() {
            return new PaymentData(this);
        }

    }

}
