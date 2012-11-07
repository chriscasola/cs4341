package edu.wpi.cs.cs4341.project2;

/**
 * Represents a Bag.
 */
public class Bag {
	/** The lowercase letter representing the item. */
	protected final char id;
	
	/** The weight capacity of the Bag. */
	protected final int weightCapacity;
	
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
		if (object instanceof Bag) {
			Bag bag = (Bag) object;
			
			// check that the values of the bags are equal
			if (id == bag.getId() && weightCapacity == bag.getWeightCapacity()) {
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
		
		return returnValue;
	}
	
	/**
	 * Returns a new Bag created from the given String.
	 * 
	 * @param bagString		The String to build a Bag from.
	 * 
	 * @return	An Bag created using the information from the given String.
	 */
	public static Bag fromString(String bagString) {
		
		String[] bagParameters = bagString.split(" ");
		
		// Ensure there are only two parameters in the String
		if (bagParameters.length != 2) {
			throw new RuntimeException("The given String must only contain two parameters.");
		}
		// Ensure the first parameter is 1 character long
		else if (bagParameters[0].length() != 1) {
			throw new RuntimeException("The first parameter contained in the given String must only be one character long.");
		}
		
		char id = bagParameters[0].charAt(0);
		int weightCapacity = Integer.parseInt(bagParameters[1]);
		
		return new Bag(id, weightCapacity);
	}
}
