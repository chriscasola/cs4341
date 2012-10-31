package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.Board;

/**
 * An interface for HeuristicFunctions
 *
 */
public interface HeuristicFunction {
	
	/**
	 * Uses the heuristic function to estimate the utility of the given node
	 * @param board the current game board state
	 * @param node the node to estimate
	 * @return an estimate of the utility of the given node
	 */
	public float calcHeuristic(Board board, Node node);
}
