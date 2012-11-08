package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;

/**
 * An interface for constraints.
 */
public interface Constraint {
	public enum Satisfaction {
		COMPLETE, PARTIAL, NONE, BROKEN
	}
	
	/**
	 * Returns true if the constraint is satisfied, false otherwise.
	 * 
	 * @return True if the constraint is satisfied, false otherwise.
	 */
	public Satisfaction satisfied();
	
	/**
	 * Returns true if the item is involved in this constraint, otherwise false
	 * @param item the item to check
	 * @return true if the item is involved in this constraint, otherwise false
	 */
	public boolean hasItem(Item item);
	
	/**
	 * Returns true if the bag is involved in this constraint, otherwise false
	 * @param bag the bag to check
	 * @return true if the bag is involved in this constraint, otherwise false
	 */
	public boolean hasBag(Bag bag);
}
