package edu.wpi.cs.connectn.tree;

import java.util.ArrayList;
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
	protected int utility;
	
	/** The parent node of this node */
	protected final Node parent;
	
	/** The depth of this node in the tree */
	protected final int depth;
	
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
		this.children.add(child.move.getColumn(), child);
	}
	
	/**
	 * Returns the child representing the given column
	 * @param colNum the column
	 * @return the child representing the given column
	 */
	public Node getChild(int colNum) {
		return children.get(colNum);
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
	public int getUtility() {
		return utility;
	}

	/**
	 * @param utility the utility to set
	 */
	public void setUtility(int utility) {
		this.utility = utility;
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
