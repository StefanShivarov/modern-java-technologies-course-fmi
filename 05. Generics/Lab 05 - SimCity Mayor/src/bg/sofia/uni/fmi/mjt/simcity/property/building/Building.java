package bg.sofia.uni.fmi.mjt.simcity.property.building;

import bg.sofia.uni.fmi.mjt.simcity.property.billable.Billable;
import bg.sofia.uni.fmi.mjt.simcity.property.buildable.BuildableType;

public abstract class Building implements Billable {

    private final BuildableType type;
    private final int area;
    private double waterConsumption;
    private double electricityConsumption;
    private double naturalGasConsumption;

    public Building(
            BuildableType type,
            int area,
            double waterConsumption,
            double electricityConsumption,
            double naturalGasConsumption
    ) {
        this.type = type;
        this.area = area;
        this.waterConsumption = waterConsumption;
        this.electricityConsumption = electricityConsumption;
        this.naturalGasConsumption = naturalGasConsumption;
    }

    @Override
    public double getWaterConsumption() {
        return waterConsumption;
    }

    @Override
    public double getElectricityConsumption() {
        return electricityConsumption;
    }

    @Override
    public double getNaturalGasConsumption() {
        return naturalGasConsumption;
    }

    @Override
    public BuildableType getType() {
        return type;
    }

    @Override
    public int getArea() {
        return area;
    }

}
