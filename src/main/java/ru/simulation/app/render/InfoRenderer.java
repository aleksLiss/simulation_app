package ru.simulation.app.render;

import ru.simulation.app.model.Grass;
import ru.simulation.app.model.Herbivore;
import ru.simulation.app.model.Predator;
import ru.simulation.app.model.SimulationMap;

public class InfoRenderer implements Renderer {

    private SimulationMap simulationMap;

    public InfoRenderer(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    @Override
    public void render() {
        int actualCountGrass = simulationMap.getAllCoordinatesBy(Grass.class).size();
        int actualCountHerbivores = simulationMap.getAllCoordinatesBy(Herbivore.class).size();
        int actualCountPredators = simulationMap.getAllCoordinatesBy(Predator.class).size();
        System.out.printf("Actual count entities: Grass: %d, Herbivores: %d, Predators: %d%n",
                actualCountGrass,
                actualCountHerbivores,
                actualCountPredators);
    }
}
