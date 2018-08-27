package by.htp.hvozdzeu.model;

import by.htp.hvozdzeu.model.entity.Entity;
import by.htp.hvozdzeu.model.enums.Role;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Client extends Entity {

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
    private Role role;
    private String email;
    private boolean available;
    private boolean isOnline;

    public Client() {

    }

    public Client(Builder builder) {
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
        this.role = builder.role;
        this.email = builder.email;
        this.available = builder.available;
        this.isOnline = builder.isOnline;
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

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAvailable() {
        return available;
    }
    
    public boolean isOnline() {
		return isOnline;
	}   

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return available == client.available &&
                isOnline == client.isOnline &&
                Objects.equals(login, client.login) &&
                Arrays.equals(password, client.password) &&
                Objects.equals(firstName, client.firstName) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(patronymic, client.patronymic) &&
                Objects.equals(dateBirth, client.dateBirth) &&
                Objects.equals(phoneHome, client.phoneHome) &&
                Objects.equals(phoneMobile, client.phoneMobile) &&
                Objects.equals(address, client.address) &&
                role == client.role &&
                Objects.equals(email, client.email);
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
                role, 
                email, 
                available, 
                isOnline
        );
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }


    @Override
    public String toString() {
        return "Client{" +
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
                ", role=" + role +
                ", email='" + email + '\'' +
                ", available=" + available +
                ", isOnline=" + isOnline +
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
        private Role role;
        private String email;
        private boolean available;
        private boolean isOnline;

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

        public Builder role(Role role) {
            this.role = role;
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
        
        public Builder isOnline(boolean isOnline) {
            this.isOnline = isOnline;
            return this;
        }

        public Client build() {
            return new Client(this);
        }

    }

}
