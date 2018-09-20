package by.htp.hvozdzeu.model;

import by.htp.hvozdzeu.model.entity.Entity;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class User extends Entity {

	private static final long serialVersionUID = 7207746452172066362L;
	
	private String login;
    private char[] password;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate dateBirth;
    private String phoneHome;
    private String phoneMobile;
    private String address;
    private String email;
    private boolean available;
    private boolean isAdmin;
    private String regCode;

    private User() {}

    public static Builder getBuilder() {
        return new User().new Builder();
    }

    public String getLogin() {
        return login;
    }

    public char[] getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAvailable() {
        return available;
    }
    
    public boolean isAdmin() {
		return isAdmin;
	}

    public String getRegCode() {
        return regCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return available == user.available &&
                isAdmin == user.isAdmin &&
                Objects.equals(login, user.login) &&
                Arrays.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(patronymic, user.patronymic) &&
                Objects.equals(dateBirth, user.dateBirth) &&
                Objects.equals(phoneHome, user.phoneHome) &&
                Objects.equals(phoneMobile, user.phoneMobile) &&
                Objects.equals(address, user.address) &&
                Objects.equals(email, user.email) &&
                Objects.equals(regCode, user.regCode);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), login, firstName, lastName, patronymic, dateBirth, phoneHome, phoneMobile, address, email, available, isAdmin, regCode);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateBirth=" + dateBirth +
                ", phoneHome='" + phoneHome + '\'' +
                ", phoneMobile='" + phoneMobile + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", available=" + available +
                ", isAdmin=" + isAdmin +
                ", regCode=" + regCode +
                '}';
    }

    public class Builder {

        private Builder(){}

        public Builder id(Long id) {
            User.this.setId(id);
            return this;
        }

        public Builder login(String login) {
            User.this.login = login;
            return this;
        }

        public Builder password(char[] password) {
            User.this.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
            User.this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            User.this.lastName = lastName;
            return this;
        }

        public Builder patronymic(String patronymic) {
            User.this.patronymic = patronymic;
            return this;
        }

        public Builder dateBirth(LocalDate dateBirth) {
            User.this.dateBirth = dateBirth;
            return this;
        }

        public Builder phoneHome(String phoneHome) {
            User.this.phoneHome = phoneHome;
            return this;
        }

        public Builder phoneMobile(String phoneMobile) {
            User.this.phoneMobile = phoneMobile;
            return this;
        }

        public Builder address(String address) {
            User.this.address = address;
            return this;
        }

        public Builder email(String email) {
            User.this.email = email;
            return this;
        }

        public Builder available(boolean available) {
            User.this.available = available;
            return this;
        }
        
        public Builder isAdmin(boolean isAdmin) {
            User.this.isAdmin = isAdmin;
            return this;
        }

        public Builder regCode(String regCode) {
            User.this.regCode = regCode;
            return this;
        }

        public User build() {
            return User.this;
        }

    }

}
