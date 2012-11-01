#CS4341 Project 1 Readme
Chris Casola
Jennifer Page

##Heuristic Functions

We designed two heuristic functions for our Project. The first one, AdvancedHeuristicFunction, subtracted 
the number of partial connection sequences for the opponent from the number of partial connection sequences 
for the player. It did this for each number of pieces possible in a sequence (ex: 2, 3, ... N-1 where N is the 
number of pieces needed in a sequence to win), recording the values in an array.  Later, these values were 
revised using the following conditions:

if (value > 0) : value = 1
else if (value < 0) : value = -1
else : value = 0

A variable named result was set to 0 and for each of these values, V, the following step was performed:

result += V * C * 2
          ---------
             N^2
             
where C is the number of checkers that were required to be in a sequence for that value. The sum of the 
equation from 2 to N-1 always has a limit of one (when assuming V is 1). Unfortunately, our code did not 
reflect this limit and due to time constraints, we did not have time to correct the defect that prevented 
our AdvancedHeuristicFunction from working.

Our second heuristic function, AnotherHeuristicFunction, provided expected values when used. It functioned 
by checking an 8 pointed star pattern of height N-2 around a potential move on the board, counting how many 
matching pieces there are in this pattern. It counts outwards from the potential move and stop counting in 
it's current counting direction if it encounters an opponents piece.