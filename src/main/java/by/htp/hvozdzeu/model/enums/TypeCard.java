package by.htp.hvozdzeu.model.enums;

public enum TypeCard {

    VISA("VISA"),
    MASTERCARD("MASTERCARD"),
    MASTERCARD_MAESTRO("MASTERCARD MAESTRO"),
    VISA_ELECTRON("VISA ELECTRON"),
    BELCARD("BELCARD"),
    UNDEFINED("UNDEFINED");

    private String type;

    TypeCard(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
