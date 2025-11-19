package ru.simulation.app.action.condition;

import ru.simulation.app.action.Action;
import ru.simulation.app.model.Herbivore;
import ru.simulation.app.map.SimulationMap;
import ru.simulation.app.utils.RandomCoordinates;

public class AddHerbivore implements Action {

    private SimulationMap simulationMap;

    public AddHerbivore(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    @Override
    public void doAction() {
        simulationMap.putIntoMap(
                RandomCoordinates.getRandomCoordinate(simulationMap.getSize()), new Herbivore()
        );
    }
}
