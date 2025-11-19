package ru.simulation.app.action.turn;

import ru.simulation.app.model.Coords;
import ru.simulation.app.model.Entity;
import ru.simulation.app.map.SimulationMap;

import java.util.Map;

public class CheckCountObjects {

    private SimulationMap simulationMap;
    private int countGrass;
    private int countHerbivore;
    private int countPredators;

    public CheckCountObjects(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    public boolean isChangeCountGrass() {
        setCountersObjects();
        int currentCounterGrass = 0;
        Map<Coords, Entity> entityMap = simulationMap.getSimulationMap();
        for (Map.Entry<Coords, Entity> entry : entityMap.entrySet()) {
            if (entry.getValue().getName() == 'g') {
                currentCounterGrass++;
            }
        }
        return currentCounterGrass != countGrass;
    }

    public boolean isChangeCountPredators() {
        setCountersObjects();
        int currentCounterPredator = 0;
        Map<Coords, Entity> entityMap = simulationMap.getSimulationMap();
        for (Map.Entry<Coords, Entity> entry : entityMap.entrySet()) {
            if (entry.getValue().getName() == 'p') {
                currentCounterPredator++;
            }
        }
        return currentCounterPredator != countPredators;
    }

    public boolean isChangeCountHerbivore() {
        setCountersObjects();
        int currentCounterHerbivore = 0;
        Map<Coords, Entity> entityMap = simulationMap.getSimulationMap();
        for (Map.Entry<Coords, Entity> entry : entityMap.entrySet()) {
            if (entry.getValue().getName() == 'h') {
                currentCounterHerbivore++;
            }
        }
        return currentCounterHerbivore != countHerbivore;
    }

    private void setCountersObjects() {
        int size = simulationMap.getSize();
        if (size > 4 && size < 15) {
            countGrass = 2;
            countHerbivore = 1;
            countPredators = 1;
        }
        if (size > 14 && size < 20) {
            countGrass = 4;
            countHerbivore = 2;
            countPredators = 4;
        }
        if (size > 19 && size < 25) {
            countGrass = 6;
            countHerbivore = 3;
            countPredators = 6;
        }
        if (size > 24 && size < 29) {
            countGrass = 8;
            countHerbivore = 4;
            countPredators = 8;
        }
        if (size < 5) {
            countGrass = 2;
            countHerbivore = 1;
            countPredators = 1;
        }
        if (size > 28) {
            countGrass = 8;
            countHerbivore = 4;
            countPredators = 8;
        }
    }
}
