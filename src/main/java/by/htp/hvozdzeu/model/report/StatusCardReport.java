package by.htp.hvozdzeu.model.report;

import by.htp.hvozdzeu.model.entity.Entity;

import java.math.BigDecimal;
import java.util.Objects;

public class StatusCardReport extends Entity {

	private static final long serialVersionUID = -7982000782809170117L;

	private String cardNumber;
	private String cardFirstName;
	private String cardLastName;
	private String validDate;
	private String typeCard;
	private String verifyCode;
	private boolean block;
	private BigDecimal balanceBankAccount;
	private String nameAccount;

	private StatusCardReport() {}

    public static Builder getBuilder(){
        return new StatusCardReport().new Builder();
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StatusCardReport that = (StatusCardReport) o;
        return block == that.block &&
                Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(cardFirstName, that.cardFirstName) &&
                Objects.equals(cardLastName, that.cardLastName) &&
                Objects.equals(validDate, that.validDate) &&
                Objects.equals(typeCard, that.typeCard) &&
                Objects.equals(verifyCode, that.verifyCode) &&
                Objects.equals(balanceBankAccount, that.balanceBankAccount) &&
                Objects.equals(nameAccount, that.nameAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                cardNumber,
                cardFirstName,
                cardLastName,
                validDate,
                typeCard,
                verifyCode,
                block,
                balanceBankAccount,
                nameAccount
        );
    }

    @Override
    public String toString() {
        return "StatusCardReport{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardFirstName='" + cardFirstName + '\'' +
                ", cardLastName='" + cardLastName + '\'' +
                ", validDate='" + validDate + '\'' +
                ", typeCard='" + typeCard + '\'' +
                ", block=" + block +
                ", balanceBankAccount=" + balanceBankAccount +
                ", nameAccount='" + nameAccount + '\'' +
                '}';
    }

    public class Builder {

	    private Builder(){}

		public Builder id(Long id) {
			StatusCardReport.this.setId(id);
			return this;
		}

		public Builder cardNumber(String cardNumber) {
            StatusCardReport.this.cardNumber = cardNumber;
			return this;
		}

		public Builder cardFirstName(String cardFirstName) {
            StatusCardReport.this.cardFirstName = cardFirstName;
			return this;
		}

		public Builder cardLastName(String cardLastName) {
            StatusCardReport.this.cardLastName = cardLastName;
			return this;
		}

		public Builder validDate(String validDate) {
            StatusCardReport.this.validDate = validDate;
			return this;
		}

		public Builder typeCard(String typeCard) {
            StatusCardReport.this.typeCard = typeCard;
			return this;
		}

		public Builder verifyCode(String verifyCode) {
            StatusCardReport.this.verifyCode = verifyCode;
			return this;
		}

		public Builder block(boolean block) {
            StatusCardReport.this.block = block;
			return this;
		}

		public Builder balanceBankAccount(BigDecimal balanceBankAccount) {
            StatusCardReport.this.balanceBankAccount = balanceBankAccount;
			return this;
		}

		public Builder nameAccount(String nameAccount) {
            StatusCardReport.this.nameAccount = nameAccount;
			return this;
		}

		public StatusCardReport build() {
			return StatusCardReport.this;
		}

	}

}
