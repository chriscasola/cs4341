package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.GameState;

public class BasicUtilityFunction {

	public static float calcUtility(Node node, GameState nodeState) {
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
