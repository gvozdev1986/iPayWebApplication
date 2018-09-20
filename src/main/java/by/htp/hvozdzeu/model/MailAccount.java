package by.htp.hvozdzeu.model;

import by.htp.hvozdzeu.model.entity.Entity;

import java.util.Objects;

public class MailAccount extends Entity {

    private static final long serialVersionUID = 5032771536878953565L;

    private String mailLogin;
    private String mailPswd;

    private MailAccount(){}

    public static Builder getBuilder(){
        return new MailAccount().new Builder();
    }

    public String getMailLogin() {
        return mailLogin;
    }

    public String getMailPswd() {
        return mailPswd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MailAccount that = (MailAccount) o;
        return Objects.equals(mailLogin, that.mailLogin) &&
                Objects.equals(mailPswd, that.mailPswd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mailLogin, mailPswd);
    }

    @Override
    public String
    toString() {
        return "MailAccount{" +
                "mailLogin='" + mailLogin + '\'' +
                ", mailPswd='" + mailPswd + '\'' +
                '}';
    }

    public class Builder {

        private Builder(){}

        public Builder id(Long id) {
            MailAccount.this.setId(id);
            return this;
        }

        public Builder mailLogin(String mailLogin) {
            MailAccount.this.mailLogin = mailLogin;
            return this;
        }

        public Builder mailPswd(String mailPswd) {
            MailAccount.this.mailPswd = mailPswd;
            return this;
        }

        public MailAccount build() {
            return MailAccount.this;
        }

    }

}
