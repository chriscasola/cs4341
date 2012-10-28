package edu.wpi.cs.connectn.board;

/**
 * Represents a Connect-N game board
 * @author Chris
 *
 */
public class Board {
	
	/** N, the number of checkers in a row required to win */
	protected final int N;
	
	/** An array of the columns that make up this board */
	protected final Column[] columns;
	
	/** The height of the board */
	protected final int height;
	
	/** The width of the board */
	protected final int width;

	/**
	 * Constructs a new board with the given properties
	 * @param height the height of the board
	 * @param width the width of the board
	 * @param N the number of checkers in a row that are required to win
	 */
	public Board(int height, int width, int N) {

		// Set the height of each column
		this.height = height;

		// Set the width of the board
		this.width = width;

		// Set N (the number of checkers in a row required to win)
		this.N = N;

		// Instantiate columns
		columns = new Column[width];
		for (int i = 0; i < width; i++)
			columns[i] = new Column(height);
	}
	
	/**
	 * Checks if a player has won the game
	 * @return -1 if no player has won, 0 if it is a draw, 1 if black won, 2 if red won
	 */
	public int checkForWin() {
		
		// Check for a winner
		switch (checkAllRows()) {
		case MAX:
			return 1;
		case MIN:
			return 2;
		default:
			break;
		}
		
		switch (checkAllColumns()) {
		case MAX:
			return 1;
		case MIN:
			return 2;
		default:
			break;
		}
		
		switch (checkAllLRDiagonals()) {
		case MAX:
			return 1;
		case MIN:
			return 2;
		default:
			break;
		}
		
		switch (checkAllRLDiagonals()) {
		case MAX:
			return 1;
		case MIN:
			return 2;
		default:
			break;
		}
		
		// Check if the game board is full, in that case there is a draw
		int numMoves = 0;
		for (int i = 0; i < width; i++)
			numMoves += columns[i].numPieces;
		if (numMoves == width * height)
			return 0;
		
		return -1;
	}

	protected Minimax checkAllRows() {
		Minimax retVal = Minimax.EMPTY;

		// Check all rows
		for (int i = 0; i < height; i++) {
			retVal = checkHorizontal(i);
			if (retVal != Minimax.EMPTY)
				return retVal;
		}
		return retVal;
	}

	protected Minimax checkAllColumns() {
		Minimax retVal = Minimax.EMPTY;

		// Check all columns
		for (int i = 0; i < width; i++) {
			retVal = checkVertical(i);
			if (retVal != Minimax.EMPTY)
				return retVal;
		}
		return retVal;
	}

	protected Minimax checkAllLRDiagonals() {
		Minimax retVal = Minimax.EMPTY;

		// Check top-left to bottom-right diagonals
		for (int i = 1; i < height; i++) {
			retVal = checkLRDiagonal(i, 0);
			if (retVal != Minimax.EMPTY)
				return retVal;
		}
		for (int i = 1; i < width; i++) {
			retVal = checkLRDiagonal(height - 1, i);
			if (retVal != Minimax.EMPTY)
				return retVal;
		}
		return retVal;
	}

	protected Minimax checkAllRLDiagonals() {
		Minimax retVal = Minimax.EMPTY;

		// Check top-right to bottom-left diagonals
		for (int i = 1; i < width; i++) {
			retVal = checkRLDiagonal(height - 1, i);
			if (retVal != Minimax.EMPTY)
				return retVal;
		}
		for (int i = 1; i < height; i++) {
			retVal = checkRLDiagonal(i, width - 1);
			if (retVal != Minimax.EMPTY)
				return retVal;
		}
		return retVal;
	}

	protected Minimax checkRLDiagonal(int row, int col) {
		int numInARow = 0;
		Minimax lastColor = Minimax.EMPTY;

		while ((row >= 0) && (col >= 0)) {
			Minimax currChecker = columns[col].getPiece(row);

			if (currChecker == Minimax.EMPTY) {
				lastColor = currChecker;
				numInARow = 0;
			}
			else if (currChecker == lastColor) {
				if (++numInARow >= N) {
					return lastColor;
				}
			}
			else {
				lastColor = currChecker;
				numInARow = 1;
			}
			row--;
			col--;
		}

		return Minimax.EMPTY;
	}

	protected Minimax checkLRDiagonal(int row, int col) {
		int numInARow = 0;
		Minimax lastColor = Minimax.EMPTY;

		while ((row >= 0) && (col < width)) {
			Minimax currChecker = columns[col].getPiece(row);

			if (currChecker == Minimax.EMPTY) {
				lastColor = currChecker;
				numInARow = 0;
			}
			else if (currChecker == lastColor) {
				if (++numInARow >= N) {
					return lastColor;
				}
			}
			else {
				lastColor = currChecker;
				numInARow = 1;
			}
			row--;
			col++;
		}
		return Minimax.EMPTY;
	}

	protected Minimax checkHorizontal(int row) {
		int numInARow = 0;
		Minimax lastColor = Minimax.EMPTY;

		for (int i = 0; i < width; i++) {
			Minimax currChecker = columns[i].getPiece(row);

			if (currChecker == Minimax.EMPTY) {
				lastColor = currChecker;
				numInARow = 0;
			}
			else if (currChecker == lastColor) {
				if (++numInARow >= N) {
					return lastColor;
				}
			}
			else {
				lastColor = currChecker;
				numInARow = 1;
			}
		}
		return Minimax.EMPTY;
	}

	protected Minimax checkVertical(int col) {
		int numInARow = 0;
		Minimax lastColor = Minimax.EMPTY;
		Column currColumn = columns[col];

		for (int i = 0; i < currColumn.numPieces; i++) {
			Minimax currChecker = currColumn.getPiece(i);
			if (currChecker == lastColor) {
				if (++numInARow >= N) {
					return lastColor;
				}
			}
			else {
				lastColor = currChecker;
				numInARow = 1;
			}
		}
		return Minimax.EMPTY;
	}

	public boolean dropPiece(Minimax piece, int colNum) {
		if (colNum < columns.length) {
			return columns[colNum].dropPiece(piece);
		}
		else {
			return false;
		}
	}

	public String toString() {
		String retVal = "";
		for (int i = height - 1; i >= 0; i--) {
			for (int j = 0; j < width; j++) {
				switch (columns[j].getPiece(i)) {
				case MAX:
					retVal += "X";
					break;
				case EMPTY:
					retVal += "-";
					break;
				case MIN:
					retVal += "O";
					break;
				}
			}
			retVal += "\n";
		}
		return retVal;
	}
}