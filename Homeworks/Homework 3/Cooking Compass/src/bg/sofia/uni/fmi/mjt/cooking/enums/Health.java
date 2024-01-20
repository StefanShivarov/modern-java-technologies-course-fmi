package bg.sofia.uni.fmi.mjt.cooking.enums;

public enum Health implements ValuedEnum {

    ALCOHOL_COCKTAIL("Alcohol-Cocktail"),
    ALCOHOL_FREE("Alcohol-Free"),
    CELERY_FREE("Celery-Free"),
    CRUSTACEAN_FREE("Crustacean-Free"),
    DAIRY_FREE("Dairy-Free"),
    DASH("DASH"),
    EGG_FREE("Egg-Free"),
    FISH_FREE("Fish-Free"),
    FODMAP_FREE("Fodmap-Free"),
    GLUTEN_FREE("Gluten-Free"),
    IMMUNO_SUPPORTIVE("Immuno-Supportive"),
    KETO_FRIENDLY("Keto-Friendly"),
    KIDNEY_FRIENDLY("Kidney-Friendly"),
    KOSHER("Kosher"),
    LOW_FAT_ABS("Low-Fat-Abs"),
    LOW_POTASSIUM("Low Potassium"),
    LOW_SUGAR("Low Sugar"),
    LUPINE_FREE("Lupine-Free"),
    MEDITERRANEAN("Mediterranean"),
    MOLLUSK_FREE("Mollusk-Free"),
    MUSTARD_FREE("Mustard-free"),
    NO_OIL_ADDED("No oil added"),
    PALEO("Paleo"),
    PEANUT_FREE("Peanut-Free"),
    PESCATARIAN("Pescatarian"),
    PORK_FREE("Pork-Free"),
    RED_MEAT_FREE("Red-Meat-Free"),
    SESAME_FREE("Sesame-Free"),
    SHELLFISH_FREE("Shellfish-Free"),
    SOY_FREE("Soy-Free"),
    SUGAR_CONSCIOUS("Sugar-Conscious"),
    SULFITE_FREE("Sulfite-free"),
    TREE_NUT_FREE("Tree-Nut-Free"),
    VEGAN("Vegan"),
    VEGETARIAN("Vegetarian"),
    WHEAT_FREE("Wheat-Free");

    private final String value;

    Health(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
