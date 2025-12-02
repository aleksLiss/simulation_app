package ru.simulation.app.action;

import ru.simulation.app.model.*;

public class StartAction implements Action {

    private SimulationMap simulationMap;
    private final int rockNumber;
    private final int treeNumber;
    private final int grassNumber;
    private final int predatorNumber;
    private final int herbivoreNumber;

    public StartAction(SimulationMap simulationMap, int rockPercent, int treePercent, int grassPercent, int predatorPercent, int herbivorePercent) {
        this.simulationMap = simulationMap;
        this.rockNumber = ((((simulationMap.getHeight() - 1) * (simulationMap.getWidth() - 1)) * rockPercent) / 100);
        this.treeNumber = ((((simulationMap.getHeight() - 1) * (simulationMap.getWidth() - 1)) * treePercent) / 100);
        this.grassNumber = ((((simulationMap.getHeight() - 1) * (simulationMap.getWidth() - 1)) * grassPercent) / 100);
        this.predatorNumber = ((((simulationMap.getHeight() - 1) * (simulationMap.getWidth() - 1)) * predatorPercent) / 100);
        this.herbivoreNumber = ((((simulationMap.getHeight() - 1) * (simulationMap.getWidth() - 1)) * herbivorePercent) / 100);
    }

    @Override
    public void execute() {
        SpawnAction spawnRock = new SpawnAction(simulationMap, rockNumber, Rock::new);
        SpawnAction spawnTree = new SpawnAction(simulationMap, treeNumber, Tree::new);
        SpawnAction spawnGrass = new SpawnAction(simulationMap, grassNumber, Grass::new);
        SpawnAction spawnPredators = new SpawnAction(simulationMap, predatorNumber, Predator::new);
        SpawnAction spawnHerbivores = new SpawnAction(simulationMap, herbivoreNumber, Herbivore::new);
        spawnRock.execute();
        spawnTree.execute();
        spawnGrass.execute();
        spawnPredators.execute();
        spawnHerbivores.execute();
    }
}
