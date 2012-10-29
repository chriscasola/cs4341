package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.GameState;
import edu.wpi.cs.connectn.board.JournaledBoard;
import edu.wpi.cs.connectn.board.Minimax;

/**
 * A worker thread that builds a part of the minimax tree
 *
 */
public class TreeWorker extends Thread {

	/** The node to start building the tree at */
	protected Node startNode;
	
	/** The JournaledBoard that this TreeWorker will use to keep track of changes to the board */
	protected JournaledBoard jboard;
	
	/** The node currently being visited */
	protected Node currentNode;
	
	/** The Utility function to use */
	protected UtilityFunction utilityFunction;
	
	/**
	 * Constructs a new TreeWorker with the given starting node and journaled board
	 * @param startNode the node to start building at
	 * @param jboard the journaled board to track moves (and possibly already containing some moves)
	 */
	public TreeWorker(Node startNode, JournaledBoard jboard) {
		this.startNode = startNode;
		this.jboard = jboard;
		this.currentNode = startNode;
		this.utilityFunction = new BasicUtilityFunction();
	}
	
	@Override
	public void run() {
		buildTree(startNode);
	}
	
	protected Node buildTree(Node currNode) {
		Minimax childOwner = (currNode.getMove().getOwner() == Minimax.MAX) ? Minimax.MIN : Minimax.MAX;
		jboard.doMove(currNode.getMove());
		
		switch (jboard.checkForWin()) {
		case DRAW:
			currNode.setUtility(utilityFunction.calcUtility(currNode, GameState.DRAW));
			return currNode;
		case MAX:
			currNode.setUtility(utilityFunction.calcUtility(currNode, GameState.MAX));
			return currNode;
		case MIN:
			currNode.setUtility(utilityFunction.calcUtility(currNode, GameState.MIN));
			return currNode;
		case IN_PROGRESS:
			for (int i = 0; i < jboard.getWidth(); i++) {
				if (!jboard.getColumn(i).isFull()) {
					Node newNode = new Node(new Move(i, childOwner), currNode, 0, currNode.getDepth() + 1);
					jboard.revert(1);
					currNode.addChild(buildTree(newNode));
				}
			}
			return currNode;	
		default:
			throw new RuntimeException("Check for win returned an unexpected value");
		}		
	}
}
