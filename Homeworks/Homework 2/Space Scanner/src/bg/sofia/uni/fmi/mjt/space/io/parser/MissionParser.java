package bg.sofia.uni.fmi.mjt.space.io.parser;

import bg.sofia.uni.fmi.mjt.space.mission.Detail;
import bg.sofia.uni.fmi.mjt.space.mission.Mission;
import bg.sofia.uni.fmi.mjt.space.mission.MissionStatus;
import bg.sofia.uni.fmi.mjt.space.rocket.RocketStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MissionParser {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE MMM dd, yyyy");

    public static Mission parseFromInput(String[] input) {
        String[] detailData = input[4].split("\\s+\\|\\s+");
        Optional<Double> price = input[6].isBlank() ?
                Optional.empty() : Optional.of(Double.parseDouble(input[6]));

        return new Mission(
                input[0],
                input[1],
                input[2],
                LocalDate.parse(input[3], dateFormatter),
                new Detail(detailData[0], detailData[1]),
                RocketStatus.valueOf(input[5]),
                price,
                MissionStatus.valueOf(input[7])
        );
    }

}
