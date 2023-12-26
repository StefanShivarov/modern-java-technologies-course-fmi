package bg.sofia.uni.fmi.mjt.space.mission;

public enum MissionStatus {

    SUCCESS("Success"),
    FAILURE("Failure"),
    PARTIAL_FAILURE("Partial Failure"),
    PRELAUNCH_FAILURE("Prelaunch Failure");

    private final String value;

    MissionStatus(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public static MissionStatus fromValue(String value) {
        for (MissionStatus missionStatus : MissionStatus.values()) {
            if (missionStatus.value.equals(value)) {
                return missionStatus;
            }
        }

        return null;
    }
}
