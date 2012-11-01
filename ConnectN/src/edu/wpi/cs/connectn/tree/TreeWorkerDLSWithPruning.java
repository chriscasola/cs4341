package edu.wpi.cs.connectn.tree;

import java.util.Iterator;

import edu.wpi.cs.connectn.board.GameState;
import edu.wpi.cs.connectn.board.JournaledBoard;
import edu.wpi.cs.connectn.board.Minimax;

public class TreeWorkerDLSWithPruning extends Thread {
	/** The JournaledBoard that this TreeWorker will use to keep track of changes to the board */
	protected JournaledBoard jboard;
	
	/** The node to start building the tree at */
	protected Node startNode;
	
	/** The Heuristic function to use */
	protected HeuristicFunction heuristicFunction;
	
	/** The Utility function to use */
	protected UtilityFunction utilityFunction;
	
	/**
	 * Constructs a new TreeWorkerDLS
	 * @param jboard the JournaledBoard
	 * @param startNode the node to start the search at
	 * @param heuristicFunction the heuristic function to utilize
	 * @param utilityFunction the utility function to utilize
	 */
	public TreeWorkerDLSWithPruning(JournaledBoard jboard, Node startNode, HeuristicFunction heuristicFunction, UtilityFunction utilityFunction) {
		this.jboard = jboard;
		this.startNode = startNode;
		this.heuristicFunction = heuristicFunction;
		this.utilityFunction = utilityFunction;
	}
	
	/**
	 * Performs a Depth Limited Search.
	 * 
	 * @param startNode		The Node to start at.
	 * @param depth			The depth to search to.
	 * @return	The Node that will most likely be visited in depth moves.
	 */
	protected Node buildTree(Node startNode, int depth) {
		Minimax nodeOwner = startNode.getMove().getOwner();
		Node bestNode = null;
		jboard.doMove(startNode.getMove());
		
		// If the depth is 0, calculate the utility value of the startNode
		if (depth == 0) {
			GameState currState = jboard.checkForWin();
			
			// If the game is over at the startNode, use the utility function to calculate its utility
			if (currState != null && currState != GameState.IN_PROGRESS) {
				startNode.setUtility(utilityFunction.calcUtility(startNode, currState));
				bestNode = startNode;
			}
			// If the game is still in progress at the startNode, use the heuristic function to calculate its utility 
			else if (currState == GameState.IN_PROGRESS) {
				startNode.setUtility(heuristicFunction.calcHeuristic(jboard, startNode));
				bestNode = startNode;
			}
			// If the currState is an unexpected value, throw an exception
			else {
				throw new RuntimeException("Check for win returned an unexpected value");
			}
		}
		// If depth is 1, create child Nodes and update the bestNode
		else if (depth == 1) {
			for (int i = 0; i < jboard.getWidth(); i++) {
				if (!jboard.getColumn(i).isFull()) {
					Node newNode = new Node(new Move(i, (nodeOwner == Minimax.MAX) ? Minimax.MIN : Minimax.MAX), startNode, 0, startNode.getDepth() + 1);
					startNode.addChild(newNode);
				}
			}
		}
		
		// If the depth is greater than 0, get the best bottom Node (for the current depth) and do Min/Max
		if (depth > 0) {
			Node bestNodeTemp;
			Node nextChildNode;
			Iterator<Node> cIterator = startNode.children.iterator();
			boolean firstChildVisited = false;
			
			// For all child nodes, recurse and do Min/Max
			while (cIterator.hasNext()) {
				// Get the next child Node
				nextChildNode = cIterator.next();
				
				// Get the bestNode for the nextChildNode via recursion
				bestNodeTemp = buildTree(nextChildNode, depth-1);
				//jboard.revert(); // revert jboard
				
				// Do Min/Max
				if (!firstChildVisited) {
					startNode.setUtility(nextChildNode.getUtility());
					firstChildVisited = true;
				}
				else if (startNode.getMove().getOwner() == Minimax.MAX && nextChildNode.getUtility() > startNode.getUtility()) {
					startNode.setUtility(nextChildNode.getUtility());
					bestNode = bestNodeTemp;
				}
				else if (startNode.getMove().getOwner() == Minimax.MIN && nextChildNode.getUtility() < startNode.getUtility()) {
					startNode.setUtility(nextChildNode.getUtility());
					bestNode = bestNodeTemp;
				}
				
				if (startNode.getParent() != null) {
					if ((startNode.getMove().getOwner() == Minimax.MAX) && (startNode.getUtility() > startNode.getParent().getUtility())) {
						startNode.clearChildren();
						break;
					}
				}
			}
		}

		jboard.revert(); // revert jboard
		return bestNode;
	}
}
