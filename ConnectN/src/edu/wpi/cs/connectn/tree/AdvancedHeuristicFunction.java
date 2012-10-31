package edu.wpi.cs.connectn.tree;

import java.util.HashMap;

import edu.wpi.cs.connectn.board.Board;
import edu.wpi.cs.connectn.board.Minimax;

public class AdvancedHeuristicFunction implements HeuristicFunction {
	
	enum Direction {
		DOWN, DOWNRIGHT, RIGHT, UPRIGHT, UP;
	}
	
	protected int[] counts;
	
	public AdvancedHeuristicFunction(int N) {
		counts = new int[N];
		for (int i = 0; i < N; i++) {
			counts[i] = 0;
		}
	}

	@Override
	public float calcHeuristic(Board board, Node node) {
		countCheckers(0, board.getHeight() - 1, Direction.DOWN, board);
		countCheckers(0, board.getHeight() - 1, Direction.DOWNRIGHT, board);
		countCheckers(0, board.getHeight() - 1, Direction.RIGHT, board);
		countCheckers(0, board.getHeight() - 1, Direction.UPRIGHT, board);
		
		float result = 0f;
	
		for (int i = 2; i < board.getN(); i++) {
			if (counts[i] > 0) {
				counts[i] = 1;
			}
			else if (counts[i] < 0) {
				counts[i] = -1;
			}
			
			result += counts[i] * (i / (board.getN() ^ 2)) * 2;
		}
		
		return result;
	}

	protected void countCheckers(int col, int row, Direction direction, Board board) {
		Integer numCheckers = 0;
		int count = 0;
		
		// Make sure col and row are within the board
		if (!((col < board.getWidth()) && ((row > 0) && (row < board.getHeight())))) {
			return;
		}
		
		Minimax startPiece = board.getColumn(col).getPiece(row); // get the current piece

		// Look N pieces ahead and count checkers
		while ((col < board.getWidth()) && ((row > 0) && (row < board.getHeight()))) {
			Minimax currPiece = board.getColumn(col).getPiece(row);
			if (currPiece == startPiece) {
				numCheckers++;
			}
			else if (currPiece == Minimax.EMPTY) {
				// do nothing
			}
			else {
				// hit a piece different from start piece
				numCheckers = 0;
				break;
			}
			count++;
			if (count < board.getN()) {
				col = getNewCol(col, direction);
				row = getNewRow(row, direction);
			}
			else {
				break;
			}
		}
		
		// Store the count
		if (numCheckers > 1 && numCheckers < board.getN()) {
			if (startPiece == Minimax.MAX) {
				counts[numCheckers] += numCheckers;
			}
			else {
				counts[numCheckers] -= numCheckers;
			}
		}
		
		// Fork children
		countCheckers(col, row, Direction.DOWN, board);
		countCheckers(col, row, Direction.DOWNRIGHT, board);
		countCheckers(col, row, Direction.RIGHT, board);
		countCheckers(col, row, Direction.UPRIGHT, board);
		if (direction == Direction.UPRIGHT){
			countCheckers(col, row, Direction.UP, board);
		}
		
	}
	
	protected int getNewCol(int oldCol, Direction direction) {
		switch (direction) {
		case DOWN:
			return oldCol;
		default:
			return oldCol + 1;
		}
	}
	
	protected int getNewRow(int oldRow, Direction direction) {
		switch (direction) {
		case DOWN:
			return oldRow - 1;
		case DOWNRIGHT:
			return oldRow - 1;
		case UPRIGHT:
			return oldRow + 1;
		case UP:
			return oldRow + 1;
		default:
			return oldRow;
		}
	}
}
