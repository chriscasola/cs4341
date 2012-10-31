package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.JournaledBoard;

public class TreeWorkerIDDFS extends TreeWorkerDLS {
	
	protected int maxDepth;
	
	protected int depthVisited;
	
	
	/**
	 * Constructs a new TreeWorkerIDDFS with the given starting node and journaled board
	 * @param startNode the node to start building at
	 * @param jboard the journaled board to track moves (and possibly already containing some moves)
	 */
	public TreeWorkerIDDFS(Node startNode, JournaledBoard jboard, int maxDepth, int depthVisited) {
		this.startNode = startNode;
		this.jboard = jboard;
		this.utilityFunction = new BasicUtilityFunction();
		this.heuristicFunction = new BasicHeuristicFunction();
	}
	
	@Override
	public void run() {
		buildTree();
	}
	
	/**
	 * Builds part of the tree, starting at the given node and 
	 * @param startNode
	 * @return
	 */
	protected Node buildTree() {
		
		Node result = null;
		
		for (int i = depthVisited + 1; i <= maxDepth; i++) {
			result = super.buildTree(startNode, i);
		}
		
		if (result == null) {
			throw new NullPointerException("Result is inexplicably null");
		}
		else {
			return result;
		}
	}
}
