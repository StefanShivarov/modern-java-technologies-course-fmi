package bg.sofia.uni.fmi.mjt.football;

import java.time.LocalDate;
import java.util.List;

public record Player(
        String name, String fullName, LocalDate birthDate, int age,
        double heightCm, double weightKg, List<Position> positions, String nationality,
        int overallRating, int potential, long valueEuro, long wageEuro, Foot preferredFoot
) {

    double calculateProspect() {
        return (double) (overallRating + potential) / age;
    }

    boolean isSimilarTo(Player other) {
        boolean canPlayInSamePosition = other.positions.stream()
                .anyMatch(this.positions::contains);

        return canPlayInSamePosition
                && this.preferredFoot == other.preferredFoot
                && Math.abs(this.overallRating - other.overallRating) <= 3;
    }
    
}
