package bg.sofia.uni.fmi.mjt.itinerary.comparators;

import bg.sofia.uni.fmi.mjt.itinerary.City;
import bg.sofia.uni.fmi.mjt.itinerary.Journey;

import java.util.Comparator;

public class JourneyCheapestPathComparator implements Comparator<Journey> {

    private final City destination;

    public JourneyCheapestPathComparator(City destination) {
        this.destination = destination;
    }

    @Override
    public int compare(Journey j1, Journey j2) {
        int result = j1.calculatePriceAfterTax()
                .add(j1.to().calculateEstimatedPriceToDestination(destination))
                .compareTo(
                        j2.calculatePriceAfterTax()
                                .add(j2.to().calculateEstimatedPriceToDestination(destination))
                );

        if (result == 0) {
            return j1.to().name().compareTo(j2.to().name());
        }

        return result;
    }

}
