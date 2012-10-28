package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.JournaledBoard;

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
	
	/**
	 * Constructs a new TreeWorker with the given starting node and journaled board
	 * @param startNode the node to start building at
	 * @param jboard the journaled board to track moves (and possibly already containing some moves)
	 */
	public TreeWorker(Node startNode, JournaledBoard jboard) {
		this.startNode = startNode;
		this.jboard = jboard;
		this.currentNode = startNode;
	}
	
	@Override
	public void run() {
		
	}
	
	
}
