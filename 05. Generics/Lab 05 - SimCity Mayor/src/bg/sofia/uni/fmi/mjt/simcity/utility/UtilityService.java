package bg.sofia.uni.fmi.mjt.simcity.utility;

import bg.sofia.uni.fmi.mjt.simcity.property.billable.Billable;

import static bg.sofia.uni.fmi.mjt.simcity.helper.HelperFunctions.roundToTwoDecimalPlaces;

import java.util.Map;

public class UtilityService implements UtilityServiceAPI {

    private final Map<UtilityType, Double> taxRates;

    public UtilityService(Map<UtilityType, Double> taxRates) {
        this.taxRates = taxRates;
    }

    @Override
    public <T extends Billable> double getUtilityCosts(UtilityType utilityType, T billable) {
        if (utilityType == null || billable == null) {
            throw new IllegalArgumentException("Invalid arguments!");
        }

        double consumption = switch (utilityType) {
            case WATER -> billable.getWaterConsumption();
            case ELECTRICITY -> billable.getElectricityConsumption();
            case NATURAL_GAS -> billable.getNaturalGasConsumption();
        };

        return taxRates.get(utilityType) * consumption;
    }

    @Override
    public <T extends Billable> double getTotalUtilityCosts(T billable) {
        if (billable == null) {
            throw new IllegalArgumentException("Billable is null!");
        }

        double waterCost =
                taxRates.get(UtilityType.WATER) * billable.getWaterConsumption();
        double electricityCost =
                taxRates.get(UtilityType.ELECTRICITY) * billable.getElectricityConsumption();
        double gasCost =
                taxRates.get(UtilityType.NATURAL_GAS) * billable.getNaturalGasConsumption();

        return waterCost + electricityCost + gasCost;
    }

    @Override
    public <T extends Billable> Map<UtilityType, Double> computeCostsDifference(
            T firstBillable, T secondBillable) {
        if (firstBillable == null || secondBillable == null) {
            throw new IllegalArgumentException("Billable is null!");
        }

        double waterDiff = roundToTwoDecimalPlaces(
                taxRates.get(UtilityType.WATER) *
                        Math.abs(firstBillable.getWaterConsumption() -
                                secondBillable.getWaterConsumption())
        );
        double electricityDiff = roundToTwoDecimalPlaces(
                taxRates.get(UtilityType.ELECTRICITY) *
                        Math.abs(firstBillable.getElectricityConsumption() -
                                secondBillable.getElectricityConsumption())
        );
        double naturalGasDiff = roundToTwoDecimalPlaces(
                taxRates.get(UtilityType.NATURAL_GAS) *
                        Math.abs(firstBillable.getNaturalGasConsumption() -
                                secondBillable.getNaturalGasConsumption())
        );

        return Map.of(
                UtilityType.WATER, waterDiff,
                UtilityType.ELECTRICITY, electricityDiff,
                UtilityType.NATURAL_GAS, naturalGasDiff
        );
    }

}
