package edu.wpi.cs.cs4341.project2.backtrack;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.backtrack.BacktrackSearch;
import edu.wpi.cs.cs4341.project2.constraints.CapacityConstraint;
import edu.wpi.cs.cs4341.project2.constraints.Constraint;
import edu.wpi.cs.cs4341.project2.constraints.InclusiveUnaryConstraint;
import edu.wpi.cs.cs4341.project2.constraints.WeightConstraint;

public class TestBacktrackSearch {

	@Test
	public void testProblem1() {
		Item[] items;
		Bag bag1;
		Bag bag2;
		
		items = new Item[5];
		items[0] = new Item('A', 3);
		items[1] = new Item('B', 1);
		items[2] = new Item('C', 7);
		items[3] = new Item('D', 4);
		items[4] = new Item('E', 9);
		bag1 = new Bag('a', 30);
		bag2 = new Bag('b', 30);
		
		InclusiveUnaryConstraint uic1 = new InclusiveUnaryConstraint(items[1], new Bag[]{bag2});
		InclusiveUnaryConstraint uic2 = new InclusiveUnaryConstraint(items[3], new Bag[]{bag1});
		
		ArrayList<Constraint> constraints = new ArrayList<Constraint>();
		constraints.add(uic1);
		constraints.add(uic2);
		
		ArrayList<Bag> bags = new ArrayList<Bag>();
		bags.add(bag1);
		bags.add(bag2);
		
		ArrayList<Item> arrItems = new ArrayList<Item>();
		for (int i = 0; i < items.length; i++) {
			arrItems.add(items[i]);
		}
		
		BacktrackSearch bts = new BacktrackSearch(arrItems, bags, constraints);
		assertTrue(bts.run());
		
		// Validate that constraints were met
		assertTrue(items[1].getAssignedBag() == bag2);
		assertTrue(items[3].getAssignedBag() == bag1);
		
		/*
		for (int i = 0; i < items.length; i++) {
			System.out.println(items[i].getId() + " is in bag " + items[i].getAssignedBag().getId());
		}
		System.out.println("END_________________\n\n");*/
	}

	@Test
	public void testProblem2() {
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(new Item('C', 6));
		items.add(new Item('D', 3));
		items.add(new Item('E', 5));
		items.add(new Item('F', 8));
		items.add(new Item('G', 15));
		items.add(new Item('H', 12));
		items.add(new Item('I', 7));
		items.add(new Item('J', 19));
		items.add(new Item('K', 4));
		items.add(new Item('L', 9));
		Item[] arrItems = new Item[10];
		arrItems = items.toArray(arrItems);
		
		Bag bagP = new Bag('p', 40);
		Bag bagQ = new Bag('q', 55);
		ArrayList<Bag> bags = new ArrayList<Bag>();
		bags.add(bagP);
		bags.add(bagQ);
		
		CapacityConstraint fl1 = new CapacityConstraint(5, 5, bagP, arrItems);
		CapacityConstraint fl2 = new CapacityConstraint(5, 5, bagQ, arrItems);
		WeightConstraint wc1 = new WeightConstraint(bagP, arrItems);
		WeightConstraint wc2 = new WeightConstraint(bagQ, arrItems);
		ArrayList<Constraint> constraints = new ArrayList<Constraint>();
		constraints.add(fl1);
		constraints.add(fl2);
		constraints.add(wc1);
		constraints.add(wc2);
		
		BacktrackSearch bts = new BacktrackSearch(items, bags, constraints);
		assertTrue(bts.run());
		
		// Get bag counts and weights
		int bagPCount = 0, bagQCount = 0, bagPWeight = 0, bagQWeight = 0;
		for (Item item : items) {
			if (item.getAssignedBag() == bagP) {
				bagPCount++;
				bagPWeight += item.getWeight();
			}
			else if (item.getAssignedBag() == bagQ) {
				bagQCount++;
				bagQWeight += item.getWeight();
			}
			else {
				fail("An item was not assigned to a valid bag");
			}
		}
		
		// Validate that capacity constraint was met
		if (!(bagPCount == 5 && bagQCount == 5)) {
			fail("Capacity constraint not met!");
		}
		
		// Validate WeightConstraint was met
		assertTrue((bagPWeight * 1f) / 40 >= .9);
		assertTrue((bagQWeight * 1f) / 55 >= .9);
		assertTrue(bagPWeight <= 40);
		assertTrue(bagQWeight <= 55);
		
		/*
		for (Item item : items) {
			System.out.println(item.getId() + " is in bag " + item.getAssignedBag().getId());
		}
		System.out.println("END_________________\n\n");*/
	}
}
