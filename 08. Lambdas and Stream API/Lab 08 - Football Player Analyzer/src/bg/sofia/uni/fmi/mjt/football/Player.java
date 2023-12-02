package bg.sofia.uni.fmi.mjt.football;

import java.time.LocalDate;
import java.util.List;

public record Player(
        String name, String fullName, LocalDate birthDate, int age,
        double heightCm, double weightKg, List<Position> positions, String nationality,
        int overallRating, int potential, long valueEuro, long wageEuro, Foot preferredFoot
) {



}
