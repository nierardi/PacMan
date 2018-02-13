package pacman;

/**
 * BoardController.java
 * Manages BoardObjects and movement of PacMan and Ghosts.
 * 
 * @author Nolan Ierardi
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.image.ImageView;

public class BoardController {
	
	private final int 
		EMPTY = 0,
		WALL = 1,
		COIN = 2,
		SPECIAL_COIN = 3,
		GHOST_DOOR = 4
	;

	private ArrayList<ArrayList<BoardObject>> board;	// holds the BoardObjects
	
	/**
	 * Constructor for BoardController.
	 * Creates a new Board. Other methods are used to fill the board.
	 */
	public BoardController() {
		board = new ArrayList<>();
	}
	
	/**
	 * Loads a specifically formatted "game.map" file to create a level.
	 * 
	 * @param mapFile the file to load
	 */
	public void loadMapFile(File mapFile) {
		
		if (Main.DEBUG) System.out.println("[BoardController:loadMapFile] Starting to load map file: " + mapFile.toString());
		
		// try-catch for IOExceptions. If we encounter one, print an error
		try {
			
			// scanner for outer file
			Scanner root = new Scanner(mapFile);
			// first process the top line that will tell us the sizes
			// this eats the first line in the process
			Scanner topLine = new Scanner(root.nextLine());
			int xs = topLine.nextInt();
			int ys = topLine.nextInt();
			int size = topLine.nextInt();
			
			// loop through all the lines and add them to the file.
			int y = 0;
			while (root.hasNextLine()) {
				int x = 0;
				Scanner line = new Scanner(root.nextLine());
				line.useDelimiter(",");
				board.add(new ArrayList<>());
				while (line.hasNext()) {
					int type = line.nextInt();
					// add the item to the BoardObject
					board.get(y).add(new BoardObject(type, size));
					x++;
				}
				y++;
			}
			
		} catch (IOException ex) {
			System.out.println("[BoardController:loadMapFile] Loading the map file has encountered an IOException: " + ex.toString());
			
			// Possibly load an empty board
		}
		
	}
	
	/**
	 * Sets the passed 2D ArrayList to the board.
	 * 
	 * @param board the 2D ArrayList to use
	 */
	public void setEntireBoard(ArrayList<ArrayList<BoardObject>> board) {
		this.board = board;
	}
	
	/**
	 * Sets a space on the board to a specific BoardObject.
	 *y
	 * @param obj the BoardObject to use
	 * @param x the x position in the board
	 * @param y the y position in the board
	 */
	public void addObject(BoardObject obj, int x, int y) {
		board.get(y).set(x, obj);
	}
	
	/**
	 * Helper method for BoardController.
	 * Sets the image of an object to the image specified.
	 * Only intended for use with Ghosts or PacMan.
	 * 
	 * @param img the ImageView to set
	 * @param x the x position in the board
	 * @param y the y position in the board
	 */
	private void setDirectObject(ImageView img, int x, int y) {
		if (board.get(y).get(x).getType() == WALL ||
			board.get(y).get(x).getType() == GHOST_DOOR) {
			System.out.println("[BoardController:setDirectObject] Invalid target object at " + x + "," + y + " : You cannot use setDirectObject on a WALL or GHOST_DOOR");
		} else {
			board.get(y).get(x).replaceImage(img);
			if (Main.DEBUG) System.out.println("[DEBUG][BoardController:setDirectObject] Set the object at " + x + "," + y + " to a custom image");
		}
	}
	
	/**
	 * Removes an object from the board at the specified coordinates.
	 * 
	 * @param x the x position in the board
	 * @param y the y position in the board
	 */
	public void removeObject(int x, int y) {
		board.get(x).remove(y);
	}
	
	/**
	 * Returns an object on the board at the specific coordinates.
	 * 
	 * @param x the x position in the board
	 * @param y the y position in the board
	 * @return the BoardObject at that position
	 */
	public BoardObject getSpecificObject(int x, int y) {
		return board.get(x).get(y);
	}
	
	/**
	 * Returns the entire board for external processing.
	 * 
	 * @return 2D array of the BoardObjects
	 */
	public ArrayList<ArrayList<BoardObject>> getBoard() {
		return board;
	}
	
	/**
	 * Helper method. Returns if the object can be passed through.
	 * 
	 * @param obj the BoardObject to check
	 * @return if the BoardObject is passable
	 */
	private boolean isPassable(BoardObject obj) {
		if (obj.getType() == WALL || obj.getType() == GHOST_DOOR) return false;
		else return true;
	}
	
	/**
	 * Helper method. Returns if the object can be eaten.
	 * 
	 * @param obj the BoardObject to check
	 * @return if the BoardObject can be eaten
	 */
	private boolean isGobbleable(BoardObject obj) {
		if (obj.getType() == COIN || obj.getType() == SPECIAL_COIN) return true;
		else return false;
	}
	
	/**
	 * Helper method. Returns if the object has a ghost.
	 * @param obj the BoardObject to check
	 * @return if the BoardObject has a ghost
	 */
	private boolean isGhost(BoardObject obj) {
		if (obj.hasGhost()) return true;
		else return false;
	}
	
	/**
	 * Moves the PacMan in the desired direction, if it is possible.
	 * The PacMan cannot move through walls or ghost doors, but it moves through
	 * coins, super coins, and empty spaces.
	 * Ghosts check for the PacMan separately.
	 * 
	 * @param dir the direction to move (0=up, 1=right, 2=down, 3=left)
	 * @param pacman the PacMan that is moving
	 * @param ghosts an array of ghosts
	 * @return points earned, 0 if none
	 */
	public int processMovement(int dir, PacMan pacman, Ghost[] ghosts) {
		//    ^0
		// <3   1>
		//    v2
		int points = 0;
		
		if (dir == 0) {
			// Moving up
			// Check what's ahead
			int pacmanNextX = pacman.getX();
			int pacmanNextY = pacman.getY() - 1;
			int pacmanCurrX = pacman.getX();
			int pacmanCurrY = pacman.getY();
			BoardObject next = board.get(pacmanNextY).get(pacmanNextX);
			BoardObject current = board.get(pacmanCurrY).get(pacmanCurrX);
			if (Main.DEBUG) System.out.println("[DEBUG][BoardController:processMovement] Moving in direction 0/UP, next BoardObject: " + next.getDesc() + " at " + pacman.getX() + "," + (pacman.getY() - 1));
			// Have pacman switch costumes
			//pacman.move(dir);
			// Can we pass through it?
			if (isPassable(next)) {
				// Can we eat it?
				if (isGobbleable(next)) {
					// Eat it
					next.coinEaten();
					points = next.getPoints();
				}
				// Move forward to the space
				pacman.move(dir);
				current.clearImage();
				next.replaceImage(pacman.getImageView());
			}
			if (isGhost(next)) {
				if (pacman.getIsPowered()) {
					// We can kill the ghost, so kill it
					points = 0;
					// Find the ghost to kill
					System.out.println("[DEBUG][BoardController:processMovement] Contacted a ghost");
					for (Ghost g : ghosts) {
						if (pacmanNextX == g.getX() && pacmanNextY == g.getY()) {
							if (Main.DEBUG) System.out.println("[DEBUG][BoardController:processMovement] Found a valid ghost and killed it: " + g.getX() + "," + g.getY());
							g.die();
						}
					}
				} else {
					// We're not powered, we can't kill the ghost, so die
					points = -1;
				}
			}
			
		} else if (dir == 1) {
			// Moving right
			// Check what's ahead
			int pacmanNextX = pacman.getX() + 1;
			int pacmanNextY = pacman.getY();
			int pacmanCurrX = pacman.getX();
			int pacmanCurrY = pacman.getY();
			BoardObject next = board.get(pacmanNextY).get(pacmanNextX);
			BoardObject current = board.get(pacmanCurrY).get(pacmanCurrX);
			if (Main.DEBUG) System.out.println("[DEBUG][BoardController:processMovement] Moving in direction 1/RIGHT, next BoardObject: " + next.getDesc() + " at " + (pacman.getX() + 1) + "," + (pacman.getY()));
			// Have pacman switch costumes
			//pacman.setDirection(dir);
			// Can we pass through it?
			if (isPassable(next)) {
				// Can we eat it?
				if (isGobbleable(next)) {
					// Eat it
					next.coinEaten();
					points = next.getPoints();
				}
				// Move forward to the space
				pacman.move(dir);
				current.clearImage();
				next.replaceImage(pacman.getImageView());
			}
			if (isGhost(next)) {
				if (pacman.getIsPowered()) {
					// We can kill the ghost, so kill it
					points = 0;
					// Find the ghost to kill
					System.out.println("[DEBUG][BoardController:processMovement] Contacted a ghost");
					for (Ghost g : ghosts) {
						if (pacmanNextX == g.getX() && pacmanNextY == g.getY()) {
							if (Main.DEBUG) System.out.println("[DEBUG][BoardController:processMovement] Found a valid ghost and killed it: " + g.getX() + "," + g.getY());
							g.die();
						}
					}
				} else {
					// We're not powered, we can't kill the ghost, so die
					points = -1;
				}
			}
			
		} else if (dir == 2) {
			// Moving down
			// Check what's ahead
			int pacmanNextX = pacman.getX();
			int pacmanNextY = pacman.getY() + 1;
			int pacmanCurrX = pacman.getX();
			int pacmanCurrY = pacman.getY();
			BoardObject next = board.get(pacmanNextY).get(pacmanNextX);
			BoardObject current = board.get(pacmanCurrY).get(pacmanCurrX);
			if (Main.DEBUG) System.out.println("[DEBUG][BoardController:processMovement] Moving in direction 2/DOWN, next BoardObject: " + next.getDesc() + " at " + (pacman.getX()) + "," + (pacman.getY() + 1));
			// Have pacman switch costumes
			//pacman.setDirection(dir);
			// Can we pass through it?
			if (isPassable(next)) {
				// Can we eat it?
				if (isGobbleable(next)) {
					// Eat it
					next.coinEaten();
					points = next.getPoints();
				}
				// Move forward to the space
				pacman.move(dir);
				current.clearImage();
				next.replaceImage(pacman.getImageView());
			}
			if (isGhost(next)) {
				if (pacman.getIsPowered()) {
					// We can kill the ghost, so kill it
					points = 0;
					// Find the ghost to kill
					System.out.println("[DEBUG][BoardController:processMovement] Contacted a ghost");
					for (Ghost g : ghosts) {
						if (pacmanNextX == g.getX() && pacmanNextY == g.getY()) {
							if (Main.DEBUG) System.out.println("[DEBUG][BoardController:processMovement] Found a valid ghost and killed it: " + g.getX() + "," + g.getY());
							g.die();
						}
					}
				} else {
					// We're not powered, we can't kill the ghost, so die
					points = -1;
				}
			}
			
		} else if (dir == 3) {
			// Moving left
			// Check what's ahead
			int pacmanNextX = pacman.getX() - 1;
			int pacmanNextY = pacman.getY();
			int pacmanCurrX = pacman.getX();
			int pacmanCurrY = pacman.getY();
			BoardObject next = board.get(pacmanNextY).get(pacmanNextX);
			BoardObject current = board.get(pacmanCurrY).get(pacmanCurrX);
			if (Main.DEBUG) System.out.println("[DEBUG][BoardController:processMovement] Moving in direction 3/LEFT, next BoardObject: " + next.getDesc() + " at " + (pacman.getX() - 1) + "," + (pacman.getY()));
			// Have pacman switch costumes
			//pacman.setDirection(dir);
			// Can we pass through it?
			if (isPassable(next)) {
				// Can we eat it?
				if (isGobbleable(next)) {
					// Eat it
					next.coinEaten();
					points = next.getPoints();
				}
				// Move forward to the space
				pacman.move(dir);
				current.clearImage();
				next.replaceImage(pacman.getImageView());
			}
			if (isGhost(next)) {
				if (pacman.getIsPowered()) {
					// We can kill the ghost, so kill it
					points = 0;
					// Find the ghost to kill
					System.out.println("[DEBUG][BoardController:processMovement] Contacted a ghost");
					for (Ghost g : ghosts) {
						if (pacmanNextX == g.getX() && pacmanNextY == g.getY()) {
							if (Main.DEBUG) System.out.println("[DEBUG][BoardController:processMovement] Found a valid ghost and killed it: " + g.getX() + "," + g.getY());
							g.die();
						}
					}
				} else {
					// We're not powered, we can't kill the ghost, so die
					points = -1;
				}
			}
			
		} else {
			System.out.println("[BoardObject:processMovement] Invalid direction specified, valid directions are 0-3");
			points = 0;
		}
		return points;
		
	}
	
	/**
	 * Sets the PacMan's starting position or moves it to its
	 * current x and y values.
	 * @param pacman the PacMan object to update
	 */
	public void updatePacmanPosition(PacMan pacman) {
		setDirectObject(pacman.getImageView(), pacman.getX(), pacman.getY());
	}
	
	/**
	 * Sets the Ghost's starting position or moves it to its
	 * current x and y values.
	 * @param ghost the Ghost object to update
	 */
	public void updateGhostPosition(Ghost ghost) {
		BoardObject current = board.get(ghost.getY()).get(ghost.getX());
		current.saveImage();
		current.replaceImage(ghost.getImageView());
	}
	
	/**
	 * Moves a Ghost to its next valid position.
	 * 
	 * @param ghost the Ghost to move
	 * @param gv the GhostValidator to use
	 */
	public void moveGhost(Ghost ghost, GhostValidator gv) {
		
		int[] ghostNext = gv.nextValidPosition();
		int ghostNextX = ghostNext[0];
		int ghostNextY = ghostNext[1];
		int ghostCurrX = ghost.getX();
		int ghostCurrY = ghost.getY();
		BoardObject current = board.get(ghostCurrY).get(ghostCurrX);
		BoardObject next = board.get(ghostNextY).get(ghostNextX);
		
		if (Main.DEBUG) System.out.println("[DEBUG][BoardController:moveGhost] ghostNext: " + ghostNextX + "," + ghostNextY);
		if (Main.DEBUG) System.out.println("[DEBUG][BoardController:moveGhost] ghostCurr: " + ghostCurrX + "," + ghostCurrY);
		
		// Set ghost next image
		if (Main.DEBUG) System.out.println("[DEBUG][BoardController:moveGhost] Running resetImage(" + ghostCurrX + "," + ghostCurrY + ")");
		current.resetImage();
		if (Main.DEBUG) System.out.println("[DEBUG][BoardController:moveGhost] Running saveImage(" + ghostNextX + "," + ghostNextY + ")");
		next.saveImage();
		if (Main.DEBUG) System.out.println("[DEBUG][BoardController:moveGhost] Running setImage(" + ghostNextX + "," + ghostNextY + ")");
		next.replaceImage(ghost.getImageView());
		ghost.setX(ghostNextX);
		ghost.setY(ghostNextY);
		
	}
	
}
