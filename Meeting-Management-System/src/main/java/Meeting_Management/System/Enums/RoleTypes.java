package Meeting_Management.System.Enums;

public enum RoleTypes {
//    SUPER_ADMIN("SUPER_ADMIN", "Super Administrator"),
    ADMIN("ADMIN", "Administrator"),
    USER("USER", "Regular User");

    private final String code;
    private final String description;

    RoleTypes(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
