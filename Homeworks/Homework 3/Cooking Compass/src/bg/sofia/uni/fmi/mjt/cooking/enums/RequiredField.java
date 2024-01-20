package bg.sofia.uni.fmi.mjt.cooking.enums;

public enum RequiredField implements ValuedEnum {

    LABEL("label"),
    DIET_LABELS("dietLabels"),
    HEALTH_LABELS("healthLabels"),
    TOTAL_WEIGHT("totalWeight"),
    CUISINE_TYPE("cuisineType"),
    MEAL_TYPE("mealType"),
    DISH_TYPE("dishType"),
    INGREDIENT_LINES("ingredientLines");

    private final String value;

    RequiredField(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
