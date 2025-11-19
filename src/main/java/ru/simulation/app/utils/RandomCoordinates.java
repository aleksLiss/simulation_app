package ru.simulation.app.utils;

import ru.simulation.app.model.Coords;

import java.util.Random;

public class RandomCoordinates {

    public static Coords getRandomCoordinate(int size) {
        Random random = new Random();
        int min = 1;
        int max = size - 1;
        return new Coords(
                random.nextInt(max - min + 1) + min,
                random.nextInt(max - min + 1) + min
        );
    }

    public static int getRandomIndex(int size) {
        return new Random().nextInt(size);
    }
}
