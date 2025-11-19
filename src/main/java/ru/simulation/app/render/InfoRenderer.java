package ru.simulation.app.render;

import ru.simulation.app.map.SimulationMap;

public class InfoRenderer implements Renderer {

    private SimulationMap simulationMap;

    public InfoRenderer(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    @Override
    public void render() {
        int countHerbivores = simulationMap.getHerbivoresCoordsList().size();
        int countPredators = simulationMap.getPredatorsCoordsList().size();
        int countGrass = simulationMap.getGrassesCoordsList().size();
        System.out.printf("Actual count on map: grass - %d, herbivores - %d, predators - %d%n",
                countGrass,
                countHerbivores,
                countPredators);
    }
}
