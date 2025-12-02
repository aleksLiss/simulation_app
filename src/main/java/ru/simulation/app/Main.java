package ru.simulation.app;

import ru.simulation.app.model.Counter;
import ru.simulation.app.model.SimulationMap;
import ru.simulation.app.render.InfoRenderer;
import ru.simulation.app.render.MapRenderer;
import ru.simulation.app.simulation.Simulation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SimulationMap simulationMap = new SimulationMap(20, 10);
        MapRenderer mapRenderer = new MapRenderer(simulationMap);
        Counter counter = new Counter();
        InfoRenderer infoRenderer = new InfoRenderer(simulationMap);
        Simulation simulation = new Simulation(
                simulationMap,
                mapRenderer,
                counter,
                infoRenderer
                );
        simulation.startSimulation();
        Scanner scanner = new Scanner(System.in);
        String pause = scanner.nextLine();
        if (pause != null) {
            simulation.pauseSimulation();
        }
        scanner.close();
    }
}