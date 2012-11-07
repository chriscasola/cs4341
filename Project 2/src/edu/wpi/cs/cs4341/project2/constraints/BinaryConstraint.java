package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Item;

/**
 * Represents a BinaryConstraint.
 */
public abstract class BinaryConstraint implements Constraint {
	protected Item[] items;
	
	/**
	 * Constructs a new BinaryConstraint.
	 * 
	 * @param item1		One item involved with this constraint.
	 * @param item2		Another item involved with this constraint.
	 */
	public BinaryConstraint(Item item1, Item item2) {
		items = new Item[2];
		items[0] = item1;
		items[1] = item2;
	}
	
	/**
	 * Returns the Items involved in this constraint.
	 * 
	 * @return An array of Items involved in this constraint.
	 */
	public Item[] getItems() {
		return items;
	}
}
