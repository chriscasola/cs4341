package edu.wpi.cs.cs4341.project2;

/**
 * Represents an Item.
 */
public class Item {
	
	/** The uppercase letter representing the item. */
	protected final char id;
	
	/** The weight of the item. */
	protected final int weight;
	
	/** The bag that the item is assigned to. */
	protected Bag assignedBag;
	
	/**
	 * Constructs an Item.
	 * 
	 * @param id		An uppercase letter that represents the Item.
	 * @param weight	The Item's weight.
	 */
	public Item(char id, int weight) {
		if (id < 65 || id > 90) {
			throw new RuntimeException("The id must be an uppercase letter.");
		}
		
		this.id = id;
		this.weight = weight;
	}
	
	/**
	 * Getter for the assignedBag.
	 * 
	 * @return	The currently assignedBag.
	 */
	public Bag getAssignedBag() {
		return assignedBag;
	}
	
	/**
	 * Getter for the Item's id.
	 * 
	 * @return A char representing the Item's id.
	 */
	public char getId() {
		return id;
	}
	
	/**
	 * Getter for the Item's weight.
	 * 
	 * @return An int representing the Item's weight.
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Assigns a Bag to this Item.
	 * 
	 * @param bag	The Bag to assign to this Item.
	 */
	public void setAssignedBag(Bag bag) {
		if (assignedBag != null && bag != null) {
			throw new RuntimeException("Attempt to overwrite bag assignment detected.");
		}
		assignedBag = bag;
	}
	
	/**
	 * Determines whether or not an instance of an Item is equal to a given Object.
	 * 
	 * @param object	The Object to compare the Item to.
	 * 
	 * @return	True if the given Object is equal to the Item. False otherwise.
	 */
	@Override
	public boolean equals(Object object) {
		// compare instances
		if (this == object) {
			return true;
		}
		
		// if the Object is and instance of Item
		if (object instanceof Item) {
			Item item = (Item) object;
			
			// check that the values of the toString function are equal
			if (id == item.id && weight == item.weight) {
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
	 * Returns a String representation of the Item instance.
	 * 
	 * @return A String representation of the Item instance.
	 */
	@Override
	public String toString() {
		return id + " " + weight;
	}
	
	/**
	 * Returns a new Item created from the given String.
	 * 
	 * @param itemString	The String to build an Item from.
	 * 
	 * @return	An Item created using the given String.
	 */
	public static Item fromString(String itemString) {
		String[] parameters = itemString.split(" ");
		
		// Ensure there are only two parameters in the String
		if (parameters.length != 2) {
			throw new RuntimeException("The given String must only contain two parameters.");
		}
		// Ensure the first parameter is 1 character long
		else if (parameters[0].length() != 1) {
			throw new RuntimeException("The first parameter contained in the given String must only be one character long.");
		}
		else {
			char id = parameters[0].charAt(0);
			int weight = Integer.parseInt(parameters[1]);
			
			return new Item(id, weight);
		}
	}
}
