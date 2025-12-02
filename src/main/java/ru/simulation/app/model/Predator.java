package ru.simulation.app.model;

public class Predator extends Creature {

    private static final int HP = 100;
    private static final int SPEED = 2;
    private int attack;

    public Predator() {
        super(HP, SPEED);
        this.attack = 50;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

}
