package ru.simulation.app.model;

import java.util.*;

public class SimulationMap {

    private Map<Coordinates, Entity> entities = new HashMap<>();
    private int width;
    private int height;

    public SimulationMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Map<Coordinates, Entity> getEntities() {
        return entities;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Optional<Entity> get(Coordinates coordinates) {
        if (!isCorrectCoordinate(coordinates)) {
            throw new IllegalArgumentException("coordinates not correct");
        }
        return Optional.of(entities.get(coordinates));
    }

    public void put(Coordinates coordinates, Entity entity) {
        if (!isCorrectCoordinate(coordinates)) {
            throw new IllegalArgumentException("coordinates not correct");
        }
        entities.put(coordinates, entity);
    }

    public boolean isBusyCoordinates(Coordinates coordinates) {
        if (!isCorrectCoordinate(coordinates)) {
            throw new IllegalArgumentException("coordinates not correct");
        }
        return entities.containsKey(coordinates);
    }

    public void delete(Coordinates coordinates) {
        if (!isCorrectCoordinate(coordinates)) {
            throw new IllegalArgumentException("coordinates not correct");
        }
        entities.remove(coordinates);
    }

    public List<Coordinates> getAllCoordinatesBy(Class<? extends Entity> clazz) {
        return entities.entrySet().stream()
                .filter(entry -> entry.getValue().getClass().equals(clazz))
                .map(Map.Entry::getKey)
                .toList();
    }

    private boolean isCorrectCoordinate(Coordinates coordinate) {
        return (coordinate.x() > -1 && coordinate.x() <= width)
                && (coordinate.y() > -1 && coordinate.y() <= height);
    }

}