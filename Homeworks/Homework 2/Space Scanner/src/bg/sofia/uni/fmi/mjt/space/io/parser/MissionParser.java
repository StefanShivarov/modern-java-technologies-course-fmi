package bg.sofia.uni.fmi.mjt.space.io.parser;

import bg.sofia.uni.fmi.mjt.space.mission.Detail;
import bg.sofia.uni.fmi.mjt.space.mission.Mission;
import bg.sofia.uni.fmi.mjt.space.mission.MissionStatus;
import bg.sofia.uni.fmi.mjt.space.rocket.RocketStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

public class MissionParser {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE MMM dd, uuuu", Locale.ENGLISH);

    public static Mission parseFromInput(String[] input) {
        String[] detailData = input[4].split("\\s+\\|\\s+");
        Optional<Double> price = input[6].isBlank() ?
                Optional.empty() : Optional.of(Double.parseDouble(
                        removeCommasFromPrice(removeQuotes(input[6]))));

        return new Mission(
                input[0],
                input[1],
                removeQuotes(input[2]),
                LocalDate.parse(removeQuotes(input[3]), dateFormatter),
                new Detail(detailData[0], detailData[1]),
                RocketStatus.fromValue(input[5]),
                price,
                MissionStatus.fromValue(input[7])
        );
    }

    private static String removeQuotes(String str) {
        return str.replaceAll("\"", "").trim();
    }

    private static String removeCommasFromPrice(String priceInput) {
        return priceInput.replaceAll(",", "");
    }

}
