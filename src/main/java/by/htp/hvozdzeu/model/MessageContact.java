package by.htp.hvozdzeu.model;

import by.htp.hvozdzeu.model.entity.Entity;

import java.util.Objects;

public class MessageContact extends Entity {

	private static final long serialVersionUID = 4461284830441916569L;
	
	private String nameContact;
    private String emailContact;
    private String phoneContact;
    private String messageFromContact;
    private boolean isRead;

    public MessageContact(Builder builder) {
        this.setId(builder.id);
        this.nameContact = builder.nameContact;
        this.emailContact = builder.emailContact;
        this.phoneContact = builder.phoneContact;
        this.messageFromContact = builder.messageFromContact;
        this.isRead = builder.isRead;
    }

    public String getNameContact() {
        return nameContact;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public String getPhoneContact() {
        return phoneContact;
    }

    public String getMessageFromContact() {
        return messageFromContact;
    }

    public boolean isRead() {
        return isRead;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MessageContact that = (MessageContact) o;
        return isRead == that.isRead &&
                Objects.equals(nameContact, that.nameContact) &&
                Objects.equals(emailContact, that.emailContact) &&
                Objects.equals(phoneContact, that.phoneContact) &&
                Objects.equals(messageFromContact, that.messageFromContact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                nameContact,
                emailContact,
                phoneContact,
                messageFromContact,
                isRead
        );
    }


    @Override
    public String toString() {
        return "MessageContact{" +
                "id='" + getId() + '\'' +
                ", nameContact='" + nameContact + '\'' +
                ", emailContact='" + emailContact + '\'' +
                ", phoneContact='" + phoneContact + '\'' +
                ", messageFromContact='" + messageFromContact + '\'' +
                ", isRead=" + isRead +
                '}';
    }

    public static class Builder {

        private Long id;
        private String nameContact;
        private String emailContact;
        private String phoneContact;
        private String messageFromContact;
        private boolean isRead;


        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nameContact(String nameContact) {
            this.nameContact = nameContact;
            return this;
        }

        public Builder emailContact(String emailContact) {
            this.emailContact = emailContact;
            return this;
        }

        public Builder phoneContact(String phoneContact) {
            this.phoneContact = phoneContact;
            return this;
        }

        public Builder messageFromContact(String messageFromContact) {
            this.messageFromContact = messageFromContact;
            return this;
        }

        public Builder isRead(boolean isRead) {
            this.isRead = isRead;
            return this;
        }


        public MessageContact build() {
            return new MessageContact(this);
        }

    }


}
