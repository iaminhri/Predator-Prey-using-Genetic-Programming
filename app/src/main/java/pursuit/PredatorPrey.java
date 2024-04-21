package pursuit;

import ec.EvolutionState;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.gp.GPProblem;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;
import pursuit.functions.EvalPrint;

@SuppressWarnings("serial")
public class PredatorPrey extends GPProblem implements SimpleProblemForm {

    /**
     * Create one predator, and multiple instances of prey.
     * fitness goal is predator eating maximum amount of preys.
     * predator: evolves with GP
     * prey: dumb and runs from predator by randomy movement.
     * fitness: number of prey eaten.
     * <p>
     * GP Lang:
     * Functions: IfPreyAhead, Behind, Right, Left, PROGN2 A B, PRGON3 A B C
     * Terminals: Move, Left, Right
     * <p>
     * Simulation: update and record all entities - location, current predator's direction,
     * time count, ...
     * <p>
     * Predator Prey Application
     * one predator eats multiple preys.
     * simulation:
     * update and record the state of all entities. location, current predator direction,
     * time count and others.
     * Predators state changes according to the functions and terminals.
     * Output: Predator movements, locations where prey eaten, location of live prey
     */

    // these are defined in the predator.params file.
    public static final String P_MOVES = "moves";
    public static final String P_Map_lenght_X = "map-x";
    public static final String P_Map_lenght_Y = "map-y";
    public static final String P_Prey_Amount = "prey_amount";


    // PredatorPrey simulation.

    public static final int PREY = -1; // Prey's position mark
    public static final int PREDATOR = 1; // predator itself
    public static final int EMPTY = 0; // empty cell mark
    public static final int ATE = 3; // prey eaten mark

    public static final int PREDATOR_X = 0;
    public static final int PREDATOR_Y = 0;


    public int map[][]; // map

    public int preyX[]; //preys X position in map
    public int preyY[]; // preys Y position in map

    public int posX, posY; // current pred's position

    public int maxX, maxY; // map max length and width

    public int moves; // moves counting

    public int maxMoves; // maximum moves

    public int prey; // predator's food

    public int preyAmount;

    public int preyEaten;

    public int pmod;


    // Predator orientations
    public static final int orientation_UP = 0;
    public static final int orientation_LEFT = 1;
    public static final int orientation_DOWN = 2;
    public static final int orientation_RIGHT = 3;

    public int orientation;

    public Object clone() {
        PredatorPrey myobj = (PredatorPrey) (super.clone()); // deep clone of the class
        myobj.map = new int[map.length][];

        for (int i = 0; i < map.length; i++) {
            myobj.map[i] = (int[]) (map[i].clone()); // shallow clone of the rows
        }

        return myobj;
    }

    public void setup(final EvolutionState state, final Parameter base) {
        // always initialize this.
        super.setup(state, base);

        // reading maxMoves and maxX and maxY from predator.params file.
        // ____________________________________________________________________________
        maxMoves = state.parameters.getInt(base.push(P_MOVES), null, 1);
        maxX = state.parameters.getInt(base.push("max-x"), null, 10);
        maxY = state.parameters.getInt(base.push("max-y"), null, 10);
        preyAmount = state.parameters.getInt(base.push(P_Prey_Amount), null, 5);

        if (maxMoves == 0 || maxX == 0 || maxY == 0) {
            state.output.error("The number of moves of a predator or max length has to be > 0");
        }
        // ____________________________________________________________________________

        // Map Initialization
        // ____________________________________________________________________________
        map = new int[maxX][maxY];
        preyX = new int[preyAmount];
        preyY = new int[preyAmount];

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                map[i][j] = EMPTY;
            }
        }

        // ____________________________________________________________________________

        // fixed position for predator's starting point.
        map[PREDATOR_X][PREDATOR_Y] = PREDATOR;

        // ____________________________________________________________________________

        // Random movement of prey
        for (int i = 0; i < preyAmount; i++) {
            int x, y;

            do {
                x = state.random[0].nextInt(maxX - 1);
                y = state.random[0].nextInt(maxY - 1);
            } while (map[x][y] != EMPTY && x < maxX && y < maxY);

            preyX[i] = x;
            preyY[i] = y;

            map[x][y] = PREY;
        }
        // ____________________________________________________________________________

        moves = 0;
        preyEaten = 0;
    }

    @Override
    public void evaluate(final EvolutionState state,
                         final Individual ind,
                         final int subpopulation,
                         final int threadnum) {
        // TODO Auto-generated method stub
        if (!ind.evaluated)  // don't bother reevaluating
        {
            preyEaten = 0;
            posX = 0;
            posY = 0;
            pmod = 97;
            map[posX][posY] = PREDATOR;
            orientation = orientation_RIGHT;

            // GP tree running
            for (moves = 0; moves < maxMoves && preyEaten < preyAmount; )
                ((GPIndividual) ind).trees[0].child.eval(
                        state, threadnum, input, stack, ((GPIndividual) ind), this);

            // the fitness better be KozaFitness!
            KozaFitness f = ((KozaFitness) ind.fitness);
            f.setStandardizedFitness(state, (preyAmount - preyEaten));
            f.hits = preyEaten;
            ind.evaluated = true;

            for (int i = 0; i < preyAmount; i++) {
                map[preyX[i]][preyY[i]] = PREY;
            }
        }
    }

    public void describe(
            final EvolutionState state,
            final Individual ind,
            final int subpopulation,
            final int threadnum,
            final int log) {

        state.output.println("\n\nBest Individual's Map\n=====================", log);

        preyEaten = 0;
        posX = 0;
        posY = 0;
        orientation = orientation_RIGHT;

        int[][] map2 = new int[map.length][];
        for (int x = 0; x < map.length; x++)
            map2[x] = (int[]) (map[x].clone());


        map2[posX][posY] = PREDATOR;

        for (moves = 0; moves < maxMoves && preyEaten < preyAmount; )
            ((EvalPrint) (((GPIndividual) ind).trees[0].child)).evalPrint(
                    state, threadnum, input, stack, ((GPIndividual) ind), this,
                    map2);

        // Print simulation map
        state.output.println("Simulation Map:", log); //

        for (int y = 0; y < map2.length; y++) {
            for (int x = 0; x < map2.length; x++) {

                switch (map2[y][x]) {
                    case PREY:
                        state.output.print("p", log);
                        break;
                    case PREDATOR:
                        state.output.print("#", log);
                        break;
                    case ATE:
                        state.output.print("X", log);
                        break;
                    case EMPTY:
                        state.output.print(".", log);
                        break;
                    default:
                        state.output.print("" + ((char) map2[x][y]), log);
                        break;
                }
            }
            state.output.println("", log);
        }

    }
}
