package ru.simulation.app.action;

import ru.simulation.app.model.*;
import ru.simulation.app.util.RandomCoordinatesFactory;

import java.util.Map;
import java.util.function.Supplier;

public class SpawnAction implements Action {

    private SimulationMap simulationMap;
    private int number;
    private Supplier<Entity> entitySupplier;

    public SpawnAction(SimulationMap simulationMap, int number, Supplier<Entity> entitySupplier) {
        this.simulationMap = simulationMap;
        this.number = number;
        this.entitySupplier = entitySupplier;
    }

    @Override
    public void execute() {
        int actualCountEntity = getActualCountEntity(entitySupplier.get().getClass());
        int neededGenerateCountEntity = number - actualCountEntity;
        try {
            for (int i = 0; i < neededGenerateCountEntity; i++) {
                Coordinates coordinates = RandomCoordinatesFactory.getRandomCoordinate(simulationMap.getWidth(), simulationMap.getHeight());
                Entity entity = entitySupplier.get();
                simulationMap.put(coordinates, entity);
            }
        } catch (IllegalArgumentException ex) {
            ex.getMessage();
        }
    }

    private int getActualCountEntity(Class<? extends Entity> clazz) {
        int actualCountEntity = 0;
        for (Map.Entry<Coordinates, Entity> entry : simulationMap.getEntities().entrySet()) {
            if (entry.getValue().getClass().equals(clazz)) {
                actualCountEntity++;
            }
        }
        return actualCountEntity;
    }
}

