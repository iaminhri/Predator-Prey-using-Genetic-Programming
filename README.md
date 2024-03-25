Predator-Prey Genetic Programming Solution<br>
The Predator-Prey problem aims to simulate one predator pursuing multiple prey instances within a map. The objective is to eat as much prey as possible within a given number of moves, with the predator evolving a successful pursuit strategy. The evolutionary process employs genetic programming with the ECJ (A Java-based Evolutionary Computation Research System) framework.<br>

Problem Description<br>
Simulation Environment: 2-dimensional array [30x30]<br>
Predator's Symbol: "#" (Hash symbol)<br>
Eaten Prey Symbol: "X" (Alphabet X)<br>
Surviving Prey Symbol: "p" (Alphabet small-p)<br>
Empty Cell Symbol: "." (Dot)<br>
Genetic Programming Parameters<br>
Function Sets:<br>
IfPreyAhead<br>
IfPreyBehind<br>
IfPreyLeft<br>
IfPreyRight<br>
Progn2<br>
Progn3<br>
Terminal Sets:<br>
Left<br>
Right<br>
Move<br>
Fitness Function<br>
The fitness for the GP program is to eat as much prey as possible within the given amount of moves, ensuring the number of prey eaten is less than the total amount spawned on the map.<br>

Evolutionary Parameters<br>
Crossover Rate: 90%<br>
Mutation Rate: 10%<br>
Elites Carried Over: 10 to the next generation<br>
Best Individual GP Expression<br>
