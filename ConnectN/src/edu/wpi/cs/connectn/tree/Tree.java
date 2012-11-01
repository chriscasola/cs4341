package edu.wpi.cs.connectn.tree;

import java.util.Iterator;

/**
 * Keeps track of a tree.
 */
public class Tree {
	protected Node headNode;
	
	/**
	 * Constructor.
	 * 
	 * @param headNode	The head Node.
	 */
	public Tree(Node headNode) {
		this.headNode = headNode;
	}
	
	/**
	 * Makes a move in the given Move's column number.
	 * 
	 * @see makeMove(int)
	 * 
	 * @param move	The move to make.
	 */
	public void makeMove(Move move) {
		makeMove(move.getColumn());
	}
	
	/**
	 * Makes a move in the given column number by changing the head Node to a child of the head Node.
	 * 
	 * @param colNum	The number of the column to make a move in.
	 */
	public void makeMove(int colNum) {
		headNode = headNode.getChild(colNum);
		headNode.setParent(null);
	}
	
	/**
	 * Sets the Tree's headNode to the given Node.
	 * 
	 * @param headNode	The new headNode.
	 */
	public void setHeadNode(Node headNode) {
		this.headNode = headNode;
	}
	
	/**
	 * Returns the Tree's head Node.
	 * 
	 * @return the Tree's head Node.
	 */
	public Node getHead() {
		return headNode;
	}
	
	/**
	 * Returns the next best Move (in terms of utility).
	 * 
	 * @return A Move representing the next best Move (in terms of utility).
	 */
	public Move getBestMove() {
		Iterator<Node> iter = headNode.children.iterator();
		while (iter.hasNext()) {
			Node currNode = iter.next();
			if (currNode.utility == headNode.utility) {
				return currNode.getMove();
			}
		}
		throw new RuntimeException();
	}
}
