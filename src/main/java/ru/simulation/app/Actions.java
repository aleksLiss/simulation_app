package ru.simulation.app;

import java.util.List;

public class Actions {
    /*
        todo add calculate if coords tree or rock
        todo not calculate if coords already was calculated (list with not calculated)

     */

    private SimulationMap simulationMap;

    public Actions(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    private List<Coords> getNeighbours(Coords currentCoord) {
        int x = currentCoord.getX();
        int y = currentCoord.getY();
        List<Coords> neighbours =
                List.of(
                        new Coords(x - 1, y),
                        new Coords(x - 1, y + 1),
                        new Coords(x, y + 1),
                        new Coords(x + 1, y + 1),
                        new Coords(x + 1, y),
                        new Coords(x + 1, y - 1),
                        new Coords(x, y - 1),
                        new Coords(x - 1, y - 1)
                );
        return neighbours;
    }

    private int calculateDiff(int start, int finish) {
        return Math.abs(finish - start);
    }

    private int calculateH(Coords finish, Coords start) {
        int diffX = calculateDiff(start.getX(), finish.getX());
        int diffY = calculateDiff(start.getY(), finish.getY());
        return (Math.min(diffX, diffY) * 14) + (Math.abs(diffX - diffY) * 10);
    }

    private int calculateF(int numberG, Coords finish, Coords start) {
        return numberG + calculateH(finish, start);
    }

    private boolean isDirect(Coords start, Coords neighbor) {
        boolean result = false;
        if (start.getX() == neighbor.getX() &&
                ((neighbor.getY() - 1 == start.getY() ) || (neighbor.getY() + 1 == start.getY()))) {
            result = true;
        }
        if (start.getY() == neighbor.getY() &&
                ((neighbor.getX() - 1 == start.getX() ) || (neighbor.getX() + 1 == start.getX()))) {
            result = true;
        }
        return result;
    }

    // todo actions
    //  выбор размера поля, количество хп, скорость для травоядных, хищников ( установить лимиты), диапазон атаки хищников
    //  init actions:
    //  0.1. set rock, tree - always static coords
    //  0.2. set grass - always static count
    //  0.4
    // todo 2. поиск травы

    private Coords searchGrass(Coords grass) {
        int goalX = 1;
        int goalY = 8;
        int startX = 7;
        int startY = 2;
        Coords finish = new Coords(goalX, goalY);
        Coords start = new Coords(startX, startY);
        System.out.printf("start x  == %d; start y == %d%n", startX, startY);
        System.out.printf("finish x == %d; finish y == %d%n", goalX, goalY);
        int minF = 1000;
        // неверно считает минимальную f
        while (startX != goalX && startY != goalY) {
            List<Coords> coords  = getNeighbours(start);
            int numberF = -1;
            for (Coords coord: coords) {
                System.out.printf("neighbor with x = %d with y %d%n", coord.getX(), coord.getY());
                if (isDirect(start, coord)) {
                    numberF = calculateF(
                            10,
                            finish,
                            coord
                    );
                } else {
                    numberF = calculateF(
                            14,
                            finish,
                            coord
                    );
                }
                if (numberF < minF) {
                    minF = numberF;
                    System.out.printf("next x %d; next y %d; min f = %d", coord.getX(), coord.getY(), minF);
                    System.out.println();
                    startX = coord.getX();
                    startY = coord.getY();
                    start = coord;
                }
            }
        }
        return new Coords(1 ,2);
    }

    public static void main(String[] args) {
        Actions actions = new Actions(
                new SimulationMap(20)
        );
        /*
        Coords start = new Coords(4, 2);
        Coords finish = new Coords(2, 5);
        List<Coords> coords = actions.getNeighbours(start);
        for (Coords coords1 : coords) {
            if (actions.isDirect(start, coords1)) {
                System.out.printf("%d %d = ", coords1.getX(), coords1.getY());
                int F = actions.calculateF(10, finish, coords1);
                System.out.print(F);
            }else {
                System.out.printf("%d %d = ", coords1.getX(), coords1.getY());
                int F = actions.calculateF(14, finish, coords1);
                System.out.print(F);
            }
            System.out.println();
        }
         */
        actions.searchGrass(new Coords(1, 2));
    }
}
