package edu.wpi.cs.connectn.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a Node in the Tree.
 */
public class Node {
	
	/** A list of children of this node */
	protected List<Node> children;
	
	/** The move this node represents */
	protected final Move move;
	
	/** The utility of being at this node (or game state) */
	protected float utility;
	
	/** The parent node of this node */
	protected Node parent;
	
	/** The depth of this node in the tree */
	protected final int depth;
	
	/**
	 * Constructs a new Node with the given Move, parent Node, utility, and depth.
	 * 
	 * @param move		The Move that this Node will represent.
	 * @param parent	The parent Node of this Node.
	 * @param utility	The utility of this Node.
	 * @param depth		The absolute depth of this Node in the Tree.
	 */
	public Node(Move move, Node parent, int utility, int depth) {
		this.move = move;
		this.parent = parent;
		this.utility = utility;
		this.children = new ArrayList<Node>();
		this.depth = depth;
	}

	/**
	 * Constructs a new Node with the given Move and parent Node.
	 * 
	 * @param move		The move that this Node will represent.
	 * @param parent	The parent Node of this Node.
	 */
	public Node(Move move, Node parent) {
		this(move, parent, 0);
	}
	
	/**
	 * Constructs a new Node with the given Move and parent Node.
	 * 
	 * @param move		The Move that this Node represents.
	 * @param parent	The parent Node of this Node. May be null.
	 * @param utility	The utility of this Node.
	 */
	public Node(Move move, Node parent, int utility) {
		this(move, parent, utility, 0);
	}
	
	/**
	 * Adds a child Node to this Node.
	 * 
	 * @param child		The child Node to add to this Node.
	 */
	public void addChild(Node child) {
		this.children.add(child);
	}
	
	/**
	 * Returns a String representation of this Node.
	 * 
	 * @return A String representing this Node.
	 */
	public String toString() {
		String retVal = "";
		retVal += "Move: Column " + move.getColumn() + " Owner " + move.getOwner() + "; Depth: " + depth + "\n";
		for (int i = 0; i < children.size(); i++) {
			retVal += children.get(i).toString();
		}
		return retVal;
	}
	
	public String stringifyGraph() {
		String retVal = "";
		retVal += move.getOwner() + "(" + move.getColumn() + ", " + utility + ")\n";
		for (int i = 0; i < children.size(); i++) {
			for (int j = 0; j < children.get(i).depth; j++)
				retVal += "\t";
			retVal += children.get(i).stringifyGraph();
		}
		return retVal;
	}
	
	/**
	 * Sets the Node's parent to the given Node.
	 * 
	 * @param parent	The new parent Node.
	 */
	protected void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * Sets the utility of the Node to the given utility.
	 * 
	 * @param utility	The new utility.
	 */
	public void setUtility(float utility) {
		this.utility = utility;
	}
	
	/**
	 * Returns the child Node that has a Move in the given colNum.
	 * 
	 * @param colNum	A column number.
	 * 
	 * @return The child Node which has a move in the given column.
	 * 
	 * @throws RuntimeException		If no child node exists with the given column number.
	 */
	public Node getChild(int colNum) throws RuntimeException {
		Iterator<Node> cIterator = children.iterator();
		Node childNode;
		
		while (cIterator.hasNext()) {
			childNode = cIterator.next();
			if (childNode != null && childNode.getMove().getColumn() == colNum) {
				return childNode;
			}
		}
		
		throw new RuntimeException("No child node exists with the given column number.");
	}
	
	/**
	 * Returns a List<Node> containing the children of this node.
	 * 
	 * @return A List<Node> containing the children of this node.
	 */
	public List<Node> getChildren() {
		return children;
	}
	
	/**
	 * Returns an integer representing the absolute depth of the Node.
	 * 
	 * @return An integer representing the depth of the Node.
	 */
	public int getDepth() {
		return depth;
	}
	
	/**
	 * Returns the Move belonging to the Node.
	 * 
	 * @return The Move
	 */
	public Move getMove() {
		return move;
	}

	/**
	 * Gets the Node's parent Node.
	 * 
	 * @return The parent Node.
	 */
	public Node getParent() {
		return parent;
	}
	
	/**
	 * Gets the Node's utility.
	 * 
	 * @return A float representing the utility of the Node.
	 */
	public float getUtility() {
		return utility;
	}
}
