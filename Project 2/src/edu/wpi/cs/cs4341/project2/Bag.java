package edu.wpi.cs.cs4341.project2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a Bag.
 */
public class Bag {
	/** The lowercase letter representing the item. */
	protected final char id;
	
	/** The weight capacity of the Bag. */
	protected final int weightCapacity;
	
	/** A list of Items in the bag. */
	protected List<Item> items;
	
	/**
	 * Constructs a new empty Bag.
	 * 
	 * @param id				A lowercase letter that represents the Bag.
	 * @param weightCapacity	The maximum weightCapacity of the Bag.
	 */
	public Bag(char id, int weightCapacity) {
		if (id < 97 || id > 122) {
			throw new RuntimeException("The id be an lowercase letter.");
		}
		
		this.id = id;
		this.weightCapacity = weightCapacity;
		items = new ArrayList<Item>();
	}
	
	/**
	 * Adds the given Item to the Bag.
	 * 
	 * @param item	The Item to add to the Bag.
	 */
	public void addItem(Item item) {
		items.add(item);
	}
	
	/**
	 * Getter for the Bag's id.
	 * 
	 * @return A lowercase letter.
	 */
	public char getId() {
		return id;
	}
	
	/**
	 * Getter for the Items in the Bag.
	 * 
	 * @return Returns a List of Items that are in the Bag.
	 */
	public List<Item> getItems() {
		return items;
	}
	
	/**
	 * Returns the weight capacity of the Bag.
	 * 
	 * @return The weight capacity of the Bag.
	 */
	public int getWeightCapacity() {
		return weightCapacity;
	}
	
	/**
	 * Determines whether or not an instance of a Bag is equal to a given Object.
	 * 
	 * @param object	The Object to compare the Bag to.
	 * 
	 * @return	True if the given Object is equal to the Bag. False otherwise.
	 */
	@Override
	public boolean equals(Object object) {
		// compare instances
		if (this == object) {
			return true;
		}
		
		// if the Object is and instance of Bag
		if (object instanceof Item) {
			Bag bag = (Bag) object;
			
			// check that the values of the toString function are equal
			if (this.toString().equals(bag.toString())) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
				
	}
	
	/**
	 * Returns a String representation of the Bag instance.
	 * 
	 * @return A String representation of the Bag instance.
	 */
	@Override
	public String toString() {
		String returnValue = id + " " + weightCapacity;
		
		// Iterate over items
		Iterator<Item> iItems = items.iterator();
		
		// Add first Item to the return value
		if (iItems.hasNext()) {
			returnValue += "|" + iItems.next().toString();
		}
		
		// Add the rest of the Items to the return value
		while (iItems.hasNext()) {
			returnValue += "|" + iItems.next().toString();
		}
		
		return returnValue;
	}
}
