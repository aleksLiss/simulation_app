package ru.simulation.app.util;

import ru.simulation.app.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PathFinder {

    private static final int DIRECT_DIRECTION = 10;
    private static final int DIAGONAL_DIRECTION = 14;

    private PathFinder() {
    }

    public static List<Coordinates> findPath(SimulationMap simulationMap, Coordinates start, Class<? extends Entity> target) {
        List<Coordinates> targetCoordinatesBy = simulationMap.getAllCoordinatesBy(target);
        Coordinates targetEntity = RandomCoordinateFromListFactory.getRandomCoordinateFromList(targetCoordinatesBy);
        List<Coordinates> exclusionsCoordinatesBy = new ArrayList<>();
        exclusionsCoordinatesBy.addAll(simulationMap.getAllCoordinatesBy(Rock.class));
        exclusionsCoordinatesBy.addAll(simulationMap.getAllCoordinatesBy(Tree.class));
        if (target.equals(Herbivore.class)) {
            exclusionsCoordinatesBy.addAll(simulationMap.getAllCoordinatesBy(Predator.class));
        }
        if (target.equals(Predator.class)) {
            exclusionsCoordinatesBy.addAll(simulationMap.getAllCoordinatesBy(Grass.class));
        }
        int startX = start.x();
        int startY = start.y();
        List<Coordinates> foundPathToTarget = new ArrayList<>();
        while ((startX != targetEntity.x()) || (startY != targetEntity.y())) {
            start = getNextCoordinate(exclusionsCoordinatesBy, start, targetEntity);
            foundPathToTarget.add(start);
            startX = start.x();
            startY = start.y();
        }
        return foundPathToTarget;
    }

    private static Coordinates getNextCoordinate(List<Coordinates> exclusion, Coordinates start, Coordinates finish) {
        List<Coordinates> coords = PathFinder.
                getNeighboursWithoutZeroCoordinates(PathFinder.calculateNeighbours(start))
                .stream()
                .filter(coord -> !exclusion.contains(coord)).toList();
        return PathFinder.nextCoord(coords, start, finish);
    }

    private static List<Coordinates> calculateNeighbours(Coordinates currentCoordinate) {
        int x = currentCoordinate.x();
        int y = currentCoordinate.y();
        return List.of(
                new Coordinates(x - 1, y),
                new Coordinates(x - 1, y + 1),
                new Coordinates(x, y + 1),
                new Coordinates(x + 1, y + 1),
                new Coordinates(x + 1, y),
                new Coordinates(x + 1, y - 1),
                new Coordinates(x, y - 1),
                new Coordinates(x - 1, y - 1)
        );
    }

    private static List<Coordinates> getNeighboursWithoutZeroCoordinates(List<Coordinates> neighbours) {
        return neighbours.stream()
                .filter(coord -> coord.x() != 0 && coord.y() != 0)
                .collect(Collectors.toList());
    }

    private static int calculateH(Coordinates start, Coordinates finish) {
        int differenceOnAxisX = calculateDifferenceBetweenStartPointAndTargetPoint(start.x(), finish.x());
        int differenceOnAxisY = calculateDifferenceBetweenStartPointAndTargetPoint(start.y(), finish.y());
        return (Math.min(differenceOnAxisX, differenceOnAxisY) * 14) + (Math.abs(differenceOnAxisX - differenceOnAxisY) * 10);
    }

    private static int calculateF(int numberG, Coordinates start, Coordinates finish) {
        return numberG + calculateH(finish, start);
    }

    private static boolean isDirectPath(Coordinates start, Coordinates neighbour) {
        boolean result = false;
        if (start.x() == neighbour.x() &&
                ((neighbour.y() - 1 == start.y()) || (neighbour.y() + 1 == start.y()))) {
            result = true;
        }
        if (start.y() == neighbour.y() &&
                ((neighbour.x() - 1 == start.x()) || (neighbour.x() + 1 == start.x()))) {
            result = true;
        }
        return result;
    }

    private static int calculateDifferenceBetweenStartPointAndTargetPoint(int start, int finish) {
        return Math.abs(finish - start);
    }

    private static int getF(Coordinates start, Coordinates finish, Coordinates coord) {
        return isDirectPath(start, coord)
                ? calculateF(DIRECT_DIRECTION, finish, coord)
                : calculateF(DIAGONAL_DIRECTION, finish, coord);
    }

    private static Coordinates nextCoord(List<Coordinates> coords, Coordinates start, Coordinates finish) {
        int numberF;
        Coordinates result = null;
        int minimalF = Integer.MAX_VALUE;
        for (Coordinates coord : coords) {
            numberF = PathFinder.getF(start, finish, coord);
            if (numberF < minimalF) {
                minimalF = numberF;
                result = coord;
            }
        }
        return result;
    }
}
