package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.JournaledBoard;

/**
 * Manages searching.
 */
public class SearchManager {
	
	/** The JournaledBoard representing past moves. */
	protected final JournaledBoard jboard;
	
	/** The Tree containing potential Moves to make. */
	protected final Tree tree;
	
	protected int timeToMove;
	
	/** The current depth that the graph has been searched. */
	protected int currentDepth;
	
	/**
	 * Constructs a SearchManager.
	 * 
	 * TODO: update this comment
	 * 
	 * @param timeToMove
	 * @param height
	 * @param width
	 * @param N
	 * @param firstMove
	 */
	protected SearchManager(int timeToMove, int height, int width, int N, Move firstMove) {
		this.timeToMove = timeToMove;
		this.tree = new Tree(new Node(firstMove, null));
		this.jboard = new JournaledBoard(height, width, N);
		this.currentDepth = 0;
	}
	
	/**
	 * Make a Move in the given column number.
	 * 
	 * @param colNum	An integer representing a column number.
	 */
	public void makeMove(int colNum) {
		tree.makeMove(colNum);
	}
	
	/**
	 * Finds, performs in the Tree, and returns the optimal Move.
	 * 
	 * @return	An integer representing the column in which the optimal Move takes place.
	 */
	public int findMaxMove() {
		
		// should check for time elapsed
		int count = 10;
		while (count-- > 0) {
			TreeWorkerIDDFS mainWorker = new TreeWorkerIDDFS(jboard.duplicate(), tree.getHead(), currentDepth + 5, currentDepth, new BasicHeuristicFunction(), new BasicUtilityFunction());
			mainWorker.run();
			currentDepth += 5;
		}
		
		Node bestChild = null;
		
		for (Node child : tree.getHead().getChildren()) {
			if (bestChild == null) {
				bestChild = child;
			}
			else {
				if (bestChild.getUtility() < child.getUtility()) {
					bestChild = child;
				}
			}
		}
		
		tree.makeMove(bestChild.getMove());
		currentDepth--;
		
		return bestChild.getMove().getColumn();
	}
}
