package bg.sofia.uni.fmi.mjt.order.server.order;

import bg.sofia.uni.fmi.mjt.order.server.destination.Destination;
import bg.sofia.uni.fmi.mjt.order.server.tshirt.TShirt;

public record Order(int id, TShirt tShirt, Destination destination) {

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"tShirt\":" + tShirt +
                ", \"destination\":" + '\"' + destination + '\"' +
                '}';
    }

}
