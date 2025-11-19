package ru.simulation.app.model;

public class Herbivore extends Creature  {

    private int hp;
    private int speed;

    public Herbivore() {
        this.hp = 100;
        this.speed = 1;
        this.icon = new Icon("\uD83E\uDD8E");
        this.name = 'h';
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

}
