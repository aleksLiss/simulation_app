package ru.simulation.app;

import ru.simulation.app.action.Action;
import ru.simulation.app.action.condition.AddGrass;
import ru.simulation.app.action.condition.AddHerbivore;
import ru.simulation.app.action.condition.AddPredator;
import ru.simulation.app.action.set.PullObjectsIntoMap;
import ru.simulation.app.action.turn.*;
import ru.simulation.app.map.SimulationMap;
import ru.simulation.app.render.InfoRenderer;
import ru.simulation.app.render.MapRenderer;
import ru.simulation.app.render.Renderer;
import ru.simulation.app.utils.Counter;

import java.util.List;

public class Simulation {

    private SimulationMap simulationMap;
    private Renderer renderMap;
    private Renderer renderInfo;
    private Counter counter;
    private List<Action> setActions;
    private List<Action> turnActions;
    private List<Action> conditionActions;
    private CheckCountObjects checkCountObjects;
    private volatile boolean isRunning;

    public Simulation() {
        simulationMap = new SimulationMap(20);
        renderMap = new MapRenderer(simulationMap);
        renderInfo = new InfoRenderer(simulationMap);
        counter = Counter.getInstance();
        checkCountObjects = new CheckCountObjects(simulationMap);
        setActions = List.of(
                new PullObjectsIntoMap(simulationMap));
        turnActions = List.of(
                new MoveCreatures(simulationMap)
        );
        conditionActions = List.of(
                new AddGrass(simulationMap),
                new AddHerbivore(simulationMap),
                new AddPredator(simulationMap)
        );
        isRunning = false;
    }

    public void nextTurn() {
        if (counter.getCount() == 0) {
            setActions.forEach(Action::doAction);
        }
        renderInfo.render();
        renderMap.render();
        turnActions.forEach(Action::doAction);
        counter.increment();
        if (checkCountObjects.isChangeCountGrass()
        || checkCountObjects.isChangeCountHerbivore()
        || checkCountObjects.isChangeCountPredators()) {
            conditionActions.forEach(Action::doAction);
        }
    };

    public void startSimulation() {
        isRunning = true;
        try {
            while (isRunning) {
                nextTurn();
                Thread.sleep(2000);
            }
        } catch (InterruptedException ex) {
            ex.getMessage();
        }
    };

    public void pauseSimulation() {
        isRunning = false;
    };
}
