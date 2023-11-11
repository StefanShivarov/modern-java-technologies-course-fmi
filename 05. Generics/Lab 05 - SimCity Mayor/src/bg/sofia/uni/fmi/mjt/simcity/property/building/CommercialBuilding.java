package bg.sofia.uni.fmi.mjt.simcity.property.building;

import bg.sofia.uni.fmi.mjt.simcity.property.buildable.BuildableType;

public class CommercialBuilding extends Building {

    public CommercialBuilding(
            int area,
            double waterConsumption,
            double electricityConsumption,
            double naturalGasConsumption
    ) {
        super(
                BuildableType.COMMERCIAL,
                area,
                waterConsumption,
                electricityConsumption,
                naturalGasConsumption
        );
    }

}
