package bg.sofia.uni.fmi.mjt.simcity.property.building;

import bg.sofia.uni.fmi.mjt.simcity.property.buildable.BuildableType;

public class InfrastructureBuilding extends Building {

    public InfrastructureBuilding(
            int area,
            double waterConsumption,
            double electricityConsumption,
            double naturalGasConsumption
    ) {
        super(
                BuildableType.INFRASTRUCTURE,
                area,
                waterConsumption,
                electricityConsumption,
                naturalGasConsumption
        );
    }

}
