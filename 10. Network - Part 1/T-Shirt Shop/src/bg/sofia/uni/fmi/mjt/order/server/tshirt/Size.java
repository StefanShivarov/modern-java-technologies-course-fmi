package bg.sofia.uni.fmi.mjt.order.server.tshirt;

public enum Size {

    S("S"),
    M("M"),
    L("L"),
    XL("XL"),
    UNKNOWN("UNKNOWN");

    private final String name;

    Size(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Size fromString(String input) {
        Size size;
        try {
            size = Size.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            size = Size.UNKNOWN;
        }
        return size;
    }

}
