package bg.sofia.uni.fmi.mjt.football;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record Player(
        String name, String fullName, LocalDate birthDate, int age,
        double heightCm, double weightKg, List<Position> positions, String nationality,
        int overallRating, int potential, long valueEuro, long wageEuro, Foot preferredFoot
) {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static final int MAX_OVERALL_DIFFERENCE_FOR_SIMILARITY = 3;

    public static Player of(String line) {
        String[] inputTokens = line.split(";");
        int tokenIndex = 0;
        return new Player(
                inputTokens[tokenIndex++],
                inputTokens[tokenIndex++],
                parseDate(inputTokens[tokenIndex++]),
                Integer.parseInt(inputTokens[tokenIndex++]),
                Double.parseDouble(inputTokens[tokenIndex++]),
                Double.parseDouble(inputTokens[tokenIndex++]),
                parsePositions(inputTokens[tokenIndex++]),
                inputTokens[tokenIndex++],
                Integer.parseInt(inputTokens[tokenIndex++]),
                Integer.parseInt(inputTokens[tokenIndex++]),
                Long.parseLong(inputTokens[tokenIndex++]),
                Long.parseLong(inputTokens[tokenIndex++]),
                Foot.valueOf(inputTokens[tokenIndex].toUpperCase())
        );
    }

    private static List<Position> parsePositions(String positionsInput) {
        return Arrays.stream(positionsInput.split(","))
                .map(Position::valueOf)
                .collect(Collectors.toList());
    }

    private static LocalDate parseDate(String dateInput) {
        return LocalDate.parse(dateInput, DATE_TIME_FORMATTER);
    }

    double calculateProspect() {
        return (double) (overallRating + potential) / age;
    }

    boolean isSimilarTo(Player other) {
        boolean canPlayInSamePosition = other.positions.stream()
                .anyMatch(this.positions::contains);

        return canPlayInSamePosition
                && this.preferredFoot == other.preferredFoot
                && Math.abs(this.overallRating - other.overallRating) <= MAX_OVERALL_DIFFERENCE_FOR_SIMILARITY;
    }

}
