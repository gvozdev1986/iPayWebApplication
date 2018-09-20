package by.htp.hvozdzeu.model.report;

import by.htp.hvozdzeu.model.entity.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class PaymentReport extends Entity {

    private static final long serialVersionUID = 5541088875758793592L;

	private LocalDate datePayment;
	private LocalTime timePayment;
	private String descriptionPayment;
	private BigDecimal amountPayment;
	private String paymentDataName;
	private String paymentDataGroup;
	private String paymentDataDescription;

	private PaymentReport() {

	}

	public static Builder getBuilder(){
		return new PaymentReport().new Builder();
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

    public BigDecimal getAmountPayment() {
        return amountPayment;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PaymentReport that = (PaymentReport) o;
        return Objects.equals(datePayment, that.datePayment) &&
                Objects.equals(timePayment, that.timePayment) &&
                Objects.equals(descriptionPayment, that.descriptionPayment) &&
                Objects.equals(amountPayment, that.amountPayment) &&
                Objects.equals(paymentDataName, that.paymentDataName) &&
                Objects.equals(paymentDataGroup, that.paymentDataGroup) &&
                Objects.equals(paymentDataDescription, that.paymentDataDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                datePayment,
                timePayment,
                descriptionPayment,
                amountPayment,
                paymentDataName,
                paymentDataGroup,
                paymentDataDescription
        );
    }


    @Override
    public String toString() {
        return "PaymentReport{" +
                "datePayment=" + datePayment +
                ", timePayment=" + timePayment +
                ", descriptionPayment='" + descriptionPayment + '\'' +
                ", amountPayment=" + amountPayment +
                ", paymentDataName='" + paymentDataName + '\'' +
                ", paymentDataGroup='" + paymentDataGroup + '\'' +
                ", paymentDataDescription='" + paymentDataDescription + '\'' +
                '}';
    }

    public class Builder {

		private Builder(){}
		
		public Builder id(Long id) {
			PaymentReport.this.setId(id);
			return this;
		}

		public Builder datePayment(LocalDate datePayment) {
            PaymentReport.this.datePayment = datePayment;
			return this;
		}

		public Builder timePayment(LocalTime timePayment) {
            PaymentReport.this.timePayment = timePayment;
			return this;
		}

		public Builder descriptionPayment(String descriptionPayment) {
            PaymentReport.this.descriptionPayment = descriptionPayment;
			return this;
		}

		public Builder amountPayment(BigDecimal amountPayment) {
            PaymentReport.this.amountPayment = amountPayment;
			return this;
		}

		public Builder paymentDataName(String paymentDataName) {
            PaymentReport.this.paymentDataName = paymentDataName;
			return this;
		}

		public Builder paymentDataGroup(String paymentDataGroup) {
            PaymentReport.this.paymentDataGroup = paymentDataGroup;
			return this;
		}

		public Builder paymentDataDescription(String paymentDataDescription) {
            PaymentReport.this.paymentDataDescription = paymentDataDescription;
			return this;
		}

		public PaymentReport build() {
			return PaymentReport.this;
		}
	}

}
