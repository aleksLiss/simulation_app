package ru.simulation.app;

import java.util.*;

public class SimulationMap {

    private static Map<Coords, Entity> SIMULATION_MAP = new HashMap<>();
    private static List<Coords> coordsList = new ArrayList<>();
    private int size;
    private static List<Coords> grassList = new ArrayList<>();

    public SimulationMap(int size) {
        this.size = size;
    }

    private void pullEntityIntoMap() {
        // todo pattern strategy
        for (int i = 0; i < 3; i++) {
            Coords coords = getRandomCoords();
            SIMULATION_MAP.put(
                coords, new Rock(coords)
            );
        }
        for (int i = 0; i < 3; i++) {
            Coords coords = getRandomCoords();
            SIMULATION_MAP.put(
                    coords, new Tree(coords)
            );
        }
        for (int i = 0; i < 3; i++) {
            Coords coords = getRandomCoords();
            SIMULATION_MAP.put(
                    coords, new Grass(coords)
            );
        }
        for (int i = 0; i < 3; i++) {
            Coords coords = getRandomCoords();
            SIMULATION_MAP.put(
                    coords, new Herbivore(coords)
            );
        }
        for (int i = 0; i < 3; i++) {
            Coords coords = getRandomCoords();
            SIMULATION_MAP.put(
                    coords, new Predator(50, coords)
            );
        }
    }

    private Coords getRandomCoords() {
        Random random = new Random();
        int min = 1;
        int max = size - 1;
        Coords resultCoord = new Coords(
                random.nextInt(max - min + 1) + min,
                random.nextInt(max - min + 1) + min
        );
        while (!isOccupied(resultCoord)) {
            coordsList.add(resultCoord);
        }
        return resultCoord;
    }

    private boolean isOccupied(Coords coord) {
        boolean result = false;
        for (Coords crd : coordsList) {
            if (coord.getX() == crd.getX()
                    && coord.getY() == crd.getY()) {
                result = true;
                break;
            }
        }
        return result;
    }

    public List<Coords> getCoordsList() {
        return coordsList;
    }

    private static void printBorder(int size) {
        for (int i = 0; i < size * 3 + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void printSimulationMap() {
        printBorder(size);
        for (int y = 0; y < size; y++) {
            System.out.print("|");
            for (int x = 0; x < size; x++) {
                Coords currentCoords = new Coords(x, y);
                if (SIMULATION_MAP.containsKey(currentCoords)) {
                    Entity entity = SIMULATION_MAP.get(currentCoords);
                    System.out.print(entity.icon + " ");
                } else {
                    System.out.print(" x "); // Пустая ячейка
                }
            }
            System.out.println("|");
        }
        printBorder(size);
    }

    public void run() {
        pullEntityIntoMap();
        printSimulationMap();
    }

    public Map<Coords, Entity> getSimulationMap() {
        return SIMULATION_MAP;
    }

    public static List<Coords> getGrassList() {
        return grassList;
    }

//    public static void setCoordsList(List<Coords> coordsList) {
//    }

    public static void main(String[] args) {
        SimulationMap simulationMap = new SimulationMap(20);
        simulationMap.run();
    }
}