package bg.sofia.uni.fmi.mjt.simcity.helper;

public class HelperFunctions {

    private static final double ONE_HUNDRED = 100.0;

    public static double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * ONE_HUNDRED) / ONE_HUNDRED;
    }

}
