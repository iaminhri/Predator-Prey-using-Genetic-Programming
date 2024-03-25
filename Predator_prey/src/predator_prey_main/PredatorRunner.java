package predator_prey_main;

import ec.Evolve;

public class PredatorRunner {
	public static void main(String[] args) {
		String pathToFiles = "H:\\BrockUniversityThridYear\\Second Semester\\COSC 4P82(GP)\\Assignment 2\\Predator_prey\\bin";
		
		int numOfJobs = 10;
		
//		String statisticType = "ec.gp.koza.KozaShortStatistics";
		
		String[] runConfig = new String[] {
				Evolve.A_FILE, "src/predator_prey_main/predator.params",
//				"-p", ("stat="+statisticType), 
				"-p", ("stat.file=$" + pathToFiles + "out.stat"),
				"-p", ("jobs=" + numOfJobs)
		};
		Evolve.main(runConfig);
	}
}
