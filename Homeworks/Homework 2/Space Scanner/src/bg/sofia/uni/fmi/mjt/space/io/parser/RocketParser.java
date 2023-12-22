package bg.sofia.uni.fmi.mjt.space.io.parser;

import bg.sofia.uni.fmi.mjt.space.rocket.Rocket;

import java.util.Optional;

public class RocketParser {

    public static Rocket parseFromInput(String[] input) {
        Optional<String> wiki = input[2].isBlank() ?
                Optional.empty() : Optional.of(input[2]);
        Optional<Double> height = input[3].isBlank() ?
                Optional.empty() : Optional.of(Double.parseDouble(input[3]));

        return new Rocket(
                input[0],
                input[1],
                wiki,
                height
        );
    }

}
