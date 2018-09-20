package by.htp.hvozdzeu.model.report;

import java.math.BigDecimal;
import java.util.Objects;

public class SumPaymentReportChartPie {

	private Integer amount;
	private String group;
	private BigDecimal sum;

	private SumPaymentReportChartPie() {}

	public static Builder getBuilder(){
		return new SumPaymentReportChartPie().new Builder();
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

	public class Builder {

		private Builder(){}

		public Builder amount(Integer amount) {
			SumPaymentReportChartPie.this.amount = amount;
			return this;
		}

		public Builder group(String group) {
            SumPaymentReportChartPie.this.group = group;
			return this;
		}

		public Builder sum(BigDecimal sum) {
            SumPaymentReportChartPie.this.sum = sum;
			return this;
		}

		public SumPaymentReportChartPie build() {
			return SumPaymentReportChartPie.this;
		}

	}

}
