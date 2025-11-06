package ru.simulation.app;

public class Predator extends Creature {

    private int hp;
    private int speed;
    private int attack;

    public Predator(int attack, Coords coords) {
        this.hp = 100;
        this.speed = 2;
        this.attack = attack;
        this.coords = coords;
        this.icon = "\uD83D\uDC0A";
        this.name = 'p';
    }

    @Override
    public void makeMove() {

    }
}
