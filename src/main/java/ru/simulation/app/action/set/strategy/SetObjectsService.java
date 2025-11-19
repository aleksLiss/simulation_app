package ru.simulation.app.action.set.strategy;

public class SetObjectsService {

    private SetObjects setObjects;

    public SetObjectsService(SetObjects setObjects) {
        this.setObjects = setObjects;
    }

    public void pullObjectIntoMap(int size) {
        if (setObjects != null) {
            setObjects.setObjects(size);
        }
    }
}
