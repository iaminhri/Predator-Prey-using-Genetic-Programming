package pursuit.functions;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import pursuit.PredatorPrey;

@SuppressWarnings("serial")
public class IfPreyBehind extends GPNode implements EvalPrint {

    @Override
    public void evalPrint(final EvolutionState state,
                          final int thread,
                          final GPData input,
                          final ADFStack stack,
                          final GPIndividual individual,
                          final Problem problem,
                          final int[][] map2) {
        // TODO Auto-generated method stub

        PredatorPrey pred = (PredatorPrey) problem;

        switch (pred.orientation) {
            case PredatorPrey.orientation_UP: //down
                if (pred.map[pred.posX][(pred.posY + 1) % pred.maxY] == PredatorPrey.PREY)
                    ((EvalPrint) children[0]).evalPrint(state, thread, input, stack, individual, pred, map2);
                else
                    ((EvalPrint) children[1]).evalPrint(state, thread, input, stack, individual, pred, map2);
                break;
            case PredatorPrey.orientation_LEFT: //right
                if (pred.map[(pred.posX + 1) % pred.maxX][pred.posY] == PredatorPrey.PREY)
                    ((EvalPrint) children[0]).evalPrint(state, thread, input, stack, individual, pred, map2);
                else
                    ((EvalPrint) children[1]).evalPrint(state, thread, input, stack, individual, pred, map2);
                break;
            case PredatorPrey.orientation_DOWN: //up

                if (pred.map[pred.posX][(pred.posY - 1 + pred.maxY) % pred.maxY] == PredatorPrey.PREY)
                    ((EvalPrint) children[0]).evalPrint(state, thread, input, stack, individual, pred, map2);
                else
                    ((EvalPrint) children[1]).evalPrint(state, thread, input, stack, individual, pred, map2);
                break;
            case PredatorPrey.orientation_RIGHT: //left

                if (pred.map[(pred.posX - 1 + pred.maxX) % pred.maxX][pred.posY] == PredatorPrey.PREY)
                    ((EvalPrint) children[0]).evalPrint(state, thread, input, stack, individual, pred, map2);
                else
                    ((EvalPrint) children[1]).evalPrint(state, thread, input, stack, individual, pred, map2);
                break;
            default:
                state.output.fatal("Bad Orientation of Predator Detected. ( " + pred.orientation + " )");
                break;
        }
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

        // xy xy xy [3,3]
        // 00 01 02 
        // 10 11 12  
        // 20 21 22 

        switch (pred.orientation) {
            case PredatorPrey.orientation_UP: // down
                if (pred.map[pred.posX][(pred.posY + 1) % pred.maxY] == PredatorPrey.PREY)
                    children[0].eval(state, thread, input, stack, individual, pred);
                else
                    children[1].eval(state, thread, input, stack, individual, pred);
                break;
            case PredatorPrey.orientation_LEFT: // right
                if (pred.map[(pred.posX + 1) % pred.maxX][pred.posY] == PredatorPrey.PREY)
                    children[0].eval(state, thread, input, stack, individual, pred);
                else
                    children[1].eval(state, thread, input, stack, individual, pred);
                break;
            case PredatorPrey.orientation_DOWN:// up
                if (pred.map[pred.posX][(pred.posY - 1 + pred.maxY) % pred.maxY] == PredatorPrey.PREY)
                    children[0].eval(state, thread, input, stack, individual, pred);
                else
                    children[1].eval(state, thread, input, stack, individual, pred);
                break;
            case PredatorPrey.orientation_RIGHT: // left
                if (pred.map[(pred.posX - 1 + pred.maxX) % pred.maxX][pred.posY] == PredatorPrey.PREY)
                    children[0].eval(state, thread, input, stack, individual, pred);
                else
                    children[1].eval(state, thread, input, stack, individual, pred);
                break;

            default:
                state.output.fatal("Bad Orientation of Predator Detected. ( " + pred.orientation + " )");
                break;
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "if-prey-behind";
    }

    public int expectedChildren() {
        return 2;
    }

}
