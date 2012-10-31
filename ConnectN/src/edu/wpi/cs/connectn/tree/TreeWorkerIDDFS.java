package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.JournaledBoard;

/**
 * Performs an Iterative Deepening Depth First Search on a given Node.
 */
public class TreeWorkerIDDFS extends TreeWorkerDLS {
	
	/** The maximum depth to search to. */
	protected int maxDepth;
	
	/** The maximum depth already visited. */
	protected int depthVisited;
	
	
	/**
	 * Constructs a new TreeWorkerIDDFS with the given starting Node and JournaledBoard.
	 * 
	 * @param startNode		The Node to start the search at.
	 * @param jboard		The JournaledBoard used to track moves (may already contain some moves).
	 * @param maxDepth		The maximum depth to search to.
	 * @param depthVisited	The maximum depth already visited.
	 */
	public TreeWorkerIDDFS(JournaledBoard jboard, Node startNode, int maxDepth, int depthVisited, HeuristicFunction heuristicFunction, UtilityFunction utilityFunction) {
		super(jboard, startNode, heuristicFunction, utilityFunction);
		this.maxDepth = maxDepth;
		this.depthVisited = depthVisited;
	}
	
	/**
	 * @see Thread#run()
	 */
	@Override
	public void run() {
		buildTree();
	}
	
	/**
	 * Performs an Iterative Deepening Depth First Search by performing a series of Depth Limited Searches 
	 * at depths from depthVisited+1 through maxDepth.
	 * 
	 * @return	The Node at the maxDepth that is most likely to be chosen after maxDepth moves.
	 */
	protected Node buildTree() {
		
		Node result = null;
		
		for (int i = depthVisited + 1; i <= maxDepth; i++) {
			result = super.buildTree(startNode, i);
		}
		
		//if (result == null) {
			//throw new NullPointerException("Result is inexplicably null");
		//}
		//else {
			return result;
		//}
	}
}
