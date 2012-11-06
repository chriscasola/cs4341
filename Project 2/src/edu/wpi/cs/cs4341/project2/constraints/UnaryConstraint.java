package edu.wpi.cs.cs4341.project2.constraints;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;

/**
 * An abstract class that represents a unary constraint.
 */
public abstract class UnaryConstraint implements Constraint {
	protected final Item item;
	protected List<Bag> bags;
	
	/**
	 * Constructs a UnaryConstraint.
	 * 
	 * @param item	The Item which the UnaryConstraint concerns.
	 */
	public UnaryConstraint(Item item) {
		this.item = item;
		bags = new ArrayList<Bag>();
	}
	
	/**
	 * Adds a given Bag to the bags which the UnaryConstraint concerns.
	 * 
	 * @param bag
	 */
	public void addBag(Bag bag) {
		bags.add(bag);
	}
	
	/**
	 * Getter for the Bags which the UnaryConstraint concerns.
	 * 
	 * @return Returns a List of Bags which the UnaryConstraint concerns.
	 */
	public List<Bag> getBags() {
		return bags;
	}
	
	/**
	 * Getter for the Item which the UnaryConstraint concerns.
	 * 
	 * @return	The Item which the UnaryConstraint concerns.
	 */
	public Item getItem() {
		return item;
	}
}
