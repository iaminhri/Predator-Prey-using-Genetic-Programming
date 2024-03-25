package functions;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

@SuppressWarnings("serial")
public class Progn3 extends GPNode implements EvalPrint{

	public void evalPrint(final EvolutionState state, 
			final int thread, 
			final GPData input, 
			final ADFStack stack, 
			final GPIndividual individual,
			final Problem problem, 
			final int[][] map2) {
		// TODO Auto-generated method stub 
		
		((EvalPrint)children[0]).evalPrint(state, thread, input, stack, individual, problem, map2);
		((EvalPrint)children[1]).evalPrint(state, thread, input, stack, individual, problem, map2);
		((EvalPrint)children[2]).evalPrint(state, thread, input, stack, individual, problem, map2);
		
	}

	@Override
	public void eval(final EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual, Problem problem) {
		// TODO Auto-generated method stub
		children[0].eval(state, thread, input, stack, individual, problem);
		children[1].eval(state, thread, input, stack, individual, problem);
		children[2].eval(state, thread, input, stack, individual, problem);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "progn3";
	}
	
	public int expectedChildren() { return 3;}
	
}
