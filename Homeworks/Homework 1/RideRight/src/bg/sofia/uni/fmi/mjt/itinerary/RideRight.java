package bg.sofia.uni.fmi.mjt.itinerary;

import bg.sofia.uni.fmi.mjt.itinerary.exception.CityNotKnownException;
import bg.sofia.uni.fmi.mjt.itinerary.exception.NoPathToDestinationException;

import java.util.List;
import java.util.SequencedCollection;

public class RideRight implements ItineraryPlanner {

    public RideRight(List<Journey> schedule) {

    }

    @Override
    public SequencedCollection<Journey> findCheapestPath(City start, City destination, boolean allowTransfer) throws CityNotKnownException, NoPathToDestinationException {
        //TODO: implement
        return null;
    }
}
