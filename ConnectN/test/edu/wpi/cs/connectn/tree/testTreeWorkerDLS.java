package edu.wpi.cs.connectn.tree;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

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
		Move firstMove = new Move(3, Minimax.MAX);
		jboard = new JournaledBoard(6, 7, 4);
		startNode = new Node(firstMove, null);
		treeWorker = new TreeWorkerDLS(jboard, startNode, new BasicHeuristicFunction(), new BasicUtilityFunction());
	}

	@Test
	public void test1() throws IOException {
		long startTime = System.currentTimeMillis();
		for (int i = 1; i < 6; i++)
			treeWorker.buildTree(startNode, i);
		System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime));
		System.out.flush();
	
		/*
		File outFile = new File("./output.txt");
		BufferedWriter output = new BufferedWriter(new FileWriter(outFile));
		output.write(startNode.stringifyGraph());
		output.close();
		*/
		System.out.println(startNode.stringifyGraph());
		System.out.flush();
		assertTrue(true);
	}

	@Test
	public void test2() {
		long startTime = System.currentTimeMillis();
		for (int i = 1; i < 6; i++)
			treeWorker.buildTree(startNode, i);
		System.out.println("Elapsed time: " + (System.currentTimeMillis() - startTime));
		System.out.flush();
		dfs(startNode);
	}
	
	public void dfs(Node currNode) {
		
		float expectedUtility = 0f;
		Iterator<Node> iter = currNode.getChildren().iterator();
		switch (currNode.move.getOwner()) {
		case MAX:
			if (iter.hasNext())
				expectedUtility = iter.next().getUtility();
			while (iter.hasNext()) {
				float currUtility = iter.next().getUtility();
				if (currUtility > expectedUtility)
					expectedUtility = currUtility;
			}
			break;
		case MIN:
			if (iter.hasNext())
				expectedUtility = iter.next().getUtility();
			while (iter.hasNext()) {
				float currUtility = iter.next().getUtility();
				if (currUtility < expectedUtility)
					expectedUtility = currUtility;
			}
			break;
		default:
			fail();
			break;
		}
		
		if ((expectedUtility != currNode.getUtility()) && (currNode.children.size() != 0))
			fail();
		
		for (Node node : currNode.getChildren()) {
			dfs(node);
		}
	}
}
