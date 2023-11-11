package bg.sofia.uni.fmi.mjt.simcity.utility;

import bg.sofia.uni.fmi.mjt.simcity.property.billable.Billable;

import java.util.Map;

public interface UtilityServiceAPI {

    <T extends Billable> double getUtilityCosts(UtilityType utilityType, T billable);

    <T extends Billable> double getTotalUtilityCosts(T billable);

    <T extends Billable> Map<UtilityType, Double> computeCostsDifference(T firstBillable, T secondBillable);

}
