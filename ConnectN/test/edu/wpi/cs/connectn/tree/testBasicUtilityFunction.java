package edu.wpi.cs.connectn.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.connectn.board.GameState;
import edu.wpi.cs.connectn.board.Minimax;

public class testBasicUtilityFunction {
	
	Node n1;
	UtilityFunction f1;
	
	@Before
	public void setUp() throws Exception {
		n1 = new Node(new Move(2, Minimax.MIN), null, 4, 48);
		f1 = new BasicUtilityFunction();
	}

	@Test
	public void test() {
		assertTrue(100f == f1.calcUtility(n1, GameState.MAX));
		assertTrue(-100f == f1.calcUtility(n1, GameState.MIN));
		assertTrue(0f == f1.calcUtility(n1, GameState.DRAW));
		
		try {
			f1.calcUtility(n1, GameState.IN_PROGRESS);
			fail();
		}
		catch (RuntimeException e) {
			if (!e.getMessage().equals("Utility function can not be evaluated for a non-leaf node!")) {
				fail();
			}
		}
	}

}
