package edu.wpi.cs.cs4341.project2.backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.BinaryConstraint;
import edu.wpi.cs.cs4341.project2.constraints.Constraint;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

/**
 * Adds the minimum remaining values and degree heuristics to
 * backtrack search.
 */
public class BacktrackSearchHeuristic extends BacktrackSearch {

	public BacktrackSearchHeuristic(List<? extends Item> items, List<Bag> bags, List<Constraint> constraints) {
		super(items, bags, constraints);
	}
	
	/**
	 * Uses the least constraining values algorithm to order the list
	 * of bags.
	 */
	@Override
	protected void orderBags(Item currItem) {
		Collections.sort(bags, new BagComparator(currItem));
	}
	
	/**
	 * Uses minimum remaining values algorithm to select the next
	 * bag to assign, breaking ties with the degree heuristic.
	 */
	@Override
	protected Item selectUnassignedItem() {
		Item bestItem = items.get(0); // start with the first item
		int bestNumBags = getNumPossibleBags(bestItem); // get size of the item's domain
		int currNumBags = bestNumBags;
		
		for (int i = 1; i < items.size(); i++) { // for each item, compare the size of it's domain with current best
			currNumBags = getNumPossibleBags(items.get(i)); // get domain of current bag
			if (currNumBags == bestNumBags) { // tie occurred, break it
				// use degree heuristic if necessary
				if (getConstraints(bestItem) < getConstraints(items.get(i))) { // compares number of constraints involving each item
					bestNumBags = currNumBags;
					bestItem = items.get(i);
				}
			}
			else if (currNumBags < bestNumBags) { // no tie, we have a new best
				bestNumBags = currNumBags;
				bestItem = items.get(i);
			}
		}
		return bestItem;
	}
	
	/**
	 * Determines how many bags the given item can be assigned to
	 * @param item the item to check
	 * @return the number of bags the given item can be assigned to
	 */
	protected int getNumPossibleBags(Item item) {
		int numBags = 0;
		for (Bag bag : bags) { // for each bag in item's domain
			item.setAssignedBag(bag); // temporarily put the item in the bag
			for (Constraint constraint : getConstraints(item, bag)) { // check if assignment is valid
				if (constraint.satisfied() == Satisfaction.BROKEN) {
					numBags--; // invalid assignment, subtract
					break;
				}
			}
			item.setAssignedBag(null); // remove item from bag
			numBags++; // increment num bags (this is counteracted in the loop if the assignment is invalid
		}
		return numBags;
	}
	
	/**
	 * Returns a list of constraints that the given item or bag are involved in
	 * @param item the item to check
	 * @param bag the bag to check for
	 * @return a list of constraints that the given item or bag are involved in
	 */
	protected List<Constraint> getConstraints(Item item, Bag bag) {
		List<Constraint> retVal = new ArrayList<Constraint>();
		for (Constraint constraint : constraints) {
			if (constraint.hasItem(item) || constraint.hasBag(bag)) { // check if the constraint has the item or bag
				retVal.add(constraint); // if yes, add it to the return list
			}
		}
		return retVal;
	}
	
	/**
	 * Returns the number of constraints the given item is involved in
	 * @param item the item to check
	 * @return the number of constraints the given item is involved in
	 */
	protected int getConstraints(Item item) {
		int numConstraints = 0;
		
		for (Constraint constraint : constraints) {
			if (constraint.hasItem(item)) { // check if the constraint has the item
				numConstraints++; // yes, so increment
			}
		}
		return numConstraints;
	}
	
	/**
	 * Returns a hashmap containing constraint item pairs that lead away
	 * from the given node in the constraint graph.
	 * @param item the item to evaluate
	 * @return a hashmap containing constraint item pairs that lead away
	 * from the given node in the constraint graph.
	 */
	protected HashMap<Constraint, Item> getConstGraphNeighbors(Item item) {
		// contains the "edges" in the constraint graph that are connected
		// to the given item
		HashMap<Constraint, Item> retVal = new HashMap<Constraint, Item>();
		
		Item[] constItems;
		for (Constraint constraint : constraints) {
			if (constraint instanceof BinaryConstraint) { // only concerned with binary constraints
				constItems = ((BinaryConstraint) constraint).getItems(); // get the items involved in the constraint
				if (constItems[0] == item) { // add an edge to the map
					retVal.put(constraint, constItems[1]);
				}
				else if (constItems[1] == item) {
					retVal.put(constraint, constItems[0]);
				}
			}
		}
		return retVal;
	}
}
