package bg.sofia.uni.fmi.mjt.cooking.enums;

public enum Diet implements ValuedEnum {

    BALANCED("Balanced"),
    HIGH_FIBER("High-Fiber"),
    HIGH_PROTEIN("High-Protein"),
    LOW_CARB("Low-Carb"),
    LOW_FAT("Low-Fat"),
    LOW_SODIUM("Low-Sodium");

    private final String value;

    Diet(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
