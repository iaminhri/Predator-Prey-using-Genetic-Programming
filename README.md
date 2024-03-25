<h2>Predator-Prey Genetic Programming Solution</h2>
<p>The Predator-Prey problem aims to simulate one predator pursuing multiple prey instances within a map. The objective is to eat as much prey as possible within a given number of moves, with the predator evolving a successful pursuit strategy. The evolutionary process employs genetic programming with the ECJ (A Java-based Evolutionary Computation Research System) framework.</p>
<h3>Problem Description</h3>
<ul>
  <li><strong>Simulation Environment:</strong> 2-dimensional array [30x30]</li>
  <li><strong>Predator's Symbol:</strong> "#" (Hash symbol)</li>
  <li><strong>Eaten Prey Symbol:</strong> "X" (Alphabet X)</li>
  <li><strong>Surviving Prey Symbol:</strong> "p" (Alphabet small-p)</li>
  <li><strong>Empty Cell Symbol:</strong> "." (Dot)</li>
</ul>
<h3>Genetic Programming Parameters</h3>
<ul>
  <li><strong>Function Sets:</strong>
    <ul>
      <li>IfPreyAhead</li>
      <li>IfPreyBehind</li>
      <li>IfPreyLeft</li>
      <li>IfPreyRight</li>
      <li>Progn2</li>
      <li>Progn3</li>
    </ul>
  </li>
  <li><strong>Terminal Sets:</strong>
    <ul>
      <li>Left</li>
      <li>Right</li>
      <li>Move</li>
    </ul>
  </li>
</ul>
<h3>Fitness Function</h3>
<p>The fitness for the GP program is to eat as much prey as possible within the given amount of moves, ensuring the number of prey eaten is less than the total amount spawned on the map.</p>
<h3>Evolutionary Parameters</h3>
<ul>
  <li><strong>Crossover Rate:</strong> 90%</li>
  <li><strong>Mutation Rate:</strong> 10%</li>
  <li><strong>Elites Carried Over:</strong> 10 to the next generation</li>
</ul>
<h3>Best Individual GP Expression</h3>
<pre><code>(progn3
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
                      (if-prey-ahead left move))
                    (if-prey-ahead left move))
                  (if-prey-left move
                    (if-prey-behind left move)))))</code></pre>
<h3>Best Individual's Map</h3>
<pre><code>Best Individual's Map
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

