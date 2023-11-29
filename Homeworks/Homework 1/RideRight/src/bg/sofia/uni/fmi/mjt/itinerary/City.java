package bg.sofia.uni.fmi.mjt.itinerary;

import java.math.BigDecimal;
import java.util.Objects;

public record City(String name, Location location) {

    private static final BigDecimal DOLLARS_PER_KM = BigDecimal.valueOf(20);
    private static final BigDecimal KM_IN_METERS = BigDecimal.valueOf(1000);
    
    public BigDecimal calculateEstimatedPriceToDestination(City destination) {
        if (destination == null) {
            throw new IllegalArgumentException("City is null!");
        }

        return DOLLARS_PER_KM
                .multiply(BigDecimal.valueOf(
                        location.calculateManhattanDistanceTo(destination.location)
                ))
                .divide(KM_IN_METERS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

}
