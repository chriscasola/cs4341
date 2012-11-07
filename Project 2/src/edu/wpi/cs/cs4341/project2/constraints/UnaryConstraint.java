package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;

/**
 * An abstract class that represents a unary constraint.
 */
public abstract class UnaryConstraint implements Constraint {
	
	/** The item to which this constraint applies */
	protected final Item item;
	
	/** The bags to which this item is associated */
	protected Bag[] bags;
	
	/**
	 * Constructs a UnaryConstraint.
	 * 
	 * @param item the Item which the UnaryConstraint concerns.
	 * @param bags the bags to which this item is associated
	 */
	public UnaryConstraint(Item item, Bag[] bags) {
		this.item = item;
		this.bags = bags;
	}
}
