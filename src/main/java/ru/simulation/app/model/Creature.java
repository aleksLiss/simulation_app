package ru.simulation.app.model;

import ru.simulation.app.utils.CalculatePath;

import java.util.List;

public abstract class Creature extends Entity implements Move {

    private int hp;
    private int speed;
    private Coords goal;

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

    public Coords getGoal() {
        return goal;
    }

    public void setGoal(Coords goal) {
        this.goal = goal;
    }

    @Override
    public Coords makeMove(Coords start, Coords goal, List<Coords> exclusion) {
        if (this.name == 'p') {
            Coords firstCoords = CalculatePath.getNextCoordinate(exclusion, start, goal);
            return CalculatePath.getNextCoordinate(exclusion, firstCoords, goal);
        }
        return CalculatePath.getNextCoordinate(exclusion, start, goal);
    }
}
