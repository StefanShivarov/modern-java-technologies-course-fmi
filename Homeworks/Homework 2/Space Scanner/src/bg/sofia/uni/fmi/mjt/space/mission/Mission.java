package bg.sofia.uni.fmi.mjt.space.mission;

import bg.sofia.uni.fmi.mjt.space.rocket.RocketStatus;

import java.time.LocalDate;
import java.util.Optional;

public record Mission(String id,
                      String company,
                      String location,
                      LocalDate date,
                      Detail detail,
                      RocketStatus rocketStatus,
                      Optional<Double> cost,
                      MissionStatus missionStatus
) {


}
