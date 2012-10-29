package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.GameState;

public interface UtilityFunction {

	public float calcUtility(Node node, GameState nodeState);
	
}
