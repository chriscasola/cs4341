package edu.wpi.cs.cs4341.project2.backtrack;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.Constraint;

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
	public BacktrackSearch() {
		this.items = new ArrayList<Item>();
		this.constraints = new ArrayList<Constraint>();
		this.headNode = new Node(null, null, null);
	}
	
	/**
	 * Performs the Backtrack search
	 * @param startNode the node to start at
	 * @return true if a solution has been found, false otherwise
	 */
	protected boolean BackTrack(Node startNode) {
		if (items.size() == 0) {
			return true;
		}
		Item currItem = selectUnassignedItem();
		for (Bag bag : bags) {
			Node currNode = new Node(bag, currItem, startNode);
			startNode.addChild(currNode);
			currNode.apply();
			items.remove(currItem);
			if (verifyConstraints()) {
				if (BackTrack(currNode)) {
					return true;
				}
			}
			items.add(currNode.revert()); // constraints violated, revert the assignment
		}
		return false; // failure
	}
	
	/**
	 * Verifies that no constraints are violated
	 * @return false if any constraints are violated, otherwise true
	 */
	protected boolean verifyConstraints() {
		for (Constraint constraint : constraints) {
			if (!(constraint.satisfied())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns an item that is not in a bag
	 * @return an item that is not in a bag
	 */
	protected Item selectUnassignedItem() {
		return items.get(0);
	}
}
