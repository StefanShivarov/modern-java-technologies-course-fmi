package bg.sofia.uni.fmi.mjt.simcity.plot;

import bg.sofia.uni.fmi.mjt.simcity.exception.BuildableAlreadyExistsException;
import bg.sofia.uni.fmi.mjt.simcity.exception.BuildableNotFoundException;
import bg.sofia.uni.fmi.mjt.simcity.exception.InsufficientPlotAreaException;
import bg.sofia.uni.fmi.mjt.simcity.property.buildable.Buildable;

import java.util.LinkedHashMap;
import java.util.Map;

public class Plot<E extends Buildable> implements PlotAPI<Buildable> {

    private Map<String, Buildable> buildables;
    private final int buildableArea;
    private int remainingArea;

    public Plot(int buildableArea) {
        this.buildableArea = buildableArea;
        this.remainingArea = buildableArea;
        this.buildables = new LinkedHashMap<>();
    }

    @Override
    public void construct(String address, Buildable buildable) {
        if (address == null || address.isBlank() || buildable == null) {
            throw new IllegalArgumentException("Invalid arguments!");
        }

        if (buildables.containsKey(address)) {
            throw new BuildableAlreadyExistsException("Buildable with address "
                    + address
                    + " already exists on plot!");
        }

        if (buildable.getArea() > remainingArea) {
            throw new InsufficientPlotAreaException("Not enough area on plot!");
        }

        remainingArea -= buildable.getArea();
        buildables.put(address, buildable);
    }

    @Override
    public void constructAll(Map<String, Buildable> buildables) {
        if (buildables == null || buildables.isEmpty()) {
            throw new IllegalArgumentException("Invalid collection of buildables was provided!");
        }

        int buildablesTotalArea = 0;
        for (Map.Entry<String, Buildable> entry : buildables.entrySet()) {
            if (this.buildables.containsKey(entry.getKey())) {
                throw new BuildableAlreadyExistsException("Buildable with address "
                        + entry.getKey()
                        + " already exists on plot!");
            }

            buildablesTotalArea += entry.getValue().getArea();
            if (buildablesTotalArea > remainingArea) {
                throw new InsufficientPlotAreaException("Not enough area on plot!");
            }
        }

        remainingArea -= buildablesTotalArea;
        this.buildables.putAll(buildables);
    }

    @Override
    public void demolish(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Invalid address!");
        }

        if (!buildables.containsKey(address)) {
            throw new BuildableNotFoundException("No buildable with address "
                    + address
                    + " was found!");
        }

        Buildable demolished = buildables.remove(address);
        remainingArea += demolished.getArea();
    }

    @Override
    public void demolishAll() {
        buildables.clear();
        remainingArea = buildableArea;
    }

    @Override
    public Map<String, Buildable> getAllBuildables() {
        return Map.copyOf(buildables);
    }

    @Override
    public int getRemainingBuildableArea() {
        return remainingArea;
    }

}
