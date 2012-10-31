package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.Board;

/**
 * A basic heuristic function.
 */
public class BasicHeuristicFunction implements HeuristicFunction {

	/**
	 * Estimates the utility of the given node based on the column number and the depth of the node
	 * NOTE: This does not perform well, a very stupid algorithm
	 * 
	 * @see HeuristicFunction#calcHeuristic(Board, Node)
	 */
	@Override
	public float calcHeuristic(Board board, Node node) {
		return node.getMove().getColumn() + node.getDepth();
	}

	
}
