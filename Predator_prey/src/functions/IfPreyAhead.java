package functions;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import predator_prey_main.PredatorPrey;


@SuppressWarnings("serial")
public class IfPreyAhead extends GPNode implements EvalPrint{

	@Override
	public void evalPrint(final EvolutionState state, 
			final int thread, 
			final GPData input, 
			final ADFStack stack, 
			final GPIndividual individual, 
			final  Problem problem, 
			final int[][] map2) {
		// TODO Auto-generated method stub
		
		PredatorPrey pred = (PredatorPrey)problem;
		
		switch(pred.orientation) {
			case PredatorPrey.orientation_UP:
				if(pred.map[ (pred.posX-1 + pred.maxX) % pred.maxX ] [pred.posY] == PredatorPrey.PREY) 
					((EvalPrint)children[0]).evalPrint(state, thread, input, stack, individual, pred, map2);
				else
					((EvalPrint)children[1]).evalPrint(state, thread, input, stack, individual, pred, map2);
				break;
			case PredatorPrey.orientation_LEFT:
				if(pred.map[pred.posX][ (pred.posY-1 + pred.maxY) % pred.maxY ] == PredatorPrey.PREY) 
					((EvalPrint)children[0]).evalPrint(state, thread, input, stack, individual, pred, map2);
				else
					((EvalPrint)children[1]).evalPrint(state, thread, input, stack, individual, pred, map2);
				break;
			case PredatorPrey.orientation_DOWN:
				if(pred.map[ (pred.posX+1) % pred.maxX ][ pred.posY] == PredatorPrey.PREY)
					((EvalPrint)children[0]).evalPrint(state, thread, input, stack, individual, pred, map2);
				else
					((EvalPrint)children[1]).evalPrint(state, thread, input, stack, individual, pred, map2);
				break;
			case PredatorPrey.orientation_RIGHT:
				if(pred.map[pred.posX][ (pred.posY+1) % pred.maxY ] == PredatorPrey.PREY)
					((EvalPrint)children[0]).evalPrint(state, thread, input, stack, individual, pred, map2);
				else
					((EvalPrint)children[1]).evalPrint(state, thread, input, stack, individual, pred, map2);
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
			final  Problem problem) {
		// TODO Auto-generated method stub
		
		PredatorPrey pred = (PredatorPrey)problem;
		
		switch(pred.orientation) {
			case PredatorPrey.orientation_UP: // UP
				if(pred.map[ (pred.posX-1 + pred.maxX) % pred.maxX ] [pred.posY] == PredatorPrey.PREY) 
					children[0].eval(state, thread, input, stack, individual, pred);
				else
					children[1].eval(state, thread, input, stack, individual, pred);
				break;
			case PredatorPrey.orientation_LEFT: // LEFT
				if(pred.map[pred.posX][ (pred.posY-1 + pred.maxY) % pred.maxY ] == PredatorPrey.PREY) 
					children[0].eval(state, thread, input, stack, individual, pred);
				else
					children[1].eval(state, thread, input, stack, individual, pred);
				break;
			case PredatorPrey.orientation_DOWN:// DOWN 
				if(pred.map[ (pred.posX+1) % pred.maxX ][ pred.posY] == PredatorPrey.PREY)
					children[0].eval(state, thread, input, stack, individual, pred);
				else
					children[1].eval(state, thread, input, stack, individual, pred);
				break;
			case PredatorPrey.orientation_RIGHT: // RIGHT
				if(pred.map[pred.posX][ (pred.posY+1) % pred.maxY ] == PredatorPrey.PREY)
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
		return "if-prey-ahead";
	}
	
	public int expectedChildren() {return 2;}

}
