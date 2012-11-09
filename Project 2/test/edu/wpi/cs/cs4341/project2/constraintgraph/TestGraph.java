package edu.wpi.cs.cs4341.project2.constraintgraph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.DAItem;
import edu.wpi.cs.cs4341.project2.constraints.Constraint;
import edu.wpi.cs.cs4341.project2.constraints.EqualBinaryConstraint;

public class TestGraph {
	
	protected List<Bag> bags;
	protected DAItem[] items;
	protected Constraint constraint;
	protected Graph graph;

	@Before
	public void setUp() throws Exception {
		bags = null;
		items = null;
		constraint = null;
		graph = null;
	}

	@Test
	public void test() {
		bags = new ArrayList<Bag>();
		Bag bagA = new Bag('a', 20);
		bags.add(bagA);
		bags.add(new Bag('b', 20));
		bags.add(new Bag('c', 20));
		items = new DAItem[2];
		items[1] = new DAItem('B', 10);
		items[1].setDomain(bags);
		Bag bagD = new Bag('d', 20); 
		bags.add(bagD);
		items[0] = new DAItem('A', 5);
		items[0].setDomain(bags);
		constraint = new EqualBinaryConstraint(items[0], items[1]);
		
		graph = new Graph(items, bags, new Constraint[]{constraint});
		assertTrue(graph.AC3());
		
		assertEquals(3, items[0].getDomain().size());
		assertEquals(3, items[1].getDomain().size());
		assertFalse(items[0].getDomain().contains(bagD));
		assertTrue(items[0].getDomain().contains(bagA));
	}

}
