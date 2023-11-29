package bg.sofia.uni.fmi.mjt.itinerary;

public record Location(int x, int y) {

    public long calculateManhattanDistanceTo(Location other) {
        if (other == null) {
            throw new IllegalArgumentException("Location is null!");
        }

        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }

}
