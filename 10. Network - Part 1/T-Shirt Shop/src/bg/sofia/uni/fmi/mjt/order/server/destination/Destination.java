package bg.sofia.uni.fmi.mjt.order.server.destination;

public enum Destination {

    EUROPE("EUROPE"),
    NORTH_AMERICA("NORTH_AMERICA"),
    AUSTRALIA("AUSTRALIA"),
    UNKNOWN("UNKNOWN");

    private final String name;

    Destination(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Destination fromString(String input) {
        Destination destination;
        try {
            destination = Destination.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            destination = Destination.UNKNOWN;
        }
        return destination;
    }

}
