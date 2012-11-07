package edu.wpi.cs.cs4341.project2.constraints;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;
import edu.wpi.cs.cs4341.project2.constraints.WeightConstraint;


public class testWeightConstraint {
	
	protected WeightConstraint weightConstraint;
	protected Item[] items;
	protected Bag bag;

	@Before
	public void setUp() throws Exception {
		bag = new Bag('a', 10);
		items = new Item[5];
	}

	@Test
	public void test() {
		
		// Create items and add them to the item array
		Item item1 = new Item('D', 2);
		Item item2 = new Item('E', 6);
		Item item3 = new Item('G', 1);
		Item item4 = new Item('F', 1);
		Item item5 = new Item('H', 1);
		items[0] = item1;
		items[1] = item2;
		items[2] = item3;
		items[3] = item4;
		items[4] = item5;
		

		weightConstraint = new WeightConstraint(bag, items);
		
		// Ensure the constaint is none (there are not items in the bag)
		assertTrue(weightConstraint.satisfied() == Satisfaction.NONE);
		
		// Add an item
		item1.setAssignedBag(bag);
		assertTrue(weightConstraint.satisfied() == Satisfaction.PARTIAL);
		
		// Add another item
		item2.setAssignedBag(bag);
		assertTrue(weightConstraint.satisfied() == Satisfaction.PARTIAL);
		
		// Add another item
		item3.setAssignedBag(bag);
		assertTrue(weightConstraint.satisfied() == Satisfaction.COMPLETE);
		
		// Add another item
		item4.setAssignedBag(bag);
		assertTrue(weightConstraint.satisfied() == Satisfaction.COMPLETE);
		
		// Add another item
		item5.setAssignedBag(bag);
		assertTrue(weightConstraint.satisfied() == Satisfaction.BROKEN);
	}

}
