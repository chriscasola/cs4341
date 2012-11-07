package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Item;

/**
 * Represents a BinaryConstraint.
 */
public abstract class BinaryConstraint implements Constraint {
	protected Item item1;
	protected Item item2;
	
	/**
	 * Constructs a new BinaryConstraint.
	 * 
	 * @param item1		One item involved with this constraint.
	 * @param item2		Another item involved with this constraint.
	 */
	public BinaryConstraint(Item item1, Item item2) {
		this.item1 = item1;
		this.item2 = item2;
	}
}
