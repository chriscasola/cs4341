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
		j1 = new JournaledBoard(5, 6, 3);		
		n1 = new Node(new Move(3, Minimax.MAX), null, 0, 0);
		tw1 = new TreeWorker(n1, j1);
	}

	@Test
	public void test() {
		tw1.run();
		assertTrue(true);
	}

}
