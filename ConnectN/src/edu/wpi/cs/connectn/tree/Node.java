package edu.wpi.cs.connectn.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a tree node
 *
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
	 * Constructs a new Node with the given Move, parent node, utility, and depth
	 * @param move the move this node will represent
	 * @param parent the parent of this node
	 * @param utility the utility of being at the state represented by this node
	 * @param depth the depth in the tree of this node
	 */
	public Node(Move move, Node parent, int utility, int depth) {
		this.move = move;
		this.parent = parent;
		this.utility = utility;
		this.children = new ArrayList<Node>();
		this.depth = depth;
	}

	/**
	 * Constructs a new Node with the given Move and parent Node
	 * @param move the move this node will represent
	 * @param parent the parent of this node
	 */
	public Node(Move move, Node parent) {
		this(move, parent, 0);
	}
	
	/**
	 * Constructs a new Node with the given Move and parent Node
	 * @param move the move this node will represent
	 * @param parent the parent of this node
	 * @param utility the utility of being at this node (or game state)
	 */
	public Node(Move move, Node parent, int utility) {
		this(move, parent, utility, 0);
	}
	
	/**
	 * Adds a child to this node. Each child will be inserted into the list
	 * of children in order, based on the column the child represents.
	 * @param child the child to add to this node
	 */
	public void addChild(Node child) {
		this.children.add(child);
	}
	
	public String toString() {
		String retVal = "";
		retVal += "Move: Column " + move.getColumn() + " Owner " + move.getOwner() + "; Depth: " + depth + "\n";
		for (int i = 0; i < children.size(); i++) {
			retVal += children.get(i).toString();
		}
		return retVal;
	}
	
	/**
	 * Returns the child representing the given column
	 * @param colNum the column
	 * @return the child representing the given column
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
	 * Returns a list containing the children of this node
	 * @return a list containing the children of this node
	 */
	public List<Node> getChildren() {
		return children;
	}
	
	/**
	 * @return the utility
	 */
	public float getUtility() {
		return utility;
	}

	/**
	 * @param utility the utility to set
	 */
	public void setUtility(float utility) {
		this.utility = utility;
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
	 * @return the move
	 */
	public Move getMove() {
		return move;
	}

	/**
	 * @return the parent
	 */
	public Node getParent() {
		return parent;
	}
	
	/**
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}
}
