package bg.sofia.uni.fmi.mjt.cooking.enums;

public enum DishType {

    BISCUITS_AND_COOKIES("Biscuits and cookies"),
    BREAD("Bread"),
    CEREALS("Cereals"),
    CONDIMENTS_AND_SAUCES("Condiments and sauces"),
    DESSERTS("Desserts"),
    DRINKS("Drinks"),
    MAIN_COURSE("Main course"),
    PANCAKE("Pancake"),
    PREPS("Preps"),
    PRESERVE("Preserve"),
    SALAD("Salad"),
    SANDWICHES("Sandwiches"),
    SIDE_DISH("Side dish"),
    SOUP("Soup"),
    STARTER("Starter"),
    SWEETS("Sweets");

    private final String value;

    DishType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
