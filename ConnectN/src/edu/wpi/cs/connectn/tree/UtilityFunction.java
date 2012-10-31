package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.GameState;

/**
 * An interface for UtilityFunctions
 *
 */
public interface UtilityFunction {

	/**
	 * Calculates the utility of the given node
	 * @param node the node to evaluate
	 * @param nodeState the state of the game at the given node
	 * @return the utility of the given node
	 */
	public float calcUtility(Node node, GameState nodeState);
	
}
