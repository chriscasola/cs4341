package edu.wpi.cs.cs4341.project2.backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.Constraint;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

/**
 * Adds the minimum remaining values and degree heuristics to
 * backtrack search.
 */
public class BacktrackSearchHeuristic extends BacktrackSearch {

	public BacktrackSearchHeuristic(List<Item> items, List<Bag> bags, List<Constraint> constraints) {
		super(items, bags, constraints);
	}
	
	@Override
	protected void orderBags() {
		Collections.sort(items, new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				// TODO implement this comparator to sort bags by order of least constraining
				return 0;
			}
		});
	}
	
	
	@Override
	protected Item selectUnassignedItem() {
		Item bestItem = items.get(0);
		int bestNumBags = getNumPossibleBags(bestItem);
		int currNumBags = bestNumBags;
		
		for (int i = 1; i < items.size(); i++) {
			currNumBags = getNumPossibleBags(items.get(i));
			if (currNumBags == bestNumBags) {
				// use degree heuristic if necessary
				if (getConstraints(bestItem) < getConstraints(items.get(i))) {
					bestNumBags = currNumBags;
					bestItem = items.get(i);
				}
			}
			else if (currNumBags < bestNumBags) {
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
		for (Bag bag : bags) {
			item.setAssignedBag(bag);
			for (Constraint constraint : getConstraints(item, bag)) {
				if (constraint.satisfied() == Satisfaction.BROKEN) {
					numBags--;
					break;
				}
			}
			item.setAssignedBag(null);
			numBags++;
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
			if (constraint.hasItem(item) || constraint.hasBag(bag)) {
				retVal.add(constraint);
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
			if (constraint.hasItem(item)) {
				numConstraints++;
			}
		}
		return numConstraints;
	}
}
