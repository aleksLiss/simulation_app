package ru.simulation.app.utils;

import ru.simulation.app.model.Coords;

import java.util.List;
import java.util.stream.Collectors;

public class CalculatePath {

    public static List<Coords> calculateNeighbours(Coords currentCoordinate) {
        int x = currentCoordinate.getX();
        int y = currentCoordinate.getY();
        return List.of(
                new Coords(x - 1, y),
                new Coords(x - 1, y + 1),
                new Coords(x, y + 1),
                new Coords(x + 1, y + 1),
                new Coords(x + 1, y),
                new Coords(x + 1, y - 1),
                new Coords(x, y - 1),
                new Coords(x - 1, y - 1)
        );
    }

    public static List<Coords> getNeighboursWithoutZeroCoordinates(List<Coords> neighbours) {
        return neighbours.stream()
                .filter(coord -> coord.getX() != 0 && coord.getY() != 0)
                .collect(Collectors.toList());
    }

    private static int calculateH(Coords start, Coords finish) {
        int diffX = calculateDiff(start.getX(), finish.getX());
        int diffY = calculateDiff(start.getY(), finish.getY());
        return (Math.min(diffX, diffY) * 14) + (Math.abs(diffX - diffY) * 10);
    }

    private static int calculateF(int numberG, Coords start, Coords finish) {
        return numberG + calculateH(finish, start);
    }

    private static boolean isDirectPath(Coords start, Coords neighbour) {
        boolean result = false;
        if (start.getX() == neighbour.getX() &&
                ((neighbour.getY() - 1 == start.getY()) || (neighbour.getY() + 1 == start.getY()))) {
            result = true;
        }
        if (start.getY() == neighbour.getY() &&
                ((neighbour.getX() - 1 == start.getX()) || (neighbour.getX() + 1 == start.getX()))) {
            result = true;
        }
        return result;
    }

    private static int calculateDiff(int start, int finish) {
        return Math.abs(finish - start);
    }

    private static int getF(Coords start, Coords finish, Coords coord) {
        return isDirectPath(start, coord)
                ? calculateF(10, finish, coord)
                : calculateF(14, finish, coord);
    }

    private static Coords nextCoord(List<Coords> coords, Coords start, Coords finish) {
        int numberF;
        Coords result = null;
        int minimalF = Integer.MAX_VALUE;
        for (Coords coord : coords) {
            numberF = CalculatePath.getF(start, finish, coord);
            if (numberF < minimalF) {
                minimalF = numberF;
                result = coord;
            }
        }
        return result;
    }

    public static Coords getNextCoordinate(List<Coords> exclusion, Coords start, Coords finish) {
            List<Coords> coords = CalculatePath.
                    getNeighboursWithoutZeroCoordinates(CalculatePath.calculateNeighbours(start))
                    .stream()
                    .filter(coord -> !exclusion.contains(coord)).toList();
            return CalculatePath.nextCoord(coords, start, finish);
    }
}
