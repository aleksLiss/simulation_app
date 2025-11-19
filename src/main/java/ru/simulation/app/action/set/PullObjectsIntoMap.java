package ru.simulation.app.action.set;

import ru.simulation.app.action.Action;
import ru.simulation.app.map.SimulationMap;
import ru.simulation.app.action.set.strategy.*;

public class PullObjectsIntoMap implements Action {

    private SimulationMap simulationMap;
    private SetObjectsService setStaticObjects;

    public PullObjectsIntoMap(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }


    @Override
    public void doAction() {
        int size = simulationMap.getSize();
        if (size > 4 && size < 15) {
            setStaticObjects = new SetObjectsService(new SetObjectsForSmallMap(simulationMap));
        }
        if (size > 14 && size < 20) {
            setStaticObjects = new SetObjectsService(new SetObjectsForMiddleMap(simulationMap));
        }
        if (size > 19 && size < 25) {
            setStaticObjects = new SetObjectsService(new SetObjectsForBigMap(simulationMap));
        }
        if (size > 24 && size < 29) {
            setStaticObjects = new SetObjectsService(new SetObjectsForExtraMap(simulationMap));
        }
        if (size < 5) {
            setStaticObjects = new SetObjectsService(new SetObjectsForSmallMap(simulationMap));
            setStaticObjects.pullObjectIntoMap(5);
            return;
        }
        if (size > 28) {
            setStaticObjects = new SetObjectsService(new SetObjectsForExtraMap(simulationMap));
            setStaticObjects.pullObjectIntoMap(28);
            return;
        }
        setStaticObjects.pullObjectIntoMap(size);
    }
}
