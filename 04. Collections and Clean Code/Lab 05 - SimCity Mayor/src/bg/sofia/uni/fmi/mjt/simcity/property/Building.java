package bg.sofia.uni.fmi.mjt.simcity.property;

import bg.sofia.uni.fmi.mjt.simcity.property.billable.Billable;
import bg.sofia.uni.fmi.mjt.simcity.property.buildable.BuildableType;

public class Building implements Billable {

    @Override
    public double getWaterConsumption() {
        return 0;
    }

    @Override
    public double getElectricityConsumption() {
        return 0;
    }

    @Override
    public double getNaturalGasConsumption() {
        return 0;
    }

    @Override
    public BuildableType getType() {
        return null;
    }

    @Override
    public int getArea() {
        return 0;
    }

}
