package edu.wpi.cs.connectn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.wpi.cs.connectn.board.Minimax;
import edu.wpi.cs.connectn.tree.Move;
import edu.wpi.cs.connectn.tree.Node;
import edu.wpi.cs.connectn.tree.SearchManager;

/**
 * @author Christopher Casola
 * @author Jennifer Page
 *
 */
public class Player {
	/** The name of the Player. */
	private static final String playerName = "the-masked-unit";
	
	/** The height of the board as defined by the referee. */
	private static int boardHeight;
	
	/** The width of the board as defined by the referee. */
	private static int boardWidth;
	
	/** The number of pieces that have to be connected to win. */
	private static int numToWin;
	
	/** Whether or not the player is going first. */
	private static boolean firstPlayer;
	
	/** The time limit in seconds for making a move. */
	private static int timeLimit;

	/**
	 * Main method. Communicates with agent and commands SearchManager.
	 * 
	 * @param args
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		SearchManager sm;
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		// Send Player name to referee
		System.out.println(playerName);
		System.out.flush();

		// Read the game information provided by the referee.
		String[] gameConfig = input.readLine().split(" ");
		boardHeight = Integer.parseInt(gameConfig[0]);
		boardWidth = Integer.parseInt(gameConfig[1]);
		numToWin = Integer.parseInt(gameConfig[2]);
		firstPlayer = (Integer.parseInt(gameConfig[3]) == 0) ? true : false;
		timeLimit = Integer.parseInt(gameConfig[4]);
		
		// If the Player is first, construct the SearchManager with the optimal first Move and tell it to 
		// search before printing the column of the firstMove
		if (firstPlayer) {
			Move firstMove = new Move(boardWidth/2, Minimax.MAX);
			sm = new SearchManager(boardHeight, boardWidth, numToWin, firstMove);
			sm.searchToDepth(5);
			System.out.println(firstMove.getColumn());
		}
		// If the Player is second, get the opponent's first move, construct the SearchManager and tell it to
		// search before printing the best move as determined by the Searchmanager
		else {
			Move firstMove = new Move(Integer.parseInt(input.readLine()), Minimax.MIN);
			sm = new SearchManager(boardHeight, boardWidth, numToWin, firstMove);
			sm.searchToDepth(5);
			Move myBestMove = sm.getBestMove();
			sm.makeMove(myBestMove);
			System.out.println(myBestMove.getColumn());
		}

		
		// Get the opponent's move, do some searching, and then end.
		int opponentMove;
		while(true) {
			opponentMove = Integer.parseInt(input.readLine());
			
			if (opponentMove < 0) {
				break;
			}
			
			sm.makeMove(new Move(opponentMove, Minimax.MIN));
			sm.searchToDepth(5);
			Move myMove = sm.getBestMove();
			sm.makeMove(myMove);
			System.out.println(myMove.getColumn());
		}
	}
}
