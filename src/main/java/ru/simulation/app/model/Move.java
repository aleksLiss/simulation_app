package ru.simulation.app.model;

import java.util.List;

public interface Move {

    Coordinates makeMove(Coordinates start, Coordinates goal, List<Coordinates> exclusion);

}
