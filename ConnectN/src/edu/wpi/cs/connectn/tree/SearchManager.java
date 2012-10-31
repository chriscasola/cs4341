package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.JournaledBoard;

public class SearchManager {
	
	protected final JournaledBoard jboard;
	
	protected final Tree tree;
	
	protected int timeToMove;
	
	protected int currentDepth;
	
	protected SearchManager(int timeToMove, int height, int width, int N, Move firstMove) {
		this.timeToMove = timeToMove;
		this.tree = new Tree(new Node(firstMove, null));
		this.jboard = new JournaledBoard(height, width, N);
		this.currentDepth = 0;
	}
	
	public void makeMove(int colNum) {
		tree.makeMove(colNum);
	}
	
	public int findMaxMove() {
		
		// should check for time elapsed
		int count = 10;
		while (count-- > 0) {
			TreeWorkerIDDFS mainWorker = new TreeWorkerIDDFS(jboard.duplicate(), tree.getHead(), currentDepth + 5, currentDepth, new BasicHeuristicFunction(), new BasicUtilityFunction());
			mainWorker.run();
			currentDepth += 5;
		}
		
		Node bestChild = null;
		
		for (Node child : tree.getHead().getChildren()) {
			if (bestChild == null) {
				bestChild = child;
			}
			else {
				if (bestChild.getUtility() < child.getUtility()) {
					bestChild = child;
				}
			}
		}
		
		tree.makeMove(bestChild.getMove());
		currentDepth--;
		
		return bestChild.getMove().getColumn();
	}
	
	
}
