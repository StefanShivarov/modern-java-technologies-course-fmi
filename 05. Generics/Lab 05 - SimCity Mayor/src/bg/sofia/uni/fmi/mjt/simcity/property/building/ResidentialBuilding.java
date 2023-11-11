package bg.sofia.uni.fmi.mjt.simcity.property.building;

import bg.sofia.uni.fmi.mjt.simcity.property.buildable.BuildableType;

public class ResidentialBuilding extends Building {

    public ResidentialBuilding(int area, double waterConsumption, double electricityConsumption, double naturalGasConsumption) {
        super(BuildableType.RESIDENTIAL, area, waterConsumption, electricityConsumption, naturalGasConsumption);
    }

}
