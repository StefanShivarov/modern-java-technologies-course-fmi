package bg.sofia.uni.fmi.mjt.cooking.enums;

public enum DishType implements ValuedEnum {

    ALCOHOL_COCKTAIL("alcohol cocktail"),
    BISCUITS_AND_COOKIES("biscuits and cookies"),
    BREAD("bread"),
    CEREALS("cereals"),
    CONDIMENTS_AND_SAUCES("condiments and sauces"),
    DESSERTS("desserts"),
    DRINKS("drinks"),
    MAIN_COURSE("main course"),
    PANCAKE("pancake"),
    PREPS("preps"),
    PRESERVE("preserve"),
    SALAD("salad"),
    SANDWICHES("sandwiches"),
    SIDE_DISH("side dish"),
    SOUP("soup"),
    STARTER("starter"),
    SWEETS("sweets");

    private final String value;

    DishType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
