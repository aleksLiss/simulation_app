package ru.simulation.app.action.set.strategy;

import ru.simulation.app.map.SimulationMap;
import ru.simulation.app.model.*;
import ru.simulation.app.utils.RandomCoordinates;

public class SetObjectsForMiddleMap implements SetObjects {

    private SimulationMap simulationMap;

    public SetObjectsForMiddleMap(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    @Override
    public void setObjects(int size) {
        for (int i = 0; i < 4; i++) {
            if (i % 2 == 0) {
                simulationMap.putIntoMap(
                        RandomCoordinates.getRandomCoordinate(size), new Rock()
                );
                simulationMap.putIntoMap(
                        RandomCoordinates.getRandomCoordinate(size), new Tree()
                );
                simulationMap.putIntoMap(
                        RandomCoordinates.getRandomCoordinate(size), new Herbivore()
                );

            }
            if (i % 2 != 0) {
                simulationMap.putIntoMap(
                        RandomCoordinates.getRandomCoordinate(size), new Grass()
                );
                simulationMap.putIntoMap(
                        RandomCoordinates.getRandomCoordinate(size), new Grass()
                );
            }
            simulationMap.putIntoMap(
                    RandomCoordinates.getRandomCoordinate(size), new Predator()
            );
        }
    }
}
