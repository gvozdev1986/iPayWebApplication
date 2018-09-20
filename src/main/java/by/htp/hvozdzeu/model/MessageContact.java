package by.htp.hvozdzeu.model;

import by.htp.hvozdzeu.model.entity.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class MessageContact extends Entity {

    private static final long serialVersionUID = 4461284830441916569L;

    private String nameContact;
    private LocalDate date;
    private LocalTime time;
    private String emailContact;
    private String phoneContact;
    private String messageFromContact;
    private boolean checkRead;

    private MessageContact() {
    }

    public static Builder getBuilder() {
        return new MessageContact().new Builder();
    }

    public String getNameContact() {
        return nameContact;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
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

    public boolean isCheckRead() {
        return checkRead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MessageContact that = (MessageContact) o;
        return checkRead == that.checkRead &&
                Objects.equals(nameContact, that.nameContact) &&
                Objects.equals(date, that.date) &&
                Objects.equals(time, that.time) &&
                Objects.equals(emailContact, that.emailContact) &&
                Objects.equals(phoneContact, that.phoneContact) &&
                Objects.equals(messageFromContact, that.messageFromContact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                nameContact,
                date,
                time,
                emailContact,
                phoneContact,
                messageFromContact,
                checkRead
        );
    }

    @Override
    public String toString() {
        return "MessageContact{" +
                "nameContact='" + nameContact + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", emailContact='" + emailContact + '\'' +
                ", phoneContact='" + phoneContact + '\'' +
                ", messageFromContact='" + messageFromContact + '\'' +
                ", checkRead=" + checkRead +
                '}';
    }

    public class Builder {

        private Builder() {
        }


        public Builder id(Long id) {
            MessageContact.this.setId(id);
            return this;
        }

        public Builder nameContact(String nameContact) {
            MessageContact.this.nameContact = nameContact;
            return this;
        }

        public Builder date(LocalDate date) {
            MessageContact.this.date = date;
            return this;
        }

        public Builder time(LocalTime time) {
            MessageContact.this.time = time;
            return this;
        }

        public Builder emailContact(String emailContact) {
            MessageContact.this.emailContact = emailContact;
            return this;
        }

        public Builder phoneContact(String phoneContact) {
            MessageContact.this.phoneContact = phoneContact;
            return this;
        }

        public Builder messageFromContact(String messageFromContact) {
            MessageContact.this.messageFromContact = messageFromContact;
            return this;
        }

        public Builder checkRead(boolean checkRead) {
            MessageContact.this.checkRead = checkRead;
            return this;
        }

        public MessageContact build() {
            return MessageContact.this;
        }

    }


}
