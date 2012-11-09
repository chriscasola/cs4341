package edu.wpi.cs.cs4341.project2.constraints;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;

public class TestUnaryConstraint {
	private Item item;
	private Bag[] bags;
	
	@Before
	public void setUp() {
		item = new Item('A', 10);
		bags = new Bag[3];
		bags[0] = new Bag('a', 80);
		bags[1] = new Bag('b', 80);
		bags[2] = new Bag('c', 80);
	}
	
	@Test
	public void testConstructorExceptions() {
		try {
			DummyUnaryConstraint constraint = new DummyUnaryConstraint(null, bags);
			fail("Exception not thrown.");
		}
		catch (NullPointerException e) {
			assertTrue(e.getMessage().equals("The parameter item must not be null."));
		}
		
		try {
			DummyUnaryConstraint constraint = new DummyUnaryConstraint(item, null);
			fail("Exception not thrown.");
		}
		catch (NullPointerException e) {
			assertTrue(e.getMessage().equals("The parameter bags must not be null."));
		}
		
		try {
			DummyUnaryConstraint constraint = new DummyUnaryConstraint(item, new Bag[]{null});
			fail("Exception not thrown.");
		}
		catch (NullPointerException e) {
			assertTrue(e.getMessage().equals("The parameter bags is null at index 0."));
		}
	}
	
	@Test
	public void testHasBag() {
		DummyUnaryConstraint constraint = new DummyUnaryConstraint(item, bags);
		
		assertTrue(constraint.hasBag(bags[0]));
		assertTrue(constraint.hasBag(bags[1]));
		assertTrue(constraint.hasBag(bags[2]));
		assertFalse(constraint.hasBag(new Bag('z', 80)));
	}
	
	@Test
	public void testHasItem() {
		DummyUnaryConstraint constraint = new DummyUnaryConstraint(item, bags);
		
		assertTrue(constraint.hasItem(item));
		assertFalse(constraint.hasItem(new Item('Z', 10)));
	}
}
