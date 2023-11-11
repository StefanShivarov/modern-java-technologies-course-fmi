package bg.sofia.uni.fmi.mjt.simcity.property.building;

import bg.sofia.uni.fmi.mjt.simcity.property.buildable.BuildableType;

public class IndustrialBuilding extends Building {

    public IndustrialBuilding(
            int area,
            double waterConsumption,
            double electricityConsumption,
            double naturalGasConsumption
    ) {
        super(
                BuildableType.INDUSTRIAL,
                area,
                waterConsumption,
                electricityConsumption,
                naturalGasConsumption
        );
    }

}
