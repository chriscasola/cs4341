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
		Move firstMove = new Move(1, Minimax.MAX);
		jboard = new JournaledBoard(3, 3, 2);
		startNode = new Node(firstMove, null);
		treeWorker = new TreeWorkerDLS(jboard, startNode, new BasicHeuristicFunction(), new BasicUtilityFunction());
	}

	@Test
	public void test1() {
		treeWorker.buildTree(startNode, 1);
		treeWorker.buildTree(startNode, 2);
		treeWorker.buildTree(startNode, 3);
		treeWorker.buildTree(startNode, 4);
		treeWorker.buildTree(startNode, 5);
		treeWorker.buildTree(startNode, 6);
		treeWorker.buildTree(startNode, 7);
		treeWorker.buildTree(startNode, 8);
		treeWorker.buildTree(startNode, 9);
		System.out.println(startNode.stringifyGraph());
		assertTrue(true);
	}

}
