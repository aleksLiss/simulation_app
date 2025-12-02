package ru.simulation.app.util;

import ru.simulation.app.model.Coordinates;

import java.util.List;
import java.util.Random;

public class RandomCoordinateFromListFactory {

    private RandomCoordinateFromListFactory(){}

    public static Coordinates getRandomCoordinateFromList(List<Coordinates> coordinates) {
        Random random = new Random();
        int randomIndex = random.nextInt(coordinates.size());
        return coordinates.get(randomIndex);
    }
}
