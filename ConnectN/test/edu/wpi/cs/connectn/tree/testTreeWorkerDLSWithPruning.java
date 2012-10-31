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
	
	protected TreeWorkerDLS treeWorkerDLSPruning;
	protected JournaledBoard jboardDLSPruning;
	protected Node startNodeDLSPruning;

	@Before
	public void setUp() throws Exception {
		Move firstMove = new Move(3, Minimax.MAX);
		jboardDLS = new JournaledBoard(6, 7, 4);
		startNodeDLS = new Node(firstMove, null);
		treeWorkerDLS = new TreeWorkerDLS(jboardDLS, startNodeDLS, new BasicHeuristicFunction(), new BasicUtilityFunction());
		
		Move firstMove2 = new Move(3, Minimax.MAX);
		jboardDLSPruning = new JournaledBoard(6, 7, 4);
		startNodeDLSPruning = new Node(firstMove2, null);
		treeWorkerDLSPruning = new TreeWorkerDLS(jboardDLSPruning, startNodeDLSPruning, new BasicHeuristicFunction(), new BasicUtilityFunction());
	}

	@Test
	public void test() {
		long startTime = System.currentTimeMillis();
		for (int i = 1; i < 6; i++)
			treeWorkerDLS.buildTree(startNodeDLS, i);
		System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime));
		System.out.flush();
		
		startTime = System.currentTimeMillis();
		for (int i = 1; i < 6; i++)
			treeWorkerDLSPruning.buildTree(startNodeDLSPruning, i);
		System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime));
		System.out.flush();
		
		Tree tree1 = new Tree(startNodeDLS);
		Tree tree2 = new Tree(startNodeDLSPruning);
		
		assertEquals(tree1.getBestMove(), tree2.getBestMove());
	}

}
