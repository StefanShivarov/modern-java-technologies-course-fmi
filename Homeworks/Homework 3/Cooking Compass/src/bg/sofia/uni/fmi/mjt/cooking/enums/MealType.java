package bg.sofia.uni.fmi.mjt.cooking.enums;

public enum MealType implements ValuedEnum {

    BREAKFAST("breakfast"),
    DINNER("dinner"),
    LUNCH("lunch"),
    LUNCH_DINNER("lunch/dinner"),
    SNACK("snack"),
    TEATIME("teatime");

    private final String value;

    MealType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
