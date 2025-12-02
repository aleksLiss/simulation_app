package ru.simulation.app.util;

import ru.simulation.app.model.SimulationMap;

public class CountEntityFactory {

    private CountEntityFactory() {
    }

    public static int getCountEntity(SimulationMap simulationMap, int percentEntity) {
        return ((((simulationMap.getHeight() - 1) * (simulationMap.getWidth() - 1)) * percentEntity) / 100);
    }
}
