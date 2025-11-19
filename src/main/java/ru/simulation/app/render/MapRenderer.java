package ru.simulation.app.render;

import ru.simulation.app.model.Coords;
import ru.simulation.app.map.SimulationMap;
import ru.simulation.app.model.Entity;

public class MapRenderer implements Renderer {

    private SimulationMap simulationMap;

    public MapRenderer(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    @Override
    public void render() {
        int size = simulationMap.getSize();
        renderBorderMap(size);
        for (int y = 0; y < size; y++) {
            System.out.print("|");
            for (int x = 0; x < size; x++) {
                Coords currentCoords = new Coords(x, y);
                if (simulationMap.isContainsKey(currentCoords)) {
                    Entity entity = simulationMap.getEntityFromMap(currentCoords);
                    System.out.print(entity.getIcon().getIcon() + " ");
                } else {
                    System.out.print(" x ");
                }
            }
            System.out.println("|");
        }
        renderBorderMap(size);
    }

    private void renderBorderMap(int size) {
        for (int i = 0; i < size * 3 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
