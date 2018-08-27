package by.htp.hvozdzeu.model.report;

import java.math.BigDecimal;
import java.util.Objects;

public class SumPaymentReportChartPie {

	private Integer amount;
	private String group;
	private BigDecimal sum;

	public SumPaymentReportChartPie() {

	}

	public SumPaymentReportChartPie(Builder builder) {
		this.amount = builder.amount;
		this.group = builder.group;
		this.sum = builder.sum;
	}

	public Integer getAmount() {
		return amount;
	}

	public String getGroup() {
		return group;
	}

	public BigDecimal getSum() {
		return sum;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SumPaymentReportChartPie that = (SumPaymentReportChartPie) o;
		return Objects.equals(amount, that.amount) && Objects.equals(group, that.group)
				&& Objects.equals(sum, that.sum);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, group, sum);
	}

	@Override
	public String toString() {
		return "SumPaymentReportChartPie{" + "amount=" + amount + ", group='" + group + '\'' + ", sum=" + sum + '}';
	}

	public static class Builder {
		private Integer amount;
		private String group;
		private BigDecimal sum;

		public Builder amount(Integer amount) {
			this.amount = amount;
			return this;
		}

		public Builder group(String group) {
			this.group = group;
			return this;
		}

		public Builder sum(BigDecimal sum) {
			this.sum = sum;
			return this;
		}

		public SumPaymentReportChartPie build() {
			return new SumPaymentReportChartPie(this);
		}

	}

}
