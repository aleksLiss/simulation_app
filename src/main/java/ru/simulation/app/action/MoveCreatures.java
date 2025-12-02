package ru.simulation.app.action;

import ru.simulation.app.model.SimulationMap;
import ru.simulation.app.model.*;
import ru.simulation.app.util.PathFinder;

import java.util.List;
import java.util.Optional;

public class MoveCreatures implements Action {

    private SimulationMap simulationMap;

    public MoveCreatures(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    @Override
    public void execute() {
        moveEntitiesByType(Herbivore.class, Grass.class);
        moveEntitiesByType(Predator.class, Herbivore.class);
    }

    private void moveEntitiesByType(Class<? extends Entity> entityType, Class<? extends Entity> targetType) {
        List<Coordinates> allCoordinatesByType = simulationMap.getAllCoordinatesBy(entityType);
        try {
            for (Coordinates coordinate : allCoordinatesByType) {
                List<Coordinates> path = PathFinder.findPath(simulationMap, coordinate, targetType);
                if (!path.isEmpty()) {
                    Coordinates nextPlace = path.getFirst();
                    Optional<Entity> optionalEntity = simulationMap.get(coordinate);
                    optionalEntity.ifPresent(entity -> {
                        simulationMap.delete(coordinate);
                        simulationMap.put(nextPlace, entity);
                    });
                }
            }
        } catch (IllegalArgumentException ex) {
            ex.getMessage();
        }
    }
}
