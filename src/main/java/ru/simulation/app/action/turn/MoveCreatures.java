package ru.simulation.app.action.turn;

import ru.simulation.app.action.Action;
import ru.simulation.app.map.SimulationMap;
import ru.simulation.app.model.*;
import ru.simulation.app.utils.RandomCoordinates;

import java.util.List;

public class MoveCreatures implements Action {

    private SimulationMap simulationMap;

    public MoveCreatures(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    @Override
    public void doAction() {
        moveHerbivores();
        movePredators();
    }

    private void moveHerbivores() {
        List<Coords> herbivores = simulationMap.getHerbivoresCoordsList();
        List<Coords> exclusionCoords = getExclusionCoordinatesList('h');
        for (Coords c : herbivores) {
            Coords goal = getRandomGoalGrass(simulationMap.getGrassesCoordsList());
            Herbivore herbivore = (Herbivore) simulationMap.getEntityFromMap(c);
            Coords newCoord = herbivore.makeMove(c, goal, exclusionCoords);
            simulationMap.deleteFromMap(c);
            simulationMap.putIntoMap(newCoord, herbivore);
        }
    }

    private void movePredators() {
        List<Coords> predators = simulationMap.getPredatorsCoordsList();
        List<Coords> exclusionCoordsForPredator = getExclusionCoordinatesList('p');
        for (Coords c : predators) {
            Coords goal = getRandomGoalHerbivores(simulationMap.getHerbivoresCoordsList());
            Predator predator = (Predator) simulationMap.getEntityFromMap(c);
            Coords newCoord = predator.makeMove(c, goal, exclusionCoordsForPredator);
            simulationMap.deleteFromMap(c);
            simulationMap.putIntoMap(newCoord, predator);
        }
    }

    private Coords getRandomGoalGrass(List<Coords> grasses) {
        if (grasses == null || grasses.isEmpty()) {
            return RandomCoordinates.getRandomCoordinate(simulationMap.getSize());
        }
        int randomIndexG = RandomCoordinates.getRandomIndex(grasses.size());
        return grasses.get(randomIndexG);
    }

    private Coords getRandomGoalHerbivores(List<Coords> herbivores) {
        if (herbivores == null || herbivores.isEmpty()) {
            return RandomCoordinates.getRandomCoordinate(simulationMap.getSize());
        }
        int randomIndexH = RandomCoordinates.getRandomIndex(herbivores.size());
        return herbivores.get(randomIndexH);
    }

    private List<Coords> getExclusionCoordinatesList(Character name) {
        List<Coords> result = simulationMap.getStaticObjects();
        if (name == 'h') {
            result.addAll(simulationMap.getPredatorsCoordsList());
        }
        return result;
    }
}
