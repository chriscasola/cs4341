package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Item;

public abstract class BinaryConstraint extends Constraint {
	protected Item[] items;
	
	public BinaryConstraint(Item item1, Item item2) {
		items = new Item[2];
		items[0] = item1;
		items[1] = item2;
	}
	
	//TODO
}
