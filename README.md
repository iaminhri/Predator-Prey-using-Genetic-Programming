Predator-Prey Genetic Programming Solution
The Predator-Prey problem aims to simulate one predator pursuing multiple prey instances within a map. The objective is to eat as much prey as possible within a given number of moves, with the predator evolving a successful pursuit strategy. The evolutionary process employs genetic programming with the ECJ (A Java-based Evolutionary Computation Research System) framework.

Problem Description
Simulation Environment: 2-dimensional array [30x30]
Predator's Symbol: "#" (Hash symbol)
Eaten Prey Symbol: "X" (Alphabet X)
Surviving Prey Symbol: "p" (Alphabet small-p)
Empty Cell Symbol: "." (Dot)
Genetic Programming Parameters
Function Sets:

IfPreyAhead
IfPreyBehind
IfPreyLeft
IfPreyRight
Progn2
Progn3
Terminal Sets:

Left
Right
Move
Fitness Function
The fitness for the GP program is to eat as much prey as possible within the given amount of moves, ensuring the number of prey eaten is less than the total amount spawned on the map.

Evolutionary Parameters
Crossover Rate: 90%
Mutation Rate: 10%
Elites Carried Over: 10 to the next generation
Best Individual GP Expression
(progn3
  (progn3
    (if-prey-behind
      (progn2 move
        (progn3
          (progn3
            (if-prey-behind left move)
            (progn3 move move move)
            (if-prey-ahead
              (if-prey-ahead left left)
              (if-prey-behind left move)))
          (if-prey-left
            (if-prey-left move (progn3 left move move))
            (if-prey-ahead left left))
          (progn3
            (if-prey-behind left move)
            (if-prey-behind
              (progn3
                (if-prey-left move move)
                (progn3
                  (if-prey-behind left move)
                  (progn3 move move move)
                  (if-prey-ahead
                    (if-prey-ahead left left)
                    (if-prey-behind left move)))
                (if-prey-ahead
                  (if-prey-left
                    (if-prey-left
                      (if-prey-behind
                        (if-prey-ahead right (progn3 move right move))
                        move)
                    left)
                  (progn2 move move))
                (if-prey-ahead
                  (if-prey-left move left)
                  (if-prey-behind left move))))
            move)
          (if-prey-ahead
            (if-prey-behind move right)
            (if-prey-behind left move)))))
      move)
    (progn3 move move move)
    (if-prey-ahead
      (if-prey-ahead left left)
      move))
  (if-prey-left
    (if-prey-left move
      (progn3
        (progn3
          (if-prey-behind left move)
          (if-prey-behind
            (if-prey-left right right)
            (if-prey-ahead
              (if-prey-ahead left left)
              (if-prey-behind left move)))
          (if-prey-ahead
            (if-prey-behind move right)
            (progn2 move move)))
        (if-prey-ahead
          (if-prey-ahead
            (if-prey-behind move move)
            (if-prey-ahead right left))
          (if-prey-behind
            (if-prey-left move move)
            (if-prey-left
              (if-prey-ahead left
                (if-prey-ahead
                  (progn2 move move)
                  move))
              (if-prey-behind right right))))
        (progn3
          (if-prey-left move move)
          (progn2
            (if-prey-behind
              (if-prey-ahead
                (progn2 (progn2 move right)
                  (progn2
                    (if-prey-ahead move
                      (progn3 move move move))
                    (progn3 move right right)))
                (if-prey-ahead
                  (if-prey-left
                    (if-prey-behind
                      (if-prey-left right right)
                      (if-prey-ahead
                        left move))
                    (if-prey-ahead left move))
                  (if-prey-left move
                    (if-prey-behind left move)))))
            (if-prey-behind
              move move)))
        (progn3 move move left)))
    (if-prey-ahead
      (if-prey-left move left)
      (progn3 move move left))))
  (if-prey-ahead
    right
    (progn3 move right move)))
Best Individual's Map
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
