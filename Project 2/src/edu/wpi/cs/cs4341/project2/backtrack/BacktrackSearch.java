package edu.wpi.cs.cs4341.project2.backtrack;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.Constraint;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

public class BacktrackSearch {

	/** List of items to be assigned */
	protected List<Item> items;

	/** List of bags to put items in */
	protected List<Bag> bags;

	/** List of constraints to be met */
	protected List<Constraint> constraints;

	/** The head node of the search */
	protected Node headNode;

	/**
	 * Constructs a new BacktrackSearch
	 */
	public BacktrackSearch(List<? extends Item> items, List<Bag> bags, List<Constraint> constraints) {
		this.items = new ArrayList<Item>();
		this.bags = bags;
		this.constraints = constraints;
		this.headNode = new Node(null, null, null);
		
		// Make sure all paramters are valid
		if (items == null || bags == null || constraints == null || items.size() < 1 || bags.size() < 1) {
			throw new RuntimeException("Invalid arguments to the BacktrackSearch constructor!");
		}

		// Create a copy of the items List
		for (Item item : items) {
			this.items.add(item);
		}
	}

	/**
	 * Runs the backtrack search
	 * @return true if a solution is found, false otherwise
	 */
	public boolean run() {
		return BackTrack(headNode) ? true : false;
	}

	/**
	 * Performs the Backtrack search
	 * @param startNode the node to start at
	 * @return true if a solution has been found, false otherwise
	 */
	protected boolean BackTrack(Node startNode) {
		if (items.size() == 0) { // All the items have been assigned, verify constraints are met and return true
			if (verifyConstraintsComplete()) {
				return true;
			}
			else {
				return false;
			}
		}
		
		// Select an unassigned item (this may or may not use heuristics, heuristics
		// are used if the child class BacktrackSearchHeuristic is instantiated as the
		// child class overrides the basic selectUnassignedItem() method.
		Item currItem = selectUnassignedItem();
		orderBags(currItem); // sort the bags using some algorithm, same note as above applies
		for (Bag bag : bags) {
			Node currNode = new Node(bag, currItem, startNode); // construct a node in the tree representing the assignment
			startNode.addChild(currNode); // add the new node to the tree
			currNode.apply(); // do the assignment (put the item in the bag)
			items.remove(currItem); // remove the item from the list since it has been assigned
			if (verifyConstraints()) { 
				if (BackTrack(currNode)) { // constraints are not broken, do not backtrack
					return true;
				}
			}
			items.add(currNode.revert()); // constraints violated, revert the assignment and backtrack
		}
		return false; // failure, backtrack
	}

	/**
	 * Verifies that no constraints are violated
	 * @return false if any constraints are violated, otherwise true
	 */
	protected boolean verifyConstraints() {
		for (Constraint constraint : constraints) {
			if (constraint.satisfied() == Satisfaction.BROKEN) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Verifies that all constraints are COMPLETE
	 * @return true if all constraints are COMPLETE, otherwise false
	 */
	protected boolean verifyConstraintsComplete() {
		for (Constraint constraint : constraints) {
			if (constraint.satisfied() != Satisfaction.COMPLETE) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns an item that is not in a bag
	 * 
	 * A subclass can override this method so that a better
	 * algorithm for picking the next item to assign can be used
	 * 
	 * @return an item that is not in a bag
	 */
	protected Item selectUnassignedItem() {
		return items.remove(0);
	}
	
	/**
	 * Orders the bags based on a heuristic (except they are not ordered
	 * for the basic backtrack search, children of this class should implement
	 * a better algorithm).
	 */
	protected void orderBags(Item currItem) {
		// do nothing for basic backtrack search
	}
}
