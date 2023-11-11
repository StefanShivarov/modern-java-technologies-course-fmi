package bg.sofia.uni.fmi.mjt.simcity.plot;

import bg.sofia.uni.fmi.mjt.simcity.property.buildable.Buildable;

import java.util.Map;

public interface PlotAPI<E extends Buildable> {

    void construct(String address, E buildable);

    void constructAll(Map<String, E> buildables);

    void demolish(String address);

    void demolishAll();

    Map<String, E> getAllBuildables();

    int getRemainingBuildableArea();

}
