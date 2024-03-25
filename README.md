The Predator-Prey problem aims to simulate one predator pursuing multiple prey instances <br>
within a map. The objective is to eat as much prey as possible within a given number <br>
of moves, and the amount of prey eaten by the predator is less than the total amount <br>
spawned on the map. In other words, the predator is to evolve a successful pursuit with <br>
a sequence of moves that maximizes the number of prey consumed by the predator. The <br>
evolutionary process to evolve the predator to pursue prey employs genetic programming with <br>
the ECJ (A Java-based Evolutionary Computation Research System) framework to solve this <br>
predator-prey problem. The GP language used in the process of solving the predator-prey <br>
problems are Function sets: IfPreyAhead, IfPreyBehind, IfPreyLeft, IfPreyRight, <br>
Progn2, Progn3 and Terminal Sets: Left, Right, Move. Prey randomly moves from <br>
one location to another when a predator looks left or right or moves in a position. Advance <br>
prey movements is used such as moving in 12 direction rather than moving in 4 direction, <br>
will discuss on comparative analysis. The simulation environment is a 2-dimensional array <br>
where the predator starts off at a starting position and starts hunting the preys. The fitness <br>
for the GP program is to eat as much prey as possible within the given amount of moves <br>
and as long as the number of prey eaten is less than the total amount of prey spawned on <br>
the map. The GP problem is performed over 10 runs with different parameter settings and <br>
statistics analysis performed (provided later in the section). The outcome of the GP program <br>
is to mark the current predator’s location, the location where the prey got eaten, and the <br>
prey that survived the predator’s pursuit. Overall, the program performs well with a 90% <br>
crossover and 10% mutation rate with 10 elites carried over to the next generation. <br>

The following GP expression is the best one obtained from all the parameters
tested in the GP problem.
Best Individual of Run:
Subpopulation 0:
Evaluated: true
Fitness: Standardized=3.0 Adjusted=0.25 Hits=27
Tree 0:
(progn3 (progn3 (if-prey-behind (progn2 move
(progn3 (progn3 (if-prey-behind left move)
(progn3 move move move) (if-prey-ahead (if-prey-ahead
left left) (if-prey-behind left move))) (if-prey-left
(if-prey-left move (progn3 left move move))
(if-prey-ahead left left)) (progn3 (if-prey-behind
left move) (if-prey-behind (progn3 (if-prey-left
move move) (progn3 (if-prey-behind left move)
(progn3 move move move) (if-prey-ahead (if-prey-ahead
left left) (if-prey-behind left move))) (if-prey-ahead
(if-prey-left (if-prey-left (if-prey-behind
(if-prey-ahead right (progn3 move right move))
move) left) (progn2 move move)) (if-prey-ahead
(if-prey-left move left) (if-prey-behind
left move)))) move) (if-prey-ahead (if-prey-behind
move right) (if-prey-behind left move)))))
move) (progn3 move move move) (if-prey-ahead
(if-prey-ahead left left) move)) (if-prey-left
(if-prey-left move (progn3 (progn3 (if-prey-behind
left move) (if-prey-behind (if-prey-left
right right) (if-prey-ahead (if-prey-ahead
left left) (if-prey-behind left move))) (if-prey-ahead
(if-prey-behind move right) (progn2 move
move))) (if-prey-ahead (if-prey-ahead (if-prey-behind
move move) (if-prey-ahead right left)) (if-prey-behind
(if-prey-left move move) (if-prey-left (if-prey-ahead
left (if-prey-ahead (progn2 move move) move))
(if-prey-behind right right)))) (progn3 (if-prey-left
move move) (progn2 (if-prey-behind (if-prey-ahead
(progn2 (progn2 move right) (progn2 (if-prey-ahead
move (progn3 move move move)) (progn3 move
right right))) (if-prey-ahead (if-prey-left
move left) (progn2 (if-prey-left (if-prey-behind
(if-prey-left right right) (if-prey-ahead
left move)) (if-prey-ahead left move)) (if-prey-left
move (if-prey-behind left move))))) (if-prey-behind
10
move move)) (progn3 move move left)) (if-prey-ahead
(if-prey-left move left) (progn3 move move
left))))) (if-prey-ahead right (progn3 move
right move))) (progn3 (if-prey-left move
move) (progn2 (progn2 (if-prey-behind (progn3
(if-prey-behind move (if-prey-left move move))
(if-prey-left (if-prey-left right right)
(if-prey-ahead right (progn3 move right (if-prey-behind
move right)))) (progn3 (if-prey-left move
move) (progn2 (progn2 (if-prey-behind (if-prey-behind
right right) (if-prey-left (progn3 (if-prey-behind
move move) (progn3 move right move) (if-prey-ahead
move left)) (if-prey-behind right right)))
(progn3 move right right)) (if-prey-left
move left)) (if-prey-ahead (if-prey-left
(if-prey-left left left) (progn2 move move))
(progn3 move move move)))) (if-prey-behind
move move)) move) (if-prey-left move left))
(if-prey-ahead (if-prey-behind move right)
(progn3 (if-prey-ahead (if-prey-ahead left
left) (if-prey-behind left move)) move move))))



* MAP - 2 dimensional [30x30]
* Predator’s current position - marked with "\#" (Hash symbol)
* Location where preys are eaten - marked with "X" (Alphabet X)
* Location of surviving prey - marked with "p" (Alphabet small-p)
* Empty cells - marked with "." ( Dot ).
Best Individual’s Map
=====================
Simulation Map:
##########..#..##############.
###.........#..#.......#######
#..##########..#..###X#######.
#..#........#####.#....#....#.
#..#..####X######.#..##X######
#..#..#....###X#X######X....#.
#####.#..####X#####..#..######
....X.#..#.#....#....#..#...#.
#######..#.#########X#..#..###
###.#....####...#.......X..###
..#.#.......#..##########.p#..
..#.#.......#..##..........#..
..X#####X####..#############..
..###..........#..#.#.........
..###.##########..#.#.........
..######X.........X.#.........
####..#.###########.#...###X##
..#...#.##.....p....#...#.....
##X####.#X..........#..p#..###
......#.##..........#...#..#..
#X########.....##########..X..
#.....#.#......X....#####..#..
#..########....X..####X#####..
#..#..#.#......#..#.....#.....
#..#..##########..#..#########
...#..#.....#.....#..#..X.....
####..#..#X########..#..####X#
..##X######.#........#..#.....
#######..#..###X######..#..###
..#......#..#...........#..#..
