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
import edu.wpi.cs.cs4341.project2.constraints.NotEqualBinaryConstraint;

public class TestCGNode {
	
	protected Node node1;
	protected Node node2;
	protected Node node3;
	protected Node node4;
	protected DAItem item1;
	protected DAItem item2;
	protected DAItem item3;
	protected DAItem item4;
	protected Bag bag1, bag2;
	protected Constraint[] constraints;

	@Before
	public void setUp() throws Exception {
		bag1 = new Bag('a', 50);
		bag2 = new Bag('b', 80);
		List<Bag> bags = new ArrayList<Bag>();
		bags.add(bag1);
		bags.add(bag2);
		item1 = new DAItem('A', 5);
		item1.setDomain(bags);
		item2 = new DAItem('B', 2);
		item2.setDomain(bags);
		item3 = new DAItem('C', 4);
		item3.setDomain(bags);
		item4 = new DAItem('D', 7);
		item4.setDomain(bags);
		
		constraints = new Constraint[4];
		constraints[0] = new EqualBinaryConstraint(item1, item2);
		constraints[1] = new EqualBinaryConstraint(item2, item3);
		constraints[2] = new EqualBinaryConstraint(item3, item1);
		constraints[3] = new NotEqualBinaryConstraint(item4, item1);
		
		node1 = new Node(item1);
		node2 = new Node(item2);
		node3 = new Node(item3);
		node4 = new Node(item4);
	}

	@Test
	public void testUpdateNeighbors() {
		node1.updateNeighbors(constraints);
		node2.updateNeighbors(constraints);
		node3.updateNeighbors(constraints);
		node4.updateNeighbors(constraints);
		
		// check node 1
		assertEquals(3, node1.neighbors.keySet().size());
		assertTrue(node1.neighbors.containsKey(constraints[0]));
		assertTrue(node1.neighbors.containsKey(constraints[2]));
		assertTrue(node1.neighbors.containsKey(constraints[3]));
		assertEquals(item2, node1.neighbors.get(constraints[0]));
		assertEquals(item3, node1.neighbors.get(constraints[2]));
		assertEquals(item4, node1.neighbors.get(constraints[3]));
		
		// check node 2
		assertEquals(2, node2.neighbors.keySet().size());
		assertTrue(node2.neighbors.containsKey(constraints[0]));
		assertTrue(node2.neighbors.containsKey(constraints[1]));
		assertEquals(item1, node2.neighbors.get(constraints[0]));
		assertEquals(item3, node2.neighbors.get(constraints[1]));
		
		// check node 3
		assertEquals(2, node3.neighbors.keySet().size());
		assertTrue(node3.neighbors.containsKey(constraints[1]));
		assertTrue(node3.neighbors.containsKey(constraints[2]));
		assertEquals(item2, node3.neighbors.get(constraints[1]));
		assertEquals(item1, node3.neighbors.get(constraints[2]));
		
		// check node 4
		assertEquals(1, node4.neighbors.keySet().size());
		assertTrue(node1.neighbors.containsKey(constraints[3]));
		assertEquals(item1, node4.neighbors.get(constraints[3]));
	}

}
