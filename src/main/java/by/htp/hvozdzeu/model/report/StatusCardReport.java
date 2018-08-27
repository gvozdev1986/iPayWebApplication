package by.htp.hvozdzeu.model.report;

import java.math.BigDecimal;
import java.util.Objects;

public class StatusCardReport {

	private Long id;
	private String cardNumber;
	private String cardFirstName;
	private String cardLastName;
	private String validDate;
	private String typeCard;
	private String verifyCode;
	private boolean block;
	private BigDecimal balanceBankAccount;
	private String nameAccount;

	public StatusCardReport() {
		super();
	}

	public StatusCardReport(Builder builder) {
		this.id = builder.id;
		this.cardNumber = builder.cardNumber;
		this.cardFirstName = builder.cardFirstName;
		this.cardLastName = builder.cardLastName;
		this.validDate = builder.validDate;
		this.typeCard = builder.typeCard;
		this.verifyCode = builder.verifyCode;
		this.block = builder.block;
		this.balanceBankAccount = builder.balanceBankAccount;
		this.nameAccount = builder.nameAccount;
	}

	public Long getId() {
		return id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getCardFirstName() {
		return cardFirstName;
	}

	public String getCardLastName() {
		return cardLastName;
	}

	public String getValidDate() {
		return validDate;
	}

	public String getTypeCard() {
		return typeCard;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public boolean isBlock() {
		return block;
	}

	public BigDecimal getBalanceBankAccount() {
		return balanceBankAccount;
	}

	public String getNameAccount() {
		return nameAccount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		StatusCardReport that = (StatusCardReport) o;
		return block == that.block && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(id, that.id)
				&& Objects.equals(cardFirstName, that.cardFirstName) && Objects.equals(cardLastName, that.cardLastName)
				&& Objects.equals(validDate, that.validDate) && Objects.equals(typeCard, that.typeCard)
				&& Objects.equals(verifyCode, that.verifyCode)
				&& Objects.equals(balanceBankAccount, that.balanceBankAccount)
				&& Objects.equals(nameAccount, that.nameAccount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cardNumber, cardFirstName, cardLastName, validDate, typeCard, verifyCode, block,
				balanceBankAccount, nameAccount);
	}

	@Override
	public String toString() {
		return "StatusCardReport{" + "id='" + id + '\'' + ", cardNumber='" + cardNumber + '\'' + ", cardFirstName='"
				+ cardFirstName + '\'' + ", cardLastName='" + cardLastName + '\'' + ", validDate='" + validDate + '\''
				+ ", typeCard='" + typeCard + '\'' + ", verifyCode='" + verifyCode + '\'' + ", block=" + block
				+ ", balanceBankAccount=" + balanceBankAccount + ", nameAccount='" + nameAccount + '\'' + '}';
	}

	public static class Builder {
		private Long id;
		private String cardNumber;
		private String cardFirstName;
		private String cardLastName;
		private String validDate;
		private String typeCard;
		private String verifyCode;
		private boolean block;
		private BigDecimal balanceBankAccount;
		private String nameAccount;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder cardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
			return this;
		}

		public Builder cardFirstName(String cardFirstName) {
			this.cardFirstName = cardFirstName;
			return this;
		}

		public Builder cardLastName(String cardLastName) {
			this.cardLastName = cardLastName;
			return this;
		}

		public Builder validDate(String validDate) {
			this.validDate = validDate;
			return this;
		}

		public Builder typeCard(String typeCard) {
			this.typeCard = typeCard;
			return this;
		}

		public Builder verifyCode(String verifyCode) {
			this.verifyCode = verifyCode;
			return this;
		}

		public Builder block(boolean block) {
			this.block = block;
			return this;
		}

		public Builder balanceBankAccount(BigDecimal balanceBankAccount) {
			this.balanceBankAccount = balanceBankAccount;
			return this;
		}

		public Builder nameAccount(String nameAccount) {
			this.nameAccount = nameAccount;
			return this;
		}

		public StatusCardReport build() {
			return new StatusCardReport(this);
		}

	}

}
