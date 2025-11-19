package ru.simulation.app.action.set.strategy;

import ru.simulation.app.map.SimulationMap;
import ru.simulation.app.model.*;
import ru.simulation.app.utils.RandomCoordinates;

public class SetObjectsForSmallMap implements SetObjects {

    private SimulationMap simulationMap;

    public SetObjectsForSmallMap(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    @Override
    public void setObjects(int size) {
        simulationMap.putIntoMap(
                RandomCoordinates.getRandomCoordinate(size), new Rock()
        );
        simulationMap.putIntoMap(
                RandomCoordinates.getRandomCoordinate(size), new Tree()
        );
        simulationMap.putIntoMap(
                RandomCoordinates.getRandomCoordinate(size), new Herbivore()
        );
        simulationMap.putIntoMap(
                RandomCoordinates.getRandomCoordinate(size), new Predator()
        );
        simulationMap.putIntoMap(
                RandomCoordinates.getRandomCoordinate(size), new Grass()
        );
        simulationMap.putIntoMap(
                RandomCoordinates.getRandomCoordinate(size), new Grass()
        );
    }
}
