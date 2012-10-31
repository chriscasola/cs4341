package edu.wpi.cs.connectn.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.connectn.board.JournaledBoard;
import edu.wpi.cs.connectn.board.Minimax;

public class testTreeWorkerDLS {
	
	protected TreeWorkerDLS treeWorker;
	protected JournaledBoard jboard;
	protected Node startNode;
	

	@Before
	public void setUp() throws Exception {
		Move firstMove = new Move(4, Minimax.MAX);
		jboard = new JournaledBoard(8, 9, 6);
		startNode = new Node(firstMove, null);
		treeWorker = new TreeWorkerDLS(jboard, startNode, new BasicHeuristicFunction(), new BasicUtilityFunction());
	}

	@Test
	public void test1() {
		long startTime = System.currentTimeMillis();
		for (int i = 1; i < 9; i++)
			treeWorker.buildTree(startNode, i);
		System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime));
		System.out.flush();
		//System.out.println(startNode.stringifyGraph());
		//System.out.flush();
		assertTrue(true);
	}

}
