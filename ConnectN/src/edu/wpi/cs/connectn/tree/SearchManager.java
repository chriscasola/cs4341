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

	/** The current depth that the graph has been searched. */
	protected int currentDepth;

	public SearchManager(int boardHeight, int boardWidth, int numToWin, Move firstMove) {
		this.tree = new Tree(new Node(firstMove, null));
		this.jboard = new JournaledBoard(boardHeight, boardWidth, numToWin);
		this.currentDepth = 0;
	}

	/**
	 * Make a Move in the given column number.
	 * 
	 * @param move	The Move to make.
	 */
	public void makeMove(Move move) {
		jboard.doMove(move);
		tree.makeMove(move.getColumn());
		currentDepth--;
	}
	
	/**
	 * Performs a search to a maximum of the given depth.
	 * 
	 * @param depth		The maximum depth to search to.
	 */
	public void searchToDepth(int depth) {
		if (depth > currentDepth) {
			TreeWorkerIDDFS mainWorker = new TreeWorkerIDDFS(jboard.duplicate(), tree.getHead(), depth, currentDepth, new AnotherHeuristicFunction(), new BasicUtilityFunction());
			mainWorker.run();
			currentDepth = depth;
		}
	}

	/**
	 * Gets the next best Move.
	 * 
	 * @return	A Move representing the best Move.
	 */
	public Move getBestMove() {
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

		return bestChild.getMove();
	}
}
