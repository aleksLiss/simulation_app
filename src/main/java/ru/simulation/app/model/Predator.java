package ru.simulation.app.model;

import ru.simulation.app.utils.CalculatePath;

import java.util.List;

public class Predator extends Creature implements Move {

    private int hp;
    private int speed;
    private int attack;

    public Predator() {
        this.hp = 100;
        this.speed = 2;
        this.attack = 50;
        this.icon = new Icon("\uD83D\uDC0A");
        this.name = 'p';
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

}
