# MaxProfitOptimizationProblem-DynamicProgramming-

This program has 3 solution for one of the optimization problems.
Suppose the truck has to pass through n number of towns. The truck can pick up the load in one town for a certain price and sell it at another. 
The truck can stop k times. 
So the problem is to find the most profitable stops for the truck so that the number of stops is less than or equal to k.

(k and n are values entered by the user).

This program uses 3 approaches to solve that problem.
1) Brute Force
2) Greedy
3) Exact or Dynamic

Example:
Town	          0	    1	    2	    3    	4
Pickup Cost:    $100	$120	$140	$110	$180
Dropoff Value:	$80	  $110	$120	$90	  $150

The same town cannot have pick up cost lower than the drop off cost, other wise there would be no problem to solve.

You can find 3 Classes inside the project each of them has a unique solution to the problem.
The best solution would be number 3 because it finds the exact maximum profit for a fast amount of time.
