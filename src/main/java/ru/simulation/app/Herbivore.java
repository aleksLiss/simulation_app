package ru.simulation.app;

public class Herbivore extends Creature {

    public Herbivore(Coords coords) {
        this.hp = 100;
        this.speed = 1;
        this.coords = coords;
        this.icon = "\uD83E\uDD8E";
        this.name = 'h';
    }

    @Override
    public void makeMove() {

    }
}
