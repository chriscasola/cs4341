package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.Board;
import edu.wpi.cs.connectn.board.Minimax;

public class AnotherHeuristicFunction implements HeuristicFunction {

	enum Direction {
		DOWN, DOWNRIGHT, DOWNLEFT, LEFT, RIGHT;
	}

	@Override
	public float calcHeuristic(Board board, Node node) {
		Integer result = getNumAdjacent(board, node.move);
		System.out.println(result.floatValue());
		return result.floatValue();
	}

	public Integer getNumAdjacent(Board board, Move move) {
		Integer count = 1;
		int col = move.getColumn();
		int row = board.getColumn(col).getNumPieces() - 1;
		int tempCol = col;
		int tempRow = row;

		// check left
		tempCol = getNewCol(tempCol, Direction.LEFT);
		while (tempCol >= 0) {
			if (board.getColumn(tempCol).getPiece(tempRow) == move.getOwner()) {
				count++;
			}
			else if (board.getColumn(tempCol).getPiece(tempRow) == Minimax.EMPTY) {
				count++;
			}
			else {
				break;
			}
			tempCol = getNewCol(tempCol, Direction.LEFT);
		}

		// check down left
		tempCol = col;
		tempRow = row;
		tempCol = getNewCol(tempCol, Direction.DOWNLEFT);
		tempRow = getNewRow(tempRow, Direction.DOWNLEFT);
		while ((tempCol >= 0) && (tempRow >= 0)) {
			if (board.getColumn(tempCol).getPiece(tempRow) == move.getOwner()) {
				count++;
			}
			else if (board.getColumn(tempCol).getPiece(tempRow) == Minimax.EMPTY) {
				count++;
			}
			else {
				break;
			}
			tempCol = getNewCol(tempCol, Direction.DOWNLEFT);
			tempRow = getNewRow(tempRow, Direction.DOWNLEFT);
		}

		// check down
		tempCol = col;
		tempRow = row;
		tempRow = getNewRow(tempRow, Direction.DOWN);
		while (tempRow >= 0) {
			if (board.getColumn(tempCol).getPiece(tempRow) == move.getOwner()) {
				count++;
			}
			else if (board.getColumn(tempCol).getPiece(tempRow) == Minimax.EMPTY) {
				count++;
			}
			else {
				break;
			}
			tempRow = getNewRow(tempRow, Direction.DOWN);
		}

		// check down right
		tempCol = col;
		tempRow = row;
		tempCol = getNewCol(tempCol, Direction.DOWNRIGHT);
		tempRow = getNewRow(tempRow, Direction.DOWNRIGHT);
		while ((tempCol < board.getWidth()) && (tempRow >= 0)) {
			if (board.getColumn(tempCol).getPiece(tempRow) == move.getOwner()) {
				count++;
			}
			else if (board.getColumn(tempCol).getPiece(tempRow) == Minimax.EMPTY) {
				count++;
			}
			else {
				break;
			}
			tempCol = getNewCol(tempCol, Direction.DOWNRIGHT);
			tempRow = getNewRow(tempRow, Direction.DOWNRIGHT);
		}

		// check right
		tempCol = col;
		tempRow = row;
		tempCol = getNewCol(tempCol, Direction.RIGHT);
		while (tempCol < board.getWidth()) {
			if (board.getColumn(tempCol).getPiece(tempRow) == move.getOwner()) {
				count++;
			}
			else if (board.getColumn(tempCol).getPiece(tempRow) == Minimax.EMPTY) {
				count++;
			}
			else {
				break;
			}
			tempCol = getNewCol(tempCol, Direction.RIGHT);
		}

		return count;
	}

	protected int getNewCol(int oldCol, Direction direction) {
		switch (direction) {
		case DOWN:
			return oldCol;
		case DOWNLEFT:
			return oldCol - 1;
		case DOWNRIGHT:
			return oldCol + 1;
		case LEFT:
			return oldCol - 1;
		case RIGHT:
			return oldCol + 1;
		default:
			return oldCol;
		}
	}

	protected int getNewRow(int oldRow, Direction direction) {
		switch (direction) {
		case DOWN:
			return oldRow - 1;
		case DOWNLEFT:
			return oldRow - 1;
		case DOWNRIGHT:
			return oldRow - 1;
		case LEFT:
			return oldRow;
		case RIGHT:
			return oldRow;
		default:
			return oldRow;
		}
	}
}
