package ru.simulation.app.map;

import ru.simulation.app.model.Coords;
import ru.simulation.app.model.Entity;

import java.util.*;

public class SimulationMap {

    private Map<Coords, Entity> simulationMap = new HashMap<>();
    private int size;

    public SimulationMap(int size) {
        this.size = size;
    }

    public Map<Coords, Entity> getSimulationMap() {
        return simulationMap;
    }

    public void putIntoMap(Coords coords, Entity entity) {
        simulationMap.put(coords, entity);
    }

    public boolean isContainsKey(Coords coords) {
        return simulationMap.containsKey(coords);
    }

    public Entity getEntityFromMap(Coords coords) {
        return simulationMap.get(coords);
    }

    public int getSize() {
        return size;
    }

    public List<Coords> getStaticObjects() {
        List<Coords> staticObjects = new ArrayList<>();
        for (Map.Entry<Coords, Entity> entry : simulationMap.entrySet()) {
            if (isCellNotAccessible(entry.getKey())) {
                staticObjects.add(entry.getKey());
            }
        }
        return staticObjects;
    }

    public List<Coords> getGrassesCoordsList() {
        return simulationMap.entrySet().stream()
                .filter(entry -> entry.getValue().getName() == 'g')
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<Coords> getHerbivoresCoordsList() {
        return simulationMap.entrySet().stream()
                .filter(entry -> entry.getValue().getName() == 'h')
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<Coords> getPredatorsCoordsList() {
        return simulationMap.entrySet().stream()
                .filter(entry -> entry.getValue().getName() == 'p')
                .map(Map.Entry::getKey)
                .toList();
    }

    private boolean isCellNotAccessible(Coords coords) {
        return (simulationMap.get(coords).getName() == 'r')
        || (simulationMap.get(coords).getName() == 't');
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void deleteFromMap(Coords coordinate) {
        simulationMap.remove(coordinate);
    }
}