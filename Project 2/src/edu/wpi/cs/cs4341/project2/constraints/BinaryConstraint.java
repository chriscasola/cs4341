package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Bag;
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
		if (item1 == null) {
			throw new NullPointerException("The parameter item1 must not be null.");
		}
		if (item2 == null) {
			throw new NullPointerException("The parameter item2 must not be null.");
		}
		
		this.item1 = item1;
		this.item2 = item2;
	}
	
	/**
	 * @return an array containing the items involved in this constraint
	 */
	public Item[] getItems() {
		return new Item[]{item1, item2};
	}
	
	@Override
	public boolean hasItem(Item item) {
		if (item1.equals(item) || item2.equals(item)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean hasBag(Bag bag) {
		return false;
	}
}
