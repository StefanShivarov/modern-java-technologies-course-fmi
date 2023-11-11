package bg.sofia.uni.fmi.mjt.simcity.plot;

import bg.sofia.uni.fmi.mjt.simcity.property.Building;
import bg.sofia.uni.fmi.mjt.simcity.property.buildable.Buildable;

import java.util.Map;

public class Plot implements PlotAPI<Building> {

    public Plot(int buildableArea) {

    }

    @Override
    public void construct(String address, Building buildable) {

    }

    @Override
    public void constructAll(Map<String, Building> buildables) {

    }

    @Override
    public void demolish(String address) {

    }

    @Override
    public void demolishAll() {

    }

    @Override
    public Map<String, Building> getAllBuildables() {
        return null;
    }

    @Override
    public int getRemainingBuildableArea() {
        return 0;
    }
}
