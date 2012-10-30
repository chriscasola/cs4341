package edu.wpi.cs.connectn.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.connectn.board.JournaledBoard;
import edu.wpi.cs.connectn.board.Minimax;

public class testTreeWorker {
	
	TreeWorker tw1;
	Node n1;
	JournaledBoard j1;

	@Before
	public void setUp() throws Exception {
		j1 = new JournaledBoard(2, 2, 2);		
		n1 = new Node(new Move(1, Minimax.MAX), null, 0, 0);
		tw1 = new TreeWorker(n1, j1);
	}

	@Test
	public void test() {
		tw1.run();
		
		String expected = "Move: Column 1 Owner MAX; Depth: 0\n"
						+ "Move: Column 0 Owner MIN; Depth: 1\n"
						+ "Move: Column 0 Owner MAX; Depth: 2\n"
						+ "Move: Column 1 Owner MAX; Depth: 2\n"
						+ "Move: Column 1 Owner MIN; Depth: 1\n"
						+ "Move: Column 0 Owner MAX; Depth: 2\n";

		assertTrue(expected.equals(n1.toString()));
	}

}
