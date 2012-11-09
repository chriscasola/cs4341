package edu.wpi.cs.cs4341.project2;

import java.util.ArrayList;
import java.util.List;

/**
 * This item adds a domain aware capability to Items
 *
 */
public class DAItem extends Item {

	/** The possible bags this item can be assigned to */
	protected List<Bag> domain;
	
	/**
	 * Constructs a new DAItem
	 * @param id the id of this item
	 * @param weight the weight of this item
	 */
	public DAItem(char id, int weight) {
		super(id, weight);
	}

	/**
	 * @return the domain of this item (list of bags it can be assigned to)
	 */
	public List<Bag> getDomain() {
		return domain;
	}
	
	/**
	 * Sets the domain of this item to the given list of bags
	 * @param bags the new domain
	 */
	public void setDomain(List<Bag> bags) {
		domain = new ArrayList<Bag>();
		for (Bag bag : bags) {
			domain.add(bag);
		}
	}
	
	/**
	 * Returns a new Item created from the given String.
	 * 
	 * @param itemString	The String to build an Item from.
	 * 
	 * @return	An Item created using the given String.
	 */
	public static DAItem fromString(String itemString) {
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
			
			return new DAItem(id, weight);
		}
	}
}
