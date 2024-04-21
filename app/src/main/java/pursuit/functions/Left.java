package pursuit.functions;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import pursuit.PredatorPrey;

@SuppressWarnings("serial")
public class Left extends GPNode implements EvalPrint {

    @Override
    public void evalPrint(final EvolutionState state,
                          final int thread,
                          final GPData input,
                          final ADFStack stack,
                          final GPIndividual individual,
                          final Problem problem,
                          final int[][] map2) {
        // TODO Auto-generated method stub
        eval(state, thread, input, stack, individual, problem);

    }

    @Override
    public void eval(final EvolutionState state,
                     final int thread,
                     final GPData input,
                     final ADFStack stack,
                     final GPIndividual individual,
                     final Problem problem) {
        // TODO Auto-generated method stub

        PredatorPrey pred = (PredatorPrey) problem;
        // changes orientation to left based on current orientation.

        switch (pred.orientation) {
            case PredatorPrey.orientation_UP:
                pred.orientation = PredatorPrey.orientation_LEFT;
                break;
            case PredatorPrey.orientation_LEFT:
                pred.orientation = PredatorPrey.orientation_DOWN;
                break;
            case PredatorPrey.orientation_DOWN:
                pred.orientation = PredatorPrey.orientation_RIGHT;
                break;
            case PredatorPrey.orientation_RIGHT:
                pred.orientation = PredatorPrey.orientation_UP;
                break;
            default:
                state.output.fatal("Bad Orientation of Predator Detected. ( " + pred.orientation + " )");
        }

        pred.moves++;

        //updates preys location when predator looks left.
        updatePreysLocation(state, pred);
//		advancePreysLocationUpdate2(state, pred);


    }

    public void advancePreysLocationUpdate2(EvolutionState state, PredatorPrey pred) {
        int[][] newMap = new int[pred.maxX][pred.maxY];

        for (int i = 0; i < pred.maxX; i++) {
            for (int j = 0; j < pred.maxY; j++) {
                if (pred.map[i][j] == PredatorPrey.PREY) {
                    newMap[i][j] = 0;
                } else {
                    newMap[i][j] = pred.map[i][j];
                }
            }
        }

        for (int i = 0; i < pred.maxY; i++) {
            for (int j = 0; j < pred.maxX; j++) {
                if (pred.map[i][j] == PredatorPrey.PREY) {
                    int direction = state.random[0].nextInt(12); // 0-3: Cardinal directions, 4-7: Diagonal directions, 8-11: Additional moves

                    int newX = i;
                    int newY = j;

                    switch (direction) {
                        case 0: // UP
                            newX--;
                            if (newX < 0) {
                                newX = pred.maxX - 1;
                            }
                            break;
                        case 1: // DOWN
                            newX++;
                            if (newX >= pred.maxX) {
                                newX = 0;
                            }
                            break;
                        case 2: // LEFT
                            newY--;
                            if (newY < 0) {
                                newY = pred.maxY - 1;
                            }
                            break;
                        case 3: // RIGHT
                            newY++;
                            if (newY >= pred.maxY) {
                                newY = 0;
                            }
                            break;
                        case 4: // Diagonal Up-Left
                            newX--;
                            newY--;
                            if (newX < 0) {
                                newX = pred.maxX - 1;
                            }
                            if (newY < 0) {
                                newY = pred.maxY - 1;
                            }
                            break;
                        case 5: // Diagonal Up-Right
                            newX--;
                            newY++;
                            if (newX < 0) {
                                newX = pred.maxX - 1;
                            }
                            if (newY >= pred.maxY) {
                                newY = 0;
                            }
                            break;
                        case 6: // Diagonal Down-Left
                            newX++;
                            newY--;
                            if (newX >= pred.maxX) {
                                newX = 0;
                            }
                            if (newY < 0) {
                                newY = pred.maxY - 1;
                            }
                            break;
                        case 7: // Diagonal Down-Right
                            newX++;
                            newY++;
                            if (newX >= pred.maxX) {
                                newX = 0;
                            }
                            if (newY >= pred.maxY) {
                                newY = 0;
                            }
                            break;
                        case 8: // Move two steps UP
                            newX -= 2;
                            if (newX < 0) {
                                newX += pred.maxX;
                            }
                            break;
                        case 9: // Move two steps DOWN
                            newX += 2;
                            if (newX >= pred.maxX) {
                                newX -= pred.maxX;
                            }
                            break;
                        case 10: // Move two steps LEFT
                            newY -= 2;
                            if (newY < 0) {
                                newY += pred.maxY;
                            }
                            break;
                        case 11: // Move two steps RIGHT
                            newY += 2;
                            if (newY >= pred.maxY) {
                                newY -= pred.maxY;
                            }
                            break;
                        default:
                            state.output.fatal("Invalid direction for prey movement.");
                            break;
                    }

                    if (newMap[newX][newY] != PredatorPrey.PREY)
                        newMap[newX][newY] = PredatorPrey.PREY;
                    else
                        newMap[i][j] = PredatorPrey.PREY;
                }
            }
        }
    }


    public void updatePreysLocation(EvolutionState state, PredatorPrey pred) {
        int[][] newMap = new int[pred.maxX][pred.maxY];

        for (int i = 0; i < pred.maxX; i++) {
            for (int j = 0; j < pred.maxY; j++) {
                if (pred.map[i][j] == PredatorPrey.PREY) {
                    newMap[i][j] = 0;
                } else {
                    newMap[i][j] = pred.map[i][j];
                }
            }
        }

        for (int i = 0; i < pred.maxY; i++) {
            for (int j = 0; j < pred.maxX; j++) {
                if (pred.map[i][j] == PredatorPrey.PREY) {
                    int direction = state.random[0].nextInt(4); // 0: UP, 1: LEFT, 2: DOWN, 3: RIGHT

                    int newX = i; // 2 0-> 1 0 , 2 3 -> 2 2,
                    //				 4 3 -> 5 3, 6 5 -> 5 5, 8 7 -> 8 8
                    int newY = j;
                    // xy xy xy
                    // 00 01 02
                    // 10 11 12
                    // 20 21 22

                    switch (direction) {
                        case 0: // left
                            newY--;
                            if (newY < 0) {
                                newY = pred.maxY - 1;
                            }
                            break;
                        case 1: // UP
                            newX--;
                            if (newX < 0) {
                                newX = pred.maxX - 1;
                            }
                            break;
                        case 2: // right
                            newY++;
                            if (newY >= pred.maxY) {
                                newY = 0;
                            }
                            break;
                        case 3: // down
                            newX++;
                            if (newX >= pred.maxX) {
                                newX = 0;
                            }
                            break;
                        default:
                            state.output.fatal("Invalid direction for prey movement.");
                            break;
                    }

                    if (newMap[newX][newY] != PredatorPrey.PREY)
                        newMap[newX][newY] = PredatorPrey.PREY;
                    else
                        newMap[i][j] = PredatorPrey.PREY;
                }
            }
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "left";
    }

    public int expectedChildren() {
        return 0;
    }

}
