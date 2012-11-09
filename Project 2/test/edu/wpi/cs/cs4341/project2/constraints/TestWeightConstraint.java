package edu.wpi.cs.cs4341.project2.constraints;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;
import edu.wpi.cs.cs4341.project2.constraints.WeightConstraint;


public class TestWeightConstraint {

	protected WeightConstraint weightConstraint;
	protected Item[] items;
	protected Bag bag;

	@Before
	public void setUp() throws Exception {
		bag = new Bag('a', 10);
		items = new Item[5];
		// Create items and add them to the item array
		items[0] = new Item('D', 2);
		items[1] = new Item('E', 6);
		items[2] = new Item('G', 1);
		items[3] = new Item('F', 1);
		items[4] = new Item('H', 1);
	}

	@Test
	public void testConstructorExceptions() {
		try {
			WeightConstraint constraint = new WeightConstraint(null, items);
			fail("Exception not thrown.");
		}
		catch (NullPointerException e) {
			e.getMessage().equals("The parameter bag must not be null.");
		}

		try {
			WeightConstraint constraint = new WeightConstraint(bag, null);
			fail("Exception not thrown.");
		}
		catch (NullPointerException e) {
			e.getMessage().equals("The parameter items must not be null.");
		}

		try {
			WeightConstraint constraint = new WeightConstraint(bag, new Item[] {null});
			fail("Exception not thrown.");
		}
		catch (NullPointerException e) {
			e.getMessage().equals("The parameter items is null at index 0.");
		}
	}

	@Test
	public void testHasBag() {
		WeightConstraint constraint = new WeightConstraint(bag, items);

		assertTrue(constraint.hasBag(bag));
		assertFalse(constraint.hasBag(new Bag('a', 80)));
	}
	
	@Test
	public void testHasItem() {
		WeightConstraint constraint = new WeightConstraint(bag, items);

		assertTrue(constraint.hasItem(items[0]));
		assertTrue(constraint.hasItem(items[1]));
		assertTrue(constraint.hasItem(items[2]));
		assertTrue(constraint.hasItem(items[3]));
		assertTrue(constraint.hasItem(items[4]));
		assertFalse(constraint.hasItem(new Item('A', 10)));
	}

	@Test
	public void test() {
		weightConstraint = new WeightConstraint(bag, items);

		// Ensure the constaint is none (there are not items in the bag)
		assertTrue(weightConstraint.satisfied() == Satisfaction.NONE);

		// Add an item
		items[0].setAssignedBag(bag);
		assertTrue(weightConstraint.satisfied() == Satisfaction.PARTIAL);

		// Add another item
		items[1].setAssignedBag(bag);
		assertTrue(weightConstraint.satisfied() == Satisfaction.PARTIAL);

		// Add another item
		items[2].setAssignedBag(bag);
		assertTrue(weightConstraint.satisfied() == Satisfaction.COMPLETE);

		// Add another item
		items[3].setAssignedBag(bag);
		assertTrue(weightConstraint.satisfied() == Satisfaction.COMPLETE);

		// Add another item
		items[4].setAssignedBag(bag);
		assertTrue(weightConstraint.satisfied() == Satisfaction.BROKEN);
	}

}
