package bg.sofia.uni.fmi.mjt.order.server.tshirt;

public enum Color {

    BLACK("BLACK"),
    WHITE("WHITE"),
    RED("RED"),
    UNKNOWN("UNKNOWN");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Color fromString(String input) {
        Color color;
        try {
            color = Color.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            color = Color.UNKNOWN;
        }
        return color;
    }

}
