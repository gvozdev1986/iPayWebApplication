package by.htp.hvozdzeu.model;

import by.htp.hvozdzeu.model.entity.Entity;
import by.htp.hvozdzeu.model.enums.TypeCard;

import java.util.Objects;

public class CreditCard extends Entity {

	private static final long serialVersionUID = -1647338790960981508L;
	
	private Long client;
    private String cardNumber;
    private String cardFirstName;
    private String cardLastName;
    private String validDate;
    private TypeCard typeCard;
    private String verifyCode;
    private boolean block;
    private boolean available;

    private CreditCard() {}

    public static Builder getBuilder() {
        return new CreditCard().new Builder();
    }

    public Long getClient() {
        return client;
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

    public TypeCard getTypeCard() {
        return typeCard;
    }

    public String getVerifyCode() {
        return verifyCode;
    }
    
    public boolean isBlock() {
		return block;
	}

    public boolean isAvailable() {
        return available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CreditCard that = (CreditCard) o;
        return available == that.available &&
        		Objects.equals(block, that.block) &&
                Objects.equals(client, that.client) &&
                Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(cardFirstName, that.cardFirstName) &&
                Objects.equals(cardLastName, that.cardLastName) &&
                Objects.equals(validDate, that.validDate) &&
                Objects.equals(typeCard, that.typeCard) &&
                Objects.equals(verifyCode, that.verifyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                client,
                cardNumber,
                cardFirstName,
                cardLastName,
                validDate,
                typeCard,
                verifyCode,
                block,
                available
        );
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + getId() +
                ", message=" + client +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardFirstName='" + cardFirstName + '\'' +
                ", cardLastName='" + cardLastName + '\'' +
                ", validDate='" + validDate + '\'' +
                ", typeCard='" + typeCard + '\'' +
                ", block='" + block + '\'' +
                ", available=" + available +
                '}';
    }

    public class Builder {

        private Builder(){}

        public Builder id(Long id) {
            CreditCard.this.setId(id);
            return this;
        }

        public Builder client(Long client) {
            CreditCard.this.client = client;
            return this;
        }

        public Builder cardNumber(String cardNumber) {
            CreditCard.this.cardNumber = cardNumber;
            return this;
        }

        public Builder cardFirstName(String cardFirstName) {
            CreditCard.this.cardFirstName = cardFirstName;
            return this;
        }

        public Builder cardLastName(String cardLastName) {
            CreditCard.this.cardLastName = cardLastName;
            return this;
        }

        public Builder validDate(String validDate) {
            CreditCard.this.validDate = validDate;
            return this;
        }

        public Builder typeCard(TypeCard typeCard) {
            CreditCard.this.typeCard = typeCard;
            return this;
        }

        public Builder verifyCode(String verifyCode) {
            CreditCard.this.verifyCode = verifyCode;
            return this;
        }
        
        public Builder block(boolean block) {
            CreditCard.this.block = block;
            return this;
        }

        public Builder available(boolean available) {
            CreditCard.this.available = available;
            return this;
        }

        public CreditCard build() {
            return CreditCard.this;
        }

    }

}
