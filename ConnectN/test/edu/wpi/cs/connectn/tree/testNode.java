package edu.wpi.cs.connectn.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.connectn.board.Minimax;

public class testNode {

	protected Node n1, n2;
	
	@Before
	public void setUp() throws Exception {
		n1 = new Node(new Move(2, Minimax.MIN), null);
		n2 = new Node(new Move(4, Minimax.MAX), n1, 5);
	}

	@Test
	public void test() {
		assertNull(n1.parent);
		assertEquals(2, n1.move.getColumn());
		assertEquals(0, n1.utility);
		
		assertEquals(n1, n2.parent);
		assertEquals(5, n2.utility);
		assertEquals(Minimax.MAX, n2.move.getOwner());
	}

}
