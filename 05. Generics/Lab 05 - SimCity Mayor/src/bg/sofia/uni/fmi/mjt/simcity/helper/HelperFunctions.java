package bg.sofia.uni.fmi.mjt.simcity.helper;

public class HelperFunctions {

    public static double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
