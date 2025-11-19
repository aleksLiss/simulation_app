package ru.simulation.app.action.condition;

import ru.simulation.app.action.Action;
import ru.simulation.app.map.SimulationMap;
import ru.simulation.app.model.Predator;
import ru.simulation.app.utils.RandomCoordinates;

public class AddPredator implements Action {

    private SimulationMap simulationMap;

    public AddPredator(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    @Override
    public void doAction() {
        simulationMap.putIntoMap(
                RandomCoordinates.getRandomCoordinate(simulationMap.getSize()), new Predator());
    }
}
