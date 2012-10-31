package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.GameState;

/**
 * A basic utility function that calculates utility based on the state
 * of the game at the given node and the depth of the node in the tree.
 */
public class BasicUtilityFunction implements UtilityFunction {
	
	/**
	 * Calculates the utility of a given Node based on the GameState and the Node's depth.
	 * 
	 * @see UtilityFunction#calcUtility(Node, GameState)
	 */
	public float calcUtility(Node node, GameState nodeState) {
		switch (nodeState) {
		case DRAW:
			return 0f;
		case MAX:
			return 1.0f / node.getDepth();
		case MIN:
			return -1.0f / node.getDepth();
		default:
			throw new RuntimeException("Utility function can not be evaluated for a non-leaf node!");
		}
	}
}
