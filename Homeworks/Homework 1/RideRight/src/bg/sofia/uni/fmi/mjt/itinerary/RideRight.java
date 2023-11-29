package bg.sofia.uni.fmi.mjt.itinerary;

import bg.sofia.uni.fmi.mjt.itinerary.comparators.JourneyCheapestPathComparator;
import bg.sofia.uni.fmi.mjt.itinerary.exception.CityNotKnownException;
import bg.sofia.uni.fmi.mjt.itinerary.exception.NoPathToDestinationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SequencedCollection;
import java.util.Set;

public class RideRight implements ItineraryPlanner {

    private Map<City, List<Journey>> citySchedulesMap;

    public RideRight(List<Journey> schedule) {
        citySchedulesMap = new HashMap<>();
        for (Journey journey : schedule) {
            if (!citySchedulesMap.containsKey(journey.from())) {
                citySchedulesMap.put(journey.from(), new ArrayList<>());
            }
            citySchedulesMap.get(journey.from()).add(journey);
        }
    }

    @Override
    public SequencedCollection<Journey> findCheapestPath(
            City start,
            City destination,
            boolean allowTransfer) throws CityNotKnownException, NoPathToDestinationException {
        if (start == null || destination == null) {
            throw new IllegalArgumentException("City is null!");
        }

        if (!citySchedulesMap.containsKey(start) || !citySchedulesMap.containsKey(destination)) {
            throw new CityNotKnownException("City not found in schedule!");
        }

        Queue<Journey> journeysFromCurrentCity =
                new PriorityQueue<>(new JourneyCheapestPathComparator(destination));
        journeysFromCurrentCity.addAll(citySchedulesMap.get(start));

        if (!allowTransfer && !hasJourneyToCity(journeysFromCurrentCity, destination)) {
            throw new NoPathToDestinationException("No path to the destination!");
        }

        return aStarAlgorithm(start, destination, journeysFromCurrentCity);
    }

    private SequencedCollection<Journey> aStarAlgorithm(
            City start,
            City destination,
            Queue<Journey> journeysFromCurrentCity) throws NoPathToDestinationException {
        Set<City> visited = new LinkedHashSet<>();
        visited.add(start);
        List<Journey> cheapestPath = new ArrayList<>();

        while (true) {
            if (visited.size() == citySchedulesMap.size() - 1
                    && !hasJourneyToCity(journeysFromCurrentCity, destination)) {
                throw new NoPathToDestinationException("No path to the destination");
            }

            Journey next = journeysFromCurrentCity.poll();

            if (next == null) {
                throw new NullPointerException("Next journey is null!");
            }

            visited.add(next.to());
            cheapestPath.add(next);

            if (next.to().equals(destination)) {
                return cheapestPath;
            }

            journeysFromCurrentCity.clear();
            journeysFromCurrentCity.addAll(citySchedulesMap.get(next.to()));
        }
    }

    private static boolean hasJourneyToCity(Queue<Journey> journeyQueue, City destination) {
        for (Journey journey : journeyQueue) {
            if (journey.to().equals(destination)) {
                return true;
            }
        }
        return false;
    }

}
