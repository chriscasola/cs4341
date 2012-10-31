package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.GameState;
import edu.wpi.cs.connectn.board.JournaledBoard;
import edu.wpi.cs.connectn.board.Minimax;

public class TreeWorkerDLS extends Thread {
	/** The JournaledBoard that this TreeWorker will use to keep track of changes to the board */
	protected JournaledBoard jboard;
	
	/** The node to start building the tree at */
	protected Node startNode;
	
	/** The Heuristic function to use */
	protected HeuristicFunction heuristicFunction;
	
	/** The Utility function to use */
	protected UtilityFunction utilityFunction;
	
	
	@Override
	public void run() {
		
	}
	
	protected Node buildTree(Node startNode, int depth) {
		Minimax nodeOwner = startNode.getMove().getOwner();
		jboard.doMove(startNode.getMove());
		/*
		GameState currState = jboard.checkForWin();
		
		switch (currState) {
		case DRAW:
			startNode.setUtility(utilityFunction.calcUtility(currNode, GameState.DRAW));
			return startNode;
		case MAX:
			startNode.setUtility(utilityFunction.calcUtility(currNode, GameState.MAX));
			return startNode;
		case MIN:
			startNode.setUtility(utilityFunction.calcUtility(currNode, GameState.MIN));
			return startNode;
		case IN_PROGRESS:
			for (int i = 0; i < jboard.getWidth(); i++) {
				if (!jboard.getColumn(i).isFull()) {
					Node newNode = new Node(new Move(i, childOwner), startNode, 0, startNode.getDepth() + 1);
					startNode.addChild(buildTree(newNode));
					jboard.revert();
				}
			}
			return currNode;	
		default:
			throw new RuntimeException("Check for win returned an unexpected value");
		}	
		
		
		
		
		*/
		
		return startNode;
	}
}
