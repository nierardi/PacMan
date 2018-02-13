package pacman;

/**
 * GhostValidator.java
 * Gives valid positions for a Ghost to move to.
 * 
 * @author Nolan Ierardi
 */

import java.util.ArrayList;
import java.util.Random;

public class GhostValidator {

	private ArrayList<ArrayList<BoardObject>> board;
	private ArrayList<Integer> validX;
	private ArrayList<Integer> validY;
	
	private final int 
		EMPTY = 0,
		WALL = 1,
		COIN = 2,
		SPECIAL_COIN = 3,
		GHOST_DOOR = 4
	;
	
	/**
	 * Constructor for GhostValidator.
	 * @param board the ArrayList of BoardObjects to use
	 */
	public GhostValidator(ArrayList<ArrayList<BoardObject>> board) {
		this.board = board;
		validX = new ArrayList<>();
		validY = new ArrayList<>();
		if (Main.DEBUG) System.out.println("[DEBUG][GhostValidator:Constructor] Board size:	" + board.size() + "/" + board.get(1).size());
		for (int y = 0 ; y < board.size() ; y++) {
			for (int x = 0 ; x < board.get(y).size() ; x++) {
				if (Main.DEBUG) System.out.println("[DEBUG][GhostValidator:Constructor] Current array position: " + x + "/" + y);
				if (board.get(y).get(x).getType() != WALL) {
					// Not a wall, this is a valid position
					if (Main.DEBUG) System.out.println("[DEBUG][GhostValidator:Constructor] Valid position: " + x + "/" + y);
					validX.add(x);
					validY.add(y);
				}
			}
		}
	}
	
	/**
	 * Returns the next coordinate represented as an int array (0: x, 1: y)
	 * that a Ghost can move to.
	 * @return an int array representing an x-y coordinate
	 */
	public int[] nextValidPosition() {
		
		Random rand = new Random();
		int r = rand.nextInt(validX.size());
		if (Main.DEBUG) System.out.println("[DEBUG][GhostValidator:nextValidPosition] Current random: " + r);
		if (Main.DEBUG) System.out.println("[DEBUG][GhostValidator:nextValidPosition] validX size: " + validX.size() + ", validY size: " + validY.size());
		return new int[]{validX.get(r), validY.get(r)};
	}
	
	
}
