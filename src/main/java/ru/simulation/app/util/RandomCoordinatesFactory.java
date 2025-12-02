package ru.simulation.app.util;

import ru.simulation.app.model.Coordinates;

import java.util.Random;

public class RandomCoordinatesFactory {

    private RandomCoordinatesFactory() {}

    public static Coordinates getRandomCoordinate(int width, int height) {
        Random random = new Random();
        return new Coordinates(
                random.nextInt(width - 1) + 1,
                random.nextInt(height - 1) + 1
        );
    }
}
