package by.htp.hvozdzeu.model.report;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class PaymentReport {

	private Long id;
	private LocalDate datePayment;
	private LocalTime timePayment;
	private String descriptionPayment;
	private BigDecimal amountPayment;
	private String paymentDataName;
	private String paymentDataGroup;
	private String paymentDataDescription;

	public PaymentReport() {

	}

	public PaymentReport(Builder builder) {
		this.id = builder.id;
		this.datePayment = builder.datePayment;
		this.timePayment = builder.timePayment;
		this.descriptionPayment = builder.descriptionPayment;
		this.amountPayment = builder.amountPayment;
		this.paymentDataName = builder.paymentDataName;
		this.paymentDataGroup = builder.paymentDataGroup;
		this.paymentDataDescription = builder.paymentDataDescription;
	}

	public Long getId() {
		return id;
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
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PaymentReport that = (PaymentReport) o;
		return Objects.equals(id, that.id) && Objects.equals(datePayment, that.datePayment)
				&& Objects.equals(timePayment, that.timePayment)
				&& Objects.equals(descriptionPayment, that.descriptionPayment)
				&& Objects.equals(amountPayment, that.amountPayment)
				&& Objects.equals(paymentDataName, that.paymentDataName)
				&& Objects.equals(paymentDataGroup, that.paymentDataGroup)
				&& Objects.equals(paymentDataDescription, that.paymentDataDescription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, datePayment, timePayment, descriptionPayment, amountPayment, paymentDataName,
				paymentDataGroup, paymentDataDescription);
	}

	@Override
	public String toString() {
		return "PaymentReport{" + "id=" + id + ", datePayment=" + datePayment + ", timePayment=" + timePayment
				+ ", descriptionPayment='" + descriptionPayment + '\'' + ", amountPayment=" + amountPayment
				+ ", paymentDataName='" + paymentDataName + '\'' + ", paymentDataGroup='" + paymentDataGroup + '\''
				+ ", paymentDataDescription='" + paymentDataDescription + '\'' + '}';
	}

	public static class Builder {
		private Long id;
		private LocalDate datePayment;
		private LocalTime timePayment;
		private String descriptionPayment;
		private BigDecimal amountPayment;
		private String paymentDataName;
		private String paymentDataGroup;
		private String paymentDataDescription;
		
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

		public Builder amountPayment(BigDecimal amountPayment) {
			this.amountPayment = amountPayment;
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

		public PaymentReport build() {
			return new PaymentReport(this);
		}
	}

}
