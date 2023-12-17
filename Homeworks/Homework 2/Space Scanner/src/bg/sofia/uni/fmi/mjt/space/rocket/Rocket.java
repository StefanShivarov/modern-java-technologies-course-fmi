package bg.sofia.uni.fmi.mjt.space.rocket;

import java.util.Optional;

public record Rocket(String id, String name, Optional<String> wiki, Optional<Double> height) {

}
