package ru.simulation.app;

public abstract class Creature extends Entity {

    public int hp;
    public int speed;

    public abstract void makeMove();
}
