package ru.simulation.app.simulation;

import ru.simulation.app.action.Action;
import ru.simulation.app.action.MoveCreatures;
import ru.simulation.app.action.SpawnAction;
import ru.simulation.app.action.StartAction;
import ru.simulation.app.model.*;
import ru.simulation.app.render.InfoRenderer;
import ru.simulation.app.render.MapRenderer;
import ru.simulation.app.render.Renderer;

import java.util.List;

public class Simulation extends Thread {

    private SimulationMap simulationMap;
    private Renderer mapRender;
    private InfoRenderer infoRenderer;
    private Counter counter;
    private List<Action> initActions;
    private List<Action> turnActions;
    private boolean isRunning = false;
    private static final int ROCK_PERCENT = 1;
    private static final int TREE_PERCENT = 1;
    private static final int GRASS_PERCENT = 3;
    private static final int PREDATOR_PERCENT = 2;
    private static final int HERBIVORE_PERCENT = 3;

    public Simulation(SimulationMap simulationMap, MapRenderer mapRenderer, Counter counter, InfoRenderer infoRenderer) {
        this.simulationMap = simulationMap;
        this.counter = counter;
        this.infoRenderer = infoRenderer;
        this.mapRender = mapRenderer;
        this.initActions = List.of(
                new StartAction(
                        simulationMap,
                        ROCK_PERCENT,
                        TREE_PERCENT,
                        GRASS_PERCENT,
                        PREDATOR_PERCENT,
                        HERBIVORE_PERCENT)
        );
        this.turnActions = List.of(
                new MoveCreatures(simulationMap),
                new SpawnAction(simulationMap, GRASS_PERCENT, Grass::new),
                new SpawnAction(simulationMap, HERBIVORE_PERCENT, Herbivore::new),
                new SpawnAction(simulationMap, PREDATOR_PERCENT, Predator::new)
        );
        this.initActions.forEach(Action::execute);
    }

    public void nextTurn() {
        infoRenderer.render();
        mapRender.render();
        turnActions.forEach(Action::execute);
        counter.increment();
    }

    public void startSimulation() {
        this.isRunning = true;
        this.start();
    }

    public void pauseSimulation() {
        this.isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            nextTurn();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }
    }
}