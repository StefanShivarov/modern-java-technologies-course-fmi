package bg.sofia.uni.fmi.mjt.order.server.tshirt;

public record TShirt(Size size, Color color) {

    @Override
    public String toString() {
        return "{"
                + "\"size\":" + '\"' + size + '\"'
                + ", \"color\":" + '\"' + color + '\"'
                + '}';
    }

}
