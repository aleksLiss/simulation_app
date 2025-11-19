package ru.simulation.app.model;

import java.util.List;

public interface Move {

    Coords makeMove(Coords start, Coords goal, List<Coords> exclusion);

}
