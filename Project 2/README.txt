Christopher Casola
Jennifer Page
CS4341 Project 2 Readme
Due 11/8/2012 at 11:59 PM

## Table of Contents ##

1. Running the Program
2. Approach
3. Evaluation of Algorithms
4. Test Cases
5. Strengths and Weaknesses
6. Error in constraint09.dat solution

## Running the Program ##
To run the program from Eclipse, right click Project2.java, choose "Run As" > "Run Configurations". In the 
window that pops up, click the Arguments tab and then type the name of the .dat file to open under Program 
Arguments.

## Approach ##
To implement the solver for CSPs, we started with the basic backtracking search algorithm with no heuristics.
The pseudo-code for our implementation is here:
	function BackTrack(csp)
		if all items are assigned and constraints are met, return true
		select an unassigned item
		for each bag in the domain of item
			construct a tree node for the item and add it to the tree
			assign the item to the bag
			remove the item from the list of unassigned items
			verify constraints
				if broken, revert the assignment and backtrack
				else recursively call backtrack on the current node (DFS)

After implementing the BacktrackSearch class, we realized it would be simply to extend the BacktrackSearch
class and simply override the selectUnassignedItem() and orderBags() methods to add heuristics to the search,
namely least constraining values and minimum remaining values. orderBags() implements least constraining value
by simply using Java's built in merge sort to sort the list using a comparator that calculates the least
constraining bag. selectUnassignedItem() implements minimum remaining value.

Next, we implemented the AC3() method in the Graph class which applies the arc consistency algorithm.

Finally, we extended the BacktrackSearchHeuristic class and added forward checking to the Backtrack algorithm. 

## Evaluation of Algorithms ##

The following chart shows the times for our backtracking (BT), backtracking with arc consistency (BT & AC), 
heuristic backtracking (BT & Heur.), heuristic backtracking with arc consistency (BT & Heur. & AC), and 
backtracking with forward checking (FC) methods. For each Problem and Method, we performed 15 trials and 
averaged the resulting times to create the chart. We chose to do problems 3 through 6 because we could check 
our answers relatively quickly by hand.

	+----------------------------------------------------------------------+
	| Problem  | BT     | BT & AC | BT & Heur. | BT & Heur. & AC | FC      |
	+----------------------------------------------------------------------|
	| 3        | 20.4   | 20.5    | 19.4       | 19.2            | 17.4    |
	| 4        | 70.2   | 67.1    | 14.4       | 13.6            | 13.3    |
	| 5        | 24     | 23.5    | 20.4       | 18.9            | 17.2    |
	| 6        | 82     | 91.3    | 22.8       | 22.7            | 19.9    |
	+----------------------------------------------------------------------+
	+----------------------------------------------------------------------+
	| Average  | 49.15  | 50.6    | 19.25      | 18.6            | 16.95   |
	+----------------------------------------------------------------------+
	
As seen in the table, there is generally a vast improvement to be had by adding the heuristic algorithms to the
basic backtracking search. There are however diminishing returns as arc consistency and forward checking are added.
These more advanced algorithms did improve the times, but not as drastically as the heuristics. There also seems to
be certain combinations of algorithms that work better for certain types of problems, in other words the types of
constraints in a problem seem to indicate the type of algorithm that would be most efficient.

## Strengths and Weaknesses ##

One of our strengths was our implementation of our Backtracking and Graph classes. These allowed us to 
extend the classes to modify or extend functionality. This simplified the process of adding heuristics and 
forward checking. Unfortunately, our code for these classes is still somewhat monolithic and could benefit 
from being broken into smaller parts to enhance modifiability, code reuse and readability.

Another strength was our robust constraint, Bag, and Item classes. These classes implemented fromString 
methods that made it easy to parse the text file. Constraint classes have a satisfied method that returns an 
enum value based on whether the constraint has been unsatisfied, partially satisfied, satisfied, or broken.

## Test Cases ##
Note, there is a test source folder which contains JUnit tests for most of our code. The constraints tests have
about 97% coverage and the coverage of the search algorithms is decent. All tests pass.

## Error in constraint09.dat solution ##

The provided solution (constraint09_solution.txt) to constraint09.dat is incorrect. We will show this by 
providing two tables below. The first table shows the total weights for the items in each bag. This table 
shows that all bags are at least 90% full (by weight) and that the items contained do not exceed the maximum 
weight.

	 p (max: 32) | q (max: 25) | r (max: 20) | x (max: 14)
	-------------------------------------------------------
	     D  3    |      C  6   |      H 12   |      E  5
	     J 19    |      G 15   |      I  7   |      F  8
	     L  9    |      K  4   |             |
	 total: 31   | total: 25   | total: 19   | total: 13

The second table shows each constraint, its value, and whether or not it is broken (and why). As shown in 
the second table, the second binary mutual exclusive constraint (I H q r) is broken because both I and H are 
in r. Due to this broken constraint, the provided solution to constraint09.dat is incorrect.

	 Constraint Name         | Value   | Outcome | Why?
	=========================+=========+=========+===================================================
	 fit-limit               | 2 3     | pass    | All bags have between 2 (inc.) and 3 (inc.) items
	-------------------------+---------+---------+---------------------------------------------------
	 unary inclusive         | C q r   | pass    | C is in q
	                         | D p q   | pass    | D is in p
	                         | H q r x | pass    | H is in r
	                         | K p q   | pass    | K is in q
	-------------------------+---------+---------+---------------------------------------------------
	 unary exclusive         | E p q r | pass    | E is in x (not p, q or r)
	                         | F q     | pass    | F is in x (not q)
	                         | G p r   | pass    | G is in q (not p or r)
	                         | I x     | pass    | I is in r (not x)
	-------------------------+---------+---------+---------------------------------------------------
	 binary equals           | L J     | pass    | L and J are in p
	                         | I H     | pass    | I and H are in r
	-------------------------+---------+---------+---------------------------------------------------
	 binary not equals       | D K     | pass    | D is in p, K is in q
	                         | I E     | pass    | I is in r, E is in x
	                         | H F     | pass    | H is in r, F is in x
	-------------------------+---------+---------+---------------------------------------------------
	 binary mutual exclusive | D K p r | pass    | D is in p, K is in q (not p or r)
	                         | I H q r | fail    | !!! Both I and H are in r !!!                     <--- ***
	                         | L F q x | pass    | L is in p (not q or x), F is in x
