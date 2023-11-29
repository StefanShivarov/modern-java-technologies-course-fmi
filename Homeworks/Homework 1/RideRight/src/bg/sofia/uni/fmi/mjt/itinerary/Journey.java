package bg.sofia.uni.fmi.mjt.itinerary;

import bg.sofia.uni.fmi.mjt.itinerary.vehicle.VehicleType;

import java.math.BigDecimal;
import java.util.Objects;

public record Journey(VehicleType vehicleType, City from, City to, BigDecimal price) {

    public BigDecimal calculatePriceAfterTax() {
        return price
                .add(price
                        .multiply(vehicleType.getGreenTax())
                );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return vehicleType == journey.vehicleType
                && Objects.equals(from, journey.from)
                && Objects.equals(to, journey.to)
                && Objects.equals(price, journey.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleType, from, to, price);
    }

    @Override
    public String toString() {
        return "Journey{"
                + vehicleType.name()
                + ", from=" + from.name()
                + ", to=" + to.name()
                + ", price=" + price
                + '}';
    }

}
