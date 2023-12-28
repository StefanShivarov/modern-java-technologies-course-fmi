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

}
