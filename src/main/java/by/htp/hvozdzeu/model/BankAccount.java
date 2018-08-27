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

    private BankAccount(Builder builder) {
        this.setId(builder.id);
        this.nameAccount = builder.nameAccount;
        this.creditCard = builder.creditCard;
        this.statusBankAccount = builder.statusBankAccount;
        this.available = builder.available;
        this.balanceBankAccount = builder.balanceBankAccount;
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

    public static class Builder {
        private Long id;
        private String nameAccount;
        private Long creditCard;
        private boolean statusBankAccount;
        private BigDecimal balanceBankAccount;
        private boolean available;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder nameAccount(String nameAccount) {
            this.nameAccount = nameAccount;
            return this;
        }

        public Builder creditCard(Long creditCard) {
            this.creditCard = creditCard;
            return this;
        }

        public Builder statusBankAccount(boolean statusBankAccount) {
            this.statusBankAccount = statusBankAccount;
            return this;
        }

        public Builder balanceBankAccount(BigDecimal balanceBankAccount) {
            this.balanceBankAccount = balanceBankAccount;
            return this;
        }

        public Builder available(boolean available) {
            this.available = available;
            return this;
        }

        public BankAccount build() {
            return new BankAccount(this);
        }

    }

}
