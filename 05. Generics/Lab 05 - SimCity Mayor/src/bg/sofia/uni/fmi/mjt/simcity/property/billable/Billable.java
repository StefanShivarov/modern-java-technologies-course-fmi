package bg.sofia.uni.fmi.mjt.simcity.property.billable;

import bg.sofia.uni.fmi.mjt.simcity.property.buildable.Buildable;

public interface Billable extends Buildable {

    double getWaterConsumption();

    double getElectricityConsumption();

    double getNaturalGasConsumption();

}
