package edu.wpi.cs.connectn.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.connectn.board.JournaledBoard;
import edu.wpi.cs.connectn.board.Minimax;

public class testTreeWorkerDLSWithPruning {
	
	protected TreeWorkerDLS treeWorkerDLS;
	protected JournaledBoard jboardDLS;
	protected Node startNodeDLS;
	
	protected TreeWorkerDLSWithPruning treeWorkerDLSPruning;
	protected JournaledBoard jboardDLSPruning;
	protected Node startNodeDLSPruning;

	@Before
	public void setUp() throws Exception {
		Move firstMove = new Move(1, Minimax.MAX);
		jboardDLS = new JournaledBoard(3, 3, 2);
		startNodeDLS = new Node(firstMove, null);
		treeWorkerDLS = new TreeWorkerDLS(jboardDLS, startNodeDLS, new AnotherHeuristicFunction(), new BasicUtilityFunction());
		
		Move firstMove2 = new Move(1, Minimax.MAX);
		jboardDLSPruning = new JournaledBoard(3, 3, 2);
		startNodeDLSPruning = new Node(firstMove2, null);
		treeWorkerDLSPruning = new TreeWorkerDLSWithPruning(jboardDLSPruning, startNodeDLSPruning, new AnotherHeuristicFunction(), new BasicUtilityFunction());
	}

	@Test
	public void test() {
		long startTime = System.currentTimeMillis();
		for (int i = 1; i < 5; i++)
			treeWorkerDLS.buildTree(startNodeDLS, i);
		System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime));
		System.out.flush();
		
		startTime = System.currentTimeMillis();
		for (int i = 1; i < 5; i++)
			treeWorkerDLSPruning.buildTree(startNodeDLSPruning, i);
		System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime));
		System.out.flush();
		
		Tree tree1 = new Tree(startNodeDLS);
		Tree tree2 = new Tree(startNodeDLSPruning);
		
		System.out.println(tree1.headNode.stringifyGraph());
		System.out.println("---------------------------------");
		System.out.println(tree2.headNode.stringifyGraph());
		
		assertEquals(tree1.getBestMove(), tree2.getBestMove());
	}

}
