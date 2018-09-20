package by.htp.hvozdzeu.model;

import by.htp.hvozdzeu.model.entity.Entity;

import java.math.BigDecimal;
import java.util.Objects;

public class BankAccount extends Entity {

	private static final long serialVersionUID = 7631340316840075453L;
	
	private Long creditCard;
	private String nameAccount;
    private boolean statusBankAccount;
    private BigDecimal balanceBankAccount;
    private boolean available;

    private BankAccount(){}

    public static Builder getBuilder() {
        return new BankAccount().new Builder();
    }

    public Long getCreditCard() {
        return creditCard;
    }
    
    public String getNameAccount() {
		return nameAccount;
	}

    public boolean isStatusBankAccount() {
        return statusBankAccount;
    }

    public boolean isAvailable() {
        return available;
    }

    public BigDecimal getBalanceBankAccount() {
        return balanceBankAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BankAccount that = (BankAccount) o;
        return statusBankAccount == that.statusBankAccount &&
                available == that.available &&
                Objects.equals(nameAccount, that.nameAccount) &&
                Objects.equals(creditCard, that.creditCard) &&
                Objects.equals(balanceBankAccount, that.balanceBankAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                nameAccount,
                creditCard,
                statusBankAccount,
                balanceBankAccount,
                available
        );
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + getId() +
                ", nameAccount=" + nameAccount + 
                ", creditCard=" + creditCard +
                ", statusBankAccount=" + statusBankAccount +
                ", balanceBankAccount=" + balanceBankAccount +
                ", available=" + available +
                '}';
    }

    public class Builder {

        private Builder(){}

        public Builder id(Long id) {
            BankAccount.this.setId(id);
            return this;
        }
        
        public Builder nameAccount(String nameAccount) {
            BankAccount.this.nameAccount = nameAccount;
            return this;
        }

        public Builder creditCard(Long creditCard) {
            BankAccount.this.creditCard = creditCard;
            return this;
        }

        public Builder statusBankAccount(boolean statusBankAccount) {
            BankAccount.this.statusBankAccount = statusBankAccount;
            return this;
        }

        public Builder balanceBankAccount(BigDecimal balanceBankAccount) {
            BankAccount.this.balanceBankAccount = balanceBankAccount;
            return this;
        }

        public Builder available(boolean available) {
            BankAccount.this.available = available;
            return this;
        }

        public BankAccount build() {
            return BankAccount.this;
        }

    }

}
