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

    public User() {

    }

    public User(Builder builder) {
        this.setId(builder.id);
        this.login = builder.login;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.patronymic = builder.patronymic;
        this.dateBirth = builder.dateBirth;
        this.phoneHome = builder.phoneHome;
        this.phoneMobile = builder.phoneMobile;
        this.address = builder.address;
        this.email = builder.email;
        this.available = builder.available;
        this.isAdmin = builder.isAdmin;
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
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(
                super.hashCode(), 
                login, 
                firstName, 
                lastName, 
                patronymic, 
                dateBirth, 
                phoneHome, 
                phoneMobile, 
                address, 
                email, 
                available, 
                isAdmin
        );
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
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
                '}';
    }

    public static class Builder {

        private Long id;
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

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(char[] password) {
            this.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder patronymic(String patronymic) {
            this.patronymic = patronymic;
            return this;
        }

        public Builder dateBirth(LocalDate dateBirth) {
            this.dateBirth = dateBirth;
            return this;
        }

        public Builder phoneHome(String phoneHome) {
            this.phoneHome = phoneHome;
            return this;
        }

        public Builder phoneMobile(String phoneMobile) {
            this.phoneMobile = phoneMobile;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder available(boolean available) {
            this.available = available;
            return this;
        }
        
        public Builder isAdmin(boolean isAdmin) {
            this.isAdmin = isAdmin;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

}
