package functions;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import predator_prey_main.PredatorPrey;

@SuppressWarnings("serial")
public class Move extends GPNode implements EvalPrint{
	
	@Override
	public void evalPrint(final EvolutionState state, 
			final int thread, 
			final GPData input, 
			final ADFStack stack, 
			final GPIndividual individual, 
			final  Problem problem,
			final int[][] map2) {
		// TODO Auto-generated method stub
		
		PredatorPrey pred = (PredatorPrey) problem;
		
		switch (pred.orientation) {
			case PredatorPrey.orientation_UP:
				pred.posY--;
				if(pred.posY < 0) {
					pred.posY = pred.maxY - 1;
				}
				break;
			case PredatorPrey.orientation_LEFT:
				pred.posX--;
				if(pred.posX < 0) {
					pred.posX = pred.maxX - 1;
				}
				break;
			case PredatorPrey.orientation_DOWN:
				pred.posY++;
				if(pred.posY >= pred.maxY) {
					pred.posY = 0;
				}
				break;
			case PredatorPrey.orientation_RIGHT:
				pred.posX++;
				
				if(pred.posX >= pred.maxX) {
					pred.posX = 0;
				}
				break;
			default:
				state.output.fatal("Bad Orientation of Predator Detected. ( " + pred.orientation + " )");
		}
		
		pred.moves++;
		
		if(pred.map[pred.posX][pred.posY] == PredatorPrey.PREY && pred.moves < pred.maxMoves) {
			pred.preyEaten++;
			pred.map[pred.posX][pred.posY] = PredatorPrey.ATE;
		}
		
		if(pred.moves < pred.maxMoves) {
			if(pred.map[pred.posX][pred.posY] == PredatorPrey.ATE)
				map2[pred.posX][pred.posY] = PredatorPrey.ATE;
			else if(pred.map[pred.posX][pred.posY] == PredatorPrey.EMPTY)
					map2[pred.posX][pred.posY] = PredatorPrey.PREDATOR;	
		}	
		
		
		updatePreysLocation(state, pred);
//		advancePreysLocationUpdate2(state,pred);
}

	@Override
	public void eval(final EvolutionState state, 
			final int thread, 
			final GPData input, 
			final ADFStack stack, 
			final GPIndividual individual, 
			final  Problem problem) {
		// TODO Auto-generated method stub
		PredatorPrey pred = (PredatorPrey) problem;
		
		switch (pred.orientation) {
			case PredatorPrey.orientation_UP:
				pred.posY--;
				if(pred.posY < 0) {
					pred.posY = pred.maxY - 1;
				}
				break;
			case PredatorPrey.orientation_LEFT:
				pred.posX--;
				if(pred.posX < 0) {
					pred.posX = pred.maxX - 1;
				}
				break;
			case PredatorPrey.orientation_DOWN:
				pred.posY++;
				if(pred.posY >= pred.maxY) {
					pred.posY = 0;
				}
				break;
			case PredatorPrey.orientation_RIGHT:
				pred.posX++;
				
				if(pred.posX >= pred.maxX) {
					pred.posX = 0;
				}
				break;
			default:
				state.output.fatal("Bad Orientation of Predator Detected. ( " + pred.orientation + " )");
		}
		
		pred.moves++;
		
		if(pred.map[pred.posX][pred.posY] == PredatorPrey.PREY && pred.moves < pred.maxMoves) {
			pred.preyEaten++;
			pred.map[pred.posX][pred.posY] = PredatorPrey.ATE;
		}		
		
		updatePreysLocation(state, pred);	
//		advancePreysLocationUpdate2(state,pred); // used this for advanced prey movement comparison analysis.
	}
	
	public void updatePreysLocation(EvolutionState state, PredatorPrey pred) {		
		int [][] newMap = new int[pred.maxX][pred.maxY];
		
		for (int i = 0; i < pred.maxX; i++) {
	        for (int j = 0; j < pred.maxY; j++) {
	        	if(pred.map[i][j] == PredatorPrey.PREY) {
	        		newMap[i][j] = 0;
	        	}
	        	else {
	        		newMap[i][j] = pred.map[i][j];
	        	}
	        }
	    }
		
		for (int i = 0; i < pred.maxY; i++) {
	    	for(int j = 0; j < pred.maxX; j++) {
	    		 if(pred.map[i][j] == PredatorPrey.PREY) {
	    	        int direction = state.random[0].nextInt(4); // 0: UP, 1: LEFT, 2: DOWN, 3: RIGHT
	    	        
	    	        int newX = i; // 2 0-> 1 0 , 2 3 -> 2 2, 
	    	        //				 4 3 -> 5 3, 6 5 -> 5 5, 8 7 -> 8 8
	    	        int newY = j;
	    	        // xy xy xy [3,3]
	    	        // 00 01 02   // 0 2 -> (random) -1 3 -> 2 0 top right corner
	    	        // 10 11 12   // 2 0 -> (random) 3 -1 -> 0 2 || 1 0 -> (random) 2 -1 -> 2 2 bottom left corner 
	    	        // 20 21 22   // 2 0 -> (7) 3 1 -> 0 1
	    	        // random up, down, left, right movement
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
	    	        
	    	        if(newMap[newX][newY] != PredatorPrey.PREY)
	    	        	newMap[newX][newY] = PredatorPrey.PREY;
	    	        else
	    	        	newMap[i][j] = PredatorPrey.PREY; 
	    		}
	    	}
	    }
	}
	
	public void advancePreysLocationUpdate2(EvolutionState state, PredatorPrey pred) {      
	    int[][] newMap = new int[pred.maxX][pred.maxY];
	    
	    
	    //copies pred.map data to newMap except the preys location, used this for edge case.
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

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "move";
	}
	
	public int expectedChildren() {
		return 0;
	}

}
