package ru.simulation.app.render;

import ru.simulation.app.model.*;

import java.util.Optional;

public class MapRenderer implements Renderer {

    private static final int CEll_WIDTH = 2;
    private static final String PREDATOR_ICON = "\uD83D\uDC0A";
    private static final String HERBIVORE_ICON = "\uD83D\uDC04";
    private static final String GRASS_ICON = "\uD83C\uDF40";
    private static final String TREE_ICON = "\uD83C\uDF84";
    private static final String ROCK_ICON = "\uD83C\uDFFF";
    private static final String EMPTY_ICON = "\uD83C\uDFFB";
    private SimulationMap simulationMap;


    public MapRenderer(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    @Override
    public void render() {
        int width = simulationMap.getWidth();
        int height = simulationMap.getHeight();
        renderBorderMap(width);
        for (int y = 0; y < height; y++) {
            System.out.print("| ");
            for (int x = 0; x < width; x++) {
                String cellContent = EMPTY_ICON;
                Coordinates currentCoordinates = new Coordinates(x, y);
                if (simulationMap.isBusyCoordinates(currentCoordinates)) {
                    Optional<Entity> entity = simulationMap.get(currentCoordinates);
                    if (entity.isPresent()) {
                        cellContent = getIconByClass(entity.get().getClass());
                    }
                }
                System.out.printf("%-" + CEll_WIDTH + "s", cellContent);
            }
            System.out.println(" |");
        }
        renderBorderMap(width);
    }

    private void renderBorderMap(int width) {
        for (int i = 0; i < width * CEll_WIDTH + 8; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private String getIconByClass(Class<? extends Entity> clazz) {
        if (Rock.class.equals(clazz)) {
            return ROCK_ICON;
        }
        if (Grass.class.equals(clazz)) {
            return GRASS_ICON;
        }
        if (Tree.class.equals(clazz)) {
            return TREE_ICON;
        }
        if (Predator.class.equals(clazz)) {
            return PREDATOR_ICON;
        }
        if (Herbivore.class.equals(clazz)) {
            return HERBIVORE_ICON;
        }
        return EMPTY_ICON;
    }
}
