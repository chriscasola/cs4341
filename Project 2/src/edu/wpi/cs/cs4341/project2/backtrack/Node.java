package edu.wpi.cs.cs4341.project2.backtrack;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;

public class Node {

	/** The bag to which an item is being assigned to at this node */
	protected Bag bag;
	
	/** The item being assigned */
	protected Item item;
	
	/** The child of this node */
	protected List<Node> children;
	
	/** The parent of this node */
	protected Node parent;
	
	/** True when the assignment has been made */
	boolean applied;
	
	/**
	 * Constructs a new node representing the assignment of Item item to Bag bag
	 * @param bag the bag to assign to
	 * @param item the item to assign to bag
	 * @param parent the parent of this node
	 */
	public Node(Bag bag, Item item, Node parent) {
		this.bag = bag;
		this.item = item;
		this.parent = parent;
		this.applied = false;
		this.children = new ArrayList<Node>();
	}
	
	/**
	 * Places item in bag. Assignment will only occur once.
	 * @return true, if the assignment was successfully, false otherwise
	 */
	public void apply() {
		if (applied == false) {
			bag.addItem(item);
			applied = true;
		}
	}
	
	/**
	 * Reverts the assignment
	 */
	public void revert() {
		bag.removeItem(item);
		applied = false;
	}
	
	/**
	 * @return the children of this node
	 */
	public List<Node> getChildren() {
		return children;
	}
}
