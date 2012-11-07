package edu.wpi.cs.cs4341.project2.constraints;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

public class TestCapacityConstraint {
	protected Bag bag;
	protected Item[] items;
	
	@Before
	public void setUp() {
		bag = new Bag('a', 80);
		items = new Item[4];
		items[0] = new Item('A', 10);
		items[1] = new Item('B', 10);
		items[2] = new Item('C', 10);
		items[3] = new Item('D', 10);
	}
	
	@Test
	public void testSatisfaction() {
		CapacityConstraint capacityConstraint = new CapacityConstraint(2, 3, bag, items);

		assertTrue(Satisfaction.NONE == capacityConstraint.satisfied());
		
		items[0].setAssignedBag(bag);
		assertTrue(Satisfaction.PARTIAL == capacityConstraint.satisfied());
		
		items[1].setAssignedBag(bag);
		assertTrue(Satisfaction.COMPLETE == capacityConstraint.satisfied());

		items[2].setAssignedBag(bag);
		assertTrue(Satisfaction.COMPLETE == capacityConstraint.satisfied());

		items[3].setAssignedBag(bag);
		assertTrue(Satisfaction.BROKEN == capacityConstraint.satisfied());
	}
}
