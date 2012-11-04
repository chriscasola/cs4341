package edu.wpi.cs.cs4341.project2.backtrack;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.Constraint;

public class BacktrackSearch {
	
	/** List of items to be assigned */
	protected List<Item> items;
	
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
	}
	
	
}
