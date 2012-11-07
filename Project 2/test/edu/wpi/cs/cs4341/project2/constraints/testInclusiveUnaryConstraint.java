package edu.wpi.cs.cs4341.project2.constraints;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

public class TestInclusiveUnaryConstraint {
	
	protected Bag[] bags;
	protected Bag bag1;
	protected Bag bag2;
	protected Bag bag3;
	protected Bag bag4;
	protected Item item1;
	protected InclusiveUnaryConstraint iuc1;

	@Before
	public void setUp() throws Exception {
		bag1 = new Bag('a', 10);
		bag2 = new Bag('b', 10);
		bag3 = new Bag('c', 10);
		bag4 = new Bag('d', 10);
		item1 = new Item('A', 3);
	}

	@Test
	public void test() {
		iuc1 = new InclusiveUnaryConstraint(item1, new Bag[]{bag2, bag4});
		assertTrue(iuc1.satisfied() == Satisfaction.NONE);
		
		item1.setAssignedBag(bag2);
		assertTrue(iuc1.satisfied() == Satisfaction.COMPLETE);
		
		item1.setAssignedBag(null);
		item1.setAssignedBag(bag1);
		assertTrue(iuc1.satisfied() == Satisfaction.BROKEN);
	}

}
