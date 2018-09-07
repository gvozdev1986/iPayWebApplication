package by.htp.hvozdzeu.model.enums;

public enum Role {

    ADMINISTRATOR("administrator"),
    CLIENT("client"),
    GUEST("guest");
	
	private String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
